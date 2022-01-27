package com.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.springboot.core.SysManager;

/**
 * springboot-link-admin
 *
 * @author 252956
 * @ClassName: App
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2020年6月20日 下午4:45:35
 */

@SpringBootApplication
@EnableTransactionManagement
public class App {
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
		System.out.println("\n启动成功：springboot-link-admin配置如下："
				+ SysManager.getConfig());
	}
}
