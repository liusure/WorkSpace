package com.saas.common.security.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.saas.common.constant.HttpStatus;
import com.saas.common.core.domain.SysUser;
import com.saas.common.exception.CustomException;
import com.saas.common.security.entity.login.LoginUser;
import com.saas.common.userservice.ICurrentUserService;

@Component
public class CurrentUserServiceImpl implements ICurrentUserService {

	@Override
	public SysUser getCurrentUser() {		
		return getLoginUser().getUser();
	}

	/**
	 * 获取用户
	 **/
	public static LoginUser getLoginUser() {
		try {
			return (LoginUser) getAuthentication().getPrincipal();
		} catch (Exception e) {
			throw new CustomException("获取用户信息异常", HttpStatus.UNAUTHORIZED);
		}
	}

	/**
	 * 获取Authentication
	 */
	public static Authentication getAuthentication() {
		return SecurityContextHolder.getContext().getAuthentication();
	}

}
