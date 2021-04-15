package com.saas.common.security.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.saas.common.constant.DomainConstants;
import com.saas.common.core.domain.SysUser;
import com.saas.common.exception.BaseException;
import com.saas.common.security.entity.login.LoginUser;

/**
 * 用户验证处理
 *
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	private static final Logger log = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

	@Autowired
	private IUserService userService;

	@Autowired
	private SysPermissionService permissionService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		SysUser user = userService.selectUserByUserName(username);
		if (null == user) {
			log.info("登录用户：{} 不存在.", username);
			throw new UsernameNotFoundException("登录用户：" + username + " 不存在");
		} else if (user.getDelFlag() == DomainConstants.DOMAIN_DEL_FLAG_DELETED) {
			log.info("登录用户：{} 已被删除.", username);
			throw new BaseException("对不起，您的账号：" + username + " 已被删除");
		} else if (user.getStatusFlag() != DomainConstants.DOMAIN__STATUS_PUBLISH) {
			log.info("登录用户：{} 已被停用.", username);
			throw new BaseException("对不起，您的账号：" + username + " 已停用");
		}

		return createLoginUser(user);
	}

	public UserDetails createLoginUser(SysUser user) {
		return new LoginUser(user, permissionService.getUserPermission(user));
	}
}
