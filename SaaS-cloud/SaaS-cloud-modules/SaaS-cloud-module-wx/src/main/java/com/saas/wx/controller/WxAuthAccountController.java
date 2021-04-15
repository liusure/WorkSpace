package com.saas.wx.controller;

import java.util.ArrayList;
import java.util.List;

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
import com.saas.wx.domain.WxAuthAccount;
import com.saas.wx.service.WxAuthAccountService;


/**
 * 微信授权账号信息Controller
 * 
 * @author bruce
 * @date 2021-01-21
 */
@RestController
@RequestMapping("/wxaccount")
public class WxAuthAccountController extends BaseController
{
    @Autowired
    private WxAuthAccountService wxAuthAccountService;

    /**
     * 查询微信授权账号信息列表
     */
    @PreAuthorize(hasPermi="system:wxaccount:list")
    @GetMapping("/list")
    public AjaxResult list(DataPageable pageable)
    {
        DataPage<WxAuthAccount> page = wxAuthAccountService.getListPage(pageable);
		return AjaxResult.success(page);
    }

    /**
     * 导出微信授权账号信息列表
     */
    @PreAuthorize(hasPermi="system:wxaccount:export")
    @Log(title = "微信授权账号信息", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(DataPageable pageable)
    {
    	List<WxAuthAccount> list = wxAuthAccountService.getList(pageable);
		List<PoiField> fields = new ArrayList<>();
		fields.add(new PoiField("id", "ID", 1));
				
		ExcelUtil<WxAuthAccount> util = new ExcelUtil<WxAuthAccount>(WxAuthAccount.class, fields);
		return util.exportExcel(list, "account");
		       
    }

    /**
     * 获取微信授权账号信息详细信息
     */
    @PreAuthorize(hasPermi="system:wxaccount:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(wxAuthAccountService.getById(id));
    }

    /**
     * 新增微信授权账号信息
     */
    @PreAuthorize(hasPermi="system:wxaccount:add")
    @Log(title = "微信授权账号信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WxAuthAccount wxAuthAccount)
    {       
        wxAuthAccountService.save(wxAuthAccount);
		return toAjax(1);
    }

    /**
     * 修改微信授权账号信息
     */
    @PreAuthorize(hasPermi="system:wxaccount:edit")
    @Log(title = "微信授权账号信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WxAuthAccount wxAuthAccount)
    {
        wxAuthAccountService.save(wxAuthAccount);
		return toAjax(1);
    }

    /**
     * 删除微信授权账号信息
     */
    @PreAuthorize(hasPermi="system:wxaccount:remove")
    @Log(title = "微信授权账号信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
    	for (Long id : ids) {
			wxAuthAccountService.delete(id);
		}
		return toAjax(ids.length);       
    }
}
