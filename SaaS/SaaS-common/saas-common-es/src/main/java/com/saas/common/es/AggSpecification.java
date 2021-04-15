package com.saas.common.es;

import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.histogram.DateHistogramInterval;

/**
 * 聚合表达逻辑
 *
 * @author bruce
 */
public class AggSpecification {

    public enum FieldType {
        KEYWORD, NUMBER, DATE
    }

    public enum IndicatorType {
        COUNT, MAX, MIN, SUM, AVG, TERMS, TOP_HITS
    }

    protected String fieldName;
    protected FieldType fieldType;
    protected IndicatorType indicatorType;
    protected DateHistogramInterval dateFormat;

    public String getAggTermName() {
        return fieldName + "_agg";
    }

    public String getAggFieldName() {
        if (fieldType.equals(FieldType.KEYWORD)) {
            return fieldName + ".keyword";
        }
        return fieldName;
    }

    public AggregationBuilder aggregationBuilder() {
        switch (indicatorType) {
            case MAX:
                return AggregationBuilders.max(getAggTermName()).field(fieldName);
            case MIN:
                return AggregationBuilders.min(getAggTermName()).field(fieldName);
            case SUM:
                return AggregationBuilders.sum(getAggTermName()).field(fieldName);
            case AVG:
                return AggregationBuilders.avg(getAggTermName()).field(fieldName);
            case TERMS:
                return AggregationBuilders.terms(getAggTermName()).field(fieldName);
            case TOP_HITS:
                return AggregationBuilders.topHits(getAggTermName()).size(1);
            case COUNT:
                if (FieldType.DATE.equals(fieldType)) {
                    return AggregationBuilders.dateHistogram(getAggTermName()).calendarInterval(DateHistogramInterval.DAY)
                            .field(getAggFieldName());
                }
                return AggregationBuilders.terms(getAggTermName()).field(getAggFieldName());
        }
        return null;

    }
}
