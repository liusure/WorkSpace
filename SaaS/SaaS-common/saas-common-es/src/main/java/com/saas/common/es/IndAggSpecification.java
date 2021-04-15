/**
 * 
 */
package com.saas.common.es;

import com.saas.common.exception.CustomException;

/**
 * 指标表达式
 * 
 * @author bruce
 *
 */
public class IndAggSpecification extends AggSpecification {

	public IndAggSpecification() {
		this.fieldType = FieldType.NUMBER;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public FieldType getFieldType() {
		return fieldType;
	}

	public IndicatorType getIndicatorType() {
		return indicatorType;
	}

	public void setIndicatorType(IndicatorType indicatorType) {
		if (indicatorType.equals(IndicatorType.COUNT)) {
			throw new CustomException("指标类型不正确");
		}
		this.indicatorType = indicatorType;
	}

}
