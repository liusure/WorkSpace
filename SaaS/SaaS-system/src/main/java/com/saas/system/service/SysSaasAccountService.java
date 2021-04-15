package com.saas.system.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saas.common.core.domain.SysSaasAccount;
import com.saas.common.jpa.BaseDao;
import com.saas.common.jpa.BaseService;
import com.saas.system.repository.SysSaasAccountDao;

/**
 * SAAS账号Service接口
 * 
 * @author bruce
 * @date 2021-01-21
 */
 @Service
public class SysSaasAccountService extends BaseService<SysSaasAccount> {

    @Autowired
	private SysSaasAccountDao modelDao;


	@Override
	protected BaseDao<SysSaasAccount> getModelDao() {
		return modelDao;
	}

	@Override
	protected boolean isUseLocalCache() {
		return false;
	}
}
