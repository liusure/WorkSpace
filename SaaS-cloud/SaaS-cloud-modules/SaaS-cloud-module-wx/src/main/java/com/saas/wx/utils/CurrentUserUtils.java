package com.saas.wx.utils;

import com.saas.common.core.domain.SysUser;

public class CurrentUserUtils {

	private static ThreadLocal<SysUser> sysUserLocal = new ThreadLocal<>();

	public static void setSysUser(SysUser sysUser) {
		sysUserLocal.set(sysUser);
	}

	public static SysUser getSysUser() {
		return sysUserLocal.get();
	}

	public static void removeAll() {
		sysUserLocal.remove();
	}

	public static Long getSaasId() {
		return getSysUser().getSaasId();
	}

	public static void setSaasId(Long saasId) {
		getSysUser().setSaasId(saasId);
	}
}
