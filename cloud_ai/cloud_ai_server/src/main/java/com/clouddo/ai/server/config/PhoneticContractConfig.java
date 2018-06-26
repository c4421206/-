package com.clouddo.ai.server.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 讯飞语音合同配置类
 * @author zhongming
 * @since 3.0
 * 2018/6/5下午1:23
 */
@Component("phoneticContractConfig")
@ConfigurationProperties(prefix = "ai.phonetic-contract.xfyun")
public class PhoneticContractConfig implements AIConfig {

    //讯飞语音合成地址
    private String serviceURL;

    //讯飞语音合成key
    private String apiKey;

    private String appId;


    public String getServiceURL() {
        return serviceURL;
    }

    public void setServiceURL(String serviceURL) {
        this.serviceURL = serviceURL;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    @Override
    public String getSecretKey() {
        return null;
    }

    @Override
    public void setSecretKey(String secretKey) {

    }
}
