package com.saas.common.security.service;

import com.saas.common.core.domain.SysUser;

public interface IUserService {
	
	public SysUser selectUserByUserName(String userName);
}
