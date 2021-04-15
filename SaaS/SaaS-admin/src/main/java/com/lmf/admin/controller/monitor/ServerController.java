package com.saas.admin.controller.monitor;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.saas.common.core.entity.AjaxResult;
import com.saas.common.web.domain.Server;

/**
 * 服务器监控
 * 
 */
@RestController
@RequestMapping("/system/monitor/server")
public class ServerController {
	@PreAuthorize("@ss.hasPermi('monitor:server:list')")
	@GetMapping()
	public AjaxResult getInfo() throws Exception {
		Server server = new Server();
		server.copyTo();
		return AjaxResult.success(server);
	}
}
