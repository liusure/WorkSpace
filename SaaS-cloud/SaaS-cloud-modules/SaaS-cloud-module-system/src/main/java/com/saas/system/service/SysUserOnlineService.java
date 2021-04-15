package com.saas.system.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.saas.cloud.api.model.LoginUser;
import com.saas.system.domain.SysUserOnline;

@Service
public class SysUserOnlineService {

	/**
	 * 通过登录地址查询信息
	 * 
	 * @param ipaddr 登录地址
	 * @param user   用户信息
	 * @return 在线用户信息
	 */

	public SysUserOnline selectOnlineByIpaddr(String ipaddr, LoginUser user) {
		if (StringUtils.equals(ipaddr, user.getIpaddr())) {
			return loginUserToUserOnline(user);
		}
		return null;
	}

	/**
	 * 通过用户名称查询信息
	 * 
	 * @param userName 用户名称
	 * @param user     用户信息
	 * @return 在线用户信息
	 */

	public SysUserOnline selectOnlineByUserName(String userName, LoginUser user) {
		if (StringUtils.equals(userName, user.getUsername())) {
			return loginUserToUserOnline(user);
		}
		return null;
	}

	/**
	 * 通过登录地址/用户名称查询信息
	 * 
	 * @param ipaddr   登录地址
	 * @param userName 用户名称
	 * @param user     用户信息
	 * @return 在线用户信息
	 */

	public SysUserOnline selectOnlineByInfo(String ipaddr, String userName, LoginUser user) {
		if (StringUtils.equals(ipaddr, user.getIpaddr()) && StringUtils.equals(userName, user.getUsername())) {
			return loginUserToUserOnline(user);
		}
		return null;
	}

	/**
	 * 设置在线用户信息
	 * 
	 * @param user 用户信息
	 * @return 在线用户
	 */

	public SysUserOnline loginUserToUserOnline(LoginUser user) {
		if (null == user || null == user.getUser()) {
			return null;
		}
		SysUserOnline sysUserOnline = new SysUserOnline();
		sysUserOnline.setTokenId(user.getToken());
		sysUserOnline.setUserName(user.getUsername());
		sysUserOnline.setIpaddr(user.getIpaddr());
		sysUserOnline.setLoginLocation(user.getLoginLocation());
		sysUserOnline.setBrowser(user.getBrowser());
		sysUserOnline.setOs(user.getOs());
		sysUserOnline.setLoginTime(user.getLoginTime());
//		if (StringUtils.isNotNull(user.getUser().getDeptId())) {
//			sysUserOnline.setDeptName(user.getUser().getDept().getDeptName());
//		}
		return sysUserOnline;
	}
}
