package com.saas.common.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.saas.common.core.domain.SysUser;
import com.saas.common.userservice.ICurrentUserService;

@Component
public class CurrentUserServiceImpl implements ICurrentUserService {

    @Autowired
    private TokenService tokenService;

    @Override
    public SysUser getCurrentUser() {
//		return tokenService.getLoginUser().getUser();
        return new SysUser(1l);
    }

}
