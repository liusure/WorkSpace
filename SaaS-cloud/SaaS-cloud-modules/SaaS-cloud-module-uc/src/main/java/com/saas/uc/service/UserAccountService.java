/**
 * 
 */
package com.saas.uc.service;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.saas.common.constant.DomainConstants;
import com.saas.common.core.entity.KeyValue;
import com.saas.common.exception.CustomException;
import com.saas.common.jpa.BaseDao;
import com.saas.common.jpa.BaseService;
import com.saas.common.util.BeanUtils;
import com.saas.uc.constant.UcConstants;
import com.saas.uc.domain.DeviceLinkAccount;
import com.saas.uc.domain.UserAccount;
import com.saas.uc.domain.UserAccountImport;
import com.saas.uc.domain.UserSnsAccount;
import com.saas.uc.model.CurrentUserInfo;
import com.saas.uc.model.LoginUserInfo;
import com.saas.uc.model.UserInfo;
import com.saas.uc.repository.UserAccountDao;

/**
 * @author bruce
 *
 */
@Service
public class UserAccountService extends BaseService<UserAccount> {
	@Autowired
	private UserAccountDao modelDao;

	@Autowired
	private RedissonClient redissonClient;

	@Autowired
	private DeviceLinkAccountService deviceLinkAccountService;

	@Autowired
	private UserSnsAccountService userSnsAccountService;

	@Autowired
	private UserAccountImportService userAccountImportService;

	@Override
	protected BaseDao<UserAccount> getModelDao() {
		return modelDao;
	}

	@Override
	protected boolean isUseLocalCache() {
		return false;
	}

	@Transactional(rollbackFor = Exception.class)
	public CurrentUserInfo login(LoginUserInfo loginUserInfoVO) throws Exception {
		logger.info("登录信息{}", loginUserInfoVO);

		RLock lock = redissonClient.getLock(UcConstants.LOCK_KEY + loginUserInfoVO.getDeviceId());

		try {
			boolean isLock = lock.tryLock(5, 100, TimeUnit.SECONDS);
			// 防止并发请求生成多个账号

			if (isLock) {

				// 获取 设备用户 、 账号用户
				LoginUserInfo userInfo = new LoginUserInfo();
				BeanUtils.copyProperties(loginUserInfoVO, userInfo);

				Date currentTime = new Date();
				DeviceLinkAccount deviceAccount = deviceLinkAccountService.getOrCreate(userInfo);
				UserAccount userAccount = getOrCreate(userInfo);

				if (deviceAccount.getLastLoginTime() == null
						|| ((currentTime.getTime() - deviceAccount.getLastLoginTime().getTime()) > 3600000)) {
					logger.info("超过1小时变更登录记录");
					deviceAccount.setLastLoginIp(deviceAccount.getLoginIp());
					deviceAccount.setLoginIp(loginUserInfoVO.getLoginIp());
					deviceAccount.setLastLoginTime(deviceAccount.getLoginTime());
					deviceAccount.setLoginTime(currentTime);

					deviceLinkAccountService.save(deviceAccount);

					userAccount.setLastLoginTime(currentTime);
					userAccount.setLastDeviceAccountId(deviceAccount.getId());
					userAccount.setLastDeviceType(loginUserInfoVO.getDeviceType());
					save(userAccount);
				}

				CurrentUserInfo currentUserInfo = new CurrentUserInfo();
				DeviceLinkAccount deviceLinkAccount = deviceLinkAccountService
						.getById(userAccount.getLastDeviceAccountId());

				currentUserInfo.setUserDevice(deviceLinkAccount);

				logger.info("登录成功");
				return currentUserInfo;
			}
		} catch (CustomException e) {
			e.printStackTrace();
			throw e;
		} finally {
			lock.unlock();
		}
		return null;
	}

