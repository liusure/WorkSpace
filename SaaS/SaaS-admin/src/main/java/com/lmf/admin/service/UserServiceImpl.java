package com.saas.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saas.common.core.domain.SysUser;
import com.saas.common.core.entity.KeyValue;
import com.saas.common.security.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private SysUserService sysUserService;

	public SysUser selectUserByUserName(String userName) {		
		return sysUserService.getUniqueModel(new KeyValue("loginName", userName));
	}

}
