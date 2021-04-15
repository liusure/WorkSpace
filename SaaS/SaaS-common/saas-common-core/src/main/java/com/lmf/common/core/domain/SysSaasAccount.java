package com.saas.common.core.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Saas账号表 sys_saas_account
 * 
 */
@Entity
@Table(name = "sys_saas_account")
public class SysSaasAccount extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private String longName;
	private String mobile;
	private String contactName;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLongName() {
		return longName;
	}

	public void setLongName(String longName) {
		this.longName = longName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

}