	/**
	 * 登录 获取 用户
	 *
	 * @param loginUserInfo 登录用户信息
	 * @return 用户
	 */
	public UserAccount getOrCreate(LoginUserInfo loginUserInfo) {
		// 获取 设备用户 、 账号用户
		DeviceLinkAccount deviceAccount = deviceLinkAccountService.getOrCreate(loginUserInfo);

		// 获取设备对应的 用户账号
		UserAccount userAccount = null;
		if (deviceAccount.getUserAccountId() != null) {
			userAccount = getById(deviceAccount.getUserAccountId());

		}
		boolean isNewUser = false;
		if (userAccount == null) {
			logger.info("生成匿名账号");
			isNewUser = true;
			userAccount = new UserAccount();
			userAccount.setNickname("访客" + deviceAccount.getId());
			userAccount.setUserType(UserAccount.USER_TYPE_PRIVATE);
			userAccount.setRegisterDeviceAccountId(deviceAccount.getId());
			userAccount.setLastLoginTime(new Date());
			userAccount.setLastDeviceAccountId(deviceAccount.getId());
			userAccount.setLastDeviceType(loginUserInfo.getDeviceType());
			userAccount.setStatusFlag(DomainConstants.DOMAIN__STATUS_PUBLISH);
			userAccount.setBindFlag(false);
			userAccount.setSex(0);
			save(userAccount);
			deviceAccount.setUserAccountId(userAccount.getId());
			deviceLinkAccountService.save(deviceAccount);

		}
		UserAccountImport ucImport = userAccountImportService.getById(userAccount.getId());
		// 0 未锁粉 未锁粉状态下可以改变邀请人ID
		boolean saveImportFlag = false;
		if (!ucImport.isLockInvite() && loginUserInfo.getInvite() != null
				&& !loginUserInfo.getInvite().equals(userAccount.getId())) {
			UserAccountImport inviteAccount = userAccountImportService.getById(loginUserInfo.getInvite());
			if (inviteAccount != null && inviteAccount.getUserRank() > 0) {
				ucImport.setInvite(loginUserInfo.getInvite());
				saveImportFlag = true;
			}
		}
		if (isNewUser) {
			ucImport.setChannelCode(loginUserInfo.getChannelCode());
			ucImport.setSceneNo(loginUserInfo.getSceneNo());
			saveImportFlag = true;
		}
		if (saveImportFlag)
			userAccountImportService.save(ucImport);

		if (deviceAccount.getUserAccountId() == null) {
			deviceAccount.setUserAccountId(userAccount.getId());
			deviceLinkAccountService.save(deviceAccount);
		}
		return userAccount;
	}

	@Transactional(rollbackFor = Exception.class)
	public DeviceLinkAccount updateUser(UserInfo userInfo) throws Exception {
		UserAccount userAccount = null;
		DeviceLinkAccount deviceAccount = null;
		if (StringUtils.isNotEmpty(userInfo.getOpenId())) {
			UserSnsAccount userSnsAccount = userSnsAccountService.getUniqueModel(
					new KeyValue("openid", userInfo.getSaasId()), new KeyValue("snsType", userInfo.getSnsType()));
			BeanUtils.copyProperties(userInfo, userSnsAccount, true);
			userSnsAccountService.save(userSnsAccount);
			userAccount = getById(userSnsAccount.getUserAccountId());
		} else {
			String deviceId = userInfo.getDeviceId();
			String cert = userInfo.getCert();
			deviceAccount = deviceLinkAccountService.getDeviceLinkAccount(deviceId, cert);
			userAccount = getById(deviceAccount.getUserAccountId());
		}
		if (deviceAccount == null) {
			String deviceId = userInfo.getDeviceId();
			String cert = userInfo.getCert();
			if (StringUtils.isNotEmpty(cert) && StringUtils.isNotEmpty(deviceId))
				deviceAccount = deviceLinkAccountService.getDeviceLinkAccount(deviceId, cert);
			else
				deviceAccount = deviceLinkAccountService.getFirstModel(
						new KeyValue("deviceId",
								UserSnsAccount.getDeviceId(userInfo.getOpenId(), userInfo.getSnsType())),
						new KeyValue("userAccountId", userAccount.getId()));
		}

		BeanUtils.copyProperties(userInfo, userAccount, true);
		userAccount.setBindFlag(true);
		save(userAccount);
		return deviceAccount;
	}

