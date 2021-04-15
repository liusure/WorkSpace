package com.saas.wx.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.saas.common.core.domain.SysUser;
import com.saas.common.security.service.CurrentUserServiceImpl;
import com.saas.common.userservice.ICurrentUserService;
import com.saas.wx.utils.CurrentUserUtils;

@Configuration
public class ICurrentUserServiceConfig {

	@Bean
	@Primary
	public ICurrentUserService configCurrentUserService(CurrentUserServiceImpl impl) {
		return new ICurrentUserService() {

			@Override
			public SysUser getCurrentUser() {
				if (CurrentUserUtils.getSysUser() != null) {
					return CurrentUserUtils.getSysUser();
				}
				return impl.getCurrentUser();
			}

		};
	}
}
