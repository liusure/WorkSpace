package com.saas.cloud.api.factory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import com.saas.cloud.api.uc.RemoteUserCenterService;
import com.saas.common.core.domain.R;
import com.saas.uc.domain.DeviceLinkAccount;
import com.saas.uc.model.UserInfo;

import feign.hystrix.FallbackFactory;

@Component
public class RemoteUserCenterFallbackFactory implements FallbackFactory<RemoteUserCenterService> {
	private static final Logger log = LoggerFactory.getLogger(RemoteUserCenterFallbackFactory.class);

	@Override
	public RemoteUserCenterService create(Throwable throwable) {
		throwable.printStackTrace();
		log.error("用户服务调用失败:{}", throwable.getMessage());
		return new RemoteUserCenterService() {
			@Override
			public R<DeviceLinkAccount> login(@RequestBody UserInfo userInfo) {
				return R.fail("登陆失败:" + throwable.getMessage());
			}

			@Override
			public R<DeviceLinkAccount> updateInfo(UserInfo userInfo) {
				return R.fail("更新信息失败:" + throwable.getMessage());
			}

		};
	}
}
