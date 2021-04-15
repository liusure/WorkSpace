package com.saas.uc.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.saas.common.exception.CustomException;
import com.saas.uc.model.CurrentUserInfo;
import com.saas.uc.model.LoginUserInfo;
import com.saas.uc.service.UserAccountService;
import com.saas.uc.utils.FrontCurrentUtils;

@Component
public class FrontAuthInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	private UserAccountService userAccountService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		FrontCurrentUtils.removeAll();

		// 当前登录用户信息
		LoginUserInfo loginUserInfo = FrontCurrentUtils.getFrontLoginUserInfo();

		if (!loginUserInfo.isValidRequest()) {
			throw new CustomException("请求信息不完整");
		}
		CurrentUserInfo userInfo = userAccountService.login(loginUserInfo);
		// 获取当前用户

		// 设置当前用户信息
		userInfo.setLoginUserInfo(loginUserInfo);
		FrontCurrentUtils.setCurrentUserInfo(userInfo);

//        Integer sceneNo = loginUserInfo.getSceneNo();
//        asyncServiceExecutor.execute(()-> {
//            if(sceneNo != null){
//                userFromSceneService.incActiveNum(sceneNo);
//            }
//        });
//
//        Long userId = CurrentUtils.getCurrentUserId();
//        asyncServiceExecutor.execute(()-> {
//            userStatisticsService.countVisitorNum(userId);
//        });

		return true;
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// 清除 当前信息
		FrontCurrentUtils.removeAll();
	}

	@Override
	public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		FrontCurrentUtils.removeAll();
		// 用户并发请求
		throw new CustomException("请不要重复点击");
	}
}
