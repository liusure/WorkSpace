package com.saas.uc.controller;

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
import com.saas.uc.domain.UserAccount;
import com.saas.uc.service.UserAccountService;

/**
 * 前端用户Controller
 * 
 * @author bruce
 * @date 2021-01-25
 */
@RestController
@RequestMapping("/uc/account")
public class UserAccountController extends BaseController{
	@Autowired
    private UserAccountService userAccountService;

    /**
     * 查询前端用户列表
     */
    @PreAuthorize(hasPermi="uc:account:list")
    @GetMapping("/list")
    public AjaxResult list(DataPageable pageable)
    {
        DataPage<UserAccount> page = userAccountService.getListPage(pageable);
		return AjaxResult.success(page);
    }

    /**
     * 导出前端用户列表
     */
    @PreAuthorize(hasPermi="uc:account:export")
    @Log(title = "前端用户", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(DataPageable pageable)
    {
    	List<UserAccount> list = userAccountService.getList(pageable);
		List<PoiField> fields = new ArrayList<>();
		fields.add(new PoiField("id", "ID", 1));
				
		ExcelUtil<UserAccount> util = new ExcelUtil<UserAccount>(UserAccount.class, fields);
		return util.exportExcel(list, "account");
		       
    }

    /**
     * 获取前端用户详细信息
     */
    @PreAuthorize(hasPermi="uc:account:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(userAccountService.getById(id));
    }

    /**
     * 新增前端用户
     */
    @PreAuthorize(hasPermi="uc:account:add")
    @Log(title = "前端用户", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody UserAccount userAccount)
    {       
        userAccountService.save(userAccount);
		return toAjax(1);
    }

    /**
     * 修改前端用户
     */
    @PreAuthorize(hasPermi="uc:account:edit")
    @Log(title = "前端用户", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody UserAccount userAccount)
    {
        userAccountService.save(userAccount);
		return toAjax(1);
    }

    /**
     * 删除前端用户
     */
    @PreAuthorize(hasPermi="uc:account:remove")
    @Log(title = "前端用户", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
    	for (Long id : ids) {
			userAccountService.delete(id);
		}
		return toAjax(ids.length);       
    }
}
