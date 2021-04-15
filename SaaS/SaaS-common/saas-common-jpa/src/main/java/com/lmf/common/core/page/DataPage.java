package com.saas.common.core.page;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DataPage<T> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5208025102172961917L;

	/**
	 * 内容
	 */

	private List<T> content = new ArrayList<>();

	/**
	 * 总记录数
	 */
	private long total;

	/**
	 * 分页信息
	 */
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private DataPageable dataPageable;

	public DataPage() {
		this.total = 0L;
		this.dataPageable = new DataPageable();
	}

	public DataPage(List<T> content, long total, DataPageable dataPageable) {
		this.content = content;
		this.total = total;
		this.dataPageable = dataPageable;
	}

	public DataPage(List<T> content, long total) {
		this.content = content;
		this.total = total;
		this.dataPageable = null;
	}

	public List<T> getContent() {
		return content;
	}

	public void setContent(List<T> content) {
		this.content = content;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public DataPageable getDataPageable() {
		return dataPageable;
	}

	public void setDataPageable(DataPageable dataPageable) {
		this.dataPageable = dataPageable;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
