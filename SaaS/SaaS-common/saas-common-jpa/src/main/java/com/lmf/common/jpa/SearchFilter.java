package com.saas.common.jpa;


import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class SearchFilter {

	public enum Operator {
		EQ, LIKE, GT, LT, GTE, LTE, NOTEQ, LLIKE, RLIKE, CLIKE, ISNULL, NOTNULL, IN
	}

	public String fieldName;
	public Object value;
	public Operator operator;

	public SearchFilter(String fieldName, Operator operator, Object value) {
		this.fieldName = fieldName;
		this.value = value;
		this.operator = operator;
	}

	/**
	 * searchParams中key的格式为 OPERATOR_FIELDNAME
	 */
	public static Map<String, SearchFilter> parse(Map<String, Object> searchParams) {
		Map<String, SearchFilter> filters = new HashMap<>();

		for (Entry<String, Object> entry : searchParams.entrySet()) {
			// 过滤掉空值
			String key = entry.getKey();
			Object value = entry.getValue();

			/*if(value == null || StringUtils.isBlank(value.toString())){
				continue;
			}*/

			// 拆分operator与filedAttribute
			String[] names = StringUtils.split(key, "_");

			if (names.length != 2) {
				throw new IllegalArgumentException(key + " is not a valid search filter name");
			}

			String operatorStr = names[0].toUpperCase();
			String fieldName = names[1];

			//需要忽略掉的字段
			if ("IG".equals(operatorStr)) {
				continue;
			}

			Operator operator = Operator.valueOf(operatorStr);

			// 创建searchFilter
			SearchFilter filter = new SearchFilter(fieldName, operator, value);
			filters.put(key, filter);
		}

		return filters;
	}

}
