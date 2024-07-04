package com.walmart.test.apps.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.walmart.test.apps.vo.AzureBSCreds;


@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.walmart.test.apps.*")
public class AppConfig {
	
	@Bean(name = "secretsBean")
	@ConfigurationProperties(prefix = "azure")
	public AzureBSCreds getAzureCreds() {
		AzureBSCreds secretBean = new AzureBSCreds();
		return secretBean;
	}

}
