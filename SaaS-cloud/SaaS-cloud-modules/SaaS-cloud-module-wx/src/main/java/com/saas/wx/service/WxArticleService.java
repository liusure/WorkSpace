package com.saas.wx.service;


import com.saas.wx.domain.WxArticle;
import com.saas.wx.repository.WxArticleDao;
import com.saas.common.jpa.BaseDao;
import com.saas.common.jpa.BaseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 图文素材Service接口
 * 
 * @author bruce
 * @date 2021-01-29
 */
 @Service
public class WxArticleService extends BaseService<WxArticle> {

    @Autowired
	private WxArticleDao modelDao;


	@Override
	protected BaseDao<WxArticle> getModelDao() {
		return modelDao;
	}

	@Override
	protected boolean isUseLocalCache() {
		return false;
	}
}
