package com.clouddo.ai.server.config;

/**
 * @author zhongming
 * @since 3.0
 * 2018/6/5下午1:34
 */
public interface AIConfig {

    String getServiceURL();

    void setServiceURL(String serviceURL);

    String getApiKey();

    void setApiKey(String apiKey);

    String getAppId();

    void setAppId(String appId);

    String getSecretKey();

    void setSecretKey(String secretKey);
}
