package com.saas.uc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saas.common.jpa.BaseDao;
import com.saas.common.jpa.BaseService;
import com.saas.uc.domain.UserSnsAccount;
import com.saas.uc.repository.UserSnsAccountDao;

@Service
public class UserSnsAccountService extends BaseService< UserSnsAccount> {

    @Autowired
	private UserSnsAccountDao modelDao;


	@Override
	protected BaseDao<UserSnsAccount> getModelDao() {
		return modelDao;
	}

	@Override
	protected boolean isUseLocalCache() {
		return false;
	}
}