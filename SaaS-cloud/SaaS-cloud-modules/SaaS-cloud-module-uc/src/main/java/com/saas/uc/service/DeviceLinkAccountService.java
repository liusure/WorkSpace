package com.saas.uc.service;

import java.util.Date;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saas.common.core.entity.KeyValue;
import com.saas.common.jpa.BaseDao;
import com.saas.common.jpa.BaseService;
import com.saas.common.util.BeanUtils;
import com.saas.uc.domain.DeviceLinkAccount;
import com.saas.uc.model.LoginUserInfo;
import com.saas.uc.repository.DeviceLinkAccountDao;

@Service
public class DeviceLinkAccountService extends BaseService<DeviceLinkAccount> {
	@Autowired
	private DeviceLinkAccountDao modelDao;

	@Override
	protected BaseDao<DeviceLinkAccount> getModelDao() {
		return modelDao;
	}

	@Override
	protected boolean isUseLocalCache() {
		return true;
	}

	/**
	 * 获取 用户设备
	 *
	 * @param loginUserInfo 登录用户信息
	 */
	public DeviceLinkAccount getOrCreate(LoginUserInfo loginUserInfo) {
		Date currentTime = new Date();
		DeviceLinkAccount deviceAccount = getDeviceLinkAccount(loginUserInfo.getDeviceId(), loginUserInfo.getCert());
		if (deviceAccount == null) {
			logger.info("生成设备账号，但没有用户账号");
			deviceAccount = new DeviceLinkAccount();

			BeanUtils.copyProperties(loginUserInfo, deviceAccount, true);

			deviceAccount.setUserAccountId(null);
			deviceAccount.setCertificates(loginUserInfo.getCert());
			deviceAccount.setLastLoginTime(currentTime);
			deviceAccount.setLastLoginIp(loginUserInfo.getLoginIp());
			save(deviceAccount);
		}
		return deviceAccount;
	}

	/**
	 * 获取设备信息
	 */
	public DeviceLinkAccount getDeviceLinkAccount(String deviceId, String cert) {
		String redisKey = getModelRedisKey("deviceId", deviceId, "cert", cert);
		return (DeviceLinkAccount) redisCache.getValueIfNullSet(redisKey, DEFAULT_TIME_OUT, DEFAULT_TIME_UNIT,
				isUseLocalCache(),
				() -> getUniqueModel(new KeyValue("deviceId", deviceId), new KeyValue("certificates", cert)));

	}

	@Override
	public void modelChangeAfter(DeviceLinkAccount newItem, DeviceLinkAccount oldItem) {
		redisCache.deleteObject(getModelRedisKey("deviceId", newItem.getDeviceId(), "cert", newItem.getCertificates()));
	}

	@Override
	public void modelChangeBefore(DeviceLinkAccount newItem, DeviceLinkAccount oldItem) {
		if (StringUtils.isEmpty(newItem.getCertificates())) {
			newItem.setCertificates(generateCert(newItem.getDeviceId()));
		}
	}

	/**
	 * 生成 cert
	 */
	private String generateCert(String deviceId) {
		String cert = RandomStringUtils.randomAlphabetic(8);
		return cert;
	}
}
