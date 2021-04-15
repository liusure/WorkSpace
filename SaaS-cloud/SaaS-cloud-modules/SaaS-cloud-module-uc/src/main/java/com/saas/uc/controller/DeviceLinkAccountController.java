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
import com.saas.uc.domain.DeviceLinkAccount;
import com.saas.uc.service.DeviceLinkAccountService;

/**
 * 用户设备信息Controller
 * 
 * @author bruce
 * @date 2021-01-25
 */
@RestController
@RequestMapping("/uc/device")
public class DeviceLinkAccountController extends BaseController
{
    @Autowired
    private DeviceLinkAccountService ftUserDeviceService;

    /**
     * 查询用户设备信息列表
     */
    @PreAuthorize(hasPermi="uc:device:list")
    @GetMapping("/list")
    public AjaxResult list(DataPageable pageable)
    {
        DataPage<DeviceLinkAccount> page = ftUserDeviceService.getListPage(pageable);
		return AjaxResult.success(page);
    }

    /**
     * 导出用户设备信息列表
     */
    @PreAuthorize(hasPermi="uc:device:export")
    @Log(title = "用户设备信息", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(DataPageable pageable)
    {
    	List<DeviceLinkAccount> list = ftUserDeviceService.getList(pageable);
		List<PoiField> fields = new ArrayList<>();
		fields.add(new PoiField("id", "ID", 1));
				
		ExcelUtil<DeviceLinkAccount> util = new ExcelUtil<DeviceLinkAccount>(DeviceLinkAccount.class, fields);
		return util.exportExcel(list, "device");
		       
    }

    /**
     * 获取用户设备信息详细信息
     */
    @PreAuthorize(hasPermi="uc:device:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(ftUserDeviceService.getById(id));
    }

    /**
     * 新增用户设备信息
     */
    @PreAuthorize(hasPermi="uc:device:add")
    @Log(title = "用户设备信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody DeviceLinkAccount ftUserDevice)
    {       
        ftUserDeviceService.save(ftUserDevice);
		return toAjax(1);
    }

    /**
     * 修改用户设备信息
     */
    @PreAuthorize(hasPermi="uc:device:edit")
    @Log(title = "用户设备信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody DeviceLinkAccount ftUserDevice)
    {
        ftUserDeviceService.save(ftUserDevice);
		return toAjax(1);
    }

    /**
     * 删除用户设备信息
     */
    @PreAuthorize(hasPermi="uc:device:remove")
    @Log(title = "用户设备信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
    	for (Long id : ids) {
			ftUserDeviceService.delete(id);
		}
		return toAjax(ids.length);       
    }
}