package com.saas.common.security.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.saas.common.core.domain.SysUser;

/**
 * 用户权限处理
 * 
 */
@Component
public class SysPermissionService {

	/**
	 * 获取用户数据权限
	 * 
	 * @param user 用户信息
	 * @return 菜单权限信息
	 */
	public Set<String> getUserPermission(SysUser user) {
		Set<String> perms = new HashSet<String>();
		// 超级管理员拥有所有权限
		if (user.isAdmin()) {
			perms.add("*:*:*");
		} else {
			user.getRoleList().forEach((role) -> {
				perms.addAll(role.getPermissionList());
			});
		}
		return perms;
	}
}

