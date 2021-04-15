package com.saas.wx.handler;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.saas.cloud.api.uc.RemoteUserCenterService;
import com.saas.uc.domain.UserSnsAccount;
import com.saas.uc.model.UserInfo;
import com.saas.wx.utils.CurrentUserUtils;

import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;

@Component
public class UnsubscribeHandler extends AbstractHandler {

	@Autowired
	private RemoteUserCenterService remoteUserCenterService;

	@Override
	public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context, WxMpService wxMpService,
			WxSessionManager sessionManager) {
		String openId = wxMessage.getFromUser();
		this.logger.info("取消关注用户 OPENID: " + openId);
		// 更新数据库为取消关注状态
		UserInfo userInfo = new UserInfo();
		userInfo.setSnsType(UserSnsAccount.SNS_WEIXINGZ);
		userInfo.setSaasId(CurrentUserUtils.getSaasId());
		userInfo.setOpenId(openId);
		userInfo.setSubscribe(false);
		remoteUserCenterService.updateInfo(userInfo);
		return null;
	}

}
