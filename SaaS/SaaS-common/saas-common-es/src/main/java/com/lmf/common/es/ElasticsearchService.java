/**
 *
 */
package com.saas.common.es;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.beanutils.BeanUtils;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.core.CountRequest;
import org.elasticsearch.client.core.CountResponse;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.metrics.ParsedSingleValueNumericMetricsAggregation;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.saas.common.core.entity.KeyValue;
import com.saas.common.core.page.DataPage;
import com.saas.common.core.page.DataPageable;
import com.saas.common.exception.CustomException;
import com.saas.common.jpa.SearchFilter;
import com.saas.common.util.StringUtils;
import com.saas.common.util.UserContextHolder;

/**
 * @author bruce
 *
 */
public abstract class ElasticsearchService<T extends ESObject> implements IESBaseService<T> {

    protected final Logger logger = LoggerFactory.getLogger(ElasticsearchService.class);

    public static final int MAX_VALUE = 5000;

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    /**
     * 需要被子类覆盖 返回 modelDao
     *
     * @return BaseDao
     */
    protected abstract String getIndex();

    /**
     * 实体类类型
     */
    private Class<T> tEntityClass;

    private Boolean isExtendsBaseEntity = false;

    private Boolean isExtendsSaasEntity = false;

    @SuppressWarnings("unchecked")
    public ElasticsearchService() {
        Type genericSuperclass = this.getClass().getGenericSuperclass();
        ParameterizedType parameterizedType = (ParameterizedType) genericSuperclass;
        tEntityClass = (Class<T>) parameterizedType.getActualTypeArguments()[0];
        if (tEntityClass != null) {
            isExtendsBaseEntity = ESBaseEntity.class.isAssignableFrom(tEntityClass);
            isExtendsSaasEntity = ESSaasEntity.class.isAssignableFrom(tEntityClass);
        }
    }

    /**
     * 创建索引
     *
     * @param index
     * @return
     */
    public boolean createIndex() throws IOException {
        if (!isIndexExist()) {
            logger.info("Index is not exits!");
        } else {
            logger.info("Index is  exits!");
            return true;
        }
        CreateIndexRequest request = new CreateIndexRequest(getIndex());
        request.settings(Settings.builder().put("index.number_of_shards", 3).put("index.number_of_replicas", 2));
        CreateIndexResponse indexresponse = restHighLevelClient.indices().create(request, RequestOptions.DEFAULT);
        logger.info("create es index " + indexresponse.isAcknowledged());
        return indexresponse.isAcknowledged();
    }

    /**
     * 删除索引
     *
     * @param index
     * @return
     */
    public boolean deleteIndex() throws IOException {
        if (!isIndexExist()) {
            logger.info("Index is not exits!");
            return true;
        }
        DeleteIndexRequest request = new DeleteIndexRequest(getIndex());
        AcknowledgedResponse deleteIndexResponse = restHighLevelClient.indices().delete(request,
                RequestOptions.DEFAULT);
        if (deleteIndexResponse.isAcknowledged()) {
            logger.info("delete index " + getIndex() + "  successfully!");
        } else {
            logger.info("Fail to delete index " + getIndex());
        }
        return deleteIndexResponse.isAcknowledged();
    }

    /**
     * 判断索引是否存在
     *
     * @param index
     * @return
     */
    public boolean isIndexExist() throws IOException {
        GetIndexRequest request = new GetIndexRequest(getIndex());
        return restHighLevelClient.indices().exists(request, RequestOptions.DEFAULT);

    }

    /**
     * 通过ID 更新数据
     *
     */
    protected boolean updateDataById(T model) {
        // UpdateRequest
        UpdateRequest updateRequest = new UpdateRequest(getIndex(), model.getId());
        updateRequest.doc(JSON.toJSONString(model), XContentType.JSON);
        // 操作ES
        try {
            UpdateResponse updateResponse = restHighLevelClient.update(updateRequest, RequestOptions.DEFAULT);
            return updateResponse.status().getStatus() == 200;
        } catch (IOException e) {
            e.printStackTrace();
            logger.error(model + e.getMessage());
            throw new CustomException("更新资源异常", e);
        }

    }

