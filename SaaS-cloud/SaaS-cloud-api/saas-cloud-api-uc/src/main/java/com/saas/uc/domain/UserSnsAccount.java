package com.saas.uc.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.saas.common.core.domain.SaasEntity;

@Entity
@Table(name = "ft_user_sns")
public class UserSnsAccount extends SaasEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int SNS_WEIBO = 1; // 微博
	public static final int SNS_ALIPAY = 2; // 阿里
	public static final int SNS_WEIXINGZ = 3;// 微信公众号
	public static final int SNS_WXMINIAPP = 4;// 微信小程序
	public static final int SNS_TTMINIAPP = 7;// 头条小程序

	/**
	 * 前端用户id
	 */
	private Long userAccountId;

	/**
	 * 用户所属产品类别 0 weixin 1 weibo 2 alipay 3 weixingz 5 SNS_WXMINIAPP
	 */
	private Integer snsType;

	/**
	 * 第三方绑定的openid信息
	 */
	private String openid;

	private String unionId;

	/**
	 * 是否通过第三方认证并获取了用户名和头像
	 */
	private boolean authFlag = false;

	/**
	 * 是否有效
	 */
	private Integer statusFlag = 0;

	/**
	 * 头像
	 */
	private String iconUrl;

	/**
	 * 产品名称
	 */
	private String snsName;

	/**
	 * 是否关注
	 */
	private boolean subscribe = false;

	private Long subscribeTime;

	public Long getUserAccountId() {
		return userAccountId;
	}

	public void setUserAccountId(Long userAccountId) {
		this.userAccountId = userAccountId;
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

	public boolean isAuthFlag() {
		return authFlag;
	}

	public void setAuthFlag(boolean authFlag) {
		this.authFlag = authFlag;
	}

	public Integer getStatusFlag() {
		return statusFlag;
	}

	public void setStatusFlag(Integer statusFlag) {
		this.statusFlag = statusFlag;
	}

	public String getIconUrl() {
		return iconUrl;
	}

	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}

	public String getSnsName() {
		return snsName;
	}

	public void setSnsName(String snsName) {
		this.snsName = snsName;
	}

	public Long getSubscribeTime() {
		return subscribeTime;
	}

	public void setSubscribeTime(Long subscribeTime) {
		this.subscribeTime = subscribeTime;
	}

	public boolean isSubscribe() {
		return subscribe;
	}

	public void setSubscribe(boolean subscribe) {
		this.subscribe = subscribe;
	}

	public static String getDeviceId(String openId, int snsType) {
		switch (snsType) {
		case SNS_WEIXINGZ:
			return "W" + openId;
		case SNS_WXMINIAPP:
			return "A" + openId;
		default:
			return "M" + openId;
		}
	}
}
