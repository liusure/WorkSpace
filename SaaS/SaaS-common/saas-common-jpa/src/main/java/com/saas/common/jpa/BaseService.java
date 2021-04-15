package com.saas.common.jpa;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import javax.persistence.EntityManager;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import com.saas.common.constant.DomainConstants;
import com.saas.common.core.domain.BaseEntity;
import com.saas.common.core.domain.IdEntity;
import com.saas.common.core.domain.SaasEntity;
import com.saas.common.core.entity.KeyValue;
import com.saas.common.core.page.DataPage;
import com.saas.common.core.page.DataPageable;
import com.saas.common.core.redis.RedisCache;
import com.saas.common.core.redis.RedisConstants;
import com.saas.common.exception.CustomException;
import com.saas.common.util.UserContextHolder;

public abstract class BaseService<T extends IdEntity> implements IBaseService<T> {

	protected final Logger logger = LoggerFactory.getLogger(BaseService.class);
	/**
	 * 默认 redis 缓存时间
	 */
	public final static long DEFAULT_TIME_OUT = 12;
	public final static TimeUnit DEFAULT_TIME_UNIT = TimeUnit.HOURS;

	@Autowired
	protected RedisCache redisCache;

	@PersistenceContext
	protected EntityManager entityManager;

	/**
	 * 实体类类型
	 */
	private Class<T> tEntityClass;

	private Boolean isExtendsBaseEntity = false;

	private Boolean isExtendsSaasEntity = false;

	@SuppressWarnings("unchecked")
	public BaseService() {
		Type genericSuperclass = this.getClass().getGenericSuperclass();
		ParameterizedType parameterizedType = (ParameterizedType) genericSuperclass;
		tEntityClass = (Class<T>) parameterizedType.getActualTypeArguments()[0];
		if (tEntityClass != null) {
			isExtendsBaseEntity = BaseEntity.class.isAssignableFrom(tEntityClass);
			isExtendsSaasEntity = SaasEntity.class.isAssignableFrom(tEntityClass);
		}
	}

	/**
	 * 需要被子类覆盖 返回 modelDao
	 *
	 * @return BaseDao
	 */
	protected abstract BaseDao<T> getModelDao();

	/**
	 * 是否开启本地缓存
	 * 
	 * @return
	 */
	protected abstract boolean isUseLocalCache();

	/**
	 * 创建 Domain类 的 redis key
	 *
	 * @return 主键redis缓存 key
	 */
	protected String getModelRedisKey(String... keys) {
		StringBuilder stringBuilder = new StringBuilder();
		for (String key : keys) {
			stringBuilder.append("_").append(key);
		}
		return RedisConstants.DOMAIN + tEntityClass.getSimpleName() + stringBuilder.toString();
	}

	/**
	 * 不进行自动赋值，直接保存数据，避免使用，追查不到操作记录
	 * 
	 * @param model
	 * @return
	 */
	public T pureSave(T model) {
		T item = null;
		if (model.getId() != null) {
			item = getById(model.getId());
		}
		modelChangeBefore(model, item);
		model = getModelDao().save(model);
		modelChangeAfter(model, item);
		redisCache.deleteObject(getModelRedisKey("id", model.getId() + ""));
		return model;
	}

	public T save(T model) {
		T item = null;
		if (model.getId() != null) {
			item = getById(model.getId());
		}
		if (isExtendsBaseEntity) {
			BaseEntity baseEntity = (BaseEntity) model;
			Long userId = UserContextHolder.getCurrentUser().getId();
			String userName = UserContextHolder.getCurrentUser().getLoginName() + "/"
					+ UserContextHolder.getCurrentUser().getName();
			if (baseEntity.getId() == null) {

				baseEntity.setCreateUserId(userId);
				baseEntity.setCreateTime(new Date());
				baseEntity.setUpdateTime(new Date());
				baseEntity.setUpdateUserId(userId);
				baseEntity.setDelFlag(0);
				baseEntity.setCreateBy(userName);
				baseEntity.setUpdateBy(userName);
			} else {
				baseEntity.setUpdateTime(new Date());
				baseEntity.setUpdateUserId(userId);
				baseEntity.setUpdateBy(userName);
			}
			if (isExtendsSaasEntity && isAutoLinkSaasId() && !UserContextHolder.getCurrentUser().isSysAccount()) {
				SaasEntity saasEntity = (SaasEntity) model;
				saasEntity.setSaasId(UserContextHolder.getCurrentUser().getSaasId());
			}

		}
		modelChangeBefore(model, item);
		model = getModelDao().save(model);
		modelChangeAfter(model, item);

		redisCache.deleteObject(getModelRedisKey("id", model.getId() + ""));
		return model;
	}

