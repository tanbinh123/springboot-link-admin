package com.springboot.common.scheduled;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.springboot.core.logger.LoggerUtil;

/**
 * 定时器启动类
 * 
 * @ClassName: ScheduledTask
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author 252956
 * @date 2020年1月3日 下午1:27:19
 *
 */
@Component
public class ScheduledTask {

	//@Scheduled(initialDelay = 1000, fixedRate = 1000 * 60)
	public void doTask1() {
		LoggerUtil.info("doTask1");
	}

	//@Scheduled(initialDelay = 1000, fixedRate = 1000 * 65)
	public void doTask2() {
		LoggerUtil.info("doTask2");
	}

}
