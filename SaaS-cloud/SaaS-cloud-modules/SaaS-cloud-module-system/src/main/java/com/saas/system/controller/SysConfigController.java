package com.saas.system.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.saas.common.util.poi.PoiField;
import com.saas.log.annotation.Log;
import com.saas.system.domain.SysConfig;
import com.saas.system.service.SysConfigService;

/**
 * 参数配置Controller
 * 
 * @author bruce
 * @date 2021-01-06
 */
@RestController
@RequestMapping("/config")
public class SysConfigController extends BaseController {
	@Autowired
	private SysConfigService sysConfigService;

	/**
	 * 查询参数配置列表
	 */
	@PreAuthorize(hasPermi="system:config:list")
	@GetMapping("/list")
	public AjaxResult list(DataPageable pageable) {
		DataPage<SysConfig> page = sysConfigService.getListPage(pageable);
		return AjaxResult.success(page);
	}

	/**
	 * 导出参数配置列表
	 */
	@PreAuthorize(hasPermi="system:config:export")
	@Log(title = "参数配置", businessType = BusinessType.EXPORT)
	@GetMapping("/export")
	public AjaxResult export(DataPageable pageable) {
		List<SysConfig> list = sysConfigService.getList(pageable);
		List<PoiField> fields = new ArrayList<>();
		fields.add(new PoiField("id", "ID", 1));

		ExcelUtil<SysConfig> util = new ExcelUtil<SysConfig>(SysConfig.class, fields);
		return util.exportExcel(list, "config");

	}

	/**
	 * 获取参数配置详细信息
	 */
	@PreAuthorize(hasPermi="system:config:query")
	@GetMapping(value = "/{id}")
	public AjaxResult getInfo(@PathVariable("id") Long id) {
		return AjaxResult.success(sysConfigService.getById(id));
	}

	/**
	 * 新增参数配置
	 */
	@PreAuthorize(hasPermi="system:config:add")
	@Log(title = "参数配置", businessType = BusinessType.INSERT)
	@PostMapping
	public AjaxResult add(@RequestBody SysConfig sysConfig) {
		if (!sysConfigService.checkFieldUnique(sysConfig, "configKey")) {
			return AjaxResult.error("新增参数'" + sysConfig.getConfigKey() + "'失败，已存在");
		}
		sysConfigService.save(sysConfig);
		return toAjax(1);
	}

	/**
	 * 修改参数配置
	 */
	@PreAuthorize(hasPermi="system:config:edit")
	@Log(title = "参数配置", businessType = BusinessType.UPDATE)
	@PutMapping
	public AjaxResult edit(@RequestBody SysConfig sysConfig) {
		if (!sysConfigService.checkFieldUnique(sysConfig, "configKey")) {
			return AjaxResult.error("修改参数'" + sysConfig.getConfigKey() + "'失败，已存在");
		}
		sysConfigService.save(sysConfig);
		return toAjax(1);
	}

	/**
	 * 删除参数配置
	 */
	@PreAuthorize(hasPermi="system:config:remove")
	@Log(title = "参数配置", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
	public AjaxResult remove(@PathVariable Long[] ids) {
		for (Long id : ids) {
			sysConfigService.delete(id);
		}
		return toAjax(ids.length);
	}

	/**
	 * 根据参数键名查询参数值
	 */
	@GetMapping(value = "/configKey/{configKey}")
	public AjaxResult getConfigKey(@PathVariable String configKey) {
		Map<String, Object> params = new HashMap<>();
		params.put("EQ_configKey", configKey);
		List<SysConfig> list = sysConfigService.getList(params);
		return AjaxResult.success(list.size() > 0 ? list.get(0) : "");
	}

	/**
	 * 清空缓存
	 */
	@PreAuthorize(hasPermi="system:config:remove")
	@Log(title = "参数管理", businessType = BusinessType.CLEAN)
	@DeleteMapping("/clearCache")
	public AjaxResult clearCache() {
//		sysConfigService.clearCache();
		return AjaxResult.success();
	}
}