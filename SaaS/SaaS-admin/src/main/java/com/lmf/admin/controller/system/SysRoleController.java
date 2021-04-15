package com.saas.admin.controller.system;

import java.util.ArrayList;
import java.util.List;

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

import com.saas.admin.service.SysUserService;
import com.saas.common.core.controller.BaseController;
import com.saas.common.core.domain.SysPrivilege;
import com.saas.common.core.domain.SysRole;
import com.saas.common.core.entity.AjaxResult;
import com.saas.common.core.page.DataPage;
import com.saas.common.core.page.DataPageable;
import com.saas.common.enums.BusinessType;
import com.saas.common.security.service.SysPermissionService;
import com.saas.common.security.service.TokenService;
import com.saas.log.annotation.Log;
import com.saas.system.service.SysRoleService;

/**
 * 角色信息
 * 
 */
@RestController
@RequestMapping("/system/role")
public class SysRoleController extends BaseController {
	@Autowired
	private SysRoleService modelService;

	@Autowired
	private TokenService tokenService;

	@Autowired
	private SysPermissionService permissionService;

	@Autowired
	private SysUserService userService;

	@PreAuthorize("@ss.hasPermi('system:role:list')")
	@GetMapping("/list")
	public AjaxResult list(DataPageable pageable) {
		DataPage<SysRole> page = modelService.getListPage(pageable);
		return AjaxResult.success(page);
	}

	// @Log(title = "角色管理", businessType = BusinessType.EXPORT)
	// @PreAuthorize("@ss.hasPermi('system:role:export')")
	// @GetMapping("/export")
//	public AjaxResult export(SysRole role) {
//		List<SysRole> list = roleService.selectRoleList(role);
//		ExcelUtil<SysRole> util = new ExcelUtil<SysRole>(SysRole.class);
//		return util.exportExcel(list, "角色数据");
//	}

	/**
	 * 根据角色编号获取详细信息
	 */
	@PreAuthorize("@ss.hasPermi('system:role:query')")
	@GetMapping(value = "/{roleId}")
	public AjaxResult getInfo(@PathVariable Long roleId) {
		return AjaxResult.success(modelService.getById(roleId));
	}

	/**
	 * 新增角色
	 */
	@PreAuthorize("@ss.hasPermi('system:role:add')")
	@Log(title = "角色管理", businessType = BusinessType.INSERT)
	@PostMapping
	public AjaxResult add(@Validated @RequestBody SysRole role) {
		if (!modelService.checkNameUnique(role)) {
			return AjaxResult.error("新增角色'" + role.getName() + "'失败，角色名称已存在");
		}
		if (role.getPrivilegeIds() != null && role.getPrivilegeIds().length > 0) {
			List<SysPrivilege> privilegeList = new ArrayList<>();
			for (int i = 0; i < role.getPrivilegeIds().length; i++) {
				privilegeList.add(new SysPrivilege(role.getPrivilegeIds()[i]));
			}
			role.setPrivilegeList(privilegeList);
		}
		modelService.save(role);
		return toAjax(1);

	}

	/**
	 * 修改保存角色
	 */
	@PreAuthorize("@ss.hasPermi('system:role:edit')")
	@Log(title = "角色管理", businessType = BusinessType.UPDATE)
	@PutMapping
	public AjaxResult edit(@Validated @RequestBody SysRole role) {
		
		
		if (!modelService.checkNameUnique(role)) {
			return AjaxResult.error("新增角色'" + role.getName() + "'失败，角色名称已存在");
		}
		if (role.getPrivilegeIds() != null && role.getPrivilegeIds().length > 0) {
			List<SysPrivilege> privilegeList = new ArrayList<>();
			for (int i = 0; i < role.getPrivilegeIds().length; i++) {
				privilegeList.add(new SysPrivilege(role.getPrivilegeIds()[i]));
			}			
			role.setPrivilegeList(privilegeList);
		} else {
			role.setPrivilegeList(new ArrayList<>());
		}
		modelService.save(role);
		return toAjax(1);

	}

	/**
	 * 修改保存数据权限
	 */
//	@PreAuthorize("@ss.hasPermi('system:role:edit')")
//	@Log(title = "角色管理", businessType = BusinessType.UPDATE)
//	@PutMapping("/dataScope")
//	public AjaxResult dataScope(@RequestBody SysRole role) {
//		roleService.checkRoleAllowed(role);
//		return toAjax(roleService.authDataScope(role));
//	}

	/**
	 * 状态修改
	 */
	@PreAuthorize("@ss.hasPermi('system:role:edit')")
//	@Log(title = "角色管理", businessType = BusinessType.UPDATE)
	@PutMapping("/changeStatus")
	public AjaxResult changeStatus(@RequestBody SysRole role) {
		SysRole model = modelService.getById(role.getId());
		if(model.getStatusFlag() != role.getStatusFlag()) {
			model.setStatusFlag(role.getStatusFlag());
		}
		modelService.save(model);
		return toAjax(1);
	}

	/**
	 * 删除角色
	 */
	@PreAuthorize("@ss.hasPermi('system:role:remove')")
	@Log(title = "角色管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{roleIds}")
	public AjaxResult remove(@PathVariable Long[] roleIds) {
		for (Long roleId : roleIds) {
			modelService.delete(roleId);
		}
		return toAjax(roleIds.length);
	}

	/**
	 * 获取角色选择框列表
	 */
	@PreAuthorize("@ss.hasPermi('system:role:query')")
	@GetMapping("/optionselect")
	public AjaxResult optionselect() {
		return AjaxResult.success(modelService.getAllModels());
	}
}
