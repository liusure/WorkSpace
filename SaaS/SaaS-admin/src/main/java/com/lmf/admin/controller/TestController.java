package com.saas.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.saas.system.repository.SysPrivilegeDao;

@RestController
public class TestController {

	@Autowired
	private SysPrivilegeDao sysPrivilegeDao;

	@RequestMapping("/")
	public String hello() {
		
		sysPrivilegeDao.findAll().forEach((item)->{
			System.out.println(item);
		});
		return "hello mini app web application!";
		
	}
}
