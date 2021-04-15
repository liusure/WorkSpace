package com.saas.wx.controller;

import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.saas.common.constant.DomainConstants;
import com.saas.common.core.controller.BaseController;
import com.saas.common.core.domain.SysUser;
import com.saas.common.core.entity.AjaxResult;
import com.saas.common.core.entity.KeyValue;
import com.saas.common.exception.CustomException;
import com.saas.common.util.UserContextHolder;
import com.saas.wx.config.WxConfig;
import com.saas.wx.domain.WxAuthAccount;
import com.saas.wx.service.WxAuthAccountService;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaDomainAction;
import cn.binarywang.wx.miniapp.bean.code.WxMaCategory;
import cn.binarywang.wx.miniapp.bean.code.WxMaCodeAuditStatus;
import cn.binarywang.wx.miniapp.bean.code.WxMaCodeSubmitAuditRequest;
import cn.binarywang.wx.miniapp.json.WxMaGsonBuilder;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.open.api.WxOpenService;
import me.chanjar.weixin.open.bean.result.WxOpenAuthorizerInfoResult;
import me.chanjar.weixin.open.bean.result.WxOpenQueryAuthResult;

@RestController
@RequestMapping("/auth")
public class WxAuthCodeController extends BaseController {

	@Autowired
	private WxOpenService wxOpenService;

	@Autowired
	private WxConfig wxConfig;

	@Autowired
	private WxAuthAccountService wxAuthAccountService;

	/**
	 * 采用前后端分离模式时，授权后回掉页面是前端页面，前端将auth_code在传回给后端
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws WxErrorException
	 */
	@PostMapping("/go_auth")
	public AjaxResult gotoPreAuthUrl(@RequestParam String redirectUrl) throws WxErrorException {
//		String host = request.getHeader("host");
//		String url = "http://" + host + "/wx/front/miniapp/auth_result";		
		try {
			String url = wxOpenService.getWxOpenComponentService().getPreAuthUrl(redirectUrl);
			// 添加来源，解决302跳转来源丢失的问题
//			response.addHeader("Referer", "http://" + host);
//			response.sendRedirect(url);
			return AjaxResult.success(url);
		} catch (Exception e) {
			logger.error("gotoPreAuthUrl", e);
			throw new CustomException(e.getMessage());
		}
	}

	@GetMapping("/auth_result")
	public AjaxResult authResult(@RequestParam("auth_code") String authorizationCode,
			@RequestParam("service_type") int serviceType) {
		try {
			WxOpenQueryAuthResult queryAuthResult = wxOpenService.getWxOpenComponentService()
					.getQueryAuth(authorizationCode);
			WxOpenAuthorizerInfoResult authInfo = wxOpenService.getWxOpenComponentService()
					.getAuthorizerInfo(queryAuthResult.getAuthorizationInfo().getAuthorizerAppid());
			Integer service_type_info = authInfo.getAuthorizerInfo().getServiceTypeInfo();
			if (service_type_info.intValue() != serviceType) {
				return AjaxResult.error(serviceType == 0 ? "您授权的不是小程序" : "您授权的不是公众号");
			}

			SysUser user = UserContextHolder.getCurrentUser();
			// 已经授权给其他saas账号，一个公众号或者小程序不能授权给多个saas账号
			WxAuthAccount account = wxAuthAccountService.getUniqueModel(
					new KeyValue("authorizerAppId", authInfo.getAuthorizationInfo().getAuthorizerAppid()),
					new KeyValue("serviceType", serviceType));
			if (account != null && account.getSaasId() != user.getSaasId()) {
				return AjaxResult.error("公众号或者小程序账号已经被授权过，请更换账号");
			}

			// 查询是否已经存在该公众号设置，如果存在，则更新授权，如果不存在，则新增

			account = wxAuthAccountService.getBySaasId(user.getSaasId(), serviceType);
			if (account == null) {
				account = new WxAuthAccount();
			} else {
				// 需要判断一下是否变更了appid，如果是需要提醒
				if (!account.getAuthorizerAppId()
						.equalsIgnoreCase(authInfo.getAuthorizationInfo().getAuthorizerAppid())) {
					return AjaxResult.error("公众号或者小程序账号变更，请先取消绑定后在重新授权");
				}
			}
			account.setAuthorizerAppId(authInfo.getAuthorizationInfo().getAuthorizerAppid());
			account.setAlias(authInfo.getAuthorizerInfo().getAlias());
			account.setAuthorizerFreshToken(authInfo.getAuthorizationInfo().getAuthorizerRefreshToken());
			account.setHeadImg(authInfo.getAuthorizerInfo().getHeadImg());
			account.setNickName(authInfo.getAuthorizerInfo().getNickName());
			account.setPrincipalName(authInfo.getAuthorizerInfo().getPrincipalName());
			account.setQrcodeUrl(authInfo.getAuthorizerInfo().getQrcodeUrl());
			account.setSaasId(user.getSaasId());
			account.setServiceType(service_type_info);
			account.setSignature(authInfo.getAuthorizerInfo().getSignature());
			account.setStatusFlag(DomainConstants.DOMAIN__STATUS_PUBLISH);
			account.setUserName(authInfo.getAuthorizerInfo().getUserName());
			account.setSaasId(user.getSaasId());
			wxAuthAccountService.save(account);

			return AjaxResult.success("更新授权成功！");

		} catch (WxErrorException e) {
			logger.error("gotoPreAuthUrl", e);
			throw new RuntimeException(e);
		}
	}

