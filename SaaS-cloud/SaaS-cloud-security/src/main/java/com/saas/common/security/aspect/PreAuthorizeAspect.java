package com.saas.common.security.aspect;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.expression.BeanFactoryResolver;
import org.springframework.core.MethodParameter;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.saas.common.security.annotation.PreAuthorize;
import com.saas.common.security.exception.PreAuthorizeException;
import com.saas.common.security.service.AuthFactory;
import com.saas.common.security.service.PermissionService;
import com.saas.common.util.ClassUtils;

/**
 * 自定义权限实现
 * 
 */
@Aspect
@Component
public class PreAuthorizeAspect implements ApplicationContextAware {
	@Autowired
	private PermissionService permissionService;

	/** 数组为0时 */
	private static final Integer ARRAY_EMPTY = 0;

	/**
	 * 表达式处理
	 */
	private static final ExpressionParser SPEL_PARSER = new SpelExpressionParser();

	@Around("@annotation(com.saas.common.security.annotation.PreAuthorize) || "
			+ "@within(com.saas.common.security.annotation.PreAuthorize)")
	public Object around(ProceedingJoinPoint point) throws Throwable {
		Signature signature = point.getSignature();
		MethodSignature methodSignature = (MethodSignature) signature;
		Method method = methodSignature.getMethod();
		// 读取权限注解，优先方法上，没有则读取类
		PreAuthorize annotation = ClassUtils.getAnnotation(method, PreAuthorize.class);

		if (annotation == null) {
			return point.proceed();
		}
		if (!StringUtils.isEmpty(annotation.value())) {
			Object[] args = point.getArgs();
			if (handleAuth(annotation.value(), method, args)) {
				return point.proceed();
			}
			throw new PreAuthorizeException();
		} else if (!StringUtils.isEmpty(annotation.hasPermi())) {
			if (permissionService.hasPermi(annotation.hasPermi())) {
				return point.proceed();
			}
			throw new PreAuthorizeException();
		} else if (!StringUtils.isEmpty(annotation.lacksPermi())) {
			if (permissionService.lacksPermi(annotation.lacksPermi())) {
				return point.proceed();
			}
			throw new PreAuthorizeException();
		} else if (ARRAY_EMPTY < annotation.hasAnyPermi().length) {
			if (permissionService.hasAnyPermi(annotation.hasAnyPermi())) {
				return point.proceed();
			}
			throw new PreAuthorizeException();
		} else if (!StringUtils.isEmpty(annotation.hasRole())) {
			if (permissionService.hasRole(annotation.hasRole())) {
				return point.proceed();
			}
			throw new PreAuthorizeException();
		} else if (!StringUtils.isEmpty(annotation.lacksRole())) {
			if (permissionService.lacksRole(annotation.lacksRole())) {
				return point.proceed();
			}
			throw new PreAuthorizeException();
		} else if (ARRAY_EMPTY < annotation.hasAnyRoles().length) {
			if (permissionService.hasAnyRoles(annotation.hasAnyRoles())) {
				return point.proceed();
			}
			throw new PreAuthorizeException();
		}

		return point.proceed();
	}

	/**
	 * 处理权限
	 *
	 * @param point 切点
	 */
	private boolean handleAuth(String condition, Method method, Object[] args) {

		// 判断表达式
		Expression expression = SPEL_PARSER.parseExpression(condition);
		// 方法参数值
		StandardEvaluationContext context = getEvaluationContext(method, args);
		return expression.getValue(context, Boolean.class);

	}

	/**
	 * 获取方法上的参数
	 *
	 * @param method 方法
	 * @param args   变量
	 * @return {SimpleEvaluationContext}
	 */
	private StandardEvaluationContext getEvaluationContext(Method method, Object[] args) {
		// 初始化Sp el表达式上下文，并设置 AuthFun
		StandardEvaluationContext context = new StandardEvaluationContext(new AuthFactory());
		// 设置表达式支持spring bean
		context.setBeanResolver(new BeanFactoryResolver(applicationContext));
		for (int i = 0; i < args.length; i++) {
			// 读取方法参数
			MethodParameter methodParam = ClassUtils.getMethodParameter(method, i);
			// 设置方法 参数名和值 为sp el变量，判断权限时可以对参数进行操作
			//如：@PreAuthorize("hasPermi({#dept.deptName})")@PreAuthorize("hasPermi(#dept.deptName)")
			context.setVariable(methodParam.getParameterName(), args[i]);
		}
		return context;
	}

	private ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

}
