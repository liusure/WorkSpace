package com.saas.common.event;

import org.springframework.context.ApplicationEvent;

public class WebLogininforEvent extends ApplicationEvent {

	public WebLogininforEvent(Logininfor source) {
		super(source);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
