/**
 * 
 */
package com.saas.common.security.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.PatternMatchUtils;
import org.springframework.util.StringUtils;

import com.saas.cloud.api.model.LoginUser;

/**
 * @author bruce
 *
 */
@Service("ss")
public class PermissionService {

	/** 所有权限标识 */
	private static final String ALL_PERMISSION = "*:*:*";

	/** 管理员角色权限标识 */
	private static final String SUPER_ADMIN = "admin";

	@Autowired
	private TokenService tokenService;

	/**
	 * 验证用户是否具备某权限
	 * 
	 * @param permission 权限字符串
	 * @return 用户是否具备某权限
	 */
	public boolean hasPermi(String permission) {		
		LoginUser userInfo = tokenService.getLoginUser();
		if (StringUtils.isEmpty(userInfo) || CollectionUtils.isEmpty(userInfo.getPermissions())) {
			return false;
		}
		return hasPermissions(userInfo.getPermissions(), permission);
	}

	/**
	 * 验证用户是否不具备某权限，与 hasPermi逻辑相反
	 *
	 * @param permission 权限字符串
	 * @return 用户是否不具备某权限
	 */
	public boolean lacksPermi(String permission) {
		return hasPermi(permission) != true;
	}

	/**
	 * 验证用户是否具有以下任意一个权限
	 *
	 * @param permissions 权限列表
	 * @return 用户是否具有以下任意一个权限
	 */
	public boolean hasAnyPermi(String[] permissions) {
		LoginUser userInfo = tokenService.getLoginUser();
		if (StringUtils.isEmpty(userInfo) || CollectionUtils.isEmpty(userInfo.getPermissions())) {
			return false;
		}
		Collection<String> authorities = userInfo.getPermissions();
		for (String permission : permissions) {
			if (permission != null && hasPermissions(authorities, permission)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 判断用户是否拥有某个角色
	 * 
	 * @param role 角色字符串
	 * @return 用户是否具备某角色
	 */
	public boolean hasRole(String role) {
		LoginUser userInfo = tokenService.getLoginUser();
		if (StringUtils.isEmpty(userInfo) || CollectionUtils.isEmpty(userInfo.getRoles())) {
			return false;
		}
		for (String roleKey : userInfo.getRoles()) {
			if (SUPER_ADMIN.equals(roleKey) || roleKey.equals(role)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 验证用户是否不具备某角色，与 isRole逻辑相反。
	 *
	 * @param role 角色名称
	 * @return 用户是否不具备某角色
	 */
	public boolean lacksRole(String role) {
		return hasRole(role) != true;
	}

	/**
	 * 验证用户是否具有以下任意一个角色
	 *
	 * @param roles 角色列表
	 * @return 用户是否具有以下任意一个角色
	 */
	public boolean hasAnyRoles(String[] roles) {
		LoginUser userInfo = tokenService.getLoginUser();
		if (StringUtils.isEmpty(userInfo) || CollectionUtils.isEmpty(userInfo.getRoles())) {
			return false;
		}
		for (String role : roles) {
			if (hasRole(role)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 判断是否包含权限
	 * 
	 * @param authorities 权限列表
	 * @param permission  权限字符串
	 * @return 用户是否具备某权限
	 */
	private boolean hasPermissions(Collection<String> authorities, String permission) {
		return authorities.stream().filter(StringUtils::hasText)
				.anyMatch(x -> ALL_PERMISSION.contains(x) || PatternMatchUtils.simpleMatch(permission, x));
	}
}
