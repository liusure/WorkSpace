package com.saas.cloud.api.model;

import java.io.Serializable;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.saas.common.core.domain.SysUser;

/**
 * 用户信息
 *
 */
public class LoginUser implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 用户唯一标识
	 */
	private String token;

	/**
	 * 登录时间
	 */
	private Long loginTime;

	/**
	 * 过期时间
	 */
	private Long expireTime;

	/**
	 * 登录IP地址
	 */
	private String ipaddr;

	/**
	 * 登录地点
	 */
	private String loginLocation;

	/**
	 * 浏览器类型
	 */
	private String browser;

	/**
	 * 操作系统
	 */
	private String os;

	/**
	 * 权限列表
	 */
	private Set<String> permissions;

	private Set<String> roles;
	/**
	 * 用户信息
	 */
	private SysUser user;

	public Set<String> getRoles() {
		return roles;
	}

	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public LoginUser() {
	}

	public LoginUser(SysUser user, Set<String> permissions) {
		this.user = user;
		this.permissions = permissions;
	}

	@JsonIgnore

	public String getPassword() {
		return user.getPassword();
	}

	public String getUsername() {
		return user.getName();
	}

	public Long getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Long loginTime) {
		this.loginTime = loginTime;
	}

	public String getIpaddr() {
		return ipaddr;
	}

	public void setIpaddr(String ipaddr) {
		this.ipaddr = ipaddr;
	}

	public String getLoginLocation() {
		return loginLocation;
	}

	public void setLoginLocation(String loginLocation) {
		this.loginLocation = loginLocation;
	}

	public String getBrowser() {
		return browser;
	}

	public void setBrowser(String browser) {
		this.browser = browser;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public Long getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(Long expireTime) {
		this.expireTime = expireTime;
	}

	public Set<String> getPermissions() {
		return permissions;
	}

	public void setPermissions(Set<String> permissions) {
		this.permissions = permissions;
	}

	public SysUser getUser() {
		return user;
	}

	public void setUser(SysUser user) {
		this.user = user;
	}

}
