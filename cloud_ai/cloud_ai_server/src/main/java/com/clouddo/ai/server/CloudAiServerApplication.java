package com.clouddo.ai.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableEurekaClient        //服务提供者
@ComponentScan(basePackages = {
		"com.clouddo.ai.server",
		//扫描权限认证工具包，扫描该包会同时扫描3个包，请查看com.cloudd.commons.auth.interceptor.InterceptorConfig类
		"com.cloudd.commons.auth.interceptor", //不扫描则不权限认证，不支持session
		//日志服务扫描
		"com.clouddo.log.common.config"
})
public class CloudAiServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudAiServerApplication.class, args);
		System.out.println("ヾ(◍°∇°◍)ﾉﾞ    AI服务模块启动成功      ヾ(◍°∇°◍)ﾉﾞ\n" +
				" ______                    _   ______            \n" +
				"|_   _ \\                  / |_|_   _ `.          \n" +
				"  | |_) |   .--.    .--. `| |-' | | `. \\  .--.   \n" +
				"  |  __'. / .'`\\ \\/ .'`\\ \\| |   | |  | |/ .'`\\ \\ \n" +
				" _| |__) || \\__. || \\__. || |, _| |_.' /| \\__. | \n" +
				"|_______/  '.__.'  '.__.' \\__/|______.'  '.__.'  ");
	}
}
