package com.saas.admin.controller.system;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
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

import com.saas.common.core.controller.BaseController;
import com.saas.common.core.domain.SysDept;
import com.saas.common.core.entity.AjaxResult;
import com.saas.common.core.page.DataPageable;
import com.saas.common.enums.BusinessType;
import com.saas.common.security.utils.SecurityUtils;
import com.saas.log.annotation.Log;
import com.saas.system.service.SysDeptService;

/**
 * 部门信息
 * 
 */
@RestController
@RequestMapping("/system/dept")
public class SysDeptController extends BaseController {
	@Autowired
	private SysDeptService deptService;

	

	/**
	 * 获取部门列表
	 */
	@PreAuthorize("@ss.hasPermi('system:dept:list')")
	@GetMapping("/list")
	public AjaxResult list(DataPageable pageable) {
		List<SysDept> depts = deptService.getList(pageable);
		return AjaxResult.success(depts);
	}

	/**
	 * 查询部门列表（排除节点）
	 */
	@PreAuthorize("@ss.hasPermi('system:dept:list')")
	@GetMapping("/list/exclude/{deptId}")
	public AjaxResult excludeChild(@PathVariable(value = "deptId", required = false) Long deptId) {
		List<SysDept> depts = deptService.getAllModels();
		Iterator<SysDept> it = depts.iterator();
		while (it.hasNext()) {
			SysDept d = (SysDept) it.next();
			if (d.getId() == deptId || ArrayUtils.contains(StringUtils.split(d.getAncestors(), ","), deptId + "")) {
				it.remove();
			}
		}
		return AjaxResult.success(depts);
	}

	/**
	 * 根据部门编号获取详细信息
	 */
	@PreAuthorize("@ss.hasPermi('system:dept:list')")
	@GetMapping(value = "/{deptId}")
	public AjaxResult getInfo(@PathVariable Long deptId) {
		return AjaxResult.success(deptService.getById(deptId));
	}

	/**
	 * 获取部门下拉树列表
	 */
	@GetMapping("/treeselect")
	public AjaxResult treeselect(SysDept dept) {
		return AjaxResult.success(deptService.buildDeptTreeSelect());
	}

	/**
	 * 加载对应角色部门列表树
	 */
	@GetMapping(value = "/roleDeptTreeselect/{roleId}")
	public AjaxResult roleDeptTreeselect(@PathVariable("roleId") Long roleId) {
		List<SysDept> depts = deptService.getAllModels();
		AjaxResult ajax = AjaxResult.success();
		// ajax.put("checkedKeys", deptService.selectDeptListByRoleId(roleId));
//		ajax.put("depts", deptService.buildDeptTreeSelect(depts));
		return ajax;
	}

	/**
	 * 新增部门
	 */
	@PreAuthorize("@ss.hasPermi('system:dept:add')")
	@Log(title = "部门管理", businessType = BusinessType.INSERT)
	@PostMapping
	public AjaxResult add(@Validated @RequestBody SysDept dept) {
//		if (UserConstants.NOT_UNIQUE.equals(deptService.checkDeptNameUnique(dept))) {
//			return AjaxResult.error("新增部门'" + dept.getDeptName() + "'失败，部门名称已存在");
//		}
//		dept.setCreateBy(SecurityUtils.getUsername());
		deptService.save(dept);
		return toAjax(1);
	}

	/**
	 * 修改部门
	 */
	@PreAuthorize("@ss.hasPermi('system:dept:edit')")
	@Log(title = "部门管理", businessType = BusinessType.UPDATE)
	@PutMapping
	public AjaxResult edit(@Validated @RequestBody SysDept dept) {
//		if (UserConstants.NOT_UNIQUE.equals(deptService.checkDeptNameUnique(dept))) {
//			return AjaxResult.error("修改部门'" + dept.getDeptName() + "'失败，部门名称已存在");
//		} else if (dept.getParentId().equals(dept.getDeptId())) {
//			return AjaxResult.error("修改部门'" + dept.getDeptName() + "'失败，上级部门不能是自己");
//		} else if (StringUtils.equals(UserConstants.DEPT_DISABLE, dept.getStatus())
//				&& deptService.selectNormalChildrenDeptById(dept.getDeptId()) > 0) {
//			return AjaxResult.error("该部门包含未停用的子部门！");
//		}
		deptService.save(dept);
		return toAjax(1);
	}

	/**
	 * 删除部门
	 */
	@PreAuthorize("@ss.hasPermi('system:dept:remove')")
	@Log(title = "部门管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{deptId}")
	public AjaxResult remove(@PathVariable Long deptId) {
//		if (deptService.hasChildByDeptId(deptId)) {
//			return AjaxResult.error("存在下级部门,不允许删除");
//		}
//		if (deptService.checkDeptExistUser(deptId)) {
//			return AjaxResult.error("部门存在用户,不允许删除");
//		}
		deptService.delete(deptId);
		return toAjax(1);
	}

}