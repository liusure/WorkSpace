package com.saas.common.security.service;

import com.saas.common.util.SpringUtils;

public class AuthFactory {

	private PermissionService getPermissionService() {
		return SpringUtils.getBean(PermissionService.class);
	}

	public boolean hasPermi(String permission) {
		return getPermissionService().hasPermi(permission);
	}

	public boolean lacksPermi(String permission) {
		return getPermissionService().lacksPermi(permission);
	}

	public boolean hasAnyPermi(String[] permissions) {
		return getPermissionService().hasAnyPermi(permissions);
	}

	public boolean hasRole(String role) {
		return getPermissionService().hasRole(role);
	}

	public boolean lacksRole(String role) {
		return getPermissionService().lacksRole(role);
	}

	public boolean hasAnyRoles(String[] roles) {
		return getPermissionService().hasAnyRoles(roles);
	}
}
