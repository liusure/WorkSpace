package com.saas.admin.controller.system;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.saas.admin.service.SysUserService;
import com.saas.common.config.LmfConfig;
import com.saas.common.core.controller.BaseController;
import com.saas.common.core.domain.SysUser;
import com.saas.common.core.entity.AjaxResult;
import com.saas.common.enums.BusinessType;
import com.saas.common.security.entity.login.LoginUser;
import com.saas.common.security.service.TokenService;
import com.saas.common.security.utils.SecurityUtils;
import com.saas.common.util.ServletUtils;
import com.saas.common.util.file.FileUploadUtils;
import com.saas.log.annotation.Log;

/**
 * 个人信息 业务处理
 * 
 */
@RestController
@RequestMapping("/system/user/profile")
public class SysProfileController extends BaseController {
	@Autowired
	private SysUserService userService;

	@Autowired
	private TokenService tokenService;

	/**
	 * 个人信息
	 */
	@GetMapping
	public AjaxResult profile() {
		LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
		SysUser user = loginUser.getUser();
		AjaxResult ajax = AjaxResult.success(user);
		ajax.put("roleGroup", user.getRoleShowNames());
		ajax.put("postGroup", user.getRoleNames());
		return ajax;
	}

	/**
	 * 修改用户
	 */
	@Log(title = "个人信息", businessType = BusinessType.UPDATE)
	@PutMapping
	public AjaxResult updateProfile(@RequestBody SysUser user) {
		SysUser dbUser = userService.getById(user.getId());
		user.setPassword(dbUser.getPassword());
		user.setRoleList(dbUser.getRoleList());
		
		userService.save(user);
		LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
		// 更新缓存用户信息
		loginUser.setUser(user);
		tokenService.setLoginUser(loginUser);
		return AjaxResult.success();

	}

	/**
	 * 重置密码
	 */
	@Log(title = "个人信息", businessType = BusinessType.UPDATE)
	@PutMapping("/updatePwd")
	public AjaxResult updatePwd(String oldPassword, String newPassword) {
		LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());				
		String password = loginUser.getPassword();
		if (!SecurityUtils.matchesPassword(oldPassword, password)) {
			return AjaxResult.error("修改密码失败，旧密码错误");
		}
		if (SecurityUtils.matchesPassword(newPassword, password)) {
			return AjaxResult.error("新密码不能与旧密码相同");
		}
		loginUser.getUser().setPlainPassword(newPassword);		
		userService.save(loginUser.getUser());
		tokenService.setLoginUser(loginUser);		
		return AjaxResult.success();

	}

	/**
	 * 头像上传
	 */
	@Log(title = "用户头像", businessType = BusinessType.UPDATE)
	@PostMapping("/avatar")
	public AjaxResult avatar(@RequestParam("avatarfile") MultipartFile file) throws IOException {
		if (!file.isEmpty()) {
			LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
			String avatar = FileUploadUtils.upload(LmfConfig.getAvatarPath(), file);
			loginUser.getUser().setAvatar(avatar);
			userService.save(loginUser.getUser());

			AjaxResult ajax = AjaxResult.success();
			ajax.put("imgUrl", avatar);
			// 更新缓存用户头像
			loginUser.getUser().setAvatar(avatar);
			tokenService.setLoginUser(loginUser);
			return ajax;
		}
		return AjaxResult.error("上传图片异常，请联系管理员");
	}
}
