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
import com.saas.wx.domain.WxMenu;
import com.saas.wx.service.WxMenuService;

/**
 * 微信菜单Controller
 * 
 * @author bruce
 * @date 2021-01-29
 */
@RestController
@RequestMapping("/wx/menu")
public class WxMenuController extends BaseController {
	@Autowired
	private WxMenuService wxMenuService;

	/**
	 * 查询微信菜单列表
	 */
	@PreAuthorize(hasPermi = "wx:menu:list")
	@GetMapping("/list")
	public AjaxResult list(DataPageable pageable) {
		DataPage<WxMenu> page = wxMenuService.getListPage(pageable);
		return AjaxResult.success(page);
	}

	/**
	 * 导出微信菜单列表
	 */
	@PreAuthorize(hasPermi = "wx:menu:export")
	@Log(title = "微信菜单", businessType = BusinessType.EXPORT)
	@GetMapping("/export")
	public AjaxResult export(DataPageable pageable) {
		List<WxMenu> list = wxMenuService.getList(pageable);
		List<PoiField> fields = new ArrayList<>();
		fields.add(new PoiField("id", "ID", 1));

		ExcelUtil<WxMenu> util = new ExcelUtil<WxMenu>(WxMenu.class, fields);
		return util.exportExcel(list, "menu");

	}

	/**
	 * 获取微信菜单详细信息
	 */
	@PreAuthorize(hasPermi = "wx:menu:query")
	@GetMapping(value = "/{id}")
	public AjaxResult getInfo(@PathVariable("id") Long id) {
		return AjaxResult.success(wxMenuService.getById(id));
	}

	/**
	 * 新增微信菜单
	 */
	@PreAuthorize(hasPermi = "wx:menu:add")
	@Log(title = "微信菜单", businessType = BusinessType.INSERT)
	@PostMapping
	public AjaxResult add(@RequestBody WxMenu wxMenu) {
		wxMenuService.save(wxMenu);
		return toAjax(1);
	}

	/**
	 * 修改微信菜单
	 */
	@PreAuthorize(hasPermi = "wx:menu:edit")
	@Log(title = "微信菜单", businessType = BusinessType.UPDATE)
	@PutMapping
	public AjaxResult edit(@RequestBody WxMenu wxMenu) {
		wxMenuService.save(wxMenu);
		return toAjax(1);
	}

	/**
	 * 删除微信菜单
	 */
	@PreAuthorize(hasPermi = "wx:menu:remove")
	@Log(title = "微信菜单", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
	public AjaxResult remove(@PathVariable Long[] ids) {
		for (Long id : ids) {
			wxMenuService.delete(id);
		}
		return toAjax(ids.length);
	}
}
