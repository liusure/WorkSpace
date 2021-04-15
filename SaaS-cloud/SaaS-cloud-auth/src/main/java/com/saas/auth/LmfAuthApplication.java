package com.saas.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import com.saas.log.annotation.EnableLmfLog;

/**
 * 认证授权中心
 * 
 */
@EnableLmfLog
@EnableFeignClients(basePackages = "com.saas.cloud.api")
@EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class, DruidDataSourceAutoConfigure.class,
		SecurityAutoConfiguration.class })
@SpringCloudApplication
public class LmfAuthApplication {
	public static void main(String[] args) {
		SpringApplication.run(LmfAuthApplication.class, args);
		System.out.println("(♥◠‿◠)ﾉﾞ  认证授权中心启动成功   ლ(´ڡ`ლ)ﾞ  \n" + ".____       _____  ___________\r\n"
				+ "|    |     /     \\ \\_   _____/\r\n" + "|    |    /  \\ /  \\ |    __)  \r\n"
				+ "|    |___/    Y    \\|     \\   \r\n" + "|_______ \\____|__  /\\___  /   \r\n"
				+ "        \\/       \\/     \\/    ");
	}
}
