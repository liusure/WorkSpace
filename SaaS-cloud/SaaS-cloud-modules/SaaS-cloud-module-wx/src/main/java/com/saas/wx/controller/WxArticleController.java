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
import com.saas.wx.domain.WxArticle;
import com.saas.wx.service.WxArticleService;


/**
 * 图文素材Controller
 * 
 * @author bruce
 * @date 2021-01-29
 */
@RestController
@RequestMapping("/wx/article")
public class WxArticleController extends BaseController
{
    @Autowired
    private WxArticleService wxArticleService;

    /**
     * 查询图文素材列表
     */
    @PreAuthorize(hasPermi="wx:article:list")
    @GetMapping("/list")
    public AjaxResult list(DataPageable pageable)
    {
        DataPage<WxArticle> page = wxArticleService.getListPage(pageable);
		return AjaxResult.success(page);
    }

    /**
     * 导出图文素材列表
     */
    @PreAuthorize(hasPermi="wx:article:export")
    @Log(title = "图文素材", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(DataPageable pageable)
    {
    	List<WxArticle> list = wxArticleService.getList(pageable);
		List<PoiField> fields = new ArrayList<>();
		fields.add(new PoiField("id", "ID", 1));
				
		ExcelUtil<WxArticle> util = new ExcelUtil<WxArticle>(WxArticle.class, fields);
		return util.exportExcel(list, "article");
		       
    }

    /**
     * 获取图文素材详细信息
     */
    @PreAuthorize(hasPermi="wx:article:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(wxArticleService.getById(id));
    }

    /**
     * 新增图文素材
     */
    @PreAuthorize(hasPermi="wx:article:add")
    @Log(title = "图文素材", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WxArticle wxArticle)
    {       
        wxArticleService.save(wxArticle);
		return toAjax(1);
    }

    /**
     * 修改图文素材
     */
    @PreAuthorize(hasPermi="wx:article:edit")
    @Log(title = "图文素材", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WxArticle wxArticle)
    {
        wxArticleService.save(wxArticle);
		return toAjax(1);
    }

    /**
     * 删除图文素材
     */
    @PreAuthorize(hasPermi="wx:article:remove")
    @Log(title = "图文素材", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
    	for (Long id : ids) {
			wxArticleService.delete(id);
		}
		return toAjax(ids.length);       
    }
}
