package com.springboot.core.web.spring;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.springboot.core.config.TokenConfig;

@Configuration
public class BeanRegister {
	/**
	 * 获取配置Bean
	 * 
	 * @return 配置对象
	 */
	@Bean
	@ConfigurationProperties(prefix = "linkadmin.token")
	public TokenConfig getSaTokenConfig() {
		return new TokenConfig();
	}

}
