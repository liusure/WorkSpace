package com.saas.uc.model;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.saas.common.util.StringUtils;

public class LoginUserInfo implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;
	/**
	 * 产品
	 */

	private Long saasId; // 使用的产品ID
	private Integer snsType; // 用户所属产品类别 0 weixin 1 weibo 2 alipay 3 weixingz 5 SNS_WXMINIAPP
	private String openid; // 第三方绑定的openid信息
	private String unionId; //
	private String fromUrl; // 来自哪个页面

	/**
	 * 用户设备
	 */

	private String deviceId; // 设备id

	private String cert; // 客户端凭证

	private String idfa; // IOS设备的 IDFA
	private int deviceType; // 设备类型 0 H5 1 IOS 2 Android 3 微信公众号
	private String deviceVersion;// 设备 OS 版本
	private String userAgent; // 浏览器信息

	private String loginIp; // 登录ip
	private Long invite; // 邀请人id
	private Integer sceneNo; // 场景编号
	
	private String channelCode;//渠道编码

	public boolean isValidRequest() {
		return saasId != null && saasId > 0 && StringUtils.isNotEmpty(cert) && StringUtils.isNotEmpty(deviceId);
	}

	public Long getSaasId() {
		return saasId;
	}

	public void setSaasId(Long saasId) {
		this.saasId = saasId;
	}

	public Integer getSnsType() {
		return snsType;
	}

	public void setSnsType(Integer snsType) {
		this.snsType = snsType;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getUnionId() {
		return unionId;
	}

	public void setUnionId(String unionId) {
		this.unionId = unionId;
	}

	public String getFromUrl() {
		return fromUrl;
	}

	public void setFromUrl(String fromUrl) {
		this.fromUrl = fromUrl;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getCert() {
		return cert;
	}

	public void setCert(String cert) {
		this.cert = cert;
	}

	public String getIdfa() {
		return idfa;
	}

	public void setIdfa(String idfa) {
		this.idfa = idfa;
	}

	public int getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(int deviceType) {
		this.deviceType = deviceType;
	}

	public String getDeviceVersion() {
		return deviceVersion;
	}

	public void setDeviceVersion(String deviceVersion) {
		this.deviceVersion = deviceVersion;
	}

	public String getUserAgent() {
		return userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	public String getLoginIp() {
		return loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

	public Long getInvite() {
		return invite;
	}

	public void setInvite(Long invite) {
		this.invite = invite;
	}

	public Integer getSceneNo() {
		return sceneNo;
	}

	public void setSceneNo(Integer sceneNo) {
		this.sceneNo = sceneNo;
	}
	
	public String getChannelCode() {
		return channelCode;
	}

	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
