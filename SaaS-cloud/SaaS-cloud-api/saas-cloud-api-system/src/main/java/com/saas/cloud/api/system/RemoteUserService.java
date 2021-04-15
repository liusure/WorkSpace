package com.saas.cloud.api.system;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.saas.cloud.api.constant.ServiceNameConstants;
import com.saas.cloud.api.factory.RemoteUserFallbackFactory;
import com.saas.cloud.api.model.LoginUser;
import com.saas.common.core.domain.R;

/**
 * 用户服务
 * 
 */
@FeignClient(contextId = "remoteUserService", value = ServiceNameConstants.SYSTEM_SERVICE, fallbackFactory = RemoteUserFallbackFactory.class)
public interface RemoteUserService {
	/**
	 * 通过用户名查询用户信息
	 *
	 * @param username 用户名
	 * @return 结果
	 */
	@GetMapping(value = "/user/info/{username}")
	public R<LoginUser> getUserInfo(@PathVariable("username") String username);

	@PostMapping(value = "/user/login")
	public R<Map<String, Object>> login(@RequestParam(name = "username") String username,
			@RequestParam(name = "password") String password);
}
