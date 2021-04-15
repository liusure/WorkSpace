package com.saas.admin.controller.system;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.saas.common.constant.Constants;
import com.saas.common.core.domain.SysUser;
import com.saas.common.core.entity.AjaxResult;
import com.saas.common.security.entity.login.LoginBody;
import com.saas.common.security.entity.login.LoginUser;
import com.saas.common.security.service.SysLoginService;
import com.saas.common.security.service.SysPermissionService;
import com.saas.common.security.service.TokenService;
import com.saas.common.util.ServletUtils;

/**
 * 登录验证
 * 
 */
@RestController
@RequestMapping("/auth")
public class SysLoginController {
	@Autowired
	private SysLoginService loginService;

	

	@Autowired
	private SysPermissionService permissionService;

	@Autowired
	private TokenService tokenService;

	/**
	 * 登录方法
	 * 
	 * @param loginBody 登录信息
	 * @return 结果
	 */
	@PostMapping("/login")
	public AjaxResult login(@RequestBody LoginBody loginBody) {
		AjaxResult ajax = AjaxResult.success();
		// 生成令牌
		String token = loginService.login(loginBody.getUsername(), loginBody.getPassword(), loginBody.getCode(),
				loginBody.getUuid());
		ajax.put(Constants.TOKEN, token);
		return ajax;
	}

	/**
	 * 获取用户信息
	 * 
	 * @return 用户信息
	 */
	@GetMapping("getInfo")
	public AjaxResult getInfo() {
		LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
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

	
}
