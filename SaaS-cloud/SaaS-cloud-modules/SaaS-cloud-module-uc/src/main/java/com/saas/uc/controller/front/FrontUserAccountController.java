package com.saas.uc.controller.front;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.saas.common.core.domain.R;
import com.saas.uc.domain.DeviceLinkAccount;
import com.saas.uc.model.UserInfo;
import com.saas.uc.service.UserAccountService;

@RestController
@RequestMapping("/front")
public class FrontUserAccountController {

	@Autowired
	private UserAccountService userAccountService;

	@PostMapping(value = "/user/login")
	public R<DeviceLinkAccount> login(@RequestBody UserInfo userInfo) {
		try {

			DeviceLinkAccount dv = userAccountService.bindUser(userInfo);
			return R.ok(dv);

		} catch (Exception e) {
			e.printStackTrace();
			return R.fail(e.getMessage());
		} 

	}

	@PostMapping(value = "/user/updateinfo")
	public R<DeviceLinkAccount> updateInfo(@RequestBody UserInfo userInfo) {
		try {
			return R.ok(userAccountService.updateUser(userInfo));
		} catch (Exception e) {
			e.printStackTrace();
			return R.fail(e.getMessage());
		}

	}
}
