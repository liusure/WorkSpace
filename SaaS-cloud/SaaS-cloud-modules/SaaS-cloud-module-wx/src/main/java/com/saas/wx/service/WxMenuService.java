package com.saas.wx.service;


import com.saas.wx.domain.WxMenu;
import com.saas.wx.repository.WxMenuDao;
import com.saas.common.jpa.BaseDao;
import com.saas.common.jpa.BaseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 微信菜单Service接口
 * 
 * @author bruce
 * @date 2021-01-29
 */
 @Service
public class WxMenuService extends BaseService<WxMenu> {

    @Autowired
	private WxMenuDao modelDao;


	@Override
	protected BaseDao<WxMenu> getModelDao() {
		return modelDao;
	}

	@Override
	protected boolean isUseLocalCache() {
		return false;
	}
}
