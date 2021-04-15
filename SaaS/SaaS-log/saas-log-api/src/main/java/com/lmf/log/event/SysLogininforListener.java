package com.saas.log.event;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.saas.common.event.Logininfor;
import com.saas.common.event.WebLogininforEvent;
import com.saas.common.util.ip.AddressUtils;
import com.saas.log.domain.SysLogininfor;
import com.saas.log.service.ISysLogininforService;

@Component
public class SysLogininforListener {
	
	@DubboReference(version = "1.0.0", check = false)
	private ISysLogininforService sysLogininforService;

	@Async
	@Order
	@EventListener(WebLogininforEvent.class)
	public void saveSysLog(WebLogininforEvent event) {
		Logininfor logininfor = (Logininfor) event.getSource();

		SysLogininfor sysLogininfor = new SysLogininfor();
		try {
			BeanUtils.copyProperties(sysLogininfor,logininfor);			
			sysLogininfor.setLoginLocation(AddressUtils.getRealAddressByIP(sysLogininfor.getIpaddr()));
			sysLogininforService.save(sysLogininfor);
		} catch (IllegalAccessException e) {

			e.printStackTrace();
		} catch (InvocationTargetException e) {

			e.printStackTrace();
		}

	}
}
