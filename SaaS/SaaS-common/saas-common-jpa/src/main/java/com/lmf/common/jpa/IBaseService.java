package com.saas.common.jpa;

import java.util.List;
import java.util.Map;

import com.saas.common.core.domain.IdEntity;
import com.saas.common.core.entity.KeyValue;
import com.saas.common.core.page.DataPage;
import com.saas.common.core.page.DataPageable;

public interface IBaseService<T extends IdEntity> {
	public T save(T model);

	public void delete(Long id);

	public T getById(Long id);

	public List<T> getList(DataPageable dataPageable);

	public DataPage<T> getListPage(DataPageable dataPageable);

	public List<T> getList(Map<String, Object> searchParams);

	public List<T> getList(Map<String, Object> searchParams, String sortType);

	public List<T> getList(Map<String, Object> searchParams, List<String> sortTypes);

	public List<T> getAllModels();

	public boolean checkFieldUnique(T item, String... fields);

	public T getUniqueModel(KeyValue... keyValues);

	public T getFirstModel(KeyValue... keyValues);

	public List<T> getModels(KeyValue... keyValues);

	/**
	 * 实体 保存、删除前 执行
	 *
	 * @param newItem 要保存的实体
	 * @param oldItem 之前的实体
	 */
	default void modelChangeBefore(T newItem, T oldItem) {
	}

	/**
	 * 实体 保存、删除后 执行
	 *
	 * @param newItem 要保存的实体
	 * @param oldItem 之前的实体
	 */
	default void modelChangeAfter(T newItem, T oldItem) {
	}

	/**
	 * 是否自动关联SAAS ID属性
	 * 
	 * @return
	 */
	default boolean isAutoLinkSaasId() {
		return true;
	}
}
