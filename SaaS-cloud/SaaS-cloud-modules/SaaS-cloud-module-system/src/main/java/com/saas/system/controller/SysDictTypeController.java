package com.saas.system.controller;

import java.io.IOException;
import java.util.List;

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
import com.saas.common.core.entity.AjaxResult;
import com.saas.common.core.page.DataPage;
import com.saas.common.core.page.DataPageable;
import com.saas.common.enums.BusinessType;
import com.saas.common.security.annotation.PreAuthorize;
import com.saas.common.util.poi.ExcelUtil;
import com.saas.log.annotation.Log;
import com.saas.system.domain.SysDictType;
import com.saas.system.service.SysDictTypeService;

/**
 * 数据字典信息
 * 
 */
@RestController
@RequestMapping("/dict/type")
public class SysDictTypeController extends BaseController {
	@Autowired
	private SysDictTypeService modelService;

	@PreAuthorize(hasPermi = "system:dict:list")
	@GetMapping("/list")
	public AjaxResult list(DataPageable pageable) {
		DataPage<SysDictType> page = modelService.getListPage(pageable);
		return AjaxResult.success(page);
	}

	@Log(title = "字典类型", businessType = BusinessType.EXPORT)
	@PreAuthorize(hasPermi = "system:dict:export")
	@GetMapping("/export")
	public AjaxResult export(DataPageable pageable) throws IOException {
		List<SysDictType> list = modelService.getList(pageable);		
		ExcelUtil<SysDictType> util = new ExcelUtil<SysDictType>(SysDictType.class);
		return util.exportExcel(list, "字段类型数据");
	}

	/**
	 * 查询字典类型详细
	 */
	@PreAuthorize(hasPermi = "system:dict:query")
	@GetMapping(value = "/{dictId}")
	public AjaxResult getInfo(@PathVariable Long dictId) {
		return AjaxResult.success(modelService.getById(dictId));
	}

	/**
	 * 新增字典类型
	 */
	@PreAuthorize(hasPermi = "system:dict:add")
	@Log(title = "字典类型", businessType = BusinessType.INSERT)
	@PostMapping
	public AjaxResult add(@Validated @RequestBody SysDictType dict) {
		if (!modelService.checkFieldUnique(dict, "dictType")) {
			return AjaxResult.error("新增字典'" + dict.getDictName() + "'失败，字典类型已存在");
		}
		modelService.save(dict);
		return toAjax(1);
	}

	/**
	 * 修改字典类型
	 */
	@PreAuthorize(hasPermi = "system:dict:edit")
	@Log(title = "字典类型", businessType = BusinessType.UPDATE)
	@PutMapping
	public AjaxResult edit(@Validated @RequestBody SysDictType dict) {
		if (!modelService.checkFieldUnique(dict, "dictType")) {
			return AjaxResult.error("新增字典'" + dict.getDictName() + "'失败，字典类型已存在");
		}
		modelService.save(dict);
		return toAjax(1);
	}

	/**
	 * 删除字典类型
	 */
	@PreAuthorize(hasPermi = "system:dict:remove")
	@Log(title = "字典类型", businessType = BusinessType.DELETE)
	@DeleteMapping("/{dictIds}")
	public AjaxResult remove(@PathVariable Long[] dictIds) {
		for (Long id : dictIds) {
			modelService.delete(id);
		}
		return toAjax(dictIds.length);
	}

	/**
	 * 清空缓存
	 */
	@PreAuthorize(hasPermi = "system:dict:remove")
	@Log(title = "字典类型", businessType = BusinessType.CLEAN)
	@DeleteMapping("/clearCache")
	public AjaxResult clearCache() {
		modelService.clearCache();
		return AjaxResult.success();
	}

	/**
	 * 获取字典选择框列表
	 */
	@GetMapping("/optionselect")
	public AjaxResult optionselect() {
		List<SysDictType> dictTypes = modelService.getAllModels();
		return AjaxResult.success(dictTypes);
	}
}
