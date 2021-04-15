package com.saas.common.jpa.aspectj;

import java.util.Map;

import javax.servlet.ServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.saas.common.core.page.DataPageable;
import com.saas.common.util.ServletUtils;

/**
 * 
 * @author bruce
 *
 */
@Aspect
@Order(2)
@Component
public class DataPageableAspectj {
	/**
	 * 定义切入点， 通过@Pointcut注解声明频繁使用的切点表达式
	 */
	@Pointcut("execution(public * com.saas.*.controller..*.*ist(com.saas.common.core.page.DataPageable,..)))")
	public void pointcut() {

	}

	/**
	 * 在连接点执行之前执行的通知
	 */
	@Before("pointcut()")
	public void doBeforeGame(JoinPoint joinPoint) {
		try {
			ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder
					.getRequestAttributes();

			Object[] args = joinPoint.getArgs();
			DataPageable dataPageable = (DataPageable) args[0];
			assert attributes != null;
			dataPageableBinder(dataPageable);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void dataPageableBinder(DataPageable pageable) {
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		if (attributes == null) {
			return;
		}
		ServletRequest request = attributes.getRequest();
		Map<String, Object> searchParams = ServletUtils.getParametersStartingWith(request, "search_");
		pageable.setReqSearchParams(searchParams);

	}

}
