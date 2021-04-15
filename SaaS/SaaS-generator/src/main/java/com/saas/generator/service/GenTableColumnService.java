package com.saas.generator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saas.common.jpa.BaseDao;
import com.saas.common.jpa.BaseService;
import com.saas.generator.domain.GenTableColumn;
import com.saas.generator.repository.GenTableColumnDao;

@Service
public class GenTableColumnService extends BaseService<GenTableColumn> {

	@Autowired
	private GenTableColumnDao modelDao;

	@Override
	protected BaseDao<GenTableColumn> getModelDao() {

		return modelDao;
	}

	@Override
	protected boolean isUseLocalCache() {
		return false;
	}
	
	@Override
	public void physicalDelete(GenTableColumn model) {
		super.physicalDelete(model);
	}

}
