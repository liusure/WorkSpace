package com.saas.system.controller;

import java.io.IOException;
import java.util.ArrayList;
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
import com.saas.common.core.entity.KeyValue;
import com.saas.common.core.page.DataPage;
import com.saas.common.core.page.DataPageable;
import com.saas.common.enums.BusinessType;
import com.saas.common.security.annotation.PreAuthorize;
import com.saas.common.util.StringUtils;
import com.saas.common.util.poi.ExcelUtil;
import com.saas.log.annotation.Log;
import com.saas.system.domain.SysDictData;
import com.saas.system.service.SysDictDataService;

/**
 * 数据字典信息
 * 
 */
@RestController
@RequestMapping("/dict/data")
public class SysDictDataController extends BaseController {

	@Autowired
	private SysDictDataService dictDataService;

	@PreAuthorize(hasPermi = "system:dict:list")
	@GetMapping("/list")
	public AjaxResult list(DataPageable pageable) {
		DataPage<SysDictData> page = dictDataService.getListPage(pageable);
		return AjaxResult.success(page);
	}

	@Log(title = "字典数据", businessType = BusinessType.EXPORT)
	@PreAuthorize(hasPermi = "system:dict:export")
	@GetMapping("/export")
	public AjaxResult export(DataPageable pageable) throws IOException {
		List<SysDictData> list = dictDataService.getList(pageable);
		ExcelUtil<SysDictData> util = new ExcelUtil<SysDictData>(SysDictData.class);
		return util.exportExcel(list, "字典数据");
	}

	/**
	 * 查询字典数据详细
	 */
	@PreAuthorize(hasPermi = "system:dict:query")
	@GetMapping(value = "/{dictCode}")
	public AjaxResult getInfo(@PathVariable Long dictCode) {
		return AjaxResult.success(dictDataService.getById(dictCode));
	}

	/**
	 * 根据字典类型查询字典数据信息
	 */
	@GetMapping(value = "/type/{dictType}")
	public AjaxResult dictType(@PathVariable String dictType) {
		List<SysDictData> data = dictDataService.getModels(new KeyValue("dictType", dictType));
		if (StringUtils.isNull(data)) {
			data = new ArrayList<SysDictData>();
		}
		return AjaxResult.success(data);
	}

	/**
	 * 新增字典类型
	 */
	@PreAuthorize(hasPermi = "system:dict:add")
	@Log(title = "字典数据", businessType = BusinessType.INSERT)
	@PostMapping
	public AjaxResult add(@Validated @RequestBody SysDictData dict) {
		if (!dictDataService.checkFieldUnique(dict, "dictType", "dictValue")) {
			return AjaxResult.error("新增字典数据'" + dict.getDictLabel() + "'失败，字典数据值已存在");
		}
		if (!dictDataService.checkFieldUnique(dict, "dictType", "dictLabel")) {
			return AjaxResult.error("新增字典数据'" + dict.getDictLabel() + "'失败，字典数据标签已存在");
		}
		dictDataService.save(dict);
		return toAjax(1);
	}

	/**
	 * 修改保存字典类型
	 */
	@PreAuthorize(hasPermi = "system:dict:edit")
	@Log(title = "字典数据", businessType = BusinessType.UPDATE)
	@PutMapping
	public AjaxResult edit(@Validated @RequestBody SysDictData dict) {
		if (!dictDataService.checkFieldUnique(dict, "dictType", "dictValue")) {
			return AjaxResult.error("新增字典数据'" + dict.getDictLabel() + "'失败，字典数据值已存在");
		}
		if (!dictDataService.checkFieldUnique(dict, "dictType", "dictLabel")) {
			return AjaxResult.error("新增字典数据'" + dict.getDictLabel() + "'失败，字典数据标签已存在");
		}
		dictDataService.save(dict);
		return toAjax(1);
	}

	/**
	 * 删除字典类型
	 */
	@PreAuthorize(hasPermi = "system:dict:remove")
	@Log(title = "字典类型", businessType = BusinessType.DELETE)
	@DeleteMapping("/{dictCodes}")
	public AjaxResult remove(@PathVariable Long[] dictCodes) {
		for (Long id : dictCodes) {
			dictDataService.delete(id);
		}
		return toAjax(dictCodes.length);
	}
}
