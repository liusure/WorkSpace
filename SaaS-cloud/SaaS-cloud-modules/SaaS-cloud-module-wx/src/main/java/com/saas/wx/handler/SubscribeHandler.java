package com.saas.wx.handler;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.saas.cloud.api.uc.RemoteUserCenterService;
import com.saas.common.core.domain.R;
import com.saas.common.util.BeanUtils;
import com.saas.uc.domain.DeviceLinkAccount;
import com.saas.uc.domain.UserSnsAccount;
import com.saas.uc.model.UserInfo;
import com.saas.wx.domain.WxQrCode;
import com.saas.wx.service.WxQrCodeService;
import com.saas.wx.service.WxReplyService;
import com.saas.wx.utils.CurrentUserUtils;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import me.chanjar.weixin.open.api.WxOpenService;

@Component
public class SubscribeHandler extends AbstractHandler {

	@Autowired
	private WxReplyService wxReplyService;

	@Autowired
	private WxQrCodeService wxQrCodeService;

	@Autowired
	private WxOpenService wxOpenService;

	@Autowired
	private RemoteUserCenterService remoteUserCenterService;

	@Override
	public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context, WxMpService wxMpService,
			WxSessionManager sessionManager) throws WxErrorException {

		this.logger.info("新关注用户 OPENID: " + wxMessage.getFromUser());

		String appId = wxMpService.getWxMpConfigStorage().getAppId();
		String openId = wxMessage.getFromUser();

		Integer sceneId = null;
		if (StringUtils.isNotEmpty(wxMessage.getEventKey()) && wxMessage.getEventKey().startsWith("qrscene_")) {
			sceneId = Integer.parseInt(wxMessage.getEventKey().substring(8));
		}
		WxQrCode qrCode = null;
		String channelCode = null;
		if (sceneId != null) {
			qrCode = wxQrCodeService.getBySceneId(sceneId.longValue());
			if (qrCode != null)
				channelCode = qrCode.getChannelCode();
		}
		// 获取微信用户基本信息
		WxMpUser userWxInfo = wxOpenService.getWxOpenComponentService().getWxMpServiceByAppid(appId).getUserService()
				.userInfo(openId);

		// 注册用户添加关注用户到本地
		UserInfo userInfo = new UserInfo();
		BeanUtils.copyProperties(userWxInfo, userInfo, true);
		userInfo.setSnsType(UserSnsAccount.SNS_WEIXINGZ);
		userInfo.setSaasId(CurrentUserUtils.getSaasId());
		userInfo.setChannelCode(channelCode);
		userInfo.setSceneNo(sceneId);
		R<DeviceLinkAccount> dv = remoteUserCenterService.login(userInfo);

		WxMpXmlOutMessage responseResult = null;
		try {
			if (qrCode != null)
				responseResult = handleSpecial(qrCode, wxMessage);
		} catch (Exception e) {
			this.logger.error(e.getMessage(), e);
		}
		if (responseResult != null) {
			return responseResult;
		}

		try {
			return wxReplyService.matchSubscribeReply(wxMessage);
		} catch (Exception e) {
			this.logger.error(e.getMessage(), e);
		}

		return null;
	}

	/**
	 * 处理特殊请求，比如如果是扫码进来的,事件KEY值，qrscene_为前缀，后面为二维码的参数值，可以做相应处理
	 */
	protected WxMpXmlOutMessage handleSpecial(WxQrCode qrCode, WxMpXmlMessage wxMessage) throws Exception {
		wxQrCodeService.processScanEvent(true,qrCode);
		return wxQrCodeService.buildOutMsg(qrCode, wxMessage);
	}

}
