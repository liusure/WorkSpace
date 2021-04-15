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
import com.saas.uc.domain.UserSnsAccount;
import com.saas.uc.service.UserSnsAccountService;

@RestController
@RequestMapping("/uc/sns")
public class UserSnsAccountController extends BaseController {
	@Autowired
	private UserSnsAccountService userSnsAccountService;

	/**
	 * 查询社交账号列表
	 */
	@PreAuthorize(hasPermi="uc:sns:list")
	@GetMapping("/list")
	public AjaxResult list(DataPageable pageable) {
		DataPage<UserSnsAccount> page = userSnsAccountService.getListPage(pageable);
		return AjaxResult.success(page);
	}

	/**
	 * 导出社交账号列表
	 */
	@PreAuthorize(hasPermi="uc:sns:export")
	@Log(title = "社交账号", businessType = BusinessType.EXPORT)
	@GetMapping("/export")
	public AjaxResult export(DataPageable pageable) {
		List<UserSnsAccount> list = userSnsAccountService.getList(pageable);
		List<PoiField> fields = new ArrayList<>();
		fields.add(new PoiField("id", "ID", 1));

		ExcelUtil<UserSnsAccount> util = new ExcelUtil<UserSnsAccount>(UserSnsAccount.class, fields);
		return util.exportExcel(list, "sns");

	}

	/**
	 * 获取社交账号详细信息
	 */
	@PreAuthorize(hasPermi="uc:sns:query")
	@GetMapping(value = "/{id}")
	public AjaxResult getInfo(@PathVariable("id") Long id) {
		return AjaxResult.success(userSnsAccountService.getById(id));
	}

	/**
	 * 新增社交账号
	 */
	@PreAuthorize(hasPermi="uc:sns:add")
	@Log(title = "社交账号", businessType = BusinessType.INSERT)
	@PostMapping
	public AjaxResult add(@RequestBody UserSnsAccount ftUserSns) {
		userSnsAccountService.save(ftUserSns);
		return toAjax(1);
	}

	/**
	 * 修改社交账号
	 */
	@PreAuthorize(hasPermi="uc:sns:edit")
	@Log(title = "社交账号", businessType = BusinessType.UPDATE)
	@PutMapping
	public AjaxResult edit(@RequestBody UserSnsAccount ftUserSns) {
		userSnsAccountService.save(ftUserSns);
		return toAjax(1);
	}

	/**
	 * 删除社交账号
	 */
	@PreAuthorize(hasPermi = "uc:sns:remove")
	@Log(title = "社交账号", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
	public AjaxResult remove(@PathVariable Long[] ids) {
		for (Long id : ids) {
			userSnsAccountService.delete(id);
		}
		return toAjax(ids.length);
	}
}
