package com.saas.common.security.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.saas.common.core.entity.AjaxResult;
import com.saas.common.exception.BaseException;
import com.saas.common.exception.CustomException;
import com.saas.common.security.exception.PreAuthorizeException;
import com.saas.common.util.StringUtils;

/**
 * 全局异常处理器
 * 
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
	private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	/**
	 * 基础异常
	 */
	@ExceptionHandler(BaseException.class)
	public AjaxResult baseException(BaseException e) {
		return AjaxResult.error(e.getDefaultMessage());
	}

	/**
	 * 业务异常
	 */
	@ExceptionHandler(CustomException.class)
	public AjaxResult businessException(CustomException e) {
		if (StringUtils.isNull(e.getCode())) {
			return AjaxResult.error(e.getMessage());
		}
		return AjaxResult.error(e.getCode(), e.getMessage());
	}

	@ExceptionHandler(Exception.class)
	public AjaxResult handleException(Exception e) {
		log.error(e.getMessage(), e);
		return AjaxResult.error(e.getMessage());
	}

	/**
	 * 自定义验证异常
	 */
	@ExceptionHandler(BindException.class)
	public AjaxResult validatedBindException(BindException e) {
		log.error(e.getMessage(), e);
		String message = e.getAllErrors().get(0).getDefaultMessage();
		return AjaxResult.error(message);
	}

	/**
	 * 自定义验证异常
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Object validExceptionHandler(MethodArgumentNotValidException e) {
		log.error(e.getMessage(), e);
		String message = e.getBindingResult().getFieldError().getDefaultMessage();
		return AjaxResult.error(message);
	}

	/**
	 * 权限异常
	 */
	@ExceptionHandler(PreAuthorizeException.class)
	public AjaxResult preAuthorizeException(PreAuthorizeException e) {
		return AjaxResult.error("没有权限，请联系管理员授权");
	}

}
