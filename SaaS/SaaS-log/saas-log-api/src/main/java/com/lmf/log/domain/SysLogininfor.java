package com.saas.log.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.saas.common.core.domain.IdEntity;

/**
 * 系统访问记录表 sys_logininfor
 * 
 */
@Entity
@Table(name = "sys_logininfor")
public class SysLogininfor extends IdEntity {
	private static final long serialVersionUID = 1L;

	/** 用户账号 */
	private String userName;

	/** 登录状态 0成功 1失败 */
	private String status;

	/** 登录IP地址 */
	private String ipaddr;

	/** 登录地点 */
	private String loginLocation;

	/** 浏览器类型 */
	private String browser;

	/** 操作系统 */
	private String os;

	/** 提示消息 */
	private String msg;

	/** 访问时间 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date loginTime;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getIpaddr() {
		return ipaddr;
	}

	public void setIpaddr(String ipaddr) {
		this.ipaddr = ipaddr;
	}

	public String getLoginLocation() {
		return loginLocation;
	}

	public void setLoginLocation(String loginLocation) {
		this.loginLocation = loginLocation;
	}

	public String getBrowser() {
		return browser;
	}

	public void setBrowser(String browser) {
		this.browser = browser;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}
}
