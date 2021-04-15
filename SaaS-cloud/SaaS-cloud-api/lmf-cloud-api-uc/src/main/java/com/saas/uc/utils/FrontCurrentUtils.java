package com.saas.uc.utils;

import com.saas.common.util.ServletUtils;
import com.saas.common.util.ip.IpUtils;
import com.saas.uc.constant.UcConstants;
import com.saas.uc.model.CurrentUserInfo;
import com.saas.uc.model.LoginUserInfo;

public class FrontCurrentUtils {
	private static ThreadLocal<LoginUserInfo> loginUserInfoLocal = new ThreadLocal<>();
	private static ThreadLocal<CurrentUserInfo> currentUserInfoLocal = new ThreadLocal<>();

	public static LoginUserInfo getFrontLoginUserInfo() {
		LoginUserInfo loginUserInfo = loginUserInfoLocal.get();
		if (loginUserInfo == null) {
			loginUserInfo = initFrontLoginUserInfo();
		}
		return loginUserInfo;
	}

	private static LoginUserInfo initFrontLoginUserInfo() {
		LoginUserInfo userInfo = new LoginUserInfo();
		// SAASID
		userInfo.setSaasId(ServletUtils.getHeaderToLong(UcConstants.HEADER_SAAS_ID));

		userInfo.setFromUrl(ServletUtils.urlDecode(ServletUtils.getHeader(UcConstants.HEADER_FROM_PAGE))); // 来自哪个页面
		// 用户所属产品类别 0 weixin 1 weibo 2 alipay 3 weixingz 5 SNS_WXMINIAPP

		userInfo.setSnsType(ServletUtils.getHeaderToInt(UcConstants.HEADER_SNS_TYPE));

		// 用户设备
		userInfo.setDeviceId(ServletUtils.getHeader(UcConstants.HEADER_DEVICE_ID)); // 设备id
		userInfo.setCert(ServletUtils.getHeader(UcConstants.HEADER_CERT)); // 客户端凭证
		userInfo.setIdfa(ServletUtils.getHeader(UcConstants.HEADER_IDFA)); // IOS设备的 IDFA

		userInfo.setDeviceType(ServletUtils.getHeaderToInt(UcConstants.HEADER_DEVICE_TYPE, 0)); // 设备类型 0 H5 1 IOS 2
																								// Android 3 微信公众号

		userInfo.setDeviceVersion(ServletUtils.getHeader(UcConstants.HEADER_DEVICE_VERSION));// 设备 OS 版本
		userInfo.setUserAgent(ServletUtils.getHeader(UcConstants.HEADER_USER_AGENT)); // 浏览器信息

		userInfo.setLoginIp(IpUtils.getIpAddr(ServletUtils.getRequest())); // 登录ip

		// 邀请人
		userInfo.setInvite(ServletUtils.getHeaderToLong(UcConstants.HEADER_INVITE));

		// 场景编号

		userInfo.setSceneNo(ServletUtils.getHeaderToInt(UcConstants.HEADER_SCENE_NO));

		userInfo.setChannelCode(ServletUtils.getHeader(UcConstants.HEADER_CHANNEL_CODE));

		loginUserInfoLocal.set(userInfo);
		return userInfo;

	}

	public static Long getCurrentUserId() {
		return getCurrentUserInfo().getId();
	}

	public static CurrentUserInfo getCurrentUserInfo() {
		return currentUserInfoLocal.get();
	}

	public static void setCurrentUserInfo(CurrentUserInfo userInfo) {
		currentUserInfoLocal.set(userInfo);
	}

	/**
	 * 清除全部。
	 */
	public static void removeAll() {
		loginUserInfoLocal.remove();
		currentUserInfoLocal.remove();
	}

}
