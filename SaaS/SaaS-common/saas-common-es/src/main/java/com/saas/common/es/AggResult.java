/**
 * 
 */
package com.saas.common.es;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 聚合结果
 * 
 * @author bruce
 *
 */
public class AggResult {

	private String name;
	private Object key;
	private long count;
	private List<AggResult> childs;
	private Map<String, Double> indAggValues;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Object getKey() {
		return key;
	}

	public void setKey(Object key) {
		this.key = key;
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	public List<AggResult> getChilds() {
		return childs;
	}

	public void setChilds(List<AggResult> childs) {
		this.childs = childs;
	}

	public Map<String, Double> getIndAggValues() {
		return indAggValues;
	}

	public void setIndAggValues(Map<String, Double> indAggValues) {
		this.indAggValues = indAggValues;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
