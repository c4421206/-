package com.clouddo.ai.server.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 百度语音配置类
 * @author zhongming
 * @since 3.0
 * 2018/6/8下午3:05
 */
@Component("baiduVoiceConfig")
@ConfigurationProperties(prefix = "ai.baidu.auth")
public class BaiduVoiceConfig implements AIConfig {

    private String appId;

    private String apiKey;

    private String secretKey;

    private String serviceURL;

    @Override
    public String getAppId() {
        return appId;
    }

    @Override
    public void setAppId(String appId) {
        this.appId = appId;
    }

    @Override
    public String getApiKey() {
        return apiKey;
    }

    @Override
    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    @Override
    public String getSecretKey() {
        return secretKey;
    }

    @Override
    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    @Override
    public String getServiceURL() {
        return serviceURL;
    }

    @Override
    public void setServiceURL(String serviceURL) {
        this.serviceURL = serviceURL;
    }
}
