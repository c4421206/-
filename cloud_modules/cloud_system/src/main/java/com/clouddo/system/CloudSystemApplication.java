package com.clouddo.system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableEurekaClient		//服务提供者
@ComponentScan(basePackages = {
		"com.clouddo.system",
		"com.cloudd.commons.auth.config",	//权限配置
		//配置权限相关拦截器（session拦截器，用户拦截器、服务拦截器）
		"com.cloudd.commons.auth.interceptor",
		//common包服务
		"com.clouddo.commons.common.service",
        //日志服务扫描
        "com.clouddo.log.common.config"
})
public class CloudSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudSystemApplication.class, args);
		System.out.println("ヾ(◍°∇°◍)ﾉﾞ    系统模块启动成功      ヾ(◍°∇°◍)ﾉﾞ\n" +
				" ______                    _   ______            \n" +
				"|_   _ \\                  / |_|_   _ `.          \n" +
				"  | |_) |   .--.    .--. `| |-' | | `. \\  .--.   \n" +
				"  |  __'. / .'`\\ \\/ .'`\\ \\| |   | |  | |/ .'`\\ \\ \n" +
				" _| |__) || \\__. || \\__. || |, _| |_.' /| \\__. | \n" +
				"|_______/  '.__.'  '.__.' \\__/|______.'  '.__.'  ");
	}
}
