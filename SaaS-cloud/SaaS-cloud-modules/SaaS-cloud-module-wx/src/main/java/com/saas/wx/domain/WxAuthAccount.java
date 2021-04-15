package com.saas.wx.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.saas.common.core.domain.SaasEntity;

/**
 * 微信授权账号信息对象 wx_auth_account
 * 
 * @author bruce
 * @date 2021-01-21
 */
@Entity
@Table(name = "wx_auth_account")
public class WxAuthAccount extends SaasEntity {
	private static final long serialVersionUID = 1L;
	
	public static final int SERVICE_TYPE_MINIAPP = 0;
	public static final int SERVICE_TYPE_MP = 2;

	/** 其他信息 */
	private String otherInfo;

	/** 名称 */
	private String userName;

	/** 签名 */
	private String signature;

	/** 二维码 */
	private String qrcodeUrl;

	/** 公司名称 */
	private String principalName;

	/** 昵称 */
	private String nickName;

	/** 头像 */
	private String headImg;

	/** 别名 */
	private String alias;

	/** Token */
	private String authorizerFreshToken;

	/** APPID */
	private String authorizerAppId;

	/** 类型 */
	private int serviceType;

	public void setOtherInfo(String otherInfo) {
		this.otherInfo = otherInfo;
	}

	public String getOtherInfo() {
		return otherInfo;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserName() {
		return userName;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getSignature() {
		return signature;
	}

	public void setQrcodeUrl(String qrcodeUrl) {
		this.qrcodeUrl = qrcodeUrl;
	}

	public String getQrcodeUrl() {
		return qrcodeUrl;
	}

	public void setPrincipalName(String principalName) {
		this.principalName = principalName;
	}

	public String getPrincipalName() {
		return principalName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getNickName() {
		return nickName;
	}

	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}

	public String getHeadImg() {
		return headImg;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getAlias() {
		return alias;
	}

	public void setAuthorizerFreshToken(String authorizerFreshToken) {
		this.authorizerFreshToken = authorizerFreshToken;
	}

	public String getAuthorizerFreshToken() {
		return authorizerFreshToken;
	}

	public void setAuthorizerAppId(String authorizerAppId) {
		this.authorizerAppId = authorizerAppId;
	}

	public String getAuthorizerAppId() {
		return authorizerAppId;
	}

	public void setServiceType(Integer serviceType) {
		this.serviceType = serviceType;
	}

	public Integer getServiceType() {
		return serviceType;
	}
}
