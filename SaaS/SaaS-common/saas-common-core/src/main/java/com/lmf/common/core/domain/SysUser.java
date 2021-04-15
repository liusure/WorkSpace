package com.saas.common.core.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Lists;
import com.saas.common.util.Collections3;

/**
 * 用户对象 sys_user
 * 
 */
@Entity
@Table(name = "sys_user")
public class SysUser extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6662712003926246689L;
	/** 用户账号 */
	private String loginName;
	private String plainPassword;
	private String password;
	private String salt;

	/** 用户昵称 */
	private String name;

	private String email;

	private String mobile;

	private Long deptId;

	private int sex;

	/** 备注 */
	private String remark;

	private List<SysRole> roleList = Lists.newArrayList(); // 有序的关联对象集合

	/** 最后登录IP */
	private String loginIp;

	/** 最后登录时间 */
	private Date loginDate;

	/** 角色组 */
	private Long[] roleIds;

	/** 用户头像 */
	private String avatar;

	private Long saasId;

	public SysUser() {

	}

	public SysUser(Long id) {
		this.id = id;
	}

	@Transient
	@JsonIgnore
	public boolean isAdmin() {
		return isAdmin(this.id);
	}

	public Long getSaasId() {
		return saasId;
	}

	public void setSaasId(Long saasId) {
		this.saasId = saasId;
	}

	/**
	 * 是否系统账号
	 * 
	 * @return
	 */
	@Transient
	@JsonIgnore
	@JSONField(serialize = false)
	public boolean isSysAccount() {
		if (saasId == null || saasId == 0l) {
			return true;
		}
		return false;
	}

	public static boolean isAdmin(Long userId) {
		return userId != null && 1L == userId;
	}

	// @Column(unique = true)
	@NotBlank
	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	@JSONField(serialize = false)
	@Transient
	public String getPlainPassword() {
		return plainPassword;
	}

	public void setPlainPassword(String plainPassword) {
		this.plainPassword = plainPassword;
	}

	@JsonIgnore
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	// @Column(unique = true)
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@NotBlank
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Email
	// @Column(unique = true)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	// 多对多定义
	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "sys_user_role", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = {
			@JoinColumn(name = "role_id") })
	// Fecth策略定义
	@Fetch(FetchMode.SUBSELECT)
	// 集合按id排序
	@OrderBy("id ASC")
	@LazyCollection(value = LazyCollectionOption.FALSE)
	public List<SysRole> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<SysRole> roleList) {
		this.roleList = roleList;
		roleIds = new Long[roleList.size()];
		for (int i = 0; i < roleList.size(); i++) {
			roleIds[i] = roleList.get(i).getId();
		}
	}

	@Transient
	public Long[] getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(Long[] roleIds) {
		this.roleIds = roleIds;
	}

	@JSONField(serialize = false)
	@Transient
	@JsonIgnore
	public String getRoleNames() {
		return Collections3.extractToString(roleList, "name", ", ");
	}

	@JSONField(serialize = false)
	@Transient
	@JsonIgnore
	public List<String> getRoleNameList() {
		return Collections3.extractToList(roleList, "name");
	}

	@JSONField(serialize = false)
	@Transient
	@JsonIgnore
	public String getRoleShowNames() {
		return Collections3.extractToString(roleList, "showName", ", ");
	}

	@Transient
	public boolean hasRole(String roleName) {
		for (SysRole role : getRoleList()) {
			if (role.getName().equals(roleName))
				return true;
		}
		return false;
	}

	public String getLoginIp() {
		return loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

	public Date getLoginDate() {
		return loginDate;
	}

	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}

	public Long getDeptId() {
		return deptId;
	}

	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

}
