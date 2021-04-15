package com.saas.auth.controller;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.saas.auth.form.LoginBody;
import com.saas.auth.service.SysLoginService;
import com.saas.cloud.api.model.LoginUser;
import com.saas.common.core.domain.R;
import com.saas.common.core.domain.SysUser;
import com.saas.common.core.entity.AjaxResult;
import com.saas.common.security.service.SysPermissionService;
import com.saas.common.security.service.TokenService;
import com.saas.common.util.StringUtils;

/**
 * token 控制
 * 
 */
@RestController
public class TokenController {
	@Autowired
	private TokenService tokenService;

	@Autowired
	private SysLoginService sysLoginService;

	@Autowired
	private SysPermissionService permissionService;

	@PostMapping("login")
	public AjaxResult login(@RequestBody LoginBody form) {
		// 用户登录
		Map<String, Object> result = sysLoginService.login(form.getUsername(), form.getPassword());
		AjaxResult ajax = AjaxResult.success();
		ajax.putAll(result);
		return ajax;

	}

	@PostMapping("logout")
	public R<?> logout(HttpServletRequest request) {
		LoginUser loginUser = tokenService.getLoginUser(request);
		if (StringUtils.isNotNull(loginUser)) {
			String username = loginUser.getUsername();
			System.out.println("delete......");
			// 删除用户缓存记录
			tokenService.delLoginUser(loginUser.getToken());
			// 记录用户退出日志
			sysLoginService.logout(username);
		}
		return R.ok();
	}

	/**
	 * 获取用户信息
	 * 
	 * @return 用户信息
	 */
	@GetMapping("getInfo")
	public AjaxResult getInfo(HttpServletRequest request) {
		LoginUser loginUser = tokenService.getLoginUser(request);
		SysUser user = loginUser.getUser();
		// 角色集合
		List<String> roles = user.getRoleNameList();
		// 权限集合
		Set<String> permissions = permissionService.getUserPermission(user);
		AjaxResult ajax = AjaxResult.success();
		ajax.put("user", user);
		ajax.put("roles", roles);
		ajax.put("permissions", permissions);
		return ajax;
	}

	@PostMapping("refresh")
	public R<?> refresh(HttpServletRequest request) {
		LoginUser loginUser = tokenService.getLoginUser(request);
		if (StringUtils.isNotNull(loginUser)) {
			// 刷新令牌有效期
			tokenService.refreshToken(loginUser);
			return R.ok();
		}
		return R.ok();
	}
}