    /**
     * 通过ID获取数据
     *
     * @param index  索引，类似数据库
     * @param type   类型，类似表
     * @param id     数据ID
     * @param fields 需要显示的字段，逗号分隔（缺省为全部字段）
     * @return
     */
    public T searchDataById(String id, String... fields) {
        // GetRequest
        GetRequest getRequest = new GetRequest(this.getIndex(), id);
        if (StringUtils.isNotEmpty(fields)) {
            FetchSourceContext fetchSourceContext = new FetchSourceContext(true, fields, null);
            getRequest.fetchSourceContext(fetchSourceContext);
        }
        // 查询ES
        try {
            GetResponse getResponse = restHighLevelClient.get(getRequest, RequestOptions.DEFAULT);
            if (getResponse.isExists()) {
                T obj = JSON.parseObject(getResponse.getSourceAsString(), this.tEntityClass);
                return obj;
            }
        } catch (IOException e) {
            e.printStackTrace();
            logger.error(id + ":" + e.getMessage());
            throw new CustomException("查询资源异常", e);
        }
        return null;

    }

    private QueryBuilder buildSearchFilter(Map<String, Object> searchParams) {
        Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
        return DynamicQuerys.bySearchFilter(filters.values());
    }

    /**
     * 统计查询条件结果集数量
     *
     * @param index      索引
     * @param type       类型
     * @param postFilter 条件
     * @return
     */
    public long countByQuery(QueryBuilder postFilter) throws IOException {
        CountRequest countRequest = new CountRequest(this.getIndex());
        countRequest.query(buildBasicPostFilter(postFilter));
        CountResponse countResponse = restHighLevelClient.count(countRequest, RequestOptions.DEFAULT);
        return countResponse.getCount();
    }

    private QueryBuilder buildBasicPostFilter(QueryBuilder postFilter) {
        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
        if (postFilter != null) {
            queryBuilder.must(postFilter);
        }
        if (isExtendsBaseEntity) {
            TermQueryBuilder delBuilder = QueryBuilders.termQuery("delFlag", false);
            queryBuilder.must(delBuilder);
            if (isExtendsSaasEntity && isAutoLinkSaasId() && !UserContextHolder.getCurrentUser().isSysAccount()) {
                TermQueryBuilder saasBuilder = QueryBuilders.termQuery("saasId",
                        UserContextHolder.getCurrentUser().getSaasId());
                queryBuilder.must(saasBuilder);
            }
        }
        return queryBuilder;
    }

    /**
     * 使用分词查询,并分页
     *
     * @param index          索引名称
     * @param startPage      默认 1 当前页
     * @param pageSize       每页显示条数
     * @param query          查询条件
     * @param fields         需要显示的字段，逗号分隔（缺省为全部字段）
     * @param sortField      排序字段
     * @param highlightField 高亮字段
     * @return
     * @throws IOException
     */
    public DataPage<T> searchDataPage(int page, int pageSize, QueryBuilder query, QueryBuilder postFilter,
                                      String fields, List<SortField> sorts, String highlightField) throws IOException {
        SearchRequest searchRequest = new SearchRequest(this.getIndex());
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchRequest.source(searchSourceBuilder);
        // 分页应用
        int from = (page - 1) * pageSize;
        from = from < 0 ? 0 : from;
        searchSourceBuilder.from(from);
        searchSourceBuilder.size(pageSize);

        // 需要显示的字段，逗号分隔（缺省为全部字段）
        if (StringUtils.isNotEmpty(fields)) {
            searchSourceBuilder.fetchSource(fields.split(","), null);
        }

        // 排序字段
        if (sorts != null && sorts.size() > 0) {
            sorts.forEach((field) -> {
                String sortField = field.field;
                SortOrder sortOrder = field.order;
                searchSourceBuilder.sort(sortField, sortOrder);
            });
        }
        // 高亮（xxx=111,aaa=222）
        if (StringUtils.isNotEmpty(highlightField)) {
            HighlightBuilder highlightBuilder = new HighlightBuilder();
            // highlightBuilder.preTags("<span style='color:red' >");//设置前缀
            // highlightBuilder.postTags("</span>");//设置后缀

            // 设置高亮字段
            highlightBuilder.field(highlightField);
            searchSourceBuilder.highlighter(highlightBuilder);
        }

        if (query != null) {
            searchSourceBuilder.query(query);
        }

        // 设置是否按查询匹配度排序
        // searchSourceBuilder.explain(true);

        // 过滤条件
        searchSourceBuilder.postFilter(buildBasicPostFilter(postFilter));

        // 打印的内容 可以在 Elasticsearch head 和 Kibana 上执行查询
        logger.info("\n{}", searchSourceBuilder);

        // 执行搜索,返回搜索响应信息
        SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        SearchHits hits = searchResponse.getHits();

        long totalHits = hits.getTotalHits().value;
        long length = hits.getHits().length;

        logger.debug("共查询到[{}]条数据,处理数据条数[{}]", totalHits, length);

        if (searchResponse.status().getStatus() == 200) {
            // 解析对象
            List<T> sourceList = setSearchResponse(searchResponse, highlightField);
            return new DataPage<T>(sourceList, totalHits);
        }
        return new DataPage<T>(new ArrayList<>(), 0);

    }

