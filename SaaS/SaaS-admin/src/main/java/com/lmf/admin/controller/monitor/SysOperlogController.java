package com.saas.admin.controller.monitor;

import java.util.ArrayList;
import java.util.List;

import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.saas.common.core.controller.BaseController;
import com.saas.common.core.entity.AjaxResult;
import com.saas.common.core.page.DataPage;
import com.saas.common.core.page.DataPageable;
import com.saas.common.enums.BusinessType;
import com.saas.common.util.poi.ExcelUtil;
import com.saas.common.util.poi.PoiField;
import com.saas.log.annotation.Log;
import com.saas.log.domain.SysOperLog;
import com.saas.log.service.ISysOperLogService;

/**
 * 操作日志记录
 * 
 * 
 */
@RestController
@RequestMapping("/system/monitor/operlog")
public class SysOperlogController extends BaseController {
	@DubboReference(version = "1.0.0", check = false)
	private ISysOperLogService modelService;

	@PreAuthorize("@ss.hasPermi('monitor:operlog:list')")
	@GetMapping("/list")
	public AjaxResult list(DataPageable pageable) {
		DataPage<SysOperLog> page = modelService.getListPage(pageable);
		return AjaxResult.success(page);
	}

	@Log(title = "操作日志", businessType = BusinessType.EXPORT)
	@PreAuthorize("@ss.hasPermi('monitor:operlog:export')")
	@GetMapping("/export")
	public AjaxResult export(DataPageable pageable) {
		List<SysOperLog> list = modelService.getList(pageable);
		List<PoiField> fields = new ArrayList<>();
		fields.add(new PoiField("id", "ID", 1));
		ExcelUtil<SysOperLog> util = new ExcelUtil<SysOperLog>(SysOperLog.class, fields);
		return util.exportExcel(list, "log");
	}

	@PreAuthorize("@ss.hasPermi('monitor:operlog:remove')")
	@DeleteMapping("/{operIds}")
	public AjaxResult remove(@PathVariable Long[] operIds) {
		for (Long id : operIds) {
			modelService.delete(id);
		}
		return toAjax(operIds.length);
	}

	@Log(title = "操作日志", businessType = BusinessType.CLEAN)
	@PreAuthorize("@ss.hasPermi('monitor:operlog:remove')")
	@DeleteMapping("/clean")
	public AjaxResult clean() {
		modelService.getAllModels().forEach(item -> {
			modelService.delete(item.getId());
		});
		return AjaxResult.success();
	}
}