	/**
	 * 逻辑删除
	 * 
	 * @param id
	 * @param operatorId
	 */
	public void delete(Long id) {
		try {
			T model = getById(id);
			if (model == null) {
				throw new CustomException(id + "该id对应实体不存在");
			}
			if (isExtendsBaseEntity) {
				Long userId = UserContextHolder.getCurrentUser().getId();
				String userName = UserContextHolder.getCurrentUser().getLoginName() + "/"
						+ UserContextHolder.getCurrentUser().getName();
				BaseEntity baseEntity = (BaseEntity) model;
				baseEntity.setDelFlag(DomainConstants.DOMAIN_DEL_FLAG_DELETED);
				baseEntity.setUpdateTime(new Date());
				baseEntity.setUpdateUserId(userId);
				baseEntity.setUpdateBy(userName);
				modelChangeBefore(model, model);
				getModelDao().save(model);
				modelChangeAfter(model, model);
				redisCache.deleteObject(getModelRedisKey("id", model.getId() + ""));
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
			BaseEntity baseEntity = (BaseEntity) model;
			baseEntity.setDelFlag(DomainConstants.DOMAIN_DEL_FLAG_DELETED);
			modelChangeBefore(model, model);
			getModelDao().save(model);
			modelChangeAfter(model, model);
			redisCache.deleteObject(getModelRedisKey("id", model.getId() + ""));
		} else {
			physicalDelete(model);
		}
	}

	/**
	 * 物理删除
	 * 
	 * @param id
	 */
	protected void physicalDelete(T model) {
		if (model == null) {
			return;
		}
		modelChangeBefore(model, model);
		getModelDao().delete(model);
		modelChangeAfter(model, model);
		redisCache.deleteObject(getModelRedisKey("id", model.getId() + ""));
	}

	@SuppressWarnings("unchecked")
	public T getById(Long id) {
		return (T) redisCache.getValueIfNullSet(getModelRedisKey("id", id + ""), DEFAULT_TIME_OUT, DEFAULT_TIME_UNIT,
				isUseLocalCache(), () -> {
					Optional<T> op = getModelDao().findById(id);
					return (op == null || !op.isPresent()) ? null : op.get();
				});
	}

	public T getSingleton(Map<String, Object> searchParams) {
		try {
			buildSearchMap(searchParams);
			Specification<T> spec = buildSearchFilter(searchParams, null);
			Optional<T> one = getModelDao().findOne(spec);
			return one.orElse(null);
		} catch (IncorrectResultSizeDataAccessException | NonUniqueResultException e) {
			e.printStackTrace();
			throw new CustomException("查询结果不唯一", e);
		} catch (Exception e) {
			e.printStackTrace();
			throw new CustomException("查询异常", e);
		}
	}

	public List<T> getList(DataPageable dataPageable) {
		try {
			// 参数
			Map<String, Object> searchParams = dataPageable.getSearchParams();
			buildSearchMap(searchParams);
			Specification<T> spec = buildSearchFilter(searchParams, null);
			Sort sort = buildSort(dataPageable.getSorts());
			return getModelDao().findAll(spec, sort);
		} catch (Exception e) {
			e.printStackTrace();
			throw new CustomException("查询异常");
		}
	}

	private Map<String, Object> buildSearchMap(Map<String, Object> searchParams) {
		if (isExtendsBaseEntity) {
			searchParams.put("EQ_delFlag", DomainConstants.DOMAIN_DEL_FLAG_NOT_DELETED);
			if (isExtendsSaasEntity && isAutoLinkSaasId() && !UserContextHolder.getCurrentUser().isSysAccount()) {
				searchParams.put("EQ_saasId", UserContextHolder.getCurrentUser().getSaasId());
			}
		}
		return searchParams;
	}

	public DataPage<T> getListPage(DataPageable dataPageable) {
		try {
			int pageNum = dataPageable.getPageNum();
			int pageSize = dataPageable.getPageSize();

			// 参数
			Map<String, Object> searchParams = dataPageable.getSearchParams();
			buildSearchMap(searchParams);

			// 排序
			List<String> sorts = dataPageable.getSorts();
			PageRequest pageRequest = buildPageRequest(pageNum, pageSize, sorts);
			Specification<T> spec = buildSearchFilter(searchParams, null);
			Page<T> listPage = getModelDao().findAll(spec, pageRequest);
			return new DataPage<T>(listPage.getContent(), listPage.getTotalElements(), dataPageable);
		} catch (Exception e) {
			e.printStackTrace();
			throw new CustomException("查询异常");
		}
	}

	/**
	 * 按条件查询全部
	 *
	 * @param searchParams 查询条件
	 * @return 结果集合
	 */
	public List<T> getList(Map<String, Object> searchParams) {
		return getList(searchParams, "", null);
	}

	/**
	 * 按条件查询全部
	 *
	 * @param searchParams 查询条件
	 * @param sortType     排序
	 * @return 结果集合
	 */
	public List<T> getList(Map<String, Object> searchParams, String sortType) {
		return getList(searchParams, sortType, null);
	}

	/**
	 * 按条件查询全部
	 *
	 * @param searchParams 查询条件
	 * @param sortTypes    排序
	 * @return 结果集合
	 */
	public List<T> getList(Map<String, Object> searchParams, List<String> sortTypes) {
		return getList(searchParams, sortTypes, null);
	}

	/**
	 * 按条件查询全部
	 *
	 * @param searchParams 查询条件
	 * @param sortType     排序
	 * @param dataType     数据格式
	 * @return 结果集合
	 */
	public List<T> getList(Map<String, Object> searchParams, String sortType, List<SearchDataTransform> dataType) {
		buildSearchMap(searchParams);
		Specification<T> spec = buildSearchFilter(searchParams, dataType);
		Sort sort = buildSort(sortType);
		return getModelDao().findAll(spec, sort);
	}

	/**
	 * 按条件查询全部
	 *
	 * @param searchParams 查询条件
	 * @param sortTypes    排序
	 * @param dataType     数据格式
	 * @return 结果集合
	 */
	public List<T> getList(Map<String, Object> searchParams, List<String> sortTypes,
			List<SearchDataTransform> dataType) {
		buildSearchMap(searchParams);
		Specification<T> spec = buildSearchFilter(searchParams, dataType);
		Sort sort = buildSort(sortTypes);
		return getModelDao().findAll(spec, sort);
	}

	/**
	 * 查询全部
	 *
	 * @return 结果集合
	 */
	public List<T> getAllModels() {
		Map<String, Object> searchParams = new HashMap<>();
		buildSearchMap(searchParams);
		return getList(searchParams);
	}

	/**
	 * 查询字段是否唯一，如果已经存在记录返回false
	 * 
	 * @param item
	 * @param fields
	 * @return
	 */
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

	/**
	 * 返回唯一结果，如果不唯一运行错误
	 * 
	 * @param keyValues
	 * @return
	 */
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

	public T getFirstModel(KeyValue... keyValues) {
		List<T> items = getModels(keyValues);
		if (items.size() > 0) {
			return items.get(0);
		} else {
			return null;
		}
	}

	/**
	 * 根据Key value键值对查询
	 * 
	 * @param keyValues
	 * @return
	 */
	public List<T> getModels(KeyValue... keyValues) {
		Map<String, Object> params = new HashMap<>();
		for (KeyValue keyValue : keyValues) {
			params.put("EQ_" + keyValue.getKey(), keyValue.getValue());
		}
		return getList(params);
	}

	public List<T> getModels(String sortType, KeyValue... keyValues) {
		Map<String, Object> params = new HashMap<>();
		for (KeyValue keyValue : keyValues) {
			params.put("EQ_" + keyValue.getKey(), keyValue.getValue());
		}
		return getList(params, sortType);
	}

	/**
	 * 分页查询
	 *
	 * @param searchParams 查询条件
	 * @param pageNumber   页码
	 * @param pageSize     每页个数
	 * @param sortType     排序
	 * @param dataType     数据格式
	 * @return 分页结果集合
	 */
	public Page<T> getListPage(Map<String, Object> searchParams, int pageNumber, int pageSize, String sortType,
			List<SearchDataTransform> dataType) {
		PageRequest pageRequest = buildPageRequest(pageNumber, pageSize, sortType);
		buildSearchMap(searchParams);
		Specification<T> spec = buildSearchFilter(searchParams, dataType);
		return getModelDao().findAll(spec, pageRequest);
	}

	/**
	 * 分页查询
	 *
	 * @param searchParams 查询条件
	 * @param pageNumber   页码
	 * @param pageSize     每页个数
	 * @param sortTypes    排序
	 * @param dataType     数据格式
	 * @return 分页结果集合
	 */
	public Page<T> getListPage(Map<String, Object> searchParams, int pageNumber, int pageSize, List<String> sortTypes,
			List<SearchDataTransform> dataType) {
		PageRequest pageRequest = buildPageRequest(pageNumber, pageSize, sortTypes);
		buildSearchMap(searchParams);
		Specification<T> spec = buildSearchFilter(searchParams, dataType);
		return getModelDao().findAll(spec, pageRequest);
	}

	/**
	 * 解析查询参数
	 *
	 * @param searchParams 查询条件
	 * @param dataType     数据格式
	 * @return 查询参数
	 */
	private Specification<T> buildSearchFilter(Map<String, Object> searchParams, List<SearchDataTransform> dataType) {
		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
		SearchDataTransform.dateTypeTransform(filters, dataType);
		return DynamicSpecifications.bySearchFilter(filters.values());
	}

	/**
	 * 创建分页请求.
	 *
	 * @param pageNumber 页码
	 * @param pagzSize   每页个数
	 * @param sortType   排序
	 * @return 分页请求
	 */
	private PageRequest buildPageRequest(int pageNumber, int pagzSize, String sortType) {
		return PageRequest.of(pageNumber - 1, pagzSize, buildSort(sortType));
	}

	/**
	 * 创建分页请求.
	 *
	 * @param pageNumber 页码
	 * @param pagzSize   每页个数
	 * @param sortTypes  排序
	 * @return 分页请求
	 */
	private PageRequest buildPageRequest(int pageNumber, int pagzSize, List<String> sortTypes) {
		return PageRequest.of(pageNumber - 1, pagzSize, buildSort(sortTypes));
	}

	/**
	 * 创建排序
	 *
	 * @param sortType 排序
	 * @return 排序
	 */
	private Sort buildSort(String sortType) {
		Sort sort;
		if (StringUtils.isEmpty(sortType) || "auto".equals(sortType)) {
			sort = Sort.by(Sort.Direction.DESC, "id");
		} else {
			if (sortType.toLowerCase().startsWith("asc_")) {
				sortType = sortType.substring(4);
				sort = Sort.by(Sort.Direction.ASC, sortType);
			} else if (sortType.toLowerCase().startsWith("desc_")) {
				sortType = sortType.substring(5);
				sort = Sort.by(Sort.Direction.DESC, sortType);
			} else {
				sort = Sort.by(Sort.Direction.DESC, sortType);
			}

		}
		return sort;
	}

	/**
	 * 创建排序
	 *
	 * @param sortTypes 排序
	 * @return 排序
	 */
	private Sort buildSort(List<String> sortTypes) {
		if (sortTypes == null || sortTypes.size() == 0) {
			return Sort.by(Sort.Direction.DESC, "id");
		}

		List<Sort.Order> orderList = new ArrayList<>();
		for (String sortType : sortTypes) {
			if (sortType.toLowerCase().startsWith("asc_")) {
				sortType = sortType.substring(4);
				orderList.add(new Sort.Order(Sort.Direction.ASC, sortType));
			} else if (sortType.toLowerCase().startsWith("desc_")) {
				sortType = sortType.substring(5);
				orderList.add(new Sort.Order(Sort.Direction.DESC, sortType));
			} else {
				orderList.add(new Sort.Order(Sort.Direction.DESC, sortType));
			}
		}
		return Sort.by(orderList);
	}

}
