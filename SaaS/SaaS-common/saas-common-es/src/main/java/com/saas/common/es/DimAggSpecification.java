/**
 * 
 */
package com.saas.common.es;

import org.elasticsearch.search.aggregations.bucket.histogram.DateHistogramInterval;

/**
 * 维度表达式
 * 
 * @author bruce
 *
 */
public class DimAggSpecification extends AggSpecification {

	public DimAggSpecification() {
		indicatorType = IndicatorType.COUNT;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getFieldName() {
		return this.fieldName;
	}

	public FieldType getFieldType() {
		return fieldType;
	}

	public void setFieldType(FieldType fieldType) {
		this.fieldType = fieldType;
	}

	public IndicatorType getIndicatorType() {
		return indicatorType;
	}

	public DateHistogramInterval getDateFormat() {
		return dateFormat;
	}

	public void setDateFormat(DateHistogramInterval dateFormat) {
		this.dateFormat = dateFormat;
	}

}
