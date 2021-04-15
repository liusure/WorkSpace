package com.saas.system.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.saas.common.core.controller.BaseController;
import com.saas.common.core.domain.SysSaasAccount;
import com.saas.common.core.entity.AjaxResult;
import com.saas.common.core.page.DataPage;
import com.saas.common.core.page.DataPageable;
import com.saas.common.enums.BusinessType;
import com.saas.common.util.poi.ExcelUtil;
import com.saas.common.util.poi.PoiField;
import com.saas.log.annotation.Log;
import com.saas.system.service.SysSaasAccountService;


/**
 * SAAS账号Controller
 * 
 * @author bruce
 * @date 2021-01-21
 */
@RestController
@RequestMapping("/system/account")
public class SysSaasAccountController extends BaseController
{
    @Autowired
    private SysSaasAccountService sysSaasAccountService;

    /**
     * 查询SAAS账号列表
     */
    @PreAuthorize("@ss.hasPermi('system:account:list')")
    @GetMapping("/list")
    public AjaxResult list(DataPageable pageable)
    {
        DataPage<SysSaasAccount> page = sysSaasAccountService.getListPage(pageable);
		return AjaxResult.success(page);
    }

    /**
     * 导出SAAS账号列表
     */
    @PreAuthorize("@ss.hasPermi('system:account:export')")
    @Log(title = "SAAS账号", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(DataPageable pageable)
    {
    	List<SysSaasAccount> list = sysSaasAccountService.getList(pageable);
		List<PoiField> fields = new ArrayList<>();
		fields.add(new PoiField("id", "ID", 1));
				
		ExcelUtil<SysSaasAccount> util = new ExcelUtil<SysSaasAccount>(SysSaasAccount.class, fields);
		return util.exportExcel(list, "account");
		       
    }

    /**
     * 获取SAAS账号详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:account:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(sysSaasAccountService.getById(id));
    }

    /**
     * 新增SAAS账号
     */
    @PreAuthorize("@ss.hasPermi('system:account:add')")
    @Log(title = "SAAS账号", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysSaasAccount sysSaasAccount)
    {       
        sysSaasAccountService.save(sysSaasAccount);
		return toAjax(1);
    }

    /**
     * 修改SAAS账号
     */
    @PreAuthorize("@ss.hasPermi('system:account:edit')")
    @Log(title = "SAAS账号", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysSaasAccount sysSaasAccount)
    {
        sysSaasAccountService.save(sysSaasAccount);
		return toAjax(1);
    }

    /**
     * 删除SAAS账号
     */
    @PreAuthorize("@ss.hasPermi('system:account:remove')")
    @Log(title = "SAAS账号", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
    	for (Long id : ids) {
			sysSaasAccountService.delete(id);
		}
		return toAjax(ids.length);       
    }
}
