

package com.saas.log.event;

import org.springframework.context.ApplicationEvent;

import com.saas.log.domain.SysOperLog;


/**
 * 系统日志事件
 */
public class SysOperLogEvent extends ApplicationEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7629771667974334965L;

	public SysOperLogEvent(SysOperLog source) {
		super(source);
	}

}
