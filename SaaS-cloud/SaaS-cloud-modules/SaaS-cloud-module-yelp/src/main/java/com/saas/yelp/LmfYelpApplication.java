package com.saas.yelp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.saas.log.annotation.EnableLmfLog;

@EnableLmfLog
@EnableFeignClients(basePackages = "com.saas.cloud.api")
@EnableAutoConfiguration(exclude = { SecurityAutoConfiguration.class })
@SpringCloudApplication
public class LmfYelpApplication {
	public static void main(String[] args) {
		SpringApplication.run(LmfYelpApplication.class, args);
		System.out.println("(♥◠‿◠)ﾉﾞ  点评模块启动成功   ლ(´ڡ`ლ)ﾞ  \n" + ".____       _____  ___________\r\n"
				+ "|    |     /     \\ \\_   _____/\r\n" + "|    |    /  \\ /  \\ |    __)  \r\n"
				+ "|    |___/    Y    \\|     \\   \r\n" + "|_______ \\____|__  /\\___  /   \r\n"
				+ "        \\/       \\/     \\/    ");
	}
}
