package com.saas.common.security.feign;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.saas.common.constant.Constants;
import com.saas.common.util.ServletUtils;
import com.saas.common.util.StringUtils;
import com.saas.common.util.ip.IpUtils;

import feign.RequestInterceptor;
import feign.RequestTemplate;

/**
 * feign 请求拦截器
 * 
 */
@Component
public class FeignRequestInterceptor implements RequestInterceptor {
	@Override
	public void apply(RequestTemplate requestTemplate) {
		HttpServletRequest httpServletRequest = ServletUtils.getRequest();
		if (StringUtils.isNotNull(httpServletRequest)) {
			Map<String, String> headers = ServletUtils.getHeaders(httpServletRequest);
			// 传递用户信息请求头，防止丢失
			String userId = headers.get(Constants.DETAILS_USER_ID);
			if (StringUtils.isNotEmpty(userId)) {
				requestTemplate.header(Constants.DETAILS_USER_ID, userId);
			}
			String userName = headers.get(Constants.DETAILS_USERNAME);
			if (StringUtils.isNotEmpty(userName)) {
				requestTemplate.header(Constants.DETAILS_USERNAME, userName);
			}
			String authentication = headers.get(Constants.AUTHORIZATION_HEADER);
			if (StringUtils.isNotEmpty(authentication)) {
				requestTemplate.header(Constants.AUTHORIZATION_HEADER, authentication);
			}
            requestTemplate.header("X-Forwarded-For", IpUtils.getIpAddr(ServletUtils.getRequest()));
		}
	}
}