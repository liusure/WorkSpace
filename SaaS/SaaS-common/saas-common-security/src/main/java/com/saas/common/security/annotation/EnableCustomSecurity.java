package com.saas.common.security.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;

import com.saas.common.security.config.SecurityConfig;
import com.saas.common.security.filter.JwtAuthenticationTokenFilter;
import com.saas.common.security.handle.AuthenticationEntryPointImpl;
import com.saas.common.security.handle.LogoutSuccessHandlerImpl;
import com.saas.common.security.service.CurrentUserServiceImpl;
import com.saas.common.security.service.SysLoginService;
import com.saas.common.security.service.UserDetailsServiceImpl;
import com.saas.common.web.config.ResourcesConfig;
import com.saas.common.web.interceptor.impl.SameUrlDataInterceptor;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
// 表示通过aop框架暴露该代理对象,AopContext能够访问
@EnableAspectJAutoProxy(exposeProxy = true)
// 开启线程异步执行
@EnableAsync
// 自动加载类
@Import({ SecurityConfig.class, JwtAuthenticationTokenFilter.class, AuthenticationEntryPointImpl.class,
		LogoutSuccessHandlerImpl.class, SysLoginService.class, UserDetailsServiceImpl.class, ResourcesConfig.class,
		SameUrlDataInterceptor.class, CurrentUserServiceImpl.class })
public @interface EnableCustomSecurity {

}
