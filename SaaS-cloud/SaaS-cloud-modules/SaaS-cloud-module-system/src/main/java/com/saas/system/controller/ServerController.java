package com.saas.system.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.saas.common.core.entity.AjaxResult;
import com.saas.common.security.annotation.PreAuthorize;
import com.saas.common.web.domain.Server;

/**
 * 服务器监控
 * 
 */
@RestController
@RequestMapping("/monitor/server")
@PreAuthorize("hasPermi('monitor:server:list')")
public class ServerController {
	
	@GetMapping()
	public AjaxResult getInfo() throws Exception {
		Server server = new Server();
		server.copyTo();
		return AjaxResult.success(server);
	}
}
