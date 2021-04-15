package com.saas.uc.domain;

import java.util.Date;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.google.common.collect.ImmutableMap;
import com.saas.common.core.domain.SaasEntity;

@Entity
@Table(name = "ft_user_device")
public class DeviceLinkAccount extends SaasEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public final static Map<Integer, String> DEVICE_TYPE_MAP = ImmutableMap.of(0, "H5", 1, "ISO", 2, "ANDROID", 3,
			"微信公众号", 4, "微信小程序");
	public static final int DEVICE_TYPE_H5 = 0;
	public static final int DEVICE_TYPE_ISO = 1;
	public static final int DEVICE_TYPE_ANDROID = 2;
	public static final int DEVICE_TYPE_WEIXIN = 3;
	public static final int DEVICE_TYPE_MINIAPP = 4;

	/**
	 * 用户id
	 */
	private Long userAccountId;

	/**
	 * 设备id
	 */
	private String deviceId;

	/**
	 * 客户端凭证
	 */
	private String certificates;

	/**
	 * IOS设备的 IDFA
	 */
	private String idfa;

	/**
	 * 极光push使用的ID，使用deviceId加密
	 */
	private String pushId;

	/**
	 * 设备类型 0 H5 1 IOS 2 Android 3 微信公众号
	 */
	private Integer deviceType = 0;

	private String deviceVersion;// 设备 OS 版本

	private String userAgent; // 浏览器信息

	/**
	 * 使用的时间
	 */
	private Date loginTime;
	private Date lastLoginTime;

	/**
	 * 登录ip
	 */
	private String loginIp;
	private String lastLoginIp;

	public Long getUserAccountId() {
		return userAccountId;
	}

	public void setUserAccountId(Long userAccountId) {
		this.userAccountId = userAccountId;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getCertificates() {
		return certificates;
	}

	public void setCertificates(String certificates) {
		this.certificates = certificates;
	}

	public String getIdfa() {
		return idfa;
	}

	public void setIdfa(String idfa) {
		this.idfa = idfa;
	}

	public String getPushId() {
		return pushId;
	}

	public void setPushId(String pushId) {
		this.pushId = pushId;
	}

	public Integer getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(Integer deviceType) {
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

	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getLoginIp() {
		return loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

	public String getLastLoginIp() {
		return lastLoginIp;
	}

	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}

}
