/**
 * 
 */
package com.saas.common.core.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.ImmutableList;

/**
 * @author quanzhi
 *
 */
@Entity
@Table(name = "sys_privilege")
public class SysPrivilege extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -576696645093777091L;

	private String name;

	private String permissions;

	public SysPrivilege() {

	}

	public SysPrivilege(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPermissions() {
		return permissions;
	}

	public void setPermissions(String permissions) {
		this.permissions = permissions;
	}

	@JSONField(serialize = false)
	@JsonIgnore
	@Transient
	public List<String> getPermissionList() {
		return ImmutableList.copyOf(StringUtils.split(permissions, ","));
	}

	@JSONField(serialize = false)
	@JsonIgnore
	@Transient
	public boolean isContained(List<String> permissionList) {
		List<String> privilegeList = ImmutableList.copyOf(StringUtils.split(permissions, ","));
		if (permissionList.containsAll(privilegeList))
			return true;
		return false;
	}

}
