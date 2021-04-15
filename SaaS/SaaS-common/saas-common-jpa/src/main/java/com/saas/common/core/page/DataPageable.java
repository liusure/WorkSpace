package com.saas.common.core.page;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.common.jpa.SearchFilter.Operator;
import com.saas.common.util.DateUtils;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DataPageable implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -366103048135436422L;
	// 默认页码
	private static final int DEFAULT_PAGE_NUMBER = 1;
	// 默认每页记录数
	private static final int DEFAULT_PAGE_SIZE = 100;
	// 最大每页记录数
	private static final int MAX_PAGE_SIZE = 1000;

	/**
	 * 每页量
	 */
	private int pageSize = DEFAULT_PAGE_SIZE;

	/**
	 * 当前页码
	 */
	private int pageNum = DEFAULT_PAGE_NUMBER;

	/**
	 * 排序 规则，asc_ 升序，desc_ 降序
	 */
	private String sort;

	/** 请求参数 */
	private Map<String, Object> params;

	/**
	 * 排序
	 */
	@JsonIgnore
	private List<String> sorts = new ArrayList<>();

	@JsonProperty
	private Map<String, Object> reqSearchParams = new HashMap<>();

	/**
	 * 构造方法
	 */
	public DataPageable() {
	}

	/**
	 * 构造方法
	 *
	 * @param pageNum  当前页码
	 * @param pageSize 每页量
	 */
	public DataPageable(Integer pageNum, Integer pageSize) {
		if (pageNum != null && pageNum >= 1) {
			this.pageNum = pageNum;
		}
		if (pageSize != null && pageSize >= 1 && pageSize <= MAX_PAGE_SIZE) {
			this.pageSize = pageSize;
		}
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		if (pageSize < 1 || pageSize > MAX_PAGE_SIZE) {
			pageSize = DEFAULT_PAGE_SIZE;
		}
		this.pageSize = pageSize;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		if (pageNum < 1) {
			pageNum = DEFAULT_PAGE_NUMBER;
		}
		this.pageNum = pageNum;
	}

	public List<String> getSorts() {
		if (this.sort != null && this.sort.length() > 0) {
			String[] sortArr = this.sort.split(",");
			for (int i = 0; i < sortArr.length; i++)
				sorts.add(sortArr[i]);
		}
		return sorts;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public Map<String, Object> getSearchParams() {
		Map<String, Object> searchParams = new HashMap<>();
		searchParams.putAll(getParams());
		searchParams.putAll(reqSearchParams);

		Map<String, Object> queryMap = new HashMap<>();

		searchParams.forEach((key, value) -> {
			String[] names = StringUtils.split(key, "_");
			if (names.length > 1) {
				Operator op = Operator.valueOf(names[0].toUpperCase());
				if (op != null) {
					if (key.endsWith("_date")) {
						value = DateUtils.parseDate(value);
						key = key.replace("_date", "");
					} else if (key.endsWith("_boolean")) {
						value = Boolean.parseBoolean(value.toString());
						key = key.replace("_boolean", "");
					}
					if (value != null && StringUtils.isNotEmpty(value.toString())
							&& !"null".equalsIgnoreCase(value.toString())) {
						queryMap.put(key, value);
					}
				}
			}
		});
		return queryMap;
	}

	public void setReqSearchParams(Map<String, Object> reqSearchParams) {
		this.reqSearchParams = reqSearchParams;
	}

	public Map<String, Object> getParams() {
		if (params == null) {
			params = new HashMap<>();
		}
		return params;
	}

	public void setParams(Map<String, Object> params) {
		this.params = params;
	}
}
