package com.saas.log.server.service;

import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import com.saas.common.jpa.BaseDao;
import com.saas.common.jpa.BaseService;
import com.saas.log.domain.SysLogininfor;
import com.saas.log.repository.SysLogininforDao;
import com.saas.log.service.ISysLogininforService;

@DubboService(version = "1.0.0")
public class SysLogininforService extends BaseService<SysLogininfor> implements ISysLogininforService{
	@Autowired
	private SysLogininforDao modelDao;

	@Override
	protected BaseDao<SysLogininfor> getModelDao() {
		return modelDao;
	}

	@Override
	protected boolean isUseLocalCache() {
		return false;
	}
}
