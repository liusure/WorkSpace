package com.saas.wx.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.saas.common.core.domain.SaasEntity;
/**
 * @author bruce
 *
 */
@Entity
@Table(name = "wx_template")
public class WxTemplate extends SaasEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String channelCode;
	private String wxTemplateId;// 微信端模版ID
	private String name;// 名称
	private int sceneType;// 使用场景

	private String first;
	private String keyword1;
	private String keyword2;
	private String keyword3;
	private String remark;
	public String getChannelCode() {
		return channelCode;
	}
	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
	}
	public String getWxTemplateId() {
		return wxTemplateId;
	}
	public void setWxTemplateId(String wxTemplateId) {
		this.wxTemplateId = wxTemplateId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getSceneType() {
		return sceneType;
	}
	public void setSceneType(int sceneType) {
		this.sceneType = sceneType;
	}
	public String getFirst() {
		return first;
	}
	public void setFirst(String first) {
		this.first = first;
	}
	public String getKeyword1() {
		return keyword1;
	}
	public void setKeyword1(String keyword1) {
		this.keyword1 = keyword1;
	}
	public String getKeyword2() {
		return keyword2;
	}
	public void setKeyword2(String keyword2) {
		this.keyword2 = keyword2;
	}
	public String getKeyword3() {
		return keyword3;
	}
	public void setKeyword3(String keyword3) {
		this.keyword3 = keyword3;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}

	
}
