package com.saas.common.core.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 菜单权限表 sys_menu
 * 
 */
@Entity
@Table(name = "sys_menu")
public class SysMenu extends BaseEntity {
	private static final long serialVersionUID = 1L;

	/** 菜单名称 */
	private String menuName;

	/** 父菜单ID */
	private Long parentId = 0l;

	/** 显示顺序 */
	private int orderNum = 0;

	/** 路由地址 */
	private String path;

	/** 组件路径 */
	private String component;

	/** 是否为外链 */
	private boolean frame;

	/** 是否缓存 */
	private boolean cache;

	/** 类型（M目录 C菜单 F按钮） */
	private String menuType;

	/** 显示状态（true显示 false隐藏） */
	private boolean visible;

	/** 权限字符串 */
	private String perms;

	/** 菜单图标 */
	private String icon;

	/** 子菜单 */
	private List<SysMenu> children = new ArrayList<SysMenu>();

	@NotBlank(message = "菜单名称不能为空")
	@Size(min = 0, max = 50, message = "菜单名称长度不能超过50个字符")
	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}

	public int getOrderNum() {
		return orderNum;
	}

	@Size(min = 0, max = 200, message = "路由地址不能超过200个字符")
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@Size(min = 0, max = 200, message = "组件路径不能超过255个字符")
	public String getComponent() {
		return component;
	}

	public void setComponent(String component) {
		this.component = component;
	}

	public boolean isFrame() {
		return frame;
	}

	public void setFrame(boolean frame) {
		this.frame = frame;
	}

	public boolean isCache() {
		return cache;
	}

	public void setCache(boolean cache) {
		this.cache = cache;
	}

	@NotBlank(message = "菜单类型不能为空")
	public String getMenuType() {
		return menuType;
	}

	public void setMenuType(String menuType) {
		this.menuType = menuType;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	@Size(min = 0, max = 100, message = "权限标识长度不能超过100个字符")
	public String getPerms() {
		return perms;
	}

	public void setPerms(String perms) {
		this.perms = perms;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	@JSONField(serialize = false)
	@Transient
	@JsonIgnore
	public List<SysMenu> getChildren() {
		return children;
	}

	public void setChildren(List<SysMenu> children) {
		this.children = children;
	}

	public void addChildren(SysMenu menu) {
		this.children.add(menu);
	}
}
