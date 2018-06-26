package com.clouddo.ai.server.voice.assistant.util;

import com.baidu.aip.auth.DevAuth;
import com.clouddo.ai.server.config.AIConfig;
import com.clouddo.commons.common.service.ApplicationContextRegister;
import com.clouddo.commons.common.service.RedisService;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.util.Calendar;
import java.util.Date;

/**
 * 百度平台token管理工具
 * @author zhongming
 * @since 3.0
 * 2018/6/12下午1:59
 */
public class BaiduAccessTokenUtil {

    //redis中存储token的key
    private static final String BAIDU_TOKEN_KEY = "baiduTokenKey";

    private static final Logger LOGGER = LoggerFactory.getLogger(BaiduAccessTokenUtil.class);

    //redis服务层
    private static RedisService redisService;

    private static AIConfig aiConfig;

    /**
     * 获取百度API接口token
     * @return token
     */
    public static String getToken() {
        //初始化
        init();
        String token = (String) redisService.get(BAIDU_TOKEN_KEY);
        if(StringUtils.isEmpty(token)) {
            token = refreshToken();
        }
        return token;
    }

    /**
     * 刷新token
     * TODO 重试机制
     */
    private static String refreshToken() {
        //获取token
        JSONObject jsonObject = DevAuth.oauth(aiConfig.getApiKey(), aiConfig.getSecretKey(), null);
        //判断token信息是否存在
        if(!jsonObject.isNull("access_token")) {
            String token = jsonObject.getString("access_token");
            //获取过期时间
            Integer expireSec = jsonObject.getInt("expires_in");
            Calendar c = Calendar.getInstance();
            c.add(Calendar.SECOND, expireSec);
            Date expireDate = c.getTime();
            //将token存储到redis
            redisService.put(BAIDU_TOKEN_KEY, token, expireDate);
            return token;
        } else if(!jsonObject.isNull("error_code")) {
            LOGGER.warn("获取token失败，失败信息：{}", jsonObject);
        }
        return null;
    }

    private static void init() {
        //初始化redisService
        if(redisService == null) {
            redisService = ApplicationContextRegister.getBean(RedisService.class);
        }
        if(aiConfig == null) {
            aiConfig = (AIConfig) ApplicationContextRegister.getBean("baiduVoiceConfig");
        }
    }
}
