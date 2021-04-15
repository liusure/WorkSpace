package com.saas.wx.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saas.common.core.entity.KeyValue;
import com.saas.common.jpa.BaseDao;
import com.saas.common.jpa.BaseService;
import com.saas.wx.domain.WxAuthAccount;
import com.saas.wx.repository.WxAuthAccountDao;

/**
 * 微信授权账号信息Service接口
 * 
 * @author bruce
 * @date 2021-01-21
 */
@Service
public class WxAuthAccountService extends BaseService<WxAuthAccount> {

	@Autowired
	private WxAuthAccountDao modelDao;

	@Override
	protected BaseDao<WxAuthAccount> getModelDao() {
		return modelDao;
	}

	@Override
	protected boolean isUseLocalCache() {
		return true;
	}
	
	@Override  
	public boolean isAutoLinkSaasId() {
		return false;
	}
	

	public WxAuthAccount getBySaasId(Long saasId, int serviceType) {
		return this.getUniqueModel(new KeyValue("saasId", saasId), new KeyValue("serviceType", serviceType));
	}

	public WxAuthAccount getByAuthorizerAppId(String appId) {
		String redisKey = getModelRedisKey("authorizerAppId", appId);
		return (WxAuthAccount) redisCache.getValueIfNullSet(redisKey, DEFAULT_TIME_OUT, DEFAULT_TIME_UNIT,
				isUseLocalCache(), () -> getUniqueModel(new KeyValue("authorizerAppId", appId)));

	}

	@Override
	public void modelChangeAfter(WxAuthAccount newItem, WxAuthAccount oldItem) {
		redisCache.deleteObject(getModelRedisKey("authorizerAppId", newItem.getAuthorizerAppId()));
		redisCache.deleteObject(getModelRedisKey("authorizerAppId", oldItem.getAuthorizerAppId()));
	}

}
