package com.saas.system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saas.common.core.domain.SysRole;
import com.saas.common.jpa.BaseDao;
import com.saas.common.jpa.BaseService;
import com.saas.system.repository.SysRoleDao;

@Service
public class SysRoleService extends BaseService<SysRole> {

	@Autowired
	private SysRoleDao modelDao;

	@Override
	protected BaseDao<SysRole> getModelDao() {
		return modelDao;
	}

	@Override
	protected boolean isUseLocalCache() {
		return false;
	}
	
	public boolean checkNameUnique(SysRole role) {
		return checkFieldUnique(role, "name");
	}

}