    /**
     * 高亮结果集 特殊处理
     *
     * @param searchResponse
     * @param highlightField
     */
    private List<T> setSearchResponse(SearchResponse searchResponse, String highlightField) {
        List<T> sourceList = new ArrayList<>();
        StringBuffer stringBuffer = new StringBuffer();

        for (SearchHit searchHit : searchResponse.getHits().getHits()) {
            searchHit.getSourceAsMap().put("id", searchHit.getId());

            if (StringUtils.isNotEmpty(highlightField)) {

                // System.out.println("遍历 高亮结果集，覆盖 正常结果集" +
                // searchHit.getSourceAsMap());
                Text[] text = searchHit.getHighlightFields().get(highlightField).getFragments();

                if (text != null) {
                    for (Text str : text) {
                        stringBuffer.append(str.string());
                    }
                    // 遍历 高亮结果集，覆盖 正常结果集
                    searchHit.getSourceAsMap().put(highlightField, stringBuffer.toString());
                }
            }
            T obj = JSON.parseObject(searchHit.getSourceAsString(), this.tEntityClass);
            sourceList.add(obj);
        }

        return sourceList;
    }

    @Override
    public void save(T model) {
        T item = null;
        if (isExtendsBaseEntity) {
            ESBaseEntity baseEntity = (ESBaseEntity) model;
            Long userId = UserContextHolder.getCurrentUser().getId();
            String userName = UserContextHolder.getCurrentUser().getLoginName() + "/"
                    + UserContextHolder.getCurrentUser().getName();

            baseEntity.setCreateTimeMills(System.currentTimeMillis());
            baseEntity.setUpdateTimeMills(baseEntity.getCreateTimeMills());
            baseEntity.setDelFlag(false);
            baseEntity.setCreateBy(userName);
            baseEntity.setUpdateBy(userName);
            baseEntity.setUpdateUserId(userId);
            baseEntity.setCreateUserId(userId);

            if (isExtendsSaasEntity && isAutoLinkSaasId() && !UserContextHolder.getCurrentUser().isSysAccount()) {
                ESSaasEntity saasEntity = (ESSaasEntity) model;
                saasEntity.setSaasId(UserContextHolder.getCurrentUser().getSaasId());
            }

        }
        modelChangeBefore(model, item);
        if (StringUtils.isEmpty(model.getId())) {
            model.setId(UUID.randomUUID().toString());
        }
        IndexRequest indexRequest = new IndexRequest(getIndex());
        String source = JSON.toJSONString(model);
        indexRequest.id(model.getId()).source(source, XContentType.JSON);
        // 操作ES
        try {
            IndexResponse indexResponse = restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
            indexResponse.forcedRefresh();
        } catch (IOException e) {
            e.printStackTrace();
            logger.error(model + e.getMessage());
            throw new CustomException("保存资源异常", e);
        }
        modelChangeAfter(model, item);

    }

