package com.saas.common.core.domain;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Lists;

/**
 * 角色表 sys_role
 * 
 */
@Entity
@Table(name = "sys_role")
public class SysRole extends BaseEntity {
	private static final long serialVersionUID = 1L;

	private String name;

	private String showName;
	
	private int orderNum;

	/** 角色组 */
	private Long[] privilegeIds;

	private List<SysPrivilege> privilegeList = Lists.newArrayList();;// 权限

	public SysRole() {
	}

	public SysRole(Long id) {
		this.id = id;
	}

	@Column(unique = true)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(unique = true)
	public String getShowName() {
		return showName;
	}

	public void setShowName(String showName) {
		this.showName = showName;
	}

	/**
	 * 不给前端返回该属性
	 * @return
	 */
	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "sys_role_privilege", joinColumns = { @JoinColumn(name = "role_id") }, inverseJoinColumns = {
			@JoinColumn(name = "privilege_id") })
	// Fecth策略定义
	@Fetch(FetchMode.SUBSELECT)
	// 集合按id排序
	@OrderBy("id ASC")
	@LazyCollection(value = LazyCollectionOption.FALSE)
	public List<SysPrivilege> getPrivilegeList() {
		return privilegeList;
	}

	public void setPrivilegeList(List<SysPrivilege> privilegeList) {
		this.privilegeList = privilegeList;
		privilegeIds = new Long[privilegeList.size()];
		for (int i = 0; i < privilegeList.size(); i++) {
			privilegeIds[i] = privilegeList.get(i).getId();
		}
	}

	@JSONField(serialize = false)
	@JsonIgnore
	@Transient
	public Set<String> getPermissionList() {
		Set<String> permissionSet = new TreeSet<>();
		privilegeList.forEach((privilege) -> {
			permissionSet.addAll(privilege.getPermissionList());
		});
		return permissionSet;
	}

	@Transient
	public Long[] getPrivilegeIds() {
		return privilegeIds;
	}

	public void setPrivilegeIds(Long[] privilegeIds) {
		this.privilegeIds = privilegeIds;
	}

	public int getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}

}
