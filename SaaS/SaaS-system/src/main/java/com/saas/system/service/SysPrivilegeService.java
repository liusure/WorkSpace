package com.saas.system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saas.common.core.domain.SysPrivilege;
import com.saas.common.jpa.BaseDao;
import com.saas.common.jpa.BaseService;
import com.saas.system.repository.SysPrivilegeDao;

@Service
public class SysPrivilegeService extends BaseService<SysPrivilege> {
	
	@Autowired
	private SysPrivilegeDao modelDao;

	@Override
	protected BaseDao<SysPrivilege> getModelDao() {
		return modelDao;
	}

	@Override
	protected boolean isUseLocalCache() {		
		return false;
	}

}
