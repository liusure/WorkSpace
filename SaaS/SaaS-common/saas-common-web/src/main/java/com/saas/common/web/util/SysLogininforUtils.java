package com.saas.common.web.util;

import java.util.Date;

import com.saas.common.constant.Constants;
import com.saas.common.event.Logininfor;
import com.saas.common.event.WebLogininforEvent;
import com.saas.common.util.ServletUtils;
import com.saas.common.util.SpringUtils;
import com.saas.common.util.ip.IpUtils;

import eu.bitwalker.useragentutils.UserAgent;

public class SysLogininforUtils {

	public static String getBlock(Object msg) {
		if (msg == null) {
			msg = "";
		}
		return "[" + msg.toString() + "]";
	}

	/**
	 * 记录登录信息
	 * 
	 * @param username 用户名
	 * @param status   状态
	 * @param message  消息
	 * @param args     列表
	 * @return 任务task
	 */
	public static void recordLogininfor(final String username, final String status, final String message,
			final Object... args) {
		final UserAgent userAgent = UserAgent.parseUserAgentString(ServletUtils.getRequest().getHeader("User-Agent"));
		final String ip = IpUtils.getIpAddr(ServletUtils.getRequest());
		// 获取客户端操作系统
		String os = userAgent.getOperatingSystem().getName();
		// 获取客户端浏览器
		String browser = userAgent.getBrowser().getName();
		// 封装对象
		Logininfor logininfor = new Logininfor();
		logininfor.setUserName(username);
		logininfor.setIpaddr(ip);
		logininfor.setBrowser(browser);
		logininfor.setOs(os);
		logininfor.setMsg(message);
		logininfor.setLoginTime(new Date());
		// 日志状态
		if (Constants.LOGIN_SUCCESS.equals(status) || Constants.LOGOUT.equals(status)) {
			logininfor.setStatus(Constants.SUCCESS);
		} else if (Constants.LOGIN_FAIL.equals(status)) {
			logininfor.setStatus(Constants.FAIL);
		}
		SpringUtils.publishEvent(new WebLogininforEvent(logininfor));

	}
}
