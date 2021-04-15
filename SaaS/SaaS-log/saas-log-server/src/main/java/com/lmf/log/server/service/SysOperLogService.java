package com.saas.log.server.service;


import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import com.saas.common.jpa.BaseDao;
import com.saas.common.jpa.BaseService;
import com.saas.log.domain.SysOperLog;
import com.saas.log.repository.SysOperLogDao;
import com.saas.log.service.ISysOperLogService;

/**
 * 操作日志记录Service接口
 * 
 * @author bruce
 * @date 2021-01-08
 */
@DubboService(version = "1.0.0")
public class SysOperLogService extends BaseService<SysOperLog> implements ISysOperLogService{

    @Autowired
	private SysOperLogDao modelDao;


	@Override
	protected BaseDao<SysOperLog> getModelDao() {
		return modelDao;
	}

	@Override
	protected boolean isUseLocalCache() {
		return false;
	}
}