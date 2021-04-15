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
import com.saas.wx.domain.WxReply;
import com.saas.wx.service.WxReplyService;

/**
 * 微信回复规则Controller
 * 
 * @author bruce
 * @date 2021-01-29
 */
@RestController
@RequestMapping("/wx/reply")
public class WxReplyController extends BaseController {
	@Autowired
	private WxReplyService wxReplyService;

	/**
	 * 查询微信回复规则列表
	 */
	@PreAuthorize(hasPermi = "wx:reply:list")
	@GetMapping("/list")
	public AjaxResult list(DataPageable pageable) {
		DataPage<WxReply> page = wxReplyService.getListPage(pageable);
		return AjaxResult.success(page);
	}

	/**
	 * 导出微信回复规则列表
	 */
	@PreAuthorize(hasPermi = "wx:reply:export")
	@Log(title = "微信回复规则", businessType = BusinessType.EXPORT)
	@GetMapping("/export")
	public AjaxResult export(DataPageable pageable) {
		List<WxReply> list = wxReplyService.getList(pageable);
		List<PoiField> fields = new ArrayList<>();
		fields.add(new PoiField("id", "ID", 1));

		ExcelUtil<WxReply> util = new ExcelUtil<WxReply>(WxReply.class, fields);
		return util.exportExcel(list, "reply");

	}

	/**
	 * 获取微信回复规则详细信息
	 */
	@PreAuthorize(hasPermi = "wx:reply:query")
	@GetMapping(value = "/{id}")
	public AjaxResult getInfo(@PathVariable("id") Long id) {
		return AjaxResult.success(wxReplyService.getById(id));
	}

	/**
	 * 新增微信回复规则
	 */
	@PreAuthorize(hasPermi = "wx:reply:add")
	@Log(title = "微信回复规则", businessType = BusinessType.INSERT)
	@PostMapping
	public AjaxResult add(@RequestBody WxReply wxReply) {
		wxReplyService.save(wxReply);
		return toAjax(1);
	}

	/**
	 * 修改微信回复规则
	 */
	@PreAuthorize(hasPermi = "wx:reply:edit")
	@Log(title = "微信回复规则", businessType = BusinessType.UPDATE)
	@PutMapping
	public AjaxResult edit(@RequestBody WxReply wxReply) {
		wxReplyService.save(wxReply);
		return toAjax(1);
	}

	/**
	 * 删除微信回复规则
	 */
	@PreAuthorize(hasPermi = "wx:reply:remove")
	@Log(title = "微信回复规则", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
	public AjaxResult remove(@PathVariable Long[] ids) {
		for (Long id : ids) {
			wxReplyService.delete(id);
		}
		return toAjax(ids.length);
	}
}
