package com.saas.wx.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 
 * 微信第三方平台账号信息
 * 
 * @author bruce
 *
 */
@Component
@ConfigurationProperties(prefix = "wechat")
public class WxConfig {

	private String componentAppId;
	private String componentAppSecret;
	private String componentToken;
	private String componentAesKey;

	//	小程序服务器域名,如www.sina.com.cn
	private String serverHost;
	
	//授权回调页面
	private String redirectUrl;

	// 在公众号第三方平台创建审核通过后，微信服务器会向其“授权事件接收URL”每隔10分钟定时推送component_verify_ticket
	// private String componentVerifyTicket;
	public String getComponentAppId() {
		return componentAppId;
	}

	public void setComponentAppId(String componentAppId) {
		this.componentAppId = componentAppId;
	}

	public String getComponentAppSecret() {
		return componentAppSecret;
	}

	public void setComponentAppSecret(String componentAppSecret) {
		this.componentAppSecret = componentAppSecret;
	}

	public String getComponentToken() {
		return componentToken;
	}

	public void setComponentToken(String componentToken) {
		this.componentToken = componentToken;
	}

	public String getComponentAesKey() {
		return componentAesKey;
	}

	public void setComponentAesKey(String componentAesKey) {
		this.componentAesKey = componentAesKey;
	}

	public String getServerHost() {
		return serverHost;
	}

	public void setServerHost(String serverHost) {
		this.serverHost = serverHost;
	}

	public String getRedirectUrl() {
		return redirectUrl;
	}

	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}

}
