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
import com.saas.wx.domain.WxMedia;
import com.saas.wx.service.WxMediaService;


/**
 * 媒体素材Controller
 * 
 * @author bruce
 * @date 2021-01-29
 */
@RestController
@RequestMapping("/wx/media")
public class WxMediaController extends BaseController
{
    @Autowired
    private WxMediaService wxMediaService;

    /**
     * 查询媒体素材列表
     */
    @PreAuthorize(hasPermi="wx:media:list")
    @GetMapping("/list")
    public AjaxResult list(DataPageable pageable)
    {
        DataPage<WxMedia> page = wxMediaService.getListPage(pageable);
		return AjaxResult.success(page);
    }

    /**
     * 导出媒体素材列表
     */
    @PreAuthorize(hasPermi="wx:media:export")
    @Log(title = "媒体素材", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(DataPageable pageable)
    {
    	List<WxMedia> list = wxMediaService.getList(pageable);
		List<PoiField> fields = new ArrayList<>();
		fields.add(new PoiField("id", "ID", 1));
				
		ExcelUtil<WxMedia> util = new ExcelUtil<WxMedia>(WxMedia.class, fields);
		return util.exportExcel(list, "media");
		       
    }

    /**
     * 获取媒体素材详细信息
     */
    @PreAuthorize(hasPermi="wx:media:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(wxMediaService.getById(id));
    }

    /**
     * 新增媒体素材
     */
    @PreAuthorize(hasPermi="wx:media:add")
    @Log(title = "媒体素材", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WxMedia wxMedia)
    {       
        wxMediaService.save(wxMedia);
		return toAjax(1);
    }

    /**
     * 修改媒体素材
     */
    @PreAuthorize(hasPermi="wx:media:edit")
    @Log(title = "媒体素材", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WxMedia wxMedia)
    {
        wxMediaService.save(wxMedia);
		return toAjax(1);
    }

    /**
     * 删除媒体素材
     */
    @PreAuthorize(hasPermi="wx:media:remove")
    @Log(title = "媒体素材", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
    	for (Long id : ids) {
			wxMediaService.delete(id);
		}
		return toAjax(ids.length);       
    }
}
