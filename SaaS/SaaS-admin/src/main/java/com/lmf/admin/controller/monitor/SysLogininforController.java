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
import com.saas.log.domain.SysLogininfor;
import com.saas.log.service.ISysLogininforService;

/**
 * 系统访问记录
 * 
 */
@RestController
@RequestMapping("/system/monitor/logininfor")
public class SysLogininforController extends BaseController {
	@DubboReference(version = "1.0.0", check = false)
	private ISysLogininforService modelService;

	@PreAuthorize("@ss.hasPermi('monitor:logininfor:list')")
	@GetMapping("/list")
	public AjaxResult list(DataPageable pageable) {
		DataPage<SysLogininfor> page = modelService.getListPage(pageable);
		return AjaxResult.success(page);
	}

	@Log(title = "登录日志", businessType = BusinessType.EXPORT)
	@PreAuthorize("@ss.hasPermi('monitor:logininfor:export')")
	@GetMapping("/export")
	public AjaxResult export(DataPageable pageable) {
		List<SysLogininfor> list = modelService.getList(pageable);
		List<PoiField> fields = new ArrayList<>();
		fields.add(new PoiField("id", "ID", 1));
		ExcelUtil<SysLogininfor> util = new ExcelUtil<SysLogininfor>(SysLogininfor.class, fields);
		return util.exportExcel(list, "log");
	}

	@PreAuthorize("@ss.hasPermi('monitor:logininfor:remove')")
	@Log(title = "登录日志", businessType = BusinessType.DELETE)
	@DeleteMapping("/{infoIds}")
	public AjaxResult remove(@PathVariable Long[] infoIds) {
		for (Long id : infoIds) {
			modelService.delete(id);
		}
		return toAjax(infoIds.length);
	}

	@PreAuthorize("@ss.hasPermi('monitor:logininfor:remove')")
	@Log(title = "登录日志", businessType = BusinessType.CLEAN)
	@DeleteMapping("/clean")
	public AjaxResult clean() {
		modelService.getAllModels().forEach(item -> {
			modelService.delete(item.getId());
		});
		return AjaxResult.success();
	}
}