    @Override
    public void delete(String id) {
        try {
            T model = getById(id);
            if (model == null) {
                throw new CustomException(id + "该id对应实体不存在");
            }
            if (isExtendsBaseEntity) {
                Long userId = UserContextHolder.getCurrentUser().getId();
                String userName = UserContextHolder.getCurrentUser().getLoginName() + "/"
                        + UserContextHolder.getCurrentUser().getName();
                ESBaseEntity baseEntity = (ESBaseEntity) model;
                baseEntity.setDelFlag(true);
                baseEntity.setUpdateTimeMills(System.currentTimeMillis());
                baseEntity.setUpdateUserId(userId);
                baseEntity.setUpdateBy(userName);
                modelChangeBefore(model, model);
                updateDataById(model);
                modelChangeAfter(model, model);
            } else {
                physicalDelete(model);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new CustomException("删除异常", e);
        }
    }

    /**
     * 不进行赋值，执行逻辑删除，避免使用，追查不到操作记录
     *
     * @param model
     */
    public void pureDelete(T model) {
        if (isExtendsBaseEntity) {
            ESBaseEntity baseEntity = (ESBaseEntity) model;
            baseEntity.setDelFlag(true);
            modelChangeBefore(model, model);
            updateDataById(model);
            modelChangeAfter(model, model);

        } else {
            physicalDelete(model);
        }
    }

    protected void physicalDelete(T model) {
        DeleteRequest deleteRequest = new DeleteRequest(getIndex(), model.getId());
        // 操作ES
        modelChangeBefore(model, model);
        try {
            DeleteResponse deleteResponse = restHighLevelClient.delete(deleteRequest, RequestOptions.DEFAULT);
            if (deleteResponse.getResult() == DocWriteResponse.Result.NOT_FOUND) {

            }
        } catch (IOException e) {
            e.printStackTrace();
            logger.error(model + e.getMessage());
            throw new CustomException("删除资源异常", e);
        }
        modelChangeAfter(model, model);
    }

    @Override
    public T getById(String id) {
        return this.searchDataById(id);
    }

    @Override
    public void update(T model) {
        T item = this.getById(model.getId());
        if (isExtendsBaseEntity) {
            ESBaseEntity baseEntity = (ESBaseEntity) model;
            Long userId = UserContextHolder.getCurrentUser().getId();
            String userName = UserContextHolder.getCurrentUser().getLoginName() + "/"
                    + UserContextHolder.getCurrentUser().getName();
            baseEntity.setUpdateTimeMills(System.currentTimeMillis());
            baseEntity.setUpdateBy(userName);
            baseEntity.setUpdateUserId(userId);
            baseEntity.setCreateUserId(userId);
            if (isExtendsSaasEntity && isAutoLinkSaasId() && !UserContextHolder.getCurrentUser().isSysAccount()) {
                ESSaasEntity saasEntity = (ESSaasEntity) model;
                saasEntity.setSaasId(UserContextHolder.getCurrentUser().getSaasId());
            }

        }
        modelChangeBefore(model, item);
        this.updateDataById(model);
        modelChangeAfter(model, item);

    }

    @Override
    public List<T> getList(DataPageable dataPageable) {
        return getListPage(dataPageable).getContent();
    }

    @Override
    public DataPage<T> getListPage(DataPageable dataPageable) {
        try {
            int pageNum = dataPageable.getPageNum();
            int pageSize = dataPageable.getPageSize();

            // 参数
            Map<String, Object> searchParams = dataPageable.getSearchParams();
            // 排序
            List<String> sorts = dataPageable.getSorts();

            QueryBuilder postFilter = buildSearchFilter(searchParams);

            List<SortField> sortOrders = new ArrayList<>();

            if (sorts != null && sorts.size() > 0) {
                SortField field = new SortField();
                String sortType = sorts.get(0);
                if (sortType.toLowerCase().startsWith("asc_")) {
                    field.field = sortType.substring(4);
                    field.order = SortOrder.ASC;
                } else if (sortType.toLowerCase().startsWith("desc_")) {
                    field.field = sortType.substring(5);
                    field.order = SortOrder.DESC;
                } else {
                    field.field = sortType;
                    field.order = SortOrder.DESC;
                }
                sortOrders.add(field);
            }

            return this.searchDataPage(pageNum, pageSize, null, postFilter, null, sortOrders, null);
        } catch (Exception e) {
            e.printStackTrace();
            throw new CustomException("查询异常");
        }
    }

    @Override
    public List<T> getList(Map<String, Object> searchParams) {
        return getList(searchParams, null);
    }

    @Override
    public List<T> getList(Map<String, Object> searchParams, String sortType) {
        DataPageable pageable = new DataPageable(1, MAX_VALUE);
        pageable.setReqSearchParams(searchParams);
        pageable.setSort(sortType);
        return this.getList(pageable);
    }

    @Override
    public List<T> getAllModels() {
        Map<String, Object> searchParams = new HashMap<>();
        return getList(searchParams);
    }

    @Override
    public boolean checkFieldUnique(T item, String... fields) {
        Map<String, Object> params = new HashMap<>();
        for (String field : fields) {
            try {
                params.put("EQ_" + field, BeanUtils.getProperty(item, field));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        List<T> datas = getList(params);
        for (T data : datas) {
            if (!data.getId().equals(item.getId()))
                return false;
        }
        return true;
    }

    @Override
    public T getUniqueModel(KeyValue... keyValues) {
        List<T> items = getModels(keyValues);
        if (items.size() > 1) {
            throw new CustomException("The result is not unique");
        } else if (items.size() == 1) {
            return items.get(0);
        } else {
            return null;
        }

    }

    @Override
    public T getFirstModel(KeyValue... keyValues) {
        List<T> items = getModels(keyValues);
        if (items.size() > 0) {
            return items.get(0);
        } else {
            return null;
        }
    }

    @Override
    public List<T> getModels(KeyValue... keyValues) {
        Map<String, Object> params = new HashMap<>();
        for (KeyValue keyValue : keyValues) {
            params.put("EQ_" + keyValue.getKey(), keyValue.getValue());
        }
        return getList(params);
    }

    @Override
    public List<T> getModels(String sortType, KeyValue... keyValues) {
        Map<String, Object> params = new HashMap<>();
        for (KeyValue keyValue : keyValues) {
            params.put("EQ_" + keyValue.getKey(), keyValue.getValue());
        }
        return getList(params, sortType);
    }

    public List<AggResult> aggregation(List<DimAggSpecification> dimSpecifications,
                                       List<IndAggSpecification> indSpecifications, QueryBuilder postFilter, List<SortField> sorts)
            throws IOException {
        SearchRequest searchRequest = new SearchRequest(this.getIndex());
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        searchSourceBuilder.size(0);

        searchRequest.source(searchSourceBuilder);
        searchSourceBuilder.postFilter(buildBasicPostFilter(postFilter));

        if (dimSpecifications == null || dimSpecifications.size() == 0) {
            throw new CustomException("维度条件不能为空");
        }

        AggregationBuilder firstAgg = null;
        AggregationBuilder lastAgg = null;
        for (AggSpecification agg : dimSpecifications) {
            if (lastAgg == null) {
                lastAgg = agg.aggregationBuilder();
                firstAgg = lastAgg;
            } else {
                AggregationBuilder groupAggSub = agg.aggregationBuilder();
                if (groupAggSub != null) {
                    lastAgg.subAggregation(groupAggSub);
                    lastAgg = groupAggSub;
                }
            }
        }
        for (AggSpecification agg : indSpecifications) {
            AggregationBuilder groupAggSub = agg.aggregationBuilder();
            lastAgg.subAggregation(groupAggSub);
        }

        if (firstAgg != null) {
            searchSourceBuilder.aggregation(firstAgg);
        }
        // 排序字段
        if (sorts != null && sorts.size() > 0) {
            sorts.forEach((field) -> {
                String sortField = field.field;
                SortOrder sortOrder = field.order;
                searchSourceBuilder.sort(sortField, sortOrder);
            });
        }
        // 打印的内容 可以在 Elasticsearch head 和 Kibana 上执行查询
        logger.info("\n{}", searchSourceBuilder);

        SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        Aggregations aggregations = searchResponse.getAggregations();

        List<AggResult> result = new ArrayList<>();
        parseAggResponse(aggregations, dimSpecifications, indSpecifications, 0, result);
        return result;
    }

    private void parseAggResponse(Aggregations aggregations, List<DimAggSpecification> dimSpecifications,
                                  List<IndAggSpecification> indSpecifications, int index, List<AggResult> result) {
        AggSpecification agg = dimSpecifications.get(index);
        Terms terms = aggregations.get(agg.getAggTermName());

        List<? extends Terms.Bucket> buckets = terms.getBuckets();
        for (Terms.Bucket bucket : buckets) {
            AggResult rs = new AggResult();
            rs.setKey(bucket.getKey());
            rs.setCount(bucket.getDocCount());
            rs.setName(terms.getName());
            result.add(rs);
            if (index == dimSpecifications.size() - 1 && indSpecifications != null && indSpecifications.size() > 0) {
                Map<String, Double> indAggValues = new HashMap<>();
                rs.setIndAggValues(indAggValues);
                Aggregations indAggregations = bucket.getAggregations();
                for (IndAggSpecification indSpe : indSpecifications) {
                    ParsedSingleValueNumericMetricsAggregation mAgg = indAggregations.get(indSpe.getAggTermName());
                    indAggValues.put(mAgg.getName(), mAgg.value());
                }
            } else {
                List<AggResult> childs = new ArrayList<>();
                rs.setChilds(childs);
                parseAggResponse(bucket.getAggregations(), dimSpecifications, indSpecifications, index + 1, childs);
            }
        }
    }
}
