package com.springboot.core.web.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.springboot.core.SysManager;
import com.springboot.core.config.TokenConfig;
import com.springboot.core.redis.IRedis;

@Component
public class BeanInject {
	/**
	 * 注入配置Bean
	 * 
	 * @param saTokenConfig
	 *            配置对象
	 */
	@Autowired(required = false)
	public void setConfig(TokenConfig tokenConfig) {
		SysManager.setConfig(tokenConfig);
	}

	@Autowired(required = false)
	public void setRedis(IRedis redis) {
		SysManager.setRedis(redis);
	}
}
