package com.saas.uc.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.saas.common.core.domain.SysUser;
import com.saas.common.exception.CustomException;
import com.saas.common.util.ServletUtils;
import com.saas.uc.constant.UcConstants;
import com.saas.uc.utils.CurrentUserUtils;

@Component
public class FrontCurrentUserInterceptor extends HandlerInterceptorAdapter {

	private static SysUser user = new SysUser();
	static {
		user.setId(0l);
		user.setLoginName("front");
		user.setName("前端用户");
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
		user.setSaasId(ServletUtils.getHeaderToLong(UcConstants.HEADER_SAAS_ID));
		CurrentUserUtils.setSysUser(user);
		return true;
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// 清除 当前信息
		CurrentUserUtils.removeAll();
	}

	@Override
	public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		CurrentUserUtils.removeAll();
		// 用户并发请求
		throw new CustomException("请不要重复点击");
	}
}
