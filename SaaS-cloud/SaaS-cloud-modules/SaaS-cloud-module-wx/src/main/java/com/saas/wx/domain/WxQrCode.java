/**
 * 
 */
package com.saas.wx.domain;

import java.util.Date;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.google.common.collect.ImmutableMap;
import com.saas.common.core.domain.SaasEntity;

/**
 * @author bruce
 *
 */
@Entity
@Table(name = "wx_qr_code")
public class WxQrCode extends SaasEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final Map<Integer, String> QRCODE_TYPE = new ImmutableMap.Builder<Integer, String>().put(0, "临时")
			.put(1, "永久").build();

	public static final Map<Integer, String> USED_TYPE = new ImmutableMap.Builder<Integer, String>().put(0, "关键词")
			.build();

	public static final Integer QRCODE_TYPE_TEMPORARY = 0;// 临时
	public static final Integer QRCODE_TYPE_FOREVER = 1;// 永久

	public static final int QRCODE_USED_TYPE_KEYWORD = 0;// 关键词 触发时按关键字处理	

	private String name;
	private String channelCode;// 渠道编号

	private Integer qrCodetype;//
	private Integer qrCodeUsedType = QRCODE_USED_TYPE_KEYWORD;// 用途
	private String qrCodeTicket;//
	private String qrCodeUrl;// 微信url
	private Integer effectiveTime;// 有效时间 小时数量
	private Integer sceneId;// 场景id
	// 关键词\文章ID
	private String content;
	private Date endTime;// 有效时间
	private String picUrl;// 下载到本地Url
	private Integer visitsNum;// 访问量
	private int newUserCount = 0;// 新用户访问量
	private Long userId;// 用户Id
	private String openId;// 创建二维码用户的openID

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getChannelCode() {
		return channelCode;
	}

	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
	}

	public Integer getQrCodetype() {
		return qrCodetype;
	}

	public void setQrCodetype(Integer qrCodetype) {
		this.qrCodetype = qrCodetype;
	}

	public Integer getQrCodeUsedType() {
		return qrCodeUsedType;
	}

	public void setQrCodeUsedType(Integer qrCodeUsedType) {
		this.qrCodeUsedType = qrCodeUsedType;
	}

	public String getQrCodeTicket() {
		return qrCodeTicket;
	}

	public void setQrCodeTicket(String qrCodeTicket) {
		this.qrCodeTicket = qrCodeTicket;
	}

	public String getQrCodeUrl() {
		return qrCodeUrl;
	}

	public void setQrCodeUrl(String qrCodeUrl) {
		this.qrCodeUrl = qrCodeUrl;
	}

	public Integer getEffectiveTime() {
		return effectiveTime;
	}

	public void setEffectiveTime(Integer effectiveTime) {
		this.effectiveTime = effectiveTime;
	}

	public Integer getSceneId() {
		return sceneId;
	}

	public void setSceneId(Integer sceneId) {
		this.sceneId = sceneId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public Integer getVisitsNum() {
		return visitsNum;
	}

	public void setVisitsNum(Integer visitsNum) {
		this.visitsNum = visitsNum;
	}

	public int getNewUserCount() {
		return newUserCount;
	}

	public void setNewUserCount(int newUserCount) {
		this.newUserCount = newUserCount;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

}
