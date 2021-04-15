package com.saas.auth.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.saas.cloud.api.system.RemoteUserService;
import com.saas.common.constant.Constants;
import com.saas.common.constant.UserConstants;
import com.saas.common.core.domain.R;
import com.saas.common.exception.BaseException;
import com.saas.common.util.StringUtils;
import com.saas.common.web.util.SysLogininforUtils;

/**
 * 登录校验方法
 * 
 */
@Component
public class SysLoginService {

	@Autowired
	private RemoteUserService remoteUserService;

	/**
	 * 登录
	 */
	public Map<String, Object> login(String username, String password) {
		// 用户名或密码为空 错误
		if (StringUtils.isAnyBlank(username, password)) {
			SysLogininforUtils.recordLogininfor(username, Constants.LOGIN_FAIL, "用户/密码必须填写");
			throw new BaseException("用户/密码必须填写");
		}
		// 密码如果不在指定范围内 错误
		if (password.length() < UserConstants.PASSWORD_MIN_LENGTH
				|| password.length() > UserConstants.PASSWORD_MAX_LENGTH) {
			SysLogininforUtils.recordLogininfor(username, Constants.LOGIN_FAIL, "用户密码不在指定范围");
			throw new BaseException("用户密码不在指定范围");
		}
		// 用户名不在指定范围内 错误
		if (username.length() < UserConstants.USERNAME_MIN_LENGTH
				|| username.length() > UserConstants.USERNAME_MAX_LENGTH) {
			SysLogininforUtils.recordLogininfor(username, Constants.LOGIN_FAIL, "用户名不在指定范围");
			throw new BaseException("用户名不在指定范围");
		}
		// 查询用户信息
		R<Map<String, Object>> userResult = remoteUserService.login(username, password);

		if (R.FAIL == userResult.getCode()) {
			throw new BaseException(userResult.getMsg());
		}

		if (StringUtils.isNull(userResult) || StringUtils.isNull(userResult.getData())) {
			SysLogininforUtils.recordLogininfor(username, Constants.LOGIN_FAIL, "登录用户不存在");
			throw new BaseException("登录用户：" + username + " 不存在");
		}
		SysLogininforUtils.recordLogininfor(username, Constants.LOGIN_SUCCESS, "登录成功");
		return userResult.getData();
	}

	public void logout(String loginName) {
		SysLogininforUtils.recordLogininfor(loginName, Constants.LOGOUT, "退出成功");
	}
}