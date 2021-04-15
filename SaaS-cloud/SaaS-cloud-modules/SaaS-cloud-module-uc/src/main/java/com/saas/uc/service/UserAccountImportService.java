package com.saas.uc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saas.common.jpa.BaseDao;
import com.saas.common.jpa.BaseService;
import com.saas.uc.domain.UserAccountImport;
import com.saas.uc.repository.UserAccountImportDao;

@Service
public class UserAccountImportService extends BaseService<UserAccountImport> {

	@Autowired
	private UserAccountImportDao modelDao;

	
	@Override
	protected BaseDao<UserAccountImport> getModelDao() {		
		return modelDao;
	}

	@Override
	protected boolean isUseLocalCache() {	
		return false;
	}
	
	

}
