package com.saas.wx.feign;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.saas.common.util.ServletUtils;
import com.saas.common.util.StringUtils;
import com.saas.uc.constant.UcConstants;
import com.saas.uc.model.LoginUserInfo;
import com.saas.uc.utils.FrontCurrentUtils;

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
		// 传递信息
		LoginUserInfo userInfo = FrontCurrentUtils.getFrontLoginUserInfo();
		requestTemplate.header(UcConstants.HEADER_SAAS_ID, "" + userInfo.getSaasId());
		HttpServletRequest httpServletRequest = ServletUtils.getRequest();

		if (StringUtils.isNotNull(httpServletRequest)) {
			Map<String, String> headers = ServletUtils.getHeaders(httpServletRequest);
			// 传递用户信息请求头，防止丢失
			headers.forEach((key, value) -> {
				requestTemplate.header(key, value);
			});

		}
	}
}