package com.saas.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.saas.common.core.domain.SysPrivilege;
import com.saas.common.core.entity.AjaxResult;
import com.saas.common.core.page.DataPage;
import com.saas.common.core.page.DataPageable;
import com.saas.common.enums.BusinessType;
import com.saas.common.security.annotation.PreAuthorize;
import com.saas.log.annotation.Log;
import com.saas.system.service.SysPrivilegeService;

/**
 * 公告 信息操作处理
 * 
 */
@RestController
@RequestMapping("/privilege")
public class SysPrivilegeController extends BaseController {
	@Autowired
	private SysPrivilegeService modelService;

	/**
	 * 获取通知公告列表
	 */
	@PreAuthorize(hasPermi="system:privilege:list")
	@GetMapping("/list")
	public AjaxResult list(DataPageable pageable) {
		DataPage<SysPrivilege> page = modelService.getListPage(pageable);
		return AjaxResult.success(page);
	}

	/**
	 * 根据通知公告编号获取详细信息
	 */
	@PreAuthorize(hasPermi="system:privilege:query")
	@GetMapping(value = "/{privilegeId}")
	public AjaxResult getInfo(@PathVariable Long privilegeId) {
		return AjaxResult.success(modelService.getById(privilegeId));
	}

	/**
	 * 新增通知公告
	 */
	@PreAuthorize(hasPermi="system:privilege:add")
	@Log(title = "系统权限", businessType = BusinessType.INSERT)
	@PostMapping
	public AjaxResult add(@Validated @RequestBody SysPrivilege privilege) {
		modelService.save(privilege);
		return toAjax(1);
	}

	/**
	 * 修改通知公告
	 */
	@PreAuthorize(hasPermi="system:privilege:edit")
	@Log(title = "系统权限", businessType = BusinessType.UPDATE)
	@PutMapping
	public AjaxResult edit(@Validated @RequestBody SysPrivilege privilege) {
		modelService.save(privilege);
		return toAjax(1);
	}

	/**
	 * 删除通知公告
	 */
	@PreAuthorize(hasPermi="system:privilege:remove")
	@Log(title = "系统权限", businessType = BusinessType.DELETE)
	@DeleteMapping("/{privilegeIds}")
	public AjaxResult remove(@PathVariable Long[] privilegeIds) {
		for (Long privilegeId : privilegeIds) {
			modelService.delete(privilegeId);
		}
		return toAjax(privilegeIds.length);
	}
}
