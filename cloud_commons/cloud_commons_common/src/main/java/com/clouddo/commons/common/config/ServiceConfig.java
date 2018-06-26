package com.clouddo.commons.common.config;

import com.clouddo.commons.common.service.ApplicationContextRegister;
import com.clouddo.commons.common.service.RedisService;
import com.clouddo.commons.common.service.impl.DefaultRedisServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * common服务配置
 * @author zhongming
 * @since 3.0
 * 2018/6/5下午2:49
 */
@Configuration
public class ServiceConfig {


    /**
     * redis服务配置
     * @param redisTemplate
     * @return
     */
    @Bean
    public RedisService redisService(RedisTemplate<Object, Object> redisTemplate) {
        return new DefaultRedisServiceImpl(redisTemplate);
    }

    /**
     * 获取spring上下文信息
     * @return
     */
    @Bean
    public ApplicationContextRegister applicationContextRegister() {
        return new ApplicationContextRegister();
    }
}
