package com.saas.wx.service;

import java.util.concurrent.TimeUnit;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saas.common.core.entity.KeyValue;
import com.saas.common.exception.CustomException;
import com.saas.common.jpa.BaseDao;
import com.saas.common.jpa.BaseService;
import com.saas.uc.constant.UcConstants;
import com.saas.wx.domain.WxQrCode;
import com.saas.wx.repository.WxQrCodeDao;

import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;

@Service
public class WxQrCodeService extends BaseService<WxQrCode> {

	@Autowired
	private WxQrCodeDao modelDao;

	@Autowired
	private WxReplyService wxReplyService;

	@Autowired
	private RedissonClient redissonClient;

	@Override
	protected BaseDao<WxQrCode> getModelDao() {
		return modelDao;
	}

	@Override
	protected boolean isUseLocalCache() {
		return false;
	}

	public WxQrCode getBySceneId(Long sceneId) {
		return this.getFirstModel(new KeyValue("sceneId", sceneId));
	}

	public void processScanEvent(boolean isNewUser, WxQrCode code) throws Exception {
		RLock lock = redissonClient.getLock(UcConstants.LOCK_KEY + code.getSceneId() + "_" + code.getSaasId());
		try {
			boolean isLock = lock.tryLock(5, 100, TimeUnit.SECONDS);
			// 防止并发请求
			if (isLock) {
				String visitsNumKey = this.getModelRedisKey("visitsNum" + code.getId());
				String newUserKey = this.getModelRedisKey("newUserNum" + code.getId());
				Long visitsNum = redisCache.increment(visitsNumKey, 1,
						code.getVisitsNum() == null ? 0l : code.getVisitsNum().longValue(), false);
				code.setVisitsNum(visitsNum.intValue());
				if (isNewUser) {
					Long newUserNum = redisCache.increment(newUserKey, 1, code.getNewUserCount(), false);
					code.setNewUserCount(newUserNum.intValue());
				}
				save(code);
			}
		} catch (CustomException e) {
			e.printStackTrace();
			throw e;
		} finally {
			lock.unlock();
		}
	}

	public WxMpXmlOutMessage buildOutMsg(WxQrCode code, WxMpXmlMessage wxMessage) {
		return wxReplyService.matchMsgReply(code.getContent(), wxMessage);
	}
}
