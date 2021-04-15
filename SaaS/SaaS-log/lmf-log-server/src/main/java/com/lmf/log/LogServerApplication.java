package com.saas.log;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableDubbo
@EnableJpaRepositories(basePackages = { "com.saas.log.repository" })
@EntityScan(basePackages = { "com.saas.log.domain" })
@SpringBootApplication
public class LogServerApplication {
	public static void main(String[] args) {
		// System.setProperty("spring.devtools.restart.enabled", "false");
		SpringApplication.run(LogServerApplication.class, args);
		System.out.println("(♥◠‿◠)ﾉﾞ  Log Server启动成功   ლ(´ڡ`ლ)ﾞ  \n" + " .-------.       ____     __        \n"
				+".____       _____  ___________\r\n"
				+ "|    |     /     \\ \\_   _____/\r\n"
				+ "|    |    /  \\ /  \\ |    __)  \r\n"
				+ "|    |___/    Y    \\|     \\   \r\n"
				+ "|_______ \\____|__  /\\___  /   \r\n"
				+ "        \\/       \\/     \\/    ");
	}
}
