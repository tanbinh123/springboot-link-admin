package com.springboot.core;

import com.springboot.core.config.TokenConfig;
import com.springboot.core.redis.IRedis;

public class SysManager {

	/**
	 * 配置文件 Bean
	 */
	private volatile static TokenConfig config;

	public static void setConfig(TokenConfig config) {
		SysManager.config = config;
	}

	public static TokenConfig getConfig() {
		return config;
	}

	private volatile static IRedis redis;

	public static void setRedis(IRedis redis) {
		SysManager.redis = redis;
	}

	public static IRedis getRedis() {
		return redis;
	}

}
