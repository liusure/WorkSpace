package com.saas.admin;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.saas.common.security.annotation.EnableCustomSecurity;
import com.saas.common.web.annotation.EnableCustomXssFilter;
import com.saas.log.annotation.EnableLmfLog;

/**
 * web容器中进行部署
 * 
 */
@EnableLmfLog
@EnableCustomSecurity
@EnableCustomXssFilter
@EnableJpaRepositories(basePackages = { "com.saas.system.repository", "com.saas.generator.repository" })
@EntityScan(basePackages = { "com.saas.common.core.domain", "com.saas.system.domain", "com.saas.generator.domain" })
public class LmfServletInitializer extends SpringBootServletInitializer
{
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application)
    {
        return application.sources(LmfApplication.class);
    }
    
    public static void main(String[] args) {
    	
    }
}
