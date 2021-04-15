package com.saas.admin.controller.system;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.saas.admin.service.SysUserService;
import com.saas.common.core.controller.BaseController;
import com.saas.common.core.domain.SysRole;
import com.saas.common.core.domain.SysUser;
import com.saas.common.core.entity.AjaxResult;
import com.saas.common.core.page.DataPage;
import com.saas.common.core.page.DataPageable;
import com.saas.common.enums.BusinessType;
import com.saas.common.security.utils.SecurityUtils;
import com.saas.common.util.poi.ExcelUtil;
import com.saas.common.util.poi.PoiField;
import com.saas.log.annotation.Log;
import com.saas.system.service.SysRoleService;

/**
 * 用户信息
 * 
 */
@RestController
@RequestMapping("/system/user")
public class SysUserController extends BaseController {
	@Autowired
	private SysUserService modelService;

	@Autowired
	private SysRoleService roleService;

	/**
	 * 获取用户列表
	 */
	@PreAuthorize("@ss.hasPermi('system:user:list')")
	@GetMapping("/list")
	public AjaxResult list(DataPageable pageable) {
		DataPage<SysUser> page = modelService.getListPage(pageable);
		return AjaxResult.success(page);
	}

	@Log(title = "用户管理", businessType = BusinessType.EXPORT)
	@PreAuthorize("@ss.hasPermi('system:user:export')")
	@GetMapping("/export")
	public AjaxResult export(DataPageable pageable) {
		List<SysUser> list = modelService.getList(pageable);
		List<PoiField> fields = new ArrayList<>();
		fields.add(new PoiField("id", "ID", 1));
		fields.add(new PoiField("name", "名称", 2));

		PoiField field = new PoiField();
		field = new PoiField("sex", "性别", 3);
		field.setReadConverterExp("0=男,1=女,2=未知");
		fields.add(field);
		ExcelUtil<SysUser> util = new ExcelUtil<SysUser>(SysUser.class, fields);
		return util.exportExcel(list, "用户数据");
	}

	@Log(title = "用户管理", businessType = BusinessType.IMPORT)
	@PreAuthorize("@ss.hasPermi('system:user:import')")
	@PostMapping("/importData")
	public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception {
		List<PoiField> fields = new ArrayList<>();
		fields.add(new PoiField("id", "ID", 1));
		fields.add(new PoiField("name", "名称", 2));

		PoiField field = new PoiField();
		field = new PoiField("sex", "性别", 3);
		field.setReadConverterExp("0=男,1=女,2=未知");
		fields.add(field);
		ExcelUtil<SysUser> util = new ExcelUtil<SysUser>(SysUser.class, fields);

		List<SysUser> userList = util.importExcel(file.getInputStream());

		return AjaxResult.success(userList);
	}

	@GetMapping("/importTemplate")
	public AjaxResult importTemplate() {
		List<PoiField> fields = new ArrayList<>();
		fields.add(new PoiField("id", "ID", 1));
		fields.add(new PoiField("name", "名称", 2));

		PoiField field = new PoiField();
		field = new PoiField("sex", "性别", 3);
		field.setReadConverterExp("0=男,1=女,2=未知");
		fields.add(field);
		ExcelUtil<SysUser> util = new ExcelUtil<SysUser>(SysUser.class, fields);
		return util.importTemplateExcel("用户数据");
	}

	/**
	 * 根据用户编号获取详细信息
	 */
	@PreAuthorize("@ss.hasPermi('system:user:query')")
	@GetMapping(value = { "/", "/{userId}" })
	public AjaxResult getInfo(@PathVariable(value = "userId", required = false) Long userId) {
		AjaxResult ajax = AjaxResult.success();
		List<SysRole> roles = roleService.getAllModels();
		ajax.put("roles", roles);
		if (null != userId) {
			ajax.put(AjaxResult.DATA_TAG, modelService.getById(userId));

		}
		return ajax;
	}

