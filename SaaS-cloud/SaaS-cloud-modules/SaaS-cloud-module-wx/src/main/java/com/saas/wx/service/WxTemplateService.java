package com.saas.wx.service;


import com.saas.wx.domain.WxTemplate;
import com.saas.wx.repository.WxTemplateDao;
import com.saas.common.jpa.BaseDao;
import com.saas.common.jpa.BaseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 微信模版消息Service接口
 * 
 * @author bruce
 * @date 2021-01-29
 */
 @Service
public class WxTemplateService extends BaseService<WxTemplate> {

    @Autowired
	private WxTemplateDao modelDao;


	@Override
	protected BaseDao<WxTemplate> getModelDao() {
		return modelDao;
	}

	@Override
	protected boolean isUseLocalCache() {
		return false;
	}
}
