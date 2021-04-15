/**
 * 
 */
package com.saas.wx.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.saas.common.core.domain.SaasEntity;

/**
 * @author bruce
 *
 */
@Entity
@Table(name = "wx_menu")
public class WxMenu extends SaasEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String name;

	// 类型 1 普通菜单 2小程序菜单 3发送消息
	private int actionType;

	// 菜单内容 1.消息 2.微页面 3.商品 4.文章 5.普通链接
	private int targetType;

	private String targetContent;

	private String linkUrl;

	private Long  parentId;

	private String channelCode; // 渠道编码

	private String pagePath; // 小程序路径

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getActionType() {
		return actionType;
	}

	public void setActionType(int actionType) {
		this.actionType = actionType;
	}

	public int getTargetType() {
		return targetType;
	}

	public void setTargetType(int targetType) {
		this.targetType = targetType;
	}

	public String getTargetContent() {
		return targetContent;
	}

	public void setTargetContent(String targetContent) {
		this.targetContent = targetContent;
	}

	public String getLinkUrl() {
		return linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getChannelCode() {
		return channelCode;
	}

	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
	}

	public String getPagePath() {
		return pagePath;
	}

	public void setPagePath(String pagePath) {
		this.pagePath = pagePath;
	}

}
