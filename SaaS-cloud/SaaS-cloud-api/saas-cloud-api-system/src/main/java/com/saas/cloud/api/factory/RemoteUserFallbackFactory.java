package com.saas.cloud.api.factory;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.saas.cloud.api.model.LoginUser;
import com.saas.cloud.api.system.RemoteUserService;
import com.saas.common.core.domain.R;

import feign.hystrix.FallbackFactory;

/**
 * 用户服务降级处理
 * 
 */
@Component
public class RemoteUserFallbackFactory implements FallbackFactory<RemoteUserService> {
	private static final Logger log = LoggerFactory.getLogger(RemoteUserFallbackFactory.class);

	@Override
	public RemoteUserService create(Throwable throwable) {
		log.error("用户服务调用失败:{}", throwable.getMessage());
		return new RemoteUserService() {
			@Override
			public R<LoginUser> getUserInfo(String username) {
				return R.fail("获取用户失败:" + throwable.getMessage());
			}

			@Override
			public R<Map<String, Object>>  login(String username, String password) {
				return R.fail("登陆失败:" + throwable.getMessage());
			}

		};
	}
}
