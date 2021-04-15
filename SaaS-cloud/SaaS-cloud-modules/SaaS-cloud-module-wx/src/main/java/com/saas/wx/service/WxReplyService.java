package com.saas.wx.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.ImmutableList;
import com.saas.common.constant.DomainConstants;
import com.saas.common.core.entity.KeyValue;
import com.saas.common.jpa.BaseDao;
import com.saas.common.jpa.BaseService;
import com.saas.common.util.StringUtils;
import com.saas.wx.domain.WxArticle;
import com.saas.wx.domain.WxMedia;
import com.saas.wx.domain.WxReply;
import com.saas.wx.repository.WxReplyDao;

import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutNewsMessage;

/**
 * 微信回复规则Service接口
 * 
 * @author bruce
 * @date 2021-01-29
 */
@Service
public class WxReplyService extends BaseService<WxReply> {

	@Autowired
	private WxReplyDao modelDao;

	@Autowired
	private WxMediaService wxMediaService;

	@Autowired
	private WxArticleService wxArticleService;

	@Override
	protected BaseDao<WxReply> getModelDao() {
		return modelDao;
	}

	@Override
	protected boolean isUseLocalCache() {
		return true;
	}

	public WxMpXmlOutMessage matchMsgReply(String msg, WxMpXmlMessage wxMessage) {
		List<WxReply> items = getModels("orderNum", new KeyValue("receiveType", WxReply.RECEIVE_TYPE_MSG),
				new KeyValue("statusFlag", DomainConstants.DOMAIN__STATUS_PUBLISH));
		for (WxReply item : items) {
			if (item.getMatchType() == WxReply.MATCH_FULL && StringUtils.equalsAnyIgnoreCase(msg,
					item.getKeyWordList().toArray(new String[item.getKeyWordList().size()]))) {
				return buildOutMsg(item, wxMessage);
			} else if (StringUtils.matches(msg, item.getKeyWordList()))
				return buildOutMsg(item, wxMessage);
		}
		return null;
	}

	public WxMpXmlOutMessage matchSubscribeReply(WxMpXmlMessage wxMessage) {
		WxReply item = this.getFirstModel(new KeyValue("receiveType", WxReply.RECEIVE_TYPE_EVENT),
				new KeyValue("matchType", WxReply.EVENT_SUBSCRIBE),
				new KeyValue("statusFlag", DomainConstants.DOMAIN__STATUS_PUBLISH));
		if (item != null) {
			return buildOutMsg(item, wxMessage);
		}
		return null;
	}

	public WxMpXmlOutMessage buildOutMsg(WxReply reply, WxMpXmlMessage wxMessage) {
		switch (reply.getReplyType()) {
		case WxReply.REPLY_TYPE_MSG:
			return WxMpXmlOutMessage.TEXT().content(reply.getReplyContent()).fromUser(wxMessage.getToUser())
					.toUser(wxMessage.getFromUser()).build();
		case WxReply.REPLY_TYPEMEDIA:
			WxMedia wxMedia = wxMediaService.getById(Long.parseLong(reply.getReplyContent()));
			switch (wxMedia.getType()) {
			case WxMedia.TYPE_IMAGE:
				return WxMpXmlOutMessage.IMAGE().mediaId(wxMedia.getMediaId()).fromUser(wxMessage.getToUser())
						.toUser(wxMessage.getFromUser()).build();
			case WxMedia.TYPE_VOICE:
				return WxMpXmlOutMessage.VOICE().mediaId(wxMedia.getMediaId()).fromUser(wxMessage.getToUser())
						.toUser(wxMessage.getFromUser()).build();
			case WxMedia.TYPE_VEDIO:
				return WxMpXmlOutMessage.VIDEO().title(wxMedia.getName()).description(wxMedia.getIntroduction())
						.mediaId(wxMedia.getMediaId()).fromUser(wxMessage.getToUser()).toUser(wxMessage.getFromUser())
						.build();
			}

		case WxReply.REPLY_TYPE_NEWS:
			List<String> newsIds = ImmutableList.copyOf(StringUtils.split(reply.getReplyContent(), ","));
			List<WxMpXmlOutNewsMessage.Item> newsItems = new ArrayList<>();
			newsIds.forEach((newsId) -> {
				WxArticle article = wxArticleService.getById(Long.parseLong(newsId));
				WxMpXmlOutNewsMessage.Item item = new WxMpXmlOutNewsMessage.Item();
				item.setDescription(article.getExtensionProfile());
				item.setPicUrl(article.getCoverImg());
				item.setTitle(article.getName());
				item.setUrl(article.getOriginalLink());
				newsItems.add(item);
			});
			return WxMpXmlOutMessage.NEWS().fromUser(wxMessage.getToUser()).toUser(wxMessage.getFromUser())
					.addArticle(newsItems.toArray(new WxMpXmlOutNewsMessage.Item[newsItems.size()])).build();
		}
		return null;
	}
}
