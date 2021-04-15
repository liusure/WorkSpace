package com.saas.admin.controller.system;

import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.saas.common.constant.Constants;
import com.saas.common.core.controller.BaseController;
import com.saas.common.core.domain.SysMenu;
import com.saas.common.core.domain.SysUser;
import com.saas.common.core.entity.AjaxResult;
import com.saas.common.core.page.DataPageable;
import com.saas.common.enums.BusinessType;
import com.saas.common.security.entity.login.LoginUser;
import com.saas.common.security.service.SysPermissionService;
import com.saas.common.security.service.TokenService;
import com.saas.common.util.ServletUtils;
import com.saas.log.annotation.Log;
import com.saas.system.service.SysMenuService;

/**
 * 菜单信息
 * 
 */
@RestController
@RequestMapping("/system/menu")
public class SysMenuController extends BaseController {
	@Autowired
	private SysMenuService menuService;

	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private SysPermissionService permissionService;
	/**
	 * 获取菜单列表
	 */
	@PreAuthorize("@ss.hasPermi('system:menu:list')")
	@GetMapping("/list")
	public AjaxResult list(DataPageable pageable) {
		List<SysMenu> menus = menuService.getList(pageable);
		return AjaxResult.success(menus);
	}

	/**
	 * 根据菜单编号获取详细信息
	 */
	@PreAuthorize("@ss.hasPermi('system:menu:query')")
	@GetMapping(value = "/{menuId}")
	public AjaxResult getInfo(@PathVariable Long menuId) {
		return AjaxResult.success(menuService.getById(menuId));
	}

	/**
	 * 获取菜单下拉树列表
	 */
//	@GetMapping("/treeselect")
//	public AjaxResult treeselect(SysMenu menu) {
//		LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
//		Long userId = loginUser.getUser().getUserId();
//		List<SysMenu> menus = menuService.selectMenuList(menu, userId);
//		return AjaxResult.success(menuService.buildMenuTreeSelect(menus));
//	}

	/**
	 * 加载对应角色菜单列表树
	 */
//	@GetMapping(value = "/roleMenuTreeselect/{roleId}")
//	public AjaxResult roleMenuTreeselect(@PathVariable("roleId") Long roleId) {
//		LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
//		List<SysMenu> menus = menuService.selectMenuList(loginUser.getUser().getUserId());
//		AjaxResult ajax = AjaxResult.success();
//		ajax.put("checkedKeys", menuService.selectMenuListByRoleId(roleId));
//		ajax.put("menus", menuService.buildMenuTreeSelect(menus));
//		return ajax;
//	}

	/**
	 * 新增菜单
	 */
	@PreAuthorize("@ss.hasPermi('system:menu:add')")
	@Log(title = "菜单管理", businessType = BusinessType.INSERT)
	@PostMapping
	public AjaxResult add(@Validated @RequestBody SysMenu menu) {
		if (!menuService.checkMenuNameUnique(menu)) {
			return AjaxResult.error("新增菜单'" + menu.getMenuName() + "'失败，菜单名称已存在");
		} else if (menu.isFrame() && !StringUtils.startsWithAny(menu.getPath(), Constants.HTTP, Constants.HTTPS)) {
			return AjaxResult.error("新增菜单'" + menu.getMenuName() + "'失败，地址必须以http(s)://开头");
		}
		menuService.save(menu);
		return toAjax(1);
	}

	/**
	 * 修改菜单
	 */
	@PreAuthorize("@ss.hasPermi('system:menu:edit')")
	@Log(title = "菜单管理", businessType = BusinessType.UPDATE)
	@PutMapping
	public AjaxResult edit(@Validated @RequestBody SysMenu menu) {

		if (!menuService.checkMenuNameUnique(menu)) {
			return AjaxResult.error("修改菜单'" + menu.getMenuName() + "'失败，菜单名称已存在");
		} else if (menu.isFrame() && !StringUtils.startsWithAny(menu.getPath(), Constants.HTTP, Constants.HTTPS)) {
			return AjaxResult.error("修改菜单'" + menu.getMenuName() + "'失败，地址必须以http(s)://开头");
		} else if (menu.getId().equals(menu.getParentId())) {
			// TODO:需要递归遍历上级不能出现自己
			return AjaxResult.error("修改菜单'" + menu.getMenuName() + "'失败，上级菜单不能选择自己");
		}
		menuService.save(menu);
		return toAjax(1);
	}

	/**
	 * 删除菜单
	 */
	@PreAuthorize("@ss.hasPermi('system:menu:remove')")
	@Log(title = "菜单管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{menuId}")
	public AjaxResult remove(@PathVariable("menuId") Long menuId) {
		if (menuService.hasChildByMenuId(menuId)) {
			return AjaxResult.error("存在子菜单,不允许删除");
		}
		menuService.delete(menuId);
		return toAjax(1);
	}
	
	/**
	 * 获取路由信息
	 * 
	 * @return 路由信息
	 */
	@GetMapping("getRouters")
	public AjaxResult getRouters() {
		LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
		// 用户信息
		SysUser user = loginUser.getUser();
		Set<String> permissions = permissionService.getUserPermission(user);
		List<SysMenu> menus = menuService.selectMenuTreeByPermissions(permissions);		
		return AjaxResult.success(menuService.buildMenus(menus));
	}
}