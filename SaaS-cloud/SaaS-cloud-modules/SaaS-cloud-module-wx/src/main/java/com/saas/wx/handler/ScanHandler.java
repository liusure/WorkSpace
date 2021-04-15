package com.saas.wx.handler;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.saas.wx.domain.WxQrCode;
import com.saas.wx.service.WxQrCodeService;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;

@Component
public class ScanHandler extends AbstractHandler {

	@Autowired
	private WxQrCodeService wxQrCodeService;

	@Override
	public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context, WxMpService wxMpService,
			WxSessionManager sessionManager) throws WxErrorException {

		Integer sceneId = null;
		if (StringUtils.isNotEmpty(wxMessage.getEventKey())) {
			sceneId = Integer.parseInt(wxMessage.getEventKey());
		}
		WxQrCode qrCode = null;
		String channelCode = null;
		if (sceneId != null) {
			qrCode = wxQrCodeService.getBySceneId(sceneId.longValue());
			if (qrCode != null)
				channelCode = qrCode.getChannelCode();
		}
		try {
			wxQrCodeService.processScanEvent(false, qrCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return wxQrCodeService.buildOutMsg(qrCode, wxMessage);
	}
}
