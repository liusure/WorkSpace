package com.saas.admin.controller.system;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
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
import com.saas.common.core.entity.AjaxResult;
import com.saas.common.core.page.DataPageable;
import com.saas.common.core.page.DataPage;
import com.saas.common.enums.BusinessType;
import com.saas.common.security.utils.SecurityUtils;
import com.saas.system.domain.SysCategory;
import com.saas.system.service.SysCategoryService;
import com.saas.common.util.poi.ExcelUtil;
import com.saas.common.util.poi.PoiField;
import com.saas.log.annotation.Log;


/**
 * 系统目录Controller
 * 
 * @author bruce
 * @date 2021-01-07
 */
@RestController
@RequestMapping("/system/category")
public class SysCategoryController extends BaseController
{
    @Autowired
    private SysCategoryService sysCategoryService;

    /**
     * 查询系统目录列表
     */
    @PreAuthorize("@ss.hasPermi('system:category:list')")
    @GetMapping("/list")
    public AjaxResult list(DataPageable pageable)
    {
        List<SysCategory> list = sysCategoryService.getList(pageable);
        return AjaxResult.success(list);
    }

    /**
     * 导出系统目录列表
     */
    @PreAuthorize("@ss.hasPermi('system:category:export')")
    @Log(title = "系统目录", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(DataPageable pageable)
    {
    	List<SysCategory> list = sysCategoryService.getList(pageable);
		List<PoiField> fields = new ArrayList<>();
		fields.add(new PoiField("id", "ID", 1));
				
		ExcelUtil<SysCategory> util = new ExcelUtil<SysCategory>(SysCategory.class, fields);
		return util.exportExcel(list, "category");
		       
    }

    /**
     * 获取系统目录详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:category:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(sysCategoryService.getById(id));
    }

    /**
     * 新增系统目录
     */
    @PreAuthorize("@ss.hasPermi('system:category:add')")
    @Log(title = "系统目录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysCategory sysCategory)
    {       
        sysCategoryService.save(sysCategory);
		return toAjax(1);
    }

    /**
     * 修改系统目录
     */
    @PreAuthorize("@ss.hasPermi('system:category:edit')")
    @Log(title = "系统目录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysCategory sysCategory)
    {
        sysCategoryService.save(sysCategory);
		return toAjax(1);
    }

    /**
     * 删除系统目录
     */
    @PreAuthorize("@ss.hasPermi('system:category:remove')")
    @Log(title = "系统目录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
    	for (Long id : ids) {
			sysCategoryService.delete(id);
		}
		return toAjax(ids.length);       
    }
}
