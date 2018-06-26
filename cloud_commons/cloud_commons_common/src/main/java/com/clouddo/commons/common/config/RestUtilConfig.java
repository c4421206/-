package com.clouddo.commons.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;

/**
 * rest工具类配置
 * 解决resttemplate无法序列化text/plain的问题
 * @author zhongming
 * @since 3.0
 * 2018/6/5下午2:53
 */
@Configuration
public class RestUtilConfig {



    @Bean
    public RestTemplate restTemplate(){
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().set(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));
        restTemplate.getMessageConverters().add(new PlainMappingJackson2HttpMessageConverter());
        return restTemplate;
    }
}