	@GetMapping("/get_authorizer_info")
	public AjaxResult getAuthorizerInfo(@RequestParam String appId) {
		try {
			return AjaxResult.success(wxOpenService.getWxOpenComponentService().getAuthorizerInfo(appId));
		} catch (WxErrorException e) {
			logger.error("getAuthorizerInfo", e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * 85001 微信号不存在或微信号设置为不可搜索 85002 小程序绑定的体验者数量达到上限 85003 微信号绑定的小程序体验者达到上限 85004
	 * 微信号已经绑定
	 */

	// 添加体验者
	@GetMapping("addTestUser")
	public AjaxResult addTestUser(@RequestParam String appId, @RequestParam String wechatId) {
		WxMaService wxMaService = wxOpenService.getWxOpenComponentService().getWxMaServiceByAppid(appId);
		try {
			wxMaService.getSettingService().bindTester(wechatId);
		} catch (WxErrorException e) {
			if (e.getError().getErrorCode() != 85004) {
				return AjaxResult.error(e.getError().getErrorCode(), e.getError().getErrorMsg());
			}
		}
		// TODO:添加入库记录
		return AjaxResult.success("绑定成功");
	}

	// 删除体验者
	@GetMapping("delTestUser")
	public AjaxResult delTestUser(@RequestParam String appId, @RequestParam String wechatId) {
		WxMaService wxMaService = wxOpenService.getWxOpenComponentService().getWxMaServiceByAppid(appId);

		try {
			wxMaService.getSettingService().unbindTester(wechatId);
			return AjaxResult.success("解绑成功");
		} catch (WxErrorException e) {
			e.printStackTrace();
			return AjaxResult.error(e.getError().getErrorCode(), e.getError().getErrorMsg());
		}
	}

	/**
	 * 发布小程序
	 * <p>
	 * 1、设置小程序服务器域名
	 * 授权给第三方的小程序，其服务器域名只可以为第三方的服务器，当小程序通过第三方发布代码上线后，小程序原先自己配置的服务器域名将被删除，
	 * 只保留第三方平台的域名，所以第三方平台在代替小程序发布代码之前，需要调用接口为小程序添加第三方自身的域名。
	 * 提示：需要先将域名登记到第三方平台的小程序服务器域名中，才可以调用接口进行配置。请求方式: POST（请使用https协议）
	 * https://api.weixin.qq.com/wxa/modify_domain?access_token=TOKEN
	 * <p>
	 * access_token 请使用第三方平台获取到的该小程序授权的authorizer_access_token action add添加,
	 * delete删除, set覆盖, get获取。当参数是get时不需要填四个域名字段 requestdomain
	 * request合法域名，当action参数是get时不需要此字段 wsrequestdomain
	 * socket合法域名，当action参数是get时不需要此字段 uploaddomain
	 * uploadFile合法域名，当action参数是get时不需要此字段 downloaddomain
	 * downloadFile合法域名，当action参数是get时不需要此字段
	 * <p>
	 * 、设置小程序业务域名（仅供第三方代小程序调用） 授权给第三方的小程序，其业务域名只可以为第三方的服务器，当小程序通过第三方发布代码上线后，
	 * 小程序原先自己配置的业务域名将被删除，只保留第三方平台的域名，所以第三方平台在代替小程序发布代码之前，需要调用接口为小程序添加业务域名。 请求方式:
	 * POST（请使用https协议）
	 * https://api.weixin.qq.com/wxa/setwebviewdomain?access_token=TOKEN
	 */

	// 设置小程序服务器域名
	@RequestMapping("modifyDomain")
	public AjaxResult modifyDomain(@RequestParam String appId) {
		logger.info("WeixinMiniAppCodeController-modifyDomain,【设置小程序服务器域名】");
		WxMaService wxMaService = wxOpenService.getWxOpenComponentService().getWxMaServiceByAppid(appId);
		List<String> requestDomain = new ArrayList<>();
		requestDomain.add(wxConfig.getServerHost());
		WxMaDomainAction modifyDomain = WxMaDomainAction.builder().action("add").uploadDomain(requestDomain)
				.requestDomain(requestDomain).downloadDomain(requestDomain).build();
		try {
			wxMaService.getSettingService().modifyDomain(modifyDomain);
			return AjaxResult.success("设置小程序服务器域名成功");
		} catch (WxErrorException e) {
			e.printStackTrace();
			return AjaxResult.error(e.getError().getErrorCode(), e.getError().getErrorMsg());
		}
	}

	/**
	 * 授权给第三方的小程序，其业务域名只可以为第三方的服务器，当小程序通过第三方发布代码上线后，小程序原先自己配置的业务域名将被删除，只保留第三方平台的域名，所以第三方平台在代替小程序发布代码之前，需要调用接口为小程序添加业务域名。
	 * 提示： 1、需要先将域名登记到第三方平台的小程序业务域名中，才可以调用接口进行配置。
	 * 2、为授权的小程序配置域名时支持配置子域名，例如第三方登记的业务域名如为qq.com，则可以直接将qq.com及其子域名（如xxx.qq.com）也配置到授权的小程序中。
	 * <p>
	 * 请求方式: POST（请使用https协议）
	 * https://api.weixin.qq.com/wxa/setwebviewdomain?access_token=TOKEN
	 * <p>
	 * 参数 说明 access_token 请使用第三方平台获取到的该小程序授权的authorizer_access_token action add添加,
	 * delete删除, set覆盖,
	 * get获取。当参数是get时不需要填webviewdomain字段。如果没有action字段参数，则默认将开放平台第三方登记的小程序业务域名全部添加到授权的小程序中
	 * webviewdomain 小程序业务域名，当action参数是get时不需要此字段
	 */

	// 设置小程序业务域名（仅供第三方代小程序调用）
	@RequestMapping("setWebViewDomain")
	public AjaxResult setWebViewDomain(@RequestParam String appId) {
		WxMaService wxMaService = wxOpenService.getWxOpenComponentService().getWxMaServiceByAppid(appId);

		List<String> requestDomain = new ArrayList<>();
		requestDomain.add(wxConfig.getServerHost());
		WxMaDomainAction modifyDomain = WxMaDomainAction.builder().action("add").webViewDomain(requestDomain).build();
		try {
			wxMaService.getSettingService().setWebViewDomain(modifyDomain);
			return AjaxResult.success("设置小程序服务器域名成功");
		} catch (WxErrorException e) {
			e.printStackTrace();
			return AjaxResult.error(e.getError().getErrorCode(), e.getError().getErrorMsg());
		}
	}

	// 上传代码并提交
	@RequestMapping("/uploadMiniCode")
	public AjaxResult uploadMiniCode(@RequestParam String appId) {
		logger.info("WeixinMiniAppCodeController-publishMiniApp,【上传代码】");
		WxMaService wxMaService = wxOpenService.getWxOpenComponentService().getWxMaServiceByAppid(appId);

		WxAuthAccount account = wxAuthAccountService.getUniqueModel(new KeyValue("authorizerAppId", appId),
				new KeyValue("serviceType", 0));
		// 可能存在域名重复授权
		try {
			List<String> requestDomain = new ArrayList<>();
			requestDomain.add(wxConfig.getServerHost());

			WxMaDomainAction getModifyDomain = WxMaDomainAction.builder().action("get").build();
			WxMaDomainAction getWxMaDomainAction = wxMaService.getSettingService().modifyDomain(getModifyDomain);
			List<String> doDomain = getWxMaDomainAction.getDownloadDomain();
			List<String> reDomain = getWxMaDomainAction.getRequestDomain();
			List<String> upDomain = getWxMaDomainAction.getUploadDomain();
			List<String> wsDomain = getWxMaDomainAction.getWsRequestDomain();

			// 删掉再添加

			logger.info("\n【获取到小程序之前设定域名为 []】", getWxMaDomainAction.toJson());
			if (getWxMaDomainAction.getRequestDomain() != null && getWxMaDomainAction.getRequestDomain().size() > 0) {
				WxMaDomainAction delModifyDomain = WxMaDomainAction.builder().action("delete").uploadDomain(upDomain)
						.requestDomain(reDomain).downloadDomain(doDomain).wsRequestDomain(wsDomain).build();
				wxMaService.getSettingService().modifyDomain(delModifyDomain);
			}

			WxMaDomainAction addmodifyDomain = WxMaDomainAction.builder().action("add").uploadDomain(requestDomain)
					.requestDomain(requestDomain).downloadDomain(requestDomain).build();
			wxMaService.getSettingService().modifyDomain(addmodifyDomain);

			WxMaDomainAction getWebViewDomain = WxMaDomainAction.builder().action("get").build();
			WxMaDomainAction getWxMaWebViewDomainAction = wxMaService.getSettingService()
					.modifyDomain(getWebViewDomain);

			if (getWxMaWebViewDomainAction.getWebViewDomain() != null) {
				WxMaDomainAction delWebViewDomain = WxMaDomainAction.builder().action("delete")
						.webViewDomain(getWxMaWebViewDomainAction.getWebViewDomain()).build();

				wxMaService.getSettingService().setWebViewDomain(delWebViewDomain);

			}

			WxMaDomainAction addWebViewDomain = WxMaDomainAction.builder().action("add").webViewDomain(requestDomain)
					.build();

			wxMaService.getSettingService().setWebViewDomain(addWebViewDomain);
		} catch (WxErrorException e) {
			// TODO: 入库 定时任务 刷新域名
			e.printStackTrace();
			logger.error("绑定域名失败，请先解绑小程序授权的第三方平台 app_id is{}", appId);

		}
		try {
			// 底部导航 tabBar
//		wxMaService.post("https://api.weixin.qq.com/wxa/commit", json);

			// 提交审核
			// 1 获取小程序可配置的商家类目
			List<WxMaCategory> list = wxMaService.getCodeService().getCategory();

			// 需要添加地址
			List<WxMaCategory> itemList = new ArrayList<WxMaCategory>();
			for (WxMaCategory wxMaCategory : list) {
				WxMaCategory category = WxMaCategory.builder().address("pages/index/index").title(account.getNickName())
						.firstClass(wxMaCategory.getFirstClass()).firstId(wxMaCategory.getFirstId())
						.secondClass(wxMaCategory.getSecondClass()).secondId(wxMaCategory.getSecondId()).build();
				itemList.add(category);
				// 提交审核项的一个列表（至少填写1项，至多填写5项）填一个就行
				break;
			}
			if (itemList.size() < 1) {
				return AjaxResult.error("请先登录公众平台补充小程序的头像，名称，简介等信息");
			}

			WxMaCodeSubmitAuditRequest wxMaCodeSubmitAuditRequest = WxMaCodeSubmitAuditRequest.builder()
					.itemList(itemList).build();

			// 审核Id 入库

			long auditid = wxMaService.getCodeService().submitAudit(wxMaCodeSubmitAuditRequest);
			// TODO: 更新之前的版本为老版本。

			return AjaxResult.success("上传代码完毕");
		} catch (WxErrorException e) {
			e.printStackTrace();
			return AjaxResult.error(e.getError().getErrorCode(), e.getError().getErrorMsg());
		}

	}

	/**
	 * 上传代码 修改域名
	 */
	@RequestMapping("/uploadMiniCodeNoSubmit")
	public AjaxResult uploadMiniCodeNoSubmit() {
		return AjaxResult.success();
	}

	/**
	 * status 审核状态，其中0为审核成功，1为审核失败，2为审核中 reason 当status=1，审核被拒绝时，返回的拒绝原因
	 *
	 * 小程序体验二维码
	 */
	@RequestMapping("/getQrcode")
	public AjaxResult getQrcode(String appid) {
		logger.info("WeixinMiniAppCodeController-getQrcode,【小程序体验二维码】");

		WxMaService wxMaService = wxOpenService.getWxOpenComponentService().getWxMaServiceByAppid(appid);
		try {
			byte[] image = wxMaService.getCodeService().getQrCode("");

			return AjaxResult.success("data:image/jpeg;base64," + Base64.getEncoder().encodeToString(image));
		} catch (WxErrorException e) {
			e.printStackTrace();
			return AjaxResult.error(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return AjaxResult.error(e.getMessage());
		}

	}

	// 获取授权小程序帐号的可选类目
	@RequestMapping("get_category")
	public AjaxResult getCategory(String appid) {
		logger.info("WeixinMiniAppCodeController-getQrcode,【获取授权小程序帐号的可选类目】");

		WxMaService wxMaService = wxOpenService.getWxOpenComponentService().getWxMaServiceByAppid(appid);
		try {
			return AjaxResult.success(wxMaService.getCodeService().getCategory());
		} catch (WxErrorException e) {
			e.printStackTrace();
			return AjaxResult.error(e.getMessage());
		}
	}

	// 获取小程序的第三方提交代码的页面配置
	@RequestMapping(value = "get_page", method = RequestMethod.POST)
	public AjaxResult getPage(String appid) {
		logger.info("WeixinMiniAppCodeController-get_page,【获取小程序的第三方提交代码的页面配置】");

		WxMaService wxMaService = wxOpenService.getWxOpenComponentService().getWxMaServiceByAppid(appid);
		try {
			List<String> list = wxMaService.getCodeService().getPage();
			return AjaxResult.success(list);
		} catch (WxErrorException e) {
			e.printStackTrace();
			return AjaxResult.error(e.getMessage());
		}
	}

	/**
	 * access_token 请使用第三方平台获取到的该小程序授权的authorizer_access_token item_list
	 * 提交审核项的一个列表（至少填写1项，至多填写5项） address 小程序的页面，可通过“获取小程序的第三方提交代码的页面配置”接口获得 tag
	 * 小程序的标签，多个标签用空格分隔，标签不能多于10个，标签长度不超过20 first_class
	 * 一级类目名称，可通过“获取授权小程序帐号的可选类目”接口获得 second_class 二级类目(同上) third_class 三级类目(同上)
	 * first_id 一级类目的ID，可通过“获取授权小程序帐号的可选类目”接口获得 second_id 二级类目的ID(同上) third_id
	 * 三级类目的ID(同上) title 小程序页面的标题,标题长度不超过32
	 */

	// 将第三方提交的代码包提交审核
	@RequestMapping("submit_audit")
	public AjaxResult submitAudit(String appid) {
		logger.info("WeixinMiniAppCodeController-submit_audit,【将第三方提交的代码包提交审核】");

		WxMaService wxMaService = wxOpenService.getWxOpenComponentService().getWxMaServiceByAppid(appid);

//        get_category();  获取可配的分类目录

		WxMaCategory wxMaCategory = WxMaCategory.builder().firstClass("商家自营").address("pages/index/index").tag("食品")
				.title("首页").firstId(304L).secondClass("食品").secondId(321L).build();

		List<WxMaCategory> list = new ArrayList();
		list.add(wxMaCategory);

		WxMaCodeSubmitAuditRequest wxMaCodeSubmitAuditRequest = WxMaCodeSubmitAuditRequest.builder().itemList(list)
				.build();

		try {
			long auditid = wxMaService.getCodeService().submitAudit(wxMaCodeSubmitAuditRequest);
			System.out.printf("" + auditid);
			return AjaxResult.success(auditid);
		} catch (WxErrorException e) {
			e.printStackTrace();
			return AjaxResult.error(e.getMessage());
		}
	}

	// 查询某个指定版本的审核状态
	@RequestMapping("getAuthStatus")
	public String getAuthStatus(String appid) {
		logger.info("WeixinMiniAppCodeController-getAuthStatus,【查询某个指定版本的审核状态】");

		WxMaService wxMaService = wxOpenService.getWxOpenComponentService().getWxMaServiceByAppid(appid);

		try {
			Long auditId = 483473478L;
			WxMaCodeAuditStatus wxMaCodeAuditStatus = wxMaService.getCodeService().getAuditStatus(auditId);
			return wxMaCodeAuditStatus.toString();
		} catch (WxErrorException e) {
			e.printStackTrace();
		}

		return "";
	}

	/**
	 * 查询最新一次提交的审核状态 返回参数说明： 参数 说明 auditid 最新的审核ID status 审核状态，其中0为审核成功，1为审核失败，2为审核中
	 * reason 当status=1，审核被拒绝时，返回的拒绝原因
	 */
	@RequestMapping("get_latest_auditstatus")
	public String getLatestAuditstatus(String appid) {
		logger.info("WeixinMiniAppCodeController-get_latest_auditstatus,【查询最新一次提交的审核状态】");

		WxMaService wxMaService = wxOpenService.getWxOpenComponentService().getWxMaServiceByAppid(appid);
		try {
			WxMaCodeAuditStatus wxMaCodeAuditStatus = wxMaService.getCodeService().getLatestAuditStatus();
			return wxMaCodeAuditStatus.toString();
		} catch (WxErrorException e) {
			e.printStackTrace();
		}
		return "success";
	}

	// 发布已通过审核的小程序
	@RequestMapping("release")
	public String release() {
		logger.info("WeixinMiniAppCodeController-release,【发布已通过审核的小程序】");
		return "success";
	}

	// 修改小程序线上代码的可见状态
	@RequestMapping("change_visitstatus")
	public String changeVisitstatus(String appid) {
		logger.info("WeixinMiniAppCodeController-change_visitstatus,【修改小程序线上代码的可见状态】");

		WxMaService wxMaService = wxOpenService.getWxOpenComponentService().getWxMaServiceByAppid(appid);
		try {

			String staus = "close"; // open
			wxMaService.getCodeService().changeVisitStatus(staus);
		} catch (WxErrorException e) {
			e.printStackTrace();
		}
		return "success";
	}

	/**
	 * 小程序版本回退 0 成功 -1 系统错误 87011 现网已经在灰度发布，不能进行版本回退 87012
	 * 该版本不能回退，可能的原因：1:无上一个线上版用于回退 2:此版本为已回退版本，不能回退 3:此版本为回退功能上线之前的版本，不能回退
	 */
	@RequestMapping("revertcoderelease")
	public String revertCoderelease(String appid) {
		logger.info("WeixinMiniAppCodeController-revertcoderelease,【小程序版本回退】");

		WxMaService wxMaService = wxOpenService.getWxOpenComponentService().getWxMaServiceByAppid(appid);
		try {

			wxMaService.getCodeService().revertCodeRelease();
		} catch (WxErrorException e) {
			e.printStackTrace();
		}
		return "success";
	}

	// 小程序审核撤回
	@RequestMapping("undocodeaudit")
	public String undocodeAudit(String appid) {
		logger.info("WeixinMiniAppCodeController-undocodeaudit,【小程序审核撤回】");

		WxMaService wxMaService = wxOpenService.getWxOpenComponentService().getWxMaServiceByAppid(appid);
		try {
			wxMaService.getCodeService().undoCodeAudit();
		} catch (WxErrorException e) {
			e.getError().getErrorMsg();

			e.printStackTrace();
		}
		return "success";
	}

	/**
	 * 目前官方只返回userStr 一段字符串 没有标识具体用户 没有实际意义 只能系统自己维护
	 */

	@RequestMapping("memberauth")
	public String memberAuth(String appid) throws WxErrorException {

		WxMaService wxMaService = wxOpenService.getWxOpenComponentService().getWxMaServiceByAppid(appid);

		HashMap param = new HashMap(1);
		param.put("action", "get_experiencer");
		String responseContent = wxMaService.post("https://api.weixin.qq.com/wxa/memberauth",
				WxMaGsonBuilder.create().toJson(param));
		return responseContent;
	}

	@RequestMapping("getDomain")
	public String getDomain(String appid) throws WxErrorException {

		WxMaService wxMaService = wxOpenService.getWxOpenComponentService().getWxMaServiceByAppid(appid);

		WxMaDomainAction getModifyDomain = WxMaDomainAction.builder().action("get").build();

		return wxMaService.getSettingService().modifyDomain(getModifyDomain).toJson();

	}

	@RequestMapping("addDomain")
	public String addDomain(String appid) throws Exception {

		WxMaService wxMaService = wxOpenService.getWxOpenComponentService().getWxMaServiceByAppid(appid);

//        File file = weixinOpenService.getInstance(formMap).getWxOpenComponentService().getWxMaServiceByAppid(miniPublicInfo.getStr("authorizer_app_id")).getQrcodeService().createWxaCode("pages/index/index",225);
//        WxMaService wxMaService = new WxMaServiceImpl();
//
//        WxMaInMemoryConfig wxMaInMemoryConfig = new WxMaInMemoryConfig();
//        wxMaInMemoryConfig.setAppid("wxdde6d44348f138ca");
//        wxMaInMemoryConfig.setSecret("d833c4fe415ced5b9a5b597c39568a7b");
//        wxMaInMemoryConfig.setAesKey("weiit");
//        wxMaService.setWxMaConfig(wxMaInMemoryConfig);
//
//
//        WxMpService wxMpService = new WxMpServiceImpl();
//        WxMpInMemoryConfigStorage wxMpInMemoryConfigStorage = new WxMpInMemoryConfigStorage();
//        wxMpInMemoryConfigStorage.setAppId("登录微信公众平台查询APPID");
//        wxMpInMemoryConfigStorage.setSecret("登录微信公众平台查询SECRET");
//        wxMpService.setWxMpConfigStorage(wxMpInMemoryConfigStorage);
//
//        //第一个方法
//        //获取code
//        //redirectURI  接收code 的接口
//        wxMpService.oauth2buildAuthorizationUrl("redirectURI","scope","state");
//
//        //第二个方法  接收code 的接口
//        //通过code获取openId
//        wxMpService.oauth2getAccessToken("code");

//        File file = wxMaService.getQrcodeService().createWxaCode("pages/index/index",225);
//        File file = wxMaService.getQrcodeService().createWxaCodeUnlimit("2259","pages/detail/detail");
//        File file = wxMaService.getQrcodeService().createQrcode("pages/detail/detail?shop_id=2259");
//        String qrCodePath = WeiitUtil.uploadFile(FileUtils.readFileToByteArray(file),"png");
//        logger.info("\n【qrCodePath is {}】",qrCodePath);
//        System.out.println(WeiitUtil.getFileDomain()+qrCodePath);
//        List<String> requestDomain = new ArrayList();
//        requestDomain.add("http://merchant.wstore.me/");
//
//        WxMaDomainAction getModifyDomain = WxMaDomainAction.builder().action("add")
//                .requestDomain(requestDomain).uploadDomain(requestDomain).uploadDomain(requestDomain)
//                .build();

//        return  wxMaService.getSettingService().modifyDomain(getModifyDomain).toJson();
//        return  WeiitUtil.getFileDomain()+qrCodePath;
		return "";

	}

}
