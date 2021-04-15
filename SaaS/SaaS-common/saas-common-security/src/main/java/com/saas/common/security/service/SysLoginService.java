package com.saas.common.security.service;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.saas.common.constant.Constants;
import com.saas.common.core.redis.RedisCache;
import com.saas.common.exception.CustomException;
import com.saas.common.exception.user.CaptchaException;
import com.saas.common.exception.user.CaptchaExpireException;
import com.saas.common.exception.user.UserPasswordNotMatchException;
import com.saas.common.security.entity.login.LoginUser;
import com.saas.common.util.MessageUtils;
import com.saas.common.web.util.SysLogininforUtils;

/**
 * 登录校验方法
 * 
 */
@Component
public class SysLoginService
{
    @Autowired
    private TokenService tokenService;

    @Resource
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisCache redisCache;

    /**
     * 登录验证
     * 
     * @param username 用户名
     * @param password 密码
     * @param code 验证码
     * @param uuid 唯一标识
     * @return 结果
     */
    public String login(String username, String password, String code, String uuid)
    {
        String verifyKey = Constants.CAPTCHA_CODE_KEY + uuid;
        String captcha = redisCache.getCacheObject(verifyKey);
        redisCache.deleteObject(verifyKey);
        if (captcha == null)
        {
        	SysLogininforUtils.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.jcaptcha.expire"));
            throw new CaptchaExpireException();
        }
        if (!code.equalsIgnoreCase(captcha))
        {
        	SysLogininforUtils.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.jcaptcha.error"));
            throw new CaptchaException();
        }
        // 用户验证
        Authentication authentication = null;
        try
        {
            // 该方法会去调用UserDetailsServiceImpl.loadUserByUsername
            authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(username, password));
        }
        catch (Exception e)
        {
        	e.printStackTrace();
            if (e instanceof BadCredentialsException)
            {
            	SysLogininforUtils.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.password.not.match"));
                throw new UserPasswordNotMatchException();
            }
            else
            {
            	SysLogininforUtils.recordLogininfor(username, Constants.LOGIN_FAIL, e.getMessage());
                throw new CustomException(e.getMessage());
            }
        }
        SysLogininforUtils.recordLogininfor(username, Constants.LOGIN_SUCCESS, MessageUtils.message("user.login.success"));
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        // 生成token
        return tokenService.createToken(loginUser);
    }
}
