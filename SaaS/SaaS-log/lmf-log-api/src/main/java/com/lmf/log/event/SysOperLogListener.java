
package com.saas.log.event;

import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.saas.common.util.ip.AddressUtils;
import com.saas.log.domain.SysOperLog;
import com.saas.log.service.ISysOperLogService;


/**
 * 异步监听日志事件
 */
@Component
public class SysOperLogListener {

	@DubboReference(version = "1.0.0", check = false)
	private  ISysOperLogService sysOperLogService;

	@Async
	@Order
	@EventListener(SysOperLogEvent.class)
	public void saveSysLog(SysOperLogEvent event) {
		SysOperLog sysLog = (SysOperLog) event.getSource();
		sysLog.setOperLocation(AddressUtils.getRealAddressByIP(sysLog.getOperIp()));
		sysOperLogService.save(sysLog);				
	}

}
