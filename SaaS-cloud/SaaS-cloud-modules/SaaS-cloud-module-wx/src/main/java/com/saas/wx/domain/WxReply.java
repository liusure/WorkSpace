package com.saas.wx.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.ImmutableList;
import com.saas.common.core.domain.SaasEntity;

@Entity
@Table(name = "wx_reply")
public class WxReply extends SaasEntity {

	public static final Integer RECEIVE_TYPE_MSG = 1;
	public static final Integer RECEIVE_TYPE_EVENT = 2;
	
	
	public static final int REPLY_TYPE_MSG = 1;
	public static final int REPLY_TYPEMEDIA = 2;
	public static final int REPLY_TYPE_NEWS = 3;
	
	public static final int MATCH_FULL = 1;
	public static final int MATCH_PATTERN = 2;
	
	public static final int EVENT_SUBSCRIBE = 1;
	public static final int EVENT_UNSUBSCRIBE = 2;
	public static final int EVENT_SCAN  = 3;
	public static final int EVENT_LOCATION = 4;
	public static final int EVENT_MENU = 5;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String ruleName;
	/*
	 * 接收普通消息 事件消息
	 */
	private int receiveType;
	/**
	 * 普通消息时：1 完全匹配，2 模式匹配， 事件消息时：1 关注取消关注事件，3 扫描带参数二维码事件，4 上报地理位置事件，5 自定义菜单事件， 6
	 * 点击菜单拉取消息时的事件推送，7 点击菜单跳转链接时的事件推送
	 */
	private int matchType;
	/**
	 * 多个关键字用,分割
	 */
	private String keyWords;

	private int replyType;
	
	//图文时为文章ID集合，用,号隔开
	private String replyContent;

	private int orderNum;// 优先级，当有多个被匹配，选取优先级最高的

	public String getRuleName() {
		return ruleName;
	}

	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}

	public int getReceiveType() {
		return receiveType;
	}

	public void setReceiveType(int receiveType) {
		this.receiveType = receiveType;
	}

	public int getMatchType() {
		return matchType;
	}

	public void setMatchType(int matchType) {
		this.matchType = matchType;
	}

	public String getKeyWords() {
		return keyWords;
	}
	@JSONField(serialize = false)
	@JsonIgnore
	@Transient
	public List<String> getKeyWordList() {
		return ImmutableList.copyOf(StringUtils.split(keyWords, ","));
	}

	public void setKeyWords(String keyWords) {
		this.keyWords = keyWords;
	}

	public int getReplyType() {
		return replyType;
	}

	public void setReplyType(int replyType) {
		this.replyType = replyType;
	}

	public String getReplyContent() {
		return replyContent;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}

	public int getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}

}
