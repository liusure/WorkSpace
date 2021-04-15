package com.saas.common.security.handle;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import com.alibaba.fastjson.JSON;
import com.saas.common.constant.Constants;
import com.saas.common.constant.HttpStatus;
import com.saas.common.core.entity.AjaxResult;
import com.saas.common.security.entity.login.LoginUser;
import com.saas.common.security.service.TokenService;
import com.saas.common.util.ServletUtils;
import com.saas.common.web.util.SysLogininforUtils;


/**
 * 自定义退出处理类 返回成功
 * 
 */
@Configuration
public class LogoutSuccessHandlerImpl implements LogoutSuccessHandler {
	@Autowired
	private TokenService tokenService;

	/**
	 * 退出处理
	 * 
	 * @return
	 */
	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		LoginUser loginUser = tokenService.getLoginUser(request);
		if (null != loginUser) {
			String userName = loginUser.getUsername();
			// 删除用户缓存记录
			tokenService.delLoginUser(loginUser.getToken());
			// 记录用户退出日志
			SysLogininforUtils.recordLogininfor(userName, Constants.LOGOUT, "退出成功");
		}
		ServletUtils.renderString(response, JSON.toJSONString(AjaxResult.error(HttpStatus.SUCCESS, "退出成功")));
	}
}
