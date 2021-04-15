/**
 * 
 */
package com.saas.system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saas.common.jpa.BaseDao;
import com.saas.common.jpa.BaseService;
import com.saas.system.domain.SysDictData;
import com.saas.system.repository.SysDictDataDao;

/**
 * @author bruce
 *
 */
@Service
public class SysDictDataService extends BaseService<SysDictData> {
	@Autowired
	private SysDictDataDao modelDao;

	@Override
	protected BaseDao<SysDictData> getModelDao() {
		return modelDao;
	}

	@Override
	protected boolean isUseLocalCache() {
		return true;
	}
}
