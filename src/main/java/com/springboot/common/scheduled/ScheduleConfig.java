package com.springboot.common.scheduled;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

/**
 * springboot 定时任务默认单线程，修改为多线程模式
 */
@Configuration
public class ScheduleConfig {

	@Bean
	public TaskScheduler taskScheduler() {
		// Spring提供的定时任务线程池类
		ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
		// 设定最大可用的线程数目
		taskScheduler.setPoolSize(10);
		return taskScheduler;
	}
}