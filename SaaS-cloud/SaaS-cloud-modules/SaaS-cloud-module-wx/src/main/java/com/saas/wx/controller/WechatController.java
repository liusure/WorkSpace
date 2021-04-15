/**
 * 
 */
package com.saas.wx.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotBlank;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.saas.cloud.api.uc.RemoteUserCenterService;
import com.saas.common.config.LmfConfig;
import com.saas.common.core.controller.BaseController;
import com.saas.common.core.domain.R;
import com.saas.common.core.entity.KeyValue;
import com.saas.common.core.redis.RedisCache;
import com.saas.common.exception.CustomException;
import com.saas.common.util.BeanUtils;
import com.saas.common.util.ServletUtils;
import com.saas.common.util.StringUtils;
import com.saas.uc.constant.UcConstants;
import com.saas.uc.domain.DeviceLinkAccount;
import com.saas.uc.domain.UserSnsAccount;
import com.saas.uc.model.LoginUserInfo;
import com.saas.uc.model.UserInfo;
import com.saas.uc.utils.FrontCurrentUtils;
import com.saas.wx.config.WxConfig;
import com.saas.wx.domain.WxAuthAccount;
import com.saas.wx.service.WxAuthAccountService;
import com.saas.wx.utils.CurrentUserUtils;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.WxMaPhoneNumberInfo;
import cn.binarywang.wx.miniapp.bean.WxMaUserInfo;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.bean.oauth2.WxOAuth2AccessToken;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.bean.kefu.WxMpKefuMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import me.chanjar.weixin.open.api.WxOpenService;
import me.chanjar.weixin.open.api.impl.WxOpenMessageRouter;
import me.chanjar.weixin.open.bean.message.WxOpenXmlMessage;

/**
 * @author bruce
 *
 */
@RestController
@RequestMapping("/front/wx")
public class WechatController extends BaseController {
	@Autowired
	private WxOpenService wxOpenService;

	@Autowired
	private WxOpenMessageRouter wxOpenMessageRouter;

	@Autowired
	private WxConfig wxConfig;

	@Autowired
	private RemoteUserCenterService remoteUserCenterService;

	@Autowired
	private WxAuthAccountService wxAuthAccountService;

	@Autowired
	private LmfConfig lmfConfig;

	@Autowired
	private RedisCache redisCache;

	@GetMapping("/authorize")
	public void authorize(@RequestParam String appId, @RequestParam String backurl, HttpServletResponse response)
			throws Exception {

		List<String> filters = new ArrayList<>();
		filters.add("from");
		filters.add("isgeappinstalled");
		backurl = StringUtils.filterParameters(backurl, filters);
		String base64BackUrl = Base64.encodeBase64URLSafeString(backurl.getBytes());

		Long saasId = ServletUtils.getHeaderToLong(UcConstants.HEADER_SAAS_ID);
		String authorizationUrl = wxOpenService.getWxOpenComponentService().getWxMpServiceByAppid(appId)
				.getOAuth2Service().buildAuthorizationUrl(wxConfig.getRedirectUrl(),
						WxConsts.OAuth2Scope.SNSAPI_USERINFO, saasId + "_" + base64BackUrl);
		response.sendRedirect(authorizationUrl);
	}

	@GetMapping("/authorizeTest")
	public R<DeviceLinkAccount> authorizeTest() throws Exception {
		System.out.println("aaaaa121111221");
		UserInfo userInfo = new UserInfo();
		LoginUserInfo loginUserInfo = FrontCurrentUtils.getFrontLoginUserInfo();
		BeanUtils.copyProperties(loginUserInfo, userInfo, true);
		userInfo.setOpenId("11112sssaaaadasdsd");
		userInfo.setUnionId("assdsdsd");

		userInfo.setSaasId(loginUserInfo.getSaasId());
		userInfo.setSnsType(UserSnsAccount.SNS_WXMINIAPP);
		R<DeviceLinkAccount> dv = remoteUserCenterService.login(userInfo);
		FrontCurrentUtils.removeAll();
		System.out.println(
				"endaaaaa121111221" + lmfConfig.getName() + ":" + ToStringBuilder.reflectionToString(lmfConfig));
		return dv;
	}

