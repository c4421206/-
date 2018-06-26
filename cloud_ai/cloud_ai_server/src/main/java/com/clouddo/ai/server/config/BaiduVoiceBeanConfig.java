package com.clouddo.ai.server.config;

import com.baidu.aip.speech.AipSpeech;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 百度bean配置
 * @author zhongming
 * @since 3.0
 * 2018/6/8下午3:12
 */
@Configuration
public class BaiduVoiceBeanConfig {

    //网络连接参数
    @Value("${ai.baidu.connectionTimeout:null}")
    private Integer connectionTimeout;
    @Value("${ai.baidu.socketTimeout:null}")
    private Integer socketTimeout;


    @Bean
    public AipSpeech aipSpeech(@Qualifier("baiduVoiceConfig") AIConfig aiConfig) {
        AipSpeech aipSpeech = new AipSpeech(aiConfig.getAppId(), aiConfig.getApiKey(), aiConfig.getSecretKey());

        // 可选：设置网络连接参数
        if(connectionTimeout != null) {
            aipSpeech.setConnectionTimeoutInMillis(connectionTimeout);
        }
        if(socketTimeout != null) {
            aipSpeech.setSocketTimeoutInMillis(socketTimeout);
        }
        return aipSpeech;
    }

}
