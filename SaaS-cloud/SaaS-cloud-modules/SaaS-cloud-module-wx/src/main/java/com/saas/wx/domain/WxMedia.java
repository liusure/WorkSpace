package com.saas.wx.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.saas.common.core.domain.SaasEntity;

@Entity
@Table(name = "wx_media")
public class WxMedia extends SaasEntity {

	public final static int TYPE_IMAGE = 1;
	public final static int TYPE_VOICE = 2;
	public final static int TYPE_VEDIO = 3;	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private String introduction;
	private String url;
	private String mediaId;

	// 媒体文件类型，分别有图片（image）、语音（voice）、视频（video）
	private int type;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

}
