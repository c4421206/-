package com.clouddo.log.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableEurekaClient        //服务提供者
@ComponentScan(basePackages = {
		"com.clouddo.log.server",
		//权限配置扫描
		"com.cloudd.commons.auth.config",
		//权限拦截器扫描
		"com.cloudd.commons.auth.interceptor",
		//common包服务
		"com.clouddo.commons.common.service"
})
public class CloudLogServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudLogServerApplication.class, args);
	}
}
