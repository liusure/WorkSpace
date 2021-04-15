package com.saas.wx.config;

import static me.chanjar.weixin.common.api.WxConsts.EventType.LOCATION;
import static me.chanjar.weixin.common.api.WxConsts.EventType.SCAN;
import static me.chanjar.weixin.common.api.WxConsts.EventType.SUBSCRIBE;
import static me.chanjar.weixin.common.api.WxConsts.EventType.UNSUBSCRIBE;
import static me.chanjar.weixin.common.api.WxConsts.XmlMsgType.EVENT;
import static me.chanjar.weixin.mp.constant.WxMpEventConstants.POI_CHECK_NOTIFY;
import static me.chanjar.weixin.mp.constant.WxMpEventConstants.CustomerService.KF_CLOSE_SESSION;
import static me.chanjar.weixin.mp.constant.WxMpEventConstants.CustomerService.KF_CREATE_SESSION;
import static me.chanjar.weixin.mp.constant.WxMpEventConstants.CustomerService.KF_SWITCH_SESSION;

import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.saas.wx.handler.KfSessionHandler;
import com.saas.wx.handler.LocationHandler;
import com.saas.wx.handler.LogHandler;
import com.saas.wx.handler.MenuHandler;
import com.saas.wx.handler.MsgHandler;
import com.saas.wx.handler.NullHandler;
import com.saas.wx.handler.ScanHandler;
import com.saas.wx.handler.StoreCheckNotifyHandler;
import com.saas.wx.handler.SubscribeHandler;
import com.saas.wx.handler.UnsubscribeHandler;

import me.chanjar.weixin.common.api.WxConsts.EventType;
import me.chanjar.weixin.common.api.WxConsts.XmlMsgType;
import me.chanjar.weixin.open.api.WxOpenConfigStorage;
import me.chanjar.weixin.open.api.WxOpenService;
import me.chanjar.weixin.open.api.impl.WxOpenInRedissonConfigStorage;
import me.chanjar.weixin.open.api.impl.WxOpenMessageRouter;
import me.chanjar.weixin.open.api.impl.WxOpenServiceImpl;

@Configuration
public class WxOpenConfig {

	private final Logger logger = LoggerFactory.getLogger(WxOpenConfig.class);

	@Autowired
	private WxConfig wxConfig;

	@Autowired
	private RedissonClient redissonClient;

	@Autowired
	protected LogHandler logHandler;

	@Autowired
	protected NullHandler nullHandler;

	@Autowired
	protected KfSessionHandler kfSessionHandler;

	@Autowired
	protected StoreCheckNotifyHandler storeCheckNotifyHandler;

	@Autowired
	private LocationHandler locationHandler;

	@Autowired
	private MenuHandler menuHandler;

	@Autowired
	private MsgHandler msgHandler;

	@Autowired
	private UnsubscribeHandler unsubscribeHandler;

	@Autowired
	private SubscribeHandler subscribeHandler;

	@Autowired
	private ScanHandler scanHandler;

	@Bean
	public WxOpenConfigStorage configWxOpenStorage() {
		WxOpenInRedissonConfigStorage configStorage = new WxOpenInRedissonConfigStorage(redissonClient);
		configStorage.setComponentAppId(wxConfig.getComponentAppId());
		configStorage.setComponentAppSecret(wxConfig.getComponentAppSecret());
		configStorage.setComponentToken(wxConfig.getComponentToken());
		configStorage.setComponentAesKey(wxConfig.getComponentAesKey());
		return configStorage;
	}

	@Bean
	public WxOpenService configWxOpenService(WxOpenConfigStorage wxOpenConfigStorage) {
		WxOpenService wxOpenService = new WxOpenServiceImpl();
		wxOpenService.setWxOpenConfigStorage(wxOpenConfigStorage);
		return wxOpenService;
	}

//	@Bean
//	public WxOpenMessageRouter configOpenMsgRouter(WxOpenService wxOpenService) {
//		WxOpenMessageRouter router = new WxOpenMessageRouter(wxOpenService);
//		// TODO:添加相应的处理类
//		router.rule().handler((wxMpXmlMessage, map, wxMpService, wxSessionManager) -> {
//			logger.info("\n接收到 {} 公众号请求消息，内容：{}", wxMpService.getWxMpConfigStorage().getAppId(), wxMpXmlMessage);
//			return null;
//		}).next();
//		return router;
//	}

	@Bean
	public WxOpenMessageRouter configRouter(WxOpenService wxOpenService) {
		WxOpenMessageRouter newRouter = new WxOpenMessageRouter(wxOpenService);

		// 记录所有事件的日志
		newRouter.rule().handler(this.logHandler).next();

		// 接收客服会话管理事件
		newRouter.rule().async(false).msgType(EVENT).event(KF_CREATE_SESSION).handler(this.kfSessionHandler).end();
		newRouter.rule().async(false).msgType(EVENT).event(KF_CLOSE_SESSION).handler(this.kfSessionHandler).end();
		newRouter.rule().async(false).msgType(EVENT).event(KF_SWITCH_SESSION).handler(this.kfSessionHandler).end();

		// 门店审核事件
		newRouter.rule().async(false).msgType(EVENT).event(POI_CHECK_NOTIFY).handler(this.storeCheckNotifyHandler)
				.end();

		// 自定义菜单事件
		newRouter.rule().async(false).msgType(EVENT).event(EventType.CLICK).handler(this.menuHandler).end();

		// 点击菜单连接事件
		newRouter.rule().async(false).msgType(EVENT).event(EventType.VIEW).handler(this.nullHandler).end();

		// 关注事件
		newRouter.rule().async(false).msgType(EVENT).event(SUBSCRIBE).handler(this.subscribeHandler).end();

		// 取消关注事件
		newRouter.rule().async(false).msgType(EVENT).event(UNSUBSCRIBE).handler(this.unsubscribeHandler).end();

		// 上报地理位置事件
		newRouter.rule().async(false).msgType(EVENT).event(LOCATION).handler(this.locationHandler).end();

		// 接收地理位置消息
		newRouter.rule().async(false).msgType(XmlMsgType.LOCATION).handler(this.locationHandler).end();

		// 扫码事件
		newRouter.rule().async(false).msgType(EVENT).event(SCAN).handler(this.scanHandler).end();

		// 默认
		newRouter.rule().async(false).handler(this.msgHandler).end();

		return newRouter;
	}
}
