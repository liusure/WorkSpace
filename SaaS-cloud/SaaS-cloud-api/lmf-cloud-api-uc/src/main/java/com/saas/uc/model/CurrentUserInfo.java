package com.saas.uc.model;

import java.io.Serializable;
import java.util.Date;

import com.saas.uc.domain.DeviceLinkAccount;
import com.saas.uc.domain.UserSnsAccount;

public class CurrentUserInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Boolean subscribe;
	private String openId;

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

	private Long saasId; //

	private String mobile; // 手机号

	private String email; // 邮箱

	private Date birthday; // 生日

	private String userDesc; // 描述

	private String region; // 地区

	private Integer sex; // 性别 0未知 1男 2女

	private Integer statusFlag; // 是否有效

	private Long invite; // 邀请人id

	private Integer userType; // 用户类型 ：内部用户 、外部导入用户

	private boolean bindFlag; // 是否绑定账号了，默认自动帮助用户生成访客账号

	private Date loginTime; // 登录时间
	
	private int snsType ;

	private DeviceLinkAccount userDevice; // 用户设备信息

	private UserSnsAccount userSns; // 用户产品

	private LoginUserInfo loginUserInfo; // 页面参数

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getSaasId() {
		return saasId;
	}

	public void setSaasId(Long saasId) {
		this.saasId = saasId;
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

	public Integer getStatusFlag() {
		return statusFlag;
	}

	public void setStatusFlag(Integer statusFlag) {
		this.statusFlag = statusFlag;
	}

	public Long getInvite() {
		return invite;
	}

	public void setInvite(Long invite) {
		this.invite = invite;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public boolean isBindFlag() {
		return bindFlag;
	}

	public void setBindFlag(boolean bindFlag) {
		this.bindFlag = bindFlag;
	}

	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	public DeviceLinkAccount getUserDevice() {
		return userDevice;
	}

	public void setUserDevice(DeviceLinkAccount userDevice) {
		this.userDevice = userDevice;
	}

	public UserSnsAccount getUserSns() {
		return userSns;
	}

	public void setUserSns(UserSnsAccount userSns) {
		this.userSns = userSns;
	}

	public LoginUserInfo getLoginUserInfo() {
		return loginUserInfo;
	}

	public void setLoginUserInfo(LoginUserInfo loginUserInfo) {
		this.loginUserInfo = loginUserInfo;
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

	public int getSnsType() {
		return snsType;
	}

	public void setSnsType(int snsType) {
		this.snsType = snsType;
	}

}
