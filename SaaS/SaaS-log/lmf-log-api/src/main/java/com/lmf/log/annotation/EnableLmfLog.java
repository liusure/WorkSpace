package com.saas.log.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.context.annotation.Import;

import com.saas.log.aspectj.LogAspect;
import com.saas.log.event.SysLogininforListener;
import com.saas.log.event.SysOperLogListener;

@EnableDubbo
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({ LogAspect.class, SysLogininforListener.class, SysOperLogListener.class })
public @interface EnableLmfLog {

}
