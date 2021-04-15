package com.saas.wx.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.saas.common.core.domain.SaasEntity;

/**
 * @author bruce
 *
 */
@Entity
@Table(name = "wx_article")
public class WxArticle extends SaasEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 渠道编码
	 */
	private String channelCode;
	private String name;// 标题
	private String author;
	private String coverImg;// 封面图片 分享图片
	private String coverMediaId;// 微信媒体ID thumb_media_id

	private String extensionProfile; // 推广简介
	private String mainBody;// 内容
	private String resourceCodes;// 资源ID列表，使用;分割
	private Integer resourceType;// 关联内容的类型

	private String originalLink;
	private boolean originalLinkFlag;
	private String mediaId; // 返回的微信图文ID
	private boolean openCommentFlag;
	private boolean onlyFansCommentFlag;

	private String replaceUrlCotent;

	public String getChannelCode() {
		return channelCode;
	}

	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getCoverImg() {
		return coverImg;
	}

	public void setCoverImg(String coverImg) {
		this.coverImg = coverImg;
	}

	public String getCoverMediaId() {
		return coverMediaId;
	}

	public void setCoverMediaId(String coverMediaId) {
		this.coverMediaId = coverMediaId;
	}

	public String getExtensionProfile() {
		return extensionProfile;
	}

	public void setExtensionProfile(String extensionProfile) {
		this.extensionProfile = extensionProfile;
	}

	public String getMainBody() {
		return mainBody;
	}

	public void setMainBody(String mainBody) {
		this.mainBody = mainBody;
	}

	public String getResourceCodes() {
		return resourceCodes;
	}

	public void setResourceCodes(String resourceCodes) {
		this.resourceCodes = resourceCodes;
	}

	public Integer getResourceType() {
		return resourceType;
	}

	public void setResourceType(Integer resourceType) {
		this.resourceType = resourceType;
	}

	public String getOriginalLink() {
		return originalLink;
	}

	public void setOriginalLink(String originalLink) {
		this.originalLink = originalLink;
	}

	public boolean isOriginalLinkFlag() {
		return originalLinkFlag;
	}

	public void setOriginalLinkFlag(boolean originalLinkFlag) {
		this.originalLinkFlag = originalLinkFlag;
	}

	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

	public boolean isOpenCommentFlag() {
		return openCommentFlag;
	}

	public void setOpenCommentFlag(boolean openCommentFlag) {
		this.openCommentFlag = openCommentFlag;
	}

	public boolean isOnlyFansCommentFlag() {
		return onlyFansCommentFlag;
	}

	public void setOnlyFansCommentFlag(boolean onlyFansCommentFlag) {
		this.onlyFansCommentFlag = onlyFansCommentFlag;
	}

	public String getReplaceUrlCotent() {
		return replaceUrlCotent;
	}

	public void setReplaceUrlCotent(String replaceUrlCotent) {
		this.replaceUrlCotent = replaceUrlCotent;
	}

}
