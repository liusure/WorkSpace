package com.saas.system.service;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saas.common.core.domain.SysUser;
import com.saas.common.core.entity.KeyValue;
import com.saas.common.jpa.BaseDao;
import com.saas.common.jpa.BaseService;
import com.saas.common.security.utils.SecurityUtils;
import com.saas.system.repository.SysUserServiceDao;

@Service
public class SysUserService extends BaseService<SysUser> {

	@Autowired
	private SysUserServiceDao modelDao;

	@Override
	protected BaseDao<SysUser> getModelDao() {
		return modelDao;
	}

	@Override
	protected boolean isUseLocalCache() {
		return true;
	}

	public SysUser selectUserByUserName(String userName) {
		return getUniqueModel(new KeyValue("loginName", userName));
	}

	/**
	 * 判断是否超级管理员.
	 */
	private boolean isSupervisor(SysUser user) {
		return ((user.getId() != null) && (user.getId() == 1L));
	}

	@Override
	public SysUser save(SysUser model) {
		if (isSupervisor(model)) {
			logger.warn("操作员{}尝试修改超级管理员用户", SecurityUtils.getUsername());
			throw new ServiceException("不能修改超级管理员用户");
		}

		// 设定安全的密码
		if (StringUtils.isNotBlank(model.getPlainPassword())) {
			model.setPassword(SecurityUtils.encryptPassword(model.getPlainPassword()));
		}

		return super.save(model);
	}

	public static void main(String[] args) {
		System.out.println(SecurityUtils.encryptPassword("123456"));
	}
}
