package com.saas.system.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saas.common.constant.UserConstants;
import com.saas.common.core.domain.SysMenu;
import com.saas.common.jpa.BaseDao;
import com.saas.common.jpa.BaseService;
import com.saas.system.repository.SysMenuDao;
import com.saas.system.vo.MetaVo;
import com.saas.system.vo.RouterVo;

@Service
public class SysMenuService extends BaseService<SysMenu> {

	@Autowired
	private SysMenuDao modelDao;

	@Override
	protected BaseDao<SysMenu> getModelDao() {
		return modelDao;
	}

	@Override
	protected boolean isUseLocalCache() {
		return true;
	}

	public List<SysMenu> buildAllMenus() {
		List<SysMenu> menus = new ArrayList<>();
		List<String> sortTypes = new ArrayList<>();
		sortTypes.add("asc_parentId");
		sortTypes.add("asc_orderNum");
		List<SysMenu> allMenus = getList(new HashMap<>(), sortTypes);		
		allMenus.forEach((menu) -> {
			if (menu.getParentId() == 0l) {
				recursionFn(menu, allMenus);
				menus.add(menu);
			}
		});
		return menus;
	}

	/**
	 * 递归构建菜单
	 * 
	 * @param menu
	 * @param allMenus
	 */
	public void recursionFn(SysMenu menu, List<SysMenu> allMenus) {
		allMenus.forEach((item) -> {			
			if (menu.getId().equals(item.getParentId())) {
				menu.addChildren(item);
			}
		});
		menu.getChildren().forEach((item) -> {
			recursionFn(item, allMenus);
		});
	}

	public List<SysMenu> selectMenuTreeByPermissions(Set<String> permissions) {
		return selectMenuTreeByPermissions(buildAllMenus(), permissions);
	}

	public List<SysMenu> selectMenuTreeByPermissions(List<SysMenu> allMenus, Set<String> permissions) {
		if (permissions.contains("*:*:*")) {
			return allMenus;
		}
		List<SysMenu> menus = new ArrayList<>();
		allMenus.forEach((menu) -> {
			if (StringUtils.isEmpty(menu.getPerms()) || permissions.contains(menu.getPerms().trim())) {
				menus.add(menu);
				hasPermsChildren(permissions, menu);
			}
		});
		return menus;
	}

	public void hasPermsChildren(Set<String> permissions, SysMenu menu) {
		List<SysMenu> children = new ArrayList<>();
		menu.getChildren().forEach((item) -> {
			if (StringUtils.isEmpty(item.getPerms()) || permissions.contains(item.getPerms().trim())) {
				children.add(item);
			}
		});
		menu.setChildren(children);
		children.forEach((item) -> {
			hasPermsChildren(permissions, item);
		});

	}

	/**
	 * 构建前端路由所需要的菜单
	 * 
	 * @param menus 菜单列表
	 * @return 路由列表
	 */

	public List<RouterVo> buildMenus(List<SysMenu> menus) {
		List<RouterVo> routers = new LinkedList<RouterVo>();
		for (SysMenu menu : menus) {
			RouterVo router = new RouterVo();
			router.setHidden(!menu.isVisible());
			router.setName(getRouteName(menu));
			router.setPath(getRouterPath(menu));
			router.setComponent(getComponent(menu));
			router.setMeta(new MetaVo(menu.getMenuName(), menu.getIcon(), !menu.isCache()));
			List<SysMenu> cMenus = menu.getChildren();
			if (!cMenus.isEmpty() && cMenus.size() > 0 && UserConstants.TYPE_DIR.equals(menu.getMenuType())) {
				router.setAlwaysShow(true);
				router.setRedirect("noRedirect");
				router.setChildren(buildMenus(cMenus));
			} else if (isMeunFrame(menu)) {
				List<RouterVo> childrenList = new ArrayList<RouterVo>();
				RouterVo children = new RouterVo();
				children.setPath(menu.getPath());
				children.setComponent(menu.getComponent());
				children.setName(StringUtils.capitalize(menu.getPath()));
				children.setMeta(new MetaVo(menu.getMenuName(), menu.getIcon(), !menu.isCache()));
				childrenList.add(children);
				router.setChildren(childrenList);
			}
			routers.add(router);
		}		
		return routers;
	}

	/**
	 * 获取路由名称
	 * 
	 * @param menu 菜单信息
	 * @return 路由名称
	 */
	public String getRouteName(SysMenu menu) {
		String routerName = StringUtils.capitalize(menu.getPath());
		// 非外链并且是一级目录（类型为目录）
		if (isMeunFrame(menu)) {
			routerName = StringUtils.EMPTY;
		}
		return routerName;
	}

	/**
	 * 获取路由地址
	 * 
	 * @param menu 菜单信息
	 * @return 路由地址
	 */
	public String getRouterPath(SysMenu menu) {
		String routerPath = menu.getPath();
		// 非外链并且是一级目录（类型为目录）
		if (0l == menu.getParentId() && UserConstants.TYPE_DIR.equals(menu.getMenuType()) && !menu.isFrame()) {
			routerPath = "/" + menu.getPath();
		}
		// 非外链并且是一级目录（类型为菜单）
		else if (isMeunFrame(menu)) {
			routerPath = "/";
		}
		return routerPath;
	}

	/**
	 * 获取组件信息
	 * 
	 * @param menu 菜单信息
	 * @return 组件信息
	 */
	public String getComponent(SysMenu menu) {
		String component = UserConstants.LAYOUT;
		if (StringUtils.isNotEmpty(menu.getComponent()) && !isMeunFrame(menu)) {
			component = menu.getComponent();
		} else if (StringUtils.isEmpty(menu.getComponent()) && isParentView(menu)) {
			component = UserConstants.PARENT_VIEW;
		}
		return component;
	}

	/**
	 * 是否为菜单内部跳转
	 * 
	 * @param menu 菜单信息
	 * @return 结果
	 */
	public boolean isMeunFrame(SysMenu menu) {
		return menu.getParentId() == 0l && UserConstants.TYPE_MENU.equals(menu.getMenuType()) && !menu.isFrame();
	}

	/**
	 * 是否为parent_view组件
	 * 
	 * @param menu 菜单信息
	 * @return 结果
	 */
	public boolean isParentView(SysMenu menu) {
		return menu.getParentId() != 0l && UserConstants.TYPE_DIR.equals(menu.getMenuType());
	}

	public boolean checkMenuNameUnique(SysMenu menu) {
		Map<String, Object> params = new HashMap<>();
		params.put("EQ_menuName", menu.getMenuName());
		List<SysMenu> menus = getList(params);
		for (SysMenu item : menus) {		
			if (!item.getId().equals(menu.getId()))
				return false;
		}
		return true;
	}
	
	public boolean hasChildByMenuId(long menuId) {
		Map<String, Object> params = new HashMap<>();
		params.put("EQ_parentId", menuId);
		List<SysMenu> menus = getList(params);
		return menus.size() > 0;
	}

}