	/**
	 * 新增用户
	 */
	@PreAuthorize("@ss.hasPermi('system:user:add')")
	@Log(title = "用户管理", businessType = BusinessType.INSERT)
	@PostMapping
	public AjaxResult add(@Validated @RequestBody SysUser user) {
		if (!modelService.checkFieldUnique(user, "loginName")) {
			return AjaxResult.error("新增用户'" + user.getLoginName() + "'失败，登录账号已存在");
		} else if (StringUtils.isNotEmpty(user.getMobile()) && !modelService.checkFieldUnique(user, "mobile")) {
			return AjaxResult.error("新增用户'" + user.getLoginName() + "'失败，手机号码已存在");
		} else if (StringUtils.isNotEmpty(user.getEmail()) && !modelService.checkFieldUnique(user, "email")) {
			return AjaxResult.error("新增用户'" + user.getLoginName() + "'失败，邮箱账号已存在");
		}
		if (user.getRoleIds() != null && user.getRoleIds().length > 0) {
			List<SysRole> roleList = new ArrayList<>();
			for (int i = 0; i < user.getRoleIds().length; i++) {
				roleList.add(new SysRole(user.getRoleIds()[i]));
			}
			user.setRoleList(roleList);
		} else {
			user.setRoleList(new ArrayList<>());
		}
		modelService.save(user);
		return toAjax(1);
	}

	/**
	 * 修改用户
	 */
	@PreAuthorize("@ss.hasPermi('system:user:edit')")
	@Log(title = "用户管理", businessType = BusinessType.UPDATE)
	@PutMapping
	public AjaxResult edit(@Validated @RequestBody SysUser user) {
		if (!modelService.checkFieldUnique(user, "loginName")) {
			return AjaxResult.error("新增用户'" + user.getLoginName() + "'失败，登录账号已存在");
		} else if (StringUtils.isNotEmpty(user.getMobile()) && !modelService.checkFieldUnique(user, "mobile")) {
			return AjaxResult.error("新增用户'" + user.getLoginName() + "'失败，手机号码已存在");
		} else if (StringUtils.isNotEmpty(user.getEmail()) && !modelService.checkFieldUnique(user, "email")) {
			return AjaxResult.error("新增用户'" + user.getLoginName() + "'失败，邮箱账号已存在");
		}

//		user.setPassword(SecurityUtils.encryptPassword(user.getPassword()));
		if (user.getRoleIds() != null && user.getRoleIds().length > 0) {
			List<SysRole> roleList = new ArrayList<>();
			for (int i = 0; i < user.getRoleIds().length; i++) {
				roleList.add(new SysRole(user.getRoleIds()[i]));
			}
			user.setRoleList(roleList);
		} else {
			user.setRoleList(new ArrayList<>());
		}
		SysUser dbUser = modelService.getById(user.getId());
		user.setPassword(dbUser.getPassword());
		modelService.save(user);
		return toAjax(1);
	}

	/**
	 * 删除用户
	 */
	@PreAuthorize("@ss.hasPermi('system:user:remove')")
	@Log(title = "用户管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{userIds}")
	public AjaxResult remove(@PathVariable Long[] userIds) {
		for (Long userId : userIds) {
			modelService.delete(userId);
		}
		return toAjax(userIds.length);
	}

	/**
	 * 重置密码
	 */
	@PreAuthorize("@ss.hasPermi('system:user:resetPwd')")
	@Log(title = "用户管理", businessType = BusinessType.UPDATE)
	@PutMapping("/resetPwd")
	public AjaxResult resetPwd(@RequestBody SysUser user) {
		SysUser model = modelService.getById(user.getId());
		model.setPlainPassword(user.getPlainPassword());
		modelService.save(model);
		return toAjax(1);
	}

	/**
	 * 状态修改
	 */
	@PreAuthorize("@ss.hasPermi('system:user:edit')")
	@Log(title = "用户管理", businessType = BusinessType.UPDATE)
	@PutMapping("/changeStatus")
	public AjaxResult changeStatus(@RequestBody SysUser user) {
		SysUser model = modelService.getById(user.getId());
		if (model.getStatusFlag() != user.getStatusFlag()) {
			model.setStatusFlag(user.getStatusFlag());
		}
		modelService.save(model);
		return toAjax(1);
	}
}
