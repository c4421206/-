package com.clouddo.ai.server.voice.assistant.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

/**
 * 百度语音工具类
 * @author zhongming
 * @since 3.0
 * 2018/6/12上午10:14
 */
public class BaiduVoiceUtil {

    private static Logger logger = LoggerFactory.getLogger(BaiduVoiceUtil.class);

    /**
     * 语音听写服务结果解析
     * @param result
     * @return
     */
    public static List<String> analysisVoiceDictation(Map<String, Object> result) {
        //获取错误码
        int errNo = (int) result.get("err_no");
        if(errNo != 0) {
            logger.warn("百度语音解析发生错误，错误码：{}", errNo);
            return null;
        }
        return (List<String>) result.get("result");
    }
}