	@GetMapping("/authorizeTest2")
	public R<DeviceLinkAccount> authorizeTest2() throws Exception {
		System.out.println("ccccc121111221");
		UserInfo userInfo = new UserInfo();
		LoginUserInfo loginUserInfo = FrontCurrentUtils.getFrontLoginUserInfo();
		BeanUtils.copyProperties(loginUserInfo, userInfo, true);
		userInfo.setOpenId("11112sssaaaadasdsd");
		userInfo.setNickname("aaaa");
		userInfo.setDeviceId("A11112sssaaaadasdsd");
		userInfo.setCert("pEYzlHCn");
		userInfo.setSaasId(loginUserInfo.getSaasId());
		userInfo.setSnsType(UserSnsAccount.SNS_WXMINIAPP);
		R<DeviceLinkAccount> dv = remoteUserCenterService.updateInfo(userInfo);
		FrontCurrentUtils.removeAll();
		return dv;
	}

	@GetMapping("/bind")
	public void bind(@RequestParam String code, @RequestParam String state, HttpServletResponse response)
			throws Exception {

		int index = state.indexOf("_");
		Long saasId = Long.parseLong(state.substring(0, index));

		WxAuthAccount account = wxAuthAccountService.getBySaasId(saasId, WxAuthAccount.SERVICE_TYPE_MP);
		String appId = account.getAuthorizerAppId();
		String base64BackUrl = state.substring(index + 1);
		String url = new String(Base64.decodeBase64(base64BackUrl));

		WxOAuth2AccessToken accessToken = wxOpenService.getWxOpenComponentService().getWxMpServiceByAppid(appId)
				.getOAuth2Service().getAccessToken(code);
		WxMpUser mpUser = wxOpenService.getWxOpenComponentService().getWxMpServiceByAppid(appId).getUserService()
				.userInfo(accessToken.getOpenId());
		UserInfo userInfo = new UserInfo();
		BeanUtils.copyProperties(mpUser, userInfo, true);
		LoginUserInfo loginUserInfo = FrontCurrentUtils.getFrontLoginUserInfo();
		BeanUtils.copyProperties(loginUserInfo, userInfo, true);
		userInfo.setSnsType(UserSnsAccount.SNS_WEIXINGZ);
		userInfo.setSaasId(saasId);
		loginUserInfo.setSaasId(saasId);
		Map<String, String> params = StringUtils.parseParameters(url);
		userInfo.setChannelCode(params.get(UcConstants.PARAMETER_CHANNEL_CODE));
		try {
			userInfo.setInvite(Long.parseLong(params.get(UcConstants.PARAMETER_INVITE)));
		} catch (Exception e) {

		}
		try {
			userInfo.setSceneNo(Integer.parseInt(params.get(UcConstants.PARAMETER_SCENE_NO)));
		} catch (Exception e) {

		}

		R<DeviceLinkAccount> dv = remoteUserCenterService.login(userInfo);
		FrontCurrentUtils.removeAll();

		DeviceLinkAccount deviceAccount = dv.getData();

		List<KeyValue> parameters = new ArrayList<>();
		parameters.add(new KeyValue("deviceId", deviceAccount.getDeviceId()));
		parameters.add(new KeyValue("cert", deviceAccount.getCertificates()));
		url = StringUtils.addParameters(url, parameters);

		if (StringUtils.isNotBlank(url)) {
			response.sendRedirect(url);
		} else {
			throw new CustomException("绑定失败");
		}

	}

	/**
	 * 小程序认证，更新后无法通过返回值获取用户名和头像，需用户自行点击按钮申请授权并通过ajax传到后台
	 * 
	 * @throws WxErrorException
	 */
	@GetMapping("/authorizeApp")
	public R<DeviceLinkAccount> authorizeApp(@RequestParam String appId, @NotBlank @RequestParam String code)
			throws WxErrorException {
		WxMaJscode2SessionResult session = wxOpenService.getWxOpenComponentService().getWxMaServiceByAppid(appId)
				.getUserService().getSessionInfo(code);

		UserInfo userInfo = new UserInfo();
		LoginUserInfo loginUserInfo = FrontCurrentUtils.getFrontLoginUserInfo();
		BeanUtils.copyProperties(loginUserInfo, userInfo, true);
		BeanUtils.copyProperties(session, userInfo, true);
		userInfo.setSaasId(loginUserInfo.getSaasId());
		userInfo.setSnsType(UserSnsAccount.SNS_WXMINIAPP);

		R<DeviceLinkAccount> dv = remoteUserCenterService.login(userInfo);
		FrontCurrentUtils.removeAll();

		return dv;
	}

