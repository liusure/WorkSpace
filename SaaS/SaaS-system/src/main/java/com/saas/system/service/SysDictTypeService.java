package com.saas.system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saas.common.core.entity.KeyValue;
import com.saas.common.dict.DictService;
import com.saas.common.jpa.BaseDao;
import com.saas.common.jpa.BaseService;
import com.saas.system.domain.SysDictData;
import com.saas.system.domain.SysDictType;
import com.saas.system.repository.SysDictTypeDao;

@Service
public class SysDictTypeService extends BaseService<SysDictType> implements DictService {
	@Autowired
	private SysDictTypeDao modelDao;

	@Autowired
	private SysDictDataService sysDictDataService;

	@Override
	protected BaseDao<SysDictType> getModelDao() {
		return modelDao;
	}

	@Override
	protected boolean isUseLocalCache() {
		return true;
	}

	public void clearCache() {

	}

	@Override
	public String getDictLabel(String dictValue, String dictType) {
		SysDictData data = sysDictDataService.getUniqueModel(new KeyValue("dictType", dictType),
				new KeyValue("dictValue", dictValue));
		return data == null ? null : data.getDictLabel();
	}

	@Override
	public String getDictValue(String dictLabel, String dictType) {
		SysDictData data = sysDictDataService.getUniqueModel(new KeyValue("dictType", dictType),
				new KeyValue("dictLabel", dictLabel));
		return data == null ? null : data.getDictValue();
	}
}
