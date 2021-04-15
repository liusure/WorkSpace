package com.saas.wx.handler;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.saas.common.util.StringUtils;
import com.saas.wx.service.WxReplyService;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.kefu.result.WxMpKfOnlineList;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;

@Component
public class MsgHandler extends AbstractHandler {

	@Autowired
	private WxReplyService wxReplyService;

	@Override
	public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context, WxMpService wxMpService,
			WxSessionManager sessionManager) {

		if (!wxMessage.getMsgType().equals(WxConsts.XmlMsgType.EVENT)) {
			// TODO 可以选择将消息保存到本地
		}

		// 当用户输入关键词如“你好”，“客服”等，并且有客服在线时，把消息转发给在线客服
		if (StringUtils.startsWithAny(wxMessage.getContent(), "你好", "客服") && hasKefuOnline(wxMpService)) {
			return WxMpXmlOutMessage.TRANSFER_CUSTOMER_SERVICE().fromUser(wxMessage.getToUser())
					.toUser(wxMessage.getFromUser()).build();
		}

		return wxReplyService.matchMsgReply(wxMessage.getContent(), wxMessage);

	}

	public boolean hasKefuOnline(WxMpService wxMpService) {
		try {
			WxMpKfOnlineList kfOnlineList = wxMpService.getKefuService().kfOnlineList();
			return kfOnlineList != null && kfOnlineList.getKfOnlineList().size() > 0;
		} catch (Exception e) {
			this.logger.error("获取客服在线状态异常: " + e.getMessage(), e);
		}

		return false;
	}

}
