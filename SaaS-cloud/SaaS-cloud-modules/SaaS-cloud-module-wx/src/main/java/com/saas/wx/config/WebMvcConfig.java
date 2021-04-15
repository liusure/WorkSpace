package com.saas.wx.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.saas.wx.interceptor.FrontCurrentUserInterceptor;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	@Autowired
	private FrontCurrentUserInterceptor currentUserInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
//		registry.addInterceptor(errorInterCeptor).addPathPatterns("/error");
		registry.addInterceptor(currentUserInterceptor).addPathPatterns("/front/**").order(1);

	}

}