	@Transactional(rollbackFor = Exception.class)
	public DeviceLinkAccount bindUser(UserInfo userInfo) throws Exception {
		String openId = userInfo.getOpenId();
		String unionId = userInfo.getUnionId();
		int snsType = userInfo.getSnsType();

		RLock lock = redissonClient.getLock(UcConstants.LOCK_KEY + openId);

		try {
			boolean isLock = lock.tryLock(5, 100, TimeUnit.SECONDS);
			// 防止并发请求生成多个账号

			if (isLock) {
				Date currentTime = new Date();
				String deviceId = UserSnsAccount.getDeviceId(openId, snsType);
				LoginUserInfo loginUserInfo = new LoginUserInfo();

				loginUserInfo.setDeviceId(deviceId);

				BeanUtils.copyProperties(userInfo, loginUserInfo, true);

				UserSnsAccount userSnsAccount = userSnsAccountService.getUniqueModel(new KeyValue("openid", openId),
						new KeyValue("snsType", snsType));
				UserAccount userAccount = null;
				if (userSnsAccount != null) {
					userAccount = getById(userSnsAccount.getUserAccountId());
				} else if (StringUtils.isNotEmpty(unionId)) {
					userAccount = getUniqueModel(new KeyValue("unionId", unionId));
				}
				boolean isNewUser = false;
				if (userAccount == null) {
					isNewUser = true;
					userAccount = new UserAccount();
					BeanUtils.copyProperties(userInfo, userAccount, true);

					userAccount.setUserType(UserAccount.USER_TYPE_PRIVATE);
					userAccount.setCreateTime(currentTime);
					userAccount.setLastLoginTime(currentTime);
					userAccount.setLastSnsType(UserSnsAccount.SNS_WXMINIAPP);
					userAccount.setUnionId(unionId);
					userAccount.setRegisterDeviceId(deviceId);
					userAccount.setStatusFlag(DomainConstants.DOMAIN__STATUS_PUBLISH);
					userAccount.setBindFlag(true);
					
					save(userAccount);
				} 

				if (userSnsAccount == null) {
					userSnsAccount = new UserSnsAccount();
					userSnsAccount.setOpenid(openId);
					userSnsAccount.setSnsType(snsType);
					userSnsAccount.setStatusFlag(DomainConstants.DOMAIN__STATUS_PUBLISH);
					userSnsAccount.setUnionId(unionId);
					userSnsAccount.setUserAccountId(userAccount.getId());
					userSnsAccount.setSubscribe(userInfo.getSubscribe() == null ? false : userInfo.getSubscribe());
					userSnsAccount.setSnsName(userInfo.getNickname());
					userSnsAccount.setSubscribeTime(userInfo.getSubscribeTime());
					userSnsAccountService.save(userSnsAccount);
				}
				userAccount.setNickname(userInfo.getNickname());
				userAccount.setCountry(userInfo.getCountry());
				userAccount.setCity(userInfo.getCity());
				userAccount.setHeadImgUrl(userInfo.getHeadImgUrl());
				userAccount.setProvince(userInfo.getProvince());
				userAccount.setSubscribe(userInfo.getSubscribe() == null ? false : userInfo.getSubscribe());
				userAccount.setSubscribeTime(userInfo.getSubscribeTime());

				userAccount.setLastLoginTime(currentTime);
				userAccount.setLastSnsType(UserSnsAccount.SNS_WXMINIAPP);
				userAccount.setLastDeviceId(deviceId);
				userAccount.setBindFlag(true);
				userAccount.setLastSnsAccountId(userSnsAccount.getId());
				userAccount.setLastSnsType(snsType);
				save(userAccount);
				
				UserAccountImport ucImport = userAccountImportService.getById(userAccount.getId());
				// 0 未锁粉 未锁粉状态下可以改变邀请人ID
				boolean saveImportFlag = false;
				if (!ucImport.isLockInvite() && loginUserInfo.getInvite() != null
						&& !loginUserInfo.getInvite().equals(userAccount.getId())) {
					UserAccountImport inviteAccount = userAccountImportService.getById(loginUserInfo.getInvite());
					if (inviteAccount != null && inviteAccount.getUserRank() > 0) {
						ucImport.setInvite(loginUserInfo.getInvite());
						saveImportFlag = true;
					}
				}
				if (isNewUser) {
					ucImport.setChannelCode(loginUserInfo.getChannelCode());
					ucImport.setSceneNo(loginUserInfo.getSceneNo());
					saveImportFlag = true;
				}
				if (saveImportFlag)
					userAccountImportService.save(ucImport);
				
				DeviceLinkAccount deviceAccount = null;

				deviceAccount = deviceLinkAccountService.getFirstModel(new KeyValue("deviceId", deviceId),
						new KeyValue("userAccountId", userAccount.getId()));

				if (deviceAccount == null) { // 生成设备账号
					deviceAccount = deviceLinkAccountService.getOrCreate(loginUserInfo);
				}

				deviceAccount.setLastLoginIp(userInfo.getLoginIp());
				deviceAccount.setLastLoginTime(currentTime);
				deviceAccount.setUserAccountId(userAccount.getId());
				deviceLinkAccountService.save(deviceAccount);
				userInfo.setId(userAccount.getId());
				return deviceAccount;
			}
		} catch (CustomException e) {
			e.printStackTrace();
			throw e;
		} finally {
			lock.unlock();
		}
		return null;

	}

}
