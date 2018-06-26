package com.clouddo.messagebus.config;

import com.clouddo.commons.common.constatns.MqQueueConstant;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Rabbit配置类
 * @author zhongming
 * @since 3.0
 * 2018/5/31上午11:05
 */
@Configuration
public class RabbitConfig {

    /**
     * 创建日志队列
     * @return
     */
    @Bean
    public Queue logQueue() {
        return new Queue(MqQueueConstant.LOG_QUEUE);
    }
}
