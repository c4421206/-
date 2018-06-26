package com.clouddo.ai.server.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 讯飞语音听写配置
 * @author zhongming
 * @since 3.0
 * 2018/6/6下午2:11
 */
@Component("voiceDictationConfig")
@ConfigurationProperties(prefix="ai.voice-dictation.xfyun")
public class VoiceDictationConfig implements AIConfig {

    //讯飞语义识别地址
    private String serviceURL;

    //讯飞语义识别key
    private String apiKey;

    private String appId;


    @Override
    public String getServiceURL() {
        return serviceURL;
    }

    @Override
    public void setServiceURL(String serviceURL) {
        this.serviceURL = serviceURL;
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
    public String getAppId() {
        return appId;
    }

    @Override
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
