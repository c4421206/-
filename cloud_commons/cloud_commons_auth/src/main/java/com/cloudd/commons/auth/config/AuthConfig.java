package com.cloudd.commons.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhongming
 * @since 3.0
 * 2018/6/28下午4:03
 */
@Configuration
public class AuthConfig {

    /**
     * 服务端认证配置信息
     * @return
     */
    @Bean
    public ServiceAuthConfig serviceAuthConfig() {
        return new ServiceAuthConfig();
    }

    /**
     * 用户认证配置信息
     * @return
     */
    @Bean
    public UserAuthConfig userAuthConfig() {
        return new UserAuthConfig();
    }
}
