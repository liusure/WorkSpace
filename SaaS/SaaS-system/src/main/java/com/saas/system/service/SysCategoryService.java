package com.saas.system.service;


import com.saas.system.domain.SysCategory;
import com.saas.system.repository.SysCategoryDao;
import com.saas.common.jpa.BaseDao;
import com.saas.common.jpa.BaseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 系统目录Service接口
 * 
 * @author bruce
 * @date 2021-01-07
 */
 @Service
public class SysCategoryService extends BaseService<SysCategory> {

    @Autowired
	private SysCategoryDao modelDao;


	@Override
	protected BaseDao<SysCategory> getModelDao() {
		return modelDao;
	}

	@Override
	protected boolean isUseLocalCache() {
		return false;
	}
}
