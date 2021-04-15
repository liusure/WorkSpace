package com.saas.uc.model;

import java.io.Serializable;
import java.util.Date;

public class UserInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long saasId; // 使用的产品ID
	private Integer snsType; // 用户所属产品类别 0 weixin 1 weibo 2 alipay 3 weixingz 5 SNS_WXMINIAPP
	private String openId; // 第三方绑定的openid信息

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

	private String channelCode;// 渠道编码

	private Boolean subscribe;
	

	private String language;
	private String city;
	private String province;
	private String country;
	private String headImgUrl;
	private Long subscribeTime;
	/**
	 * https://mp.weixin.qq.com/cgi-bin/announce?action=getannouncement&announce_id=11513156443eZYea&version=&lang=zh_CN
	 * 
	 * <pre>
	 * 只有在将公众号绑定到微信开放平台帐号后，才会出现该字段。
	 * 另外，在用户未关注公众号时，将不返回用户unionID信息。
	 * 已关注的用户，开发者可使用“获取用户基本信息接口”获取unionID；
	 * 未关注用户，开发者可使用“微信授权登录接口”并将scope参数设置为snsapi_userinfo，获取用户unionID
	 * </pre>
	 */
	private String unionId;

	private String nickname; // 姓名

	private Long id; // 用户id

	private String mobile; // 手机号

	private String email; // 邮箱

	private Date birthday; // 生日

	private String userDesc; // 描述

	private String region; // 地区

	private Integer sex; // 性别 0未知 1男 2女

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

	public Boolean getSubscribe() {
		return subscribe;
	}

	public void setSubscribe(Boolean subscribe) {
		this.subscribe = subscribe;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getHeadImgUrl() {
		return headImgUrl;
	}

	public void setHeadImgUrl(String headImgUrl) {
		this.headImgUrl = headImgUrl;
	}

	public Long getSubscribeTime() {
		return subscribeTime;
	}

	public void setSubscribeTime(Long subscribeTime) {
		this.subscribeTime = subscribeTime;
	}

	public String getUnionId() {
		return unionId;
	}

	public void setUnionId(String unionId) {
		this.unionId = unionId;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getUserDesc() {
		return userDesc;
	}

	public void setUserDesc(String userDesc) {
		this.userDesc = userDesc;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}
	
	

}
