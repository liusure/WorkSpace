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
import com.saas.wx.domain.WxTemplate;
import com.saas.wx.service.WxTemplateService;


/**
 * 微信模版消息Controller
 * 
 * @author bruce
 * @date 2021-01-29
 */
@RestController
@RequestMapping("/wx/template")
public class WxTemplateController extends BaseController
{
    @Autowired
    private WxTemplateService wxTemplateService;

    /**
     * 查询微信模版消息列表
     */
    @PreAuthorize(hasPermi="wx:template:list")
    @GetMapping("/list")
    public AjaxResult list(DataPageable pageable)
    {
        DataPage<WxTemplate> page = wxTemplateService.getListPage(pageable);
		return AjaxResult.success(page);
    }
    /**
     * 导出微信模版消息列表
     */
    @PreAuthorize(hasPermi="wx:template:export")
    @Log(title = "微信模版消息", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(DataPageable pageable)
    {
    	List<WxTemplate> list = wxTemplateService.getList(pageable);
		List<PoiField> fields = new ArrayList<>();
		fields.add(new PoiField("id", "ID", 1));
				
		ExcelUtil<WxTemplate> util = new ExcelUtil<WxTemplate>(WxTemplate.class, fields);
		return util.exportExcel(list, "template");
		       
    }

    /**
     * 获取微信模版消息详细信息
     */
    @PreAuthorize(hasPermi="wx:template:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(wxTemplateService.getById(id));
    }

    /**
     * 新增微信模版消息
     */
    @PreAuthorize(hasPermi="wx:template:add")
    @Log(title = "微信模版消息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WxTemplate wxTemplate)
    {       
        wxTemplateService.save(wxTemplate);
		return toAjax(1);
    }

    /**
     * 修改微信模版消息
     */
    @PreAuthorize(hasPermi="wx:template:edit")
    @Log(title = "微信模版消息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WxTemplate wxTemplate)
    {
        wxTemplateService.save(wxTemplate);
		return toAjax(1);
    }

    /**
     * 删除微信模版消息
     */
    @PreAuthorize(hasPermi="wx:template:remove")
    @Log(title = "微信模版消息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
    	for (Long id : ids) {
			wxTemplateService.delete(id);
		}
		return toAjax(ids.length);       
    }
}
