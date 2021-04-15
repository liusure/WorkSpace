package com.saas.wx.service;


import com.saas.wx.domain.WxMedia;
import com.saas.wx.repository.WxMediaDao;
import com.saas.common.jpa.BaseDao;
import com.saas.common.jpa.BaseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 媒体素材Service接口
 * 
 * @author bruce
 * @date 2021-01-29
 */
 @Service
public class WxMediaService extends BaseService<WxMedia> {

    @Autowired
	private WxMediaDao modelDao;


	@Override
	protected BaseDao<WxMedia> getModelDao() {
		return modelDao;
	}

	@Override
	protected boolean isUseLocalCache() {
		return false;
	}
}
