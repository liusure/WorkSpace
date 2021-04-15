package com.saas.cloud.api.uc;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.saas.cloud.api.factory.RemoteUserCenterFallbackFactory;
import com.saas.common.core.domain.R;
import com.saas.uc.domain.DeviceLinkAccount;
import com.saas.uc.model.UserInfo;

/**
 * 前端用户服务
 * 
 * @author bruce
 *
 */
@FeignClient(contextId = "remoteUserCenterService", value = ServiceNameConstants.USER_CENTER_SERVICE, fallbackFactory = RemoteUserCenterFallbackFactory.class)
public interface RemoteUserCenterService {

	@PostMapping(value = "/front/user/login")
	public R<DeviceLinkAccount> login(@RequestBody UserInfo userInfo);
	
	@PostMapping(value = "/front/user/updateinfo")
	public R<DeviceLinkAccount> updateInfo(@RequestBody UserInfo userInfo);
}