	/**
	 * <pre>
	 * 获取用户信息接口
	 * </pre>
	 */
	@GetMapping("/info")
	public R<DeviceLinkAccount> info(@RequestParam String appId, String sessionKey, String signature, String rawData,
			String encryptedData, String iv) {
		final WxMaService wxService = wxOpenService.getWxOpenComponentService().getWxMaServiceByAppid(appId);

		// 用户信息校验
		if (!wxService.getUserService().checkUserInfo(sessionKey, rawData, signature)) {
			return R.fail("获取用户信息出错");
		}
		// 解密用户信息
		WxMaUserInfo wxUserInfo = wxService.getUserService().getUserInfo(sessionKey, encryptedData, iv);

		UserInfo userInfo = new UserInfo();
		LoginUserInfo loginUserInfo = FrontCurrentUtils.getFrontLoginUserInfo();
		BeanUtils.copyProperties(loginUserInfo, userInfo, true);
		BeanUtils.copyProperties(wxUserInfo, userInfo, true);

		userInfo.setSaasId(loginUserInfo.getSaasId());
		userInfo.setSnsType(UserSnsAccount.SNS_WXMINIAPP);

		userInfo.setNickname(wxUserInfo.getNickName());
		userInfo.setHeadImgUrl(wxUserInfo.getAvatarUrl());

		R<DeviceLinkAccount> r = remoteUserCenterService.updateInfo(userInfo);
		FrontCurrentUtils.removeAll();
		return r;
	}

	/**
	 * <pre>
	 * 获取用户绑定手机号信息
	 * </pre>
	 */
	@GetMapping("/phone")
	public R<DeviceLinkAccount> phone(@RequestParam String appId, String sessionKey, String signature, String rawData,
			String encryptedData, String iv) {
		final WxMaService wxService = wxOpenService.getWxOpenComponentService().getWxMaServiceByAppid(appId);

		// 用户信息校验
		if (!wxService.getUserService().checkUserInfo(sessionKey, rawData, signature)) {
			return R.fail("获取手机号码出错");
		}

		// 解密
		WxMaPhoneNumberInfo phoneNoInfo = wxService.getUserService().getPhoneNoInfo(sessionKey, encryptedData, iv);

		UserInfo userInfo = new UserInfo();
		LoginUserInfo loginUserInfo = FrontCurrentUtils.getFrontLoginUserInfo();
		BeanUtils.copyProperties(loginUserInfo, userInfo, true);

		userInfo.setSaasId(loginUserInfo.getSaasId());
		userInfo.setSnsType(UserSnsAccount.SNS_WXMINIAPP);
		userInfo.setMobile(phoneNoInfo.getPurePhoneNumber());

		R<DeviceLinkAccount> r = remoteUserCenterService.updateInfo(userInfo);
		FrontCurrentUtils.removeAll();
		return r;
	}

	@RequestMapping("receive_ticket")
	public void receiveTicket(@RequestBody(required = false) String requestBody,
			@RequestParam("timestamp") String timestamp, @RequestParam("nonce") String nonce,
			@RequestParam("signature") String signature,
			@RequestParam(value = "encrypt_type", required = false) String encType,
			@RequestParam(value = "msg_signature", required = false) String msgSignature,
			HttpServletResponse response) {

		logger.info(
				"\n接收微信请求：[signature=[{}], encType=[{}], msgSignature=[{}],"
						+ " timestamp=[{}], nonce=[{}], requestBody=[\n{}\n] ",
				signature, encType, msgSignature, timestamp, nonce, requestBody);

		if (!StringUtils.equalsIgnoreCase("aes", encType)
				|| !wxOpenService.getWxOpenComponentService().checkSignature(timestamp, nonce, signature)) {
			throw new IllegalArgumentException("非法请求，可能属于伪造的请求！");
		}

		// aes加密的消息
		WxOpenXmlMessage inMessage = WxOpenXmlMessage.fromEncryptedXml(requestBody,
				wxOpenService.getWxOpenConfigStorage(), timestamp, nonce, msgSignature);
		this.logger.debug(inMessage.getComponentVerifyTicket());
		this.logger.debug("\n消息解密后内容为：\n{} ", inMessage.toString());
//		if (inMessage.getComponentVerifyTicket() != null) {
//			logger.info("\n 更新component_verify_ticket: {}", inMessage.getComponentVerifyTicket());
//			wxOpenService.getWxOpenConfigStorage().setComponentVerifyTicket(inMessage.getComponentVerifyTicket());
//		}

		try {
			String out = wxOpenService.getWxOpenComponentService().route(inMessage);
			this.logger.debug("\n组装回复信息：{}", out);
			response.getWriter().write(out);
		} catch (Exception e) {
			this.logger.error("receive_ticket", e);
		}
	}

