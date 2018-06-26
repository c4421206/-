package com.clouddo.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({
		//本项目
		"com.clouddo.auth",
		//config包
		"com.cloudd.commons.auth.config",
		//common feign
		"com.clouddo.commons.common.service",
		"com.cloudd.commons.auth.util",
		"com.cloudd.commons.auth.interceptor"
})
@EnableFeignClients		//开启feign
@EnableEurekaClient   //服务提供者
public class CloudAuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudAuthApplication.class, args);
		System.out.println("ヾ(◍°∇°◍)ﾉﾞ    认证中心启动成功      ヾ(◍°∇°◍)ﾉﾞ\n" +
				" ______                    _   ______            \n" +
				"|_   _ \\                  / |_|_   _ `.          \n" +
				"  | |_) |   .--.    .--. `| |-' | | `. \\  .--.   \n" +
				"  |  __'. / .'`\\ \\/ .'`\\ \\| |   | |  | |/ .'`\\ \\ \n" +
				" _| |__) || \\__. || \\__. || |, _| |_.' /| \\__. | \n" +
				"|_______/  '.__.'  '.__.' \\__/|______.'  '.__.'  ");
	}
}
