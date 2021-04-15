package com.saas.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.saas.common.security.annotation.EnableCustomSecurity;
import com.saas.common.web.annotation.EnableCustomXssFilter;
import com.saas.log.annotation.EnableLmfLog;

/**
 * 启动程序
 * 
 */
@EnableLmfLog
@EnableCustomSecurity
@EnableCustomXssFilter
@SpringBootApplication(scanBasePackages ={"com.saas.admin","com.saas.system","com.saas.generator"}) // (exclude = {
						// DataSourceAutoConfiguration.class,SecurityAutoConfiguration.class })
@EnableJpaRepositories(basePackages = { "com.saas.system.repository", "com.saas.generator.repository" })
@EntityScan(basePackages = { "com.saas.common.core.domain", "com.saas.system.domain", "com.saas.generator.domain" })
public class LmfApplication {
	public static void main(String[] args) {
		// System.setProperty("spring.devtools.restart.enabled", "false");
		SpringApplication.run(LmfApplication.class, args);
		System.out.println("(♥◠‿◠)ﾉﾞ  LMF启动成功   ლ(´ڡ`ლ)ﾞ  \n" + " .-------.       ____     __        \n"
				+ ".____       _____  ___________\r\n" + "|    |     /     \\ \\_   _____/\r\n"
				+ "|    |    /  \\ /  \\ |    __)  \r\n" + "|    |___/    Y    \\|     \\   \r\n"
				+ "|_______ \\____|__  /\\___  /   \r\n" + "        \\/       \\/     \\/    ");
	}

}
