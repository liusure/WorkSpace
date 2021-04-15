package com.saas.common.jpa;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BaseDao<T> extends PagingAndSortingRepository<T, Long>, JpaSpecificationExecutor<T> {

}
