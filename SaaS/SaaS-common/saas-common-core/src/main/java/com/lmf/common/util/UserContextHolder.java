package com.saas.common.util;

import com.saas.common.core.domain.SysUser;
import com.saas.common.userservice.ICurrentUserService;

public class UserContextHolder {

	public static SysUser getCurrentUser() {
		return SpringUtils.getBean(ICurrentUserService.class).getCurrentUser();
	}
}
