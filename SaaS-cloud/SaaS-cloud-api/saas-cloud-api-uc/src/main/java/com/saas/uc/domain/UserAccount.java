package com.saas.uc.domain;

import java.util.Date;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.google.common.collect.ImmutableMap;
import com.saas.common.core.domain.SaasEntity;

@Entity
@Table(name = "ft_user_account")
public class UserAccount extends SaasEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 用户类型
	 */
	public static final Integer USER_TYPE_EXTERNAL = 1;// 外部用户
	public static final Integer USER_TYPE_PRIVATE = 0;// 内部用户

	public static final Map<Integer, String> VIP_RANK = new ImmutableMap.Builder<Integer, String>().put(0, "普通用户")
			.put(1, "VIP用户").put(2, "明星用户").put(3, "顶级用户").build();
	/**
	 * 会员等级
	 */
	public static final int GENERAL_USER = 0; // 普通用户
	public static final int VIP_USER = 1; // VIP用户
	public static final int STAR_USER = 2; // 明星用户
	public static final int TOP_USER = 3; // 顶级用户

	///////////// 用户基本信息 /////////////

	private String nickname; // 姓名
	private String realName;// 真实姓名
	private Integer isRealName = 0; // 是否实名 0未实名 1已实名
	private String headImgUrl; // 头像 icon url
	private Date realNameTime;// 实名时间

	private Boolean subscribe;
	private Long subscribeTime;
	private String language;
	private String city;
	private String province;
	private String country;

	private String mobile; // 手机号

	private String wxNumber;// 微信号

	private String email; // 邮箱

	private Date birthday; // 生日

	private String userDesc; // 描述

	private String region; // 地区

	private int sex = 0; // 性别 0未知 1男 2女

	///////////// 用户账号信息 /////////////

	

	// ---------------------------------
	private Integer userType; // 用户类型 ：内部用户 、外部导入用户

	private boolean bindFlag = false; // 是否绑定账号了，默认自动帮助用户生成访客账号

	private Long registerDeviceAccountId;// 用户注册设备账号id

	private String registerDeviceId; // 用户注册设备账号id

	///////////// 上次 信息 /////////////

	private Date lastLoginTime; // 上次登录时间

	private Integer lastSnsType; // 上次登录 产品

	private Long lastSnsAccountId; // 上次 登录 产品用户id

	private Integer lastDeviceType; // 上次登录设备类型

	private Long lastDeviceAccountId;// 最后登录使用的设备账号ID

	private String lastDeviceId; // 上次登录设备id

	///////////// 其他用户信息 /////////////

	private String unionId;// 关注微信公众账号的Union ID

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public Integer getIsRealName() {
		return isRealName;
	}

	public void setIsRealName(Integer isRealName) {
		this.isRealName = isRealName;
	}

	public Date getRealNameTime() {
		return realNameTime;
	}

	public void setRealNameTime(Date realNameTime) {
		this.realNameTime = realNameTime;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getWxNumber() {
		return wxNumber;
	}

	public void setWxNumber(String wxNumber) {
		this.wxNumber = wxNumber;
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

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getHeadImgUrl() {
		return headImgUrl;
	}

	public void setHeadImgUrl(String headImgUrl) {
		this.headImgUrl = headImgUrl;
	}

	public Boolean getSubscribe() {
		return subscribe;
	}

	public void setSubscribe(Boolean subscribe) {
		this.subscribe = subscribe;
	}

	public Long getSubscribeTime() {
		return subscribeTime;
	}

	public void setSubscribeTime(Long subscribeTime) {
		this.subscribeTime = subscribeTime;
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

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
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

	public Long getRegisterDeviceAccountId() {
		return registerDeviceAccountId;
	}

	public void setRegisterDeviceAccountId(Long registerDeviceAccountId) {
		this.registerDeviceAccountId = registerDeviceAccountId;
	}

	public String getRegisterDeviceId() {
		return registerDeviceId;
	}

	public void setRegisterDeviceId(String registerDeviceId) {
		this.registerDeviceId = registerDeviceId;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public Integer getLastSnsType() {
		return lastSnsType;
	}

	public void setLastSnsType(Integer lastSnsType) {
		this.lastSnsType = lastSnsType;
	}

	public Long getLastSnsAccountId() {
		return lastSnsAccountId;
	}

	public void setLastSnsAccountId(Long lastSnsAccountId) {
		this.lastSnsAccountId = lastSnsAccountId;
	}

	public Integer getLastDeviceType() {
		return lastDeviceType;
	}

	public void setLastDeviceType(Integer lastDeviceType) {
		this.lastDeviceType = lastDeviceType;
	}

	public Long getLastDeviceAccountId() {
		return lastDeviceAccountId;
	}

	public void setLastDeviceAccountId(Long lastDeviceAccountId) {
		this.lastDeviceAccountId = lastDeviceAccountId;
	}

	public String getLastDeviceId() {
		return lastDeviceId;
	}

	public void setLastDeviceId(String lastDeviceId) {
		this.lastDeviceId = lastDeviceId;
	}

	public String getUnionId() {
		return unionId;
	}

	public void setUnionId(String unionId) {
		this.unionId = unionId;
	}

}
