package com.saas.system.service;


import com.saas.system.domain.SysConfig;
import com.saas.system.repository.SysConfigDao;
import com.saas.common.jpa.BaseDao;
import com.saas.common.jpa.BaseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 参数配置Service接口
 * 
 * @author bruce
 * @date 2021-01-06
 */
 @Service
public class SysConfigService extends BaseService<SysConfig> {

    @Autowired
	private SysConfigDao modelDao;


	@Override
	protected BaseDao<SysConfig> getModelDao() {
		return modelDao;
	}

	@Override
	protected boolean isUseLocalCache() {
		return false;
	}
	
}
