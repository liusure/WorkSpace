package com.saas.common.es;

import java.util.Collection;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.ExistsQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.PrefixQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.index.query.TermsQueryBuilder;
import org.elasticsearch.index.query.WildcardQueryBuilder;

import com.saas.common.jpa.SearchFilter;

public class DynamicQuerys {

    public static QueryBuilder bySearchFilter(final Collection<SearchFilter> filters) {
        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
        if (filters != null && !filters.isEmpty()) {
            for (SearchFilter filter : filters) {
                // logic operator
                switch (filter.operator) {
                    case EQ:
                        MatchQueryBuilder eqBuilder = QueryBuilders.matchQuery(filter.fieldName, filter.value);
                        queryBuilder.must(eqBuilder);
                        break;
                    case LIKE:
                        TermQueryBuilder likeBuilder = QueryBuilders.termQuery(filter.fieldName, filter.value);
                        queryBuilder.must(likeBuilder);
                        break;
                    case LLIKE:
                        PrefixQueryBuilder llikeBuilder = QueryBuilders.prefixQuery(filter.fieldName,
                                filter.value.toString());
                        queryBuilder.must(llikeBuilder);
                        break;
                    case RLIKE:
                        WildcardQueryBuilder rlikeBuilder = QueryBuilders.wildcardQuery(filter.fieldName,
                                "*" + filter.value);
                        queryBuilder.must(rlikeBuilder);
                        break;
                    case CLIKE:
                        WildcardQueryBuilder clikeBuilder = QueryBuilders.wildcardQuery(filter.fieldName,
                                filter.value.toString());
                        queryBuilder.must(clikeBuilder);
                        break;
                    case GT:
                        RangeQueryBuilder gtBuilder = QueryBuilders.rangeQuery(filter.fieldName);
                        gtBuilder.gt(filter.value);
                        queryBuilder.must(gtBuilder);
                        break;
                    case LT:
                        RangeQueryBuilder ltBuilder = QueryBuilders.rangeQuery(filter.fieldName);
                        ltBuilder.lt(filter.value);
                        queryBuilder.must(ltBuilder);
                        break;
                    case GTE:
                        RangeQueryBuilder gteBuilder = QueryBuilders.rangeQuery(filter.fieldName);
                        gteBuilder.gte(filter.value);
                        queryBuilder.must(gteBuilder);
                        break;
                    case LTE:
                        RangeQueryBuilder lteBuilder = QueryBuilders.rangeQuery(filter.fieldName);
                        lteBuilder.gte(filter.value);
                        queryBuilder.must(lteBuilder);
                        break;
                    case NOTEQ:
                        TermQueryBuilder notEQBuilder = QueryBuilders.termQuery(filter.fieldName, filter.value);
                        queryBuilder.mustNot(notEQBuilder);
                        break;
                    case ISNULL:
                        ExistsQueryBuilder notExistBuilder = QueryBuilders.existsQuery(filter.fieldName);
                        queryBuilder.mustNot(notExistBuilder);
                        break;
                    case NOTNULL:
                        ExistsQueryBuilder existBuilder = QueryBuilders.existsQuery(filter.fieldName);
                        queryBuilder.must(existBuilder);
                        break;
                    case IN:
                        TermsQueryBuilder inBuilder = QueryBuilders.termsQuery(filter.fieldName, (Collection) filter.value);
                        queryBuilder.must(inBuilder);
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + filter.operator);
                }
            }
        }
        return queryBuilder;

    }
}