	/**
	 * 公共事件响应方法，该url是配置在第三方平台上面的事件响应url，配置此url的目的是，一旦今后公众号授权交给了此第三方，那后面公众号上面点击公众号上面的事件操作，都会转到此url上面。
	 *
	 * @param requestBody
	 * @param appId
	 * @param signature
	 * @param timestamp
	 * @param nonce
	 * @param openid
	 * @param encType
	 * @param msgSignature
	 * @return
	 */
	@RequestMapping("{appId}/callback")
	public Object callback(@RequestBody(required = false) String requestBody, @PathVariable("appId") String appId,
			@RequestParam("signature") String signature, @RequestParam("timestamp") String timestamp,
			@RequestParam("nonce") String nonce, @RequestParam("openid") String openid,
			@RequestParam("encrypt_type") String encType, @RequestParam("msg_signature") String msgSignature) {
		this.logger.info(
				"\n接收微信请求：[appId=[{}], openid=[{}], signature=[{}], encType=[{}], msgSignature=[{}],"
						+ " timestamp=[{}], nonce=[{}], requestBody=[\n{}\n] ",
				appId, openid, signature, encType, msgSignature, timestamp, nonce, requestBody);
		if (!StringUtils.equalsIgnoreCase("aes", encType)
				|| !wxOpenService.getWxOpenComponentService().checkSignature(timestamp, nonce, signature)) {
			throw new IllegalArgumentException("非法请求，可能属于伪造的请求！");
		}
		WxAuthAccount account = wxAuthAccountService.getByAuthorizerAppId(appId);
		CurrentUserUtils.setSaasId(account.getSaasId());

		String out = "";
		// aes加密的消息
		WxMpXmlMessage inMessage = WxOpenXmlMessage.fromEncryptedMpXml(requestBody,
				wxOpenService.getWxOpenConfigStorage(), timestamp, nonce, msgSignature);
		this.logger.debug("\n消息解密后内容为：\n{} ", inMessage.toString());
		// 全网发布测试用例
		if (StringUtils.equalsAnyIgnoreCase(appId, "wxd101a85aa106f53e", "wx570bc396a51b8ff8")) {
			try {
				if (StringUtils.equals(inMessage.getMsgType(), "text")) {
					if (StringUtils.equals(inMessage.getContent(), "TESTCOMPONENT_MSG_TYPE_TEXT")) {
						out = WxOpenXmlMessage.wxMpOutXmlMessageToEncryptedXml(
								WxMpXmlOutMessage.TEXT().content("TESTCOMPONENT_MSG_TYPE_TEXT_callback")
										.fromUser(inMessage.getToUser()).toUser(inMessage.getFromUser()).build(),
								wxOpenService.getWxOpenConfigStorage());
					} else if (StringUtils.startsWith(inMessage.getContent(), "QUERY_AUTH_CODE:")) {
						String msg = inMessage.getContent().replace("QUERY_AUTH_CODE:", "") + "_from_api";
						WxMpKefuMessage kefuMessage = WxMpKefuMessage.TEXT().content(msg)
								.toUser(inMessage.getFromUser()).build();
						wxOpenService.getWxOpenComponentService().getWxMpServiceByAppid(appId).getKefuService()
								.sendKefuMessage(kefuMessage);
					}
				} else if (StringUtils.equals(inMessage.getMsgType(), "event")) {
					WxMpKefuMessage kefuMessage = WxMpKefuMessage.TEXT().content(inMessage.getEvent() + "from_callback")
							.toUser(inMessage.getFromUser()).build();
					wxOpenService.getWxOpenComponentService().getWxMpServiceByAppid(appId).getKefuService()
							.sendKefuMessage(kefuMessage);
				}
			} catch (WxErrorException e) {
				logger.error("callback", e);
			}
		} else {
//			关于重试的消息排重，推荐使用FromUserName + CreateTime 排重。
			String key = "Wechat_callback_" + inMessage.getFromUser() + "_" + inMessage.getCreateTime();
			String value = redisCache.getCacheObject(key);
			if (StringUtils.isEmpty(value)) {
				redisCache.setCacheObject(key, "process", 30l, TimeUnit.MINUTES);				
				WxMpXmlOutMessage outMessage = wxOpenMessageRouter.route(inMessage, appId);
				//TODO:消息处理需要记录，定期检查处理有问题的消息
				if (outMessage != null) {
					out = WxOpenXmlMessage.wxMpOutXmlMessageToEncryptedXml(outMessage,
							wxOpenService.getWxOpenConfigStorage());
				}
				redisCache.setCacheObject(key, "done", 1l, TimeUnit.MINUTES);
				
			}

		}
		return out;
	}

}
