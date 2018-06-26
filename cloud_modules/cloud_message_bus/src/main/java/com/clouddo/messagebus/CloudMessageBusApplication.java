package com.clouddo.messagebus;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients		//启用feign支持
@EnableEurekaClient		//服务提供者
@EnableRabbit
public class CloudMessageBusApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudMessageBusApplication.class, args);
	}
}
