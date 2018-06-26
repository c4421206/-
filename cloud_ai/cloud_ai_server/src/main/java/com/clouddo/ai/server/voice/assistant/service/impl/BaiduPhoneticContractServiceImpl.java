package com.clouddo.ai.server.voice.assistant.service.impl;

import com.baidu.aip.speech.AipSpeech;
import com.baidu.aip.speech.TtsResponse;
import com.clouddo.ai.server.voice.assistant.service.PhoneticContractService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 百度语音合成服务
 * @author zhongming
 * @since 3.0
 * 2018/6/12上午10:34
 */
@Service("baiduPhoneticContractServiceImpl")
public class BaiduPhoneticContractServiceImpl implements PhoneticContractService {

    private static Logger logger = LoggerFactory.getLogger(BaiduPhoneticContractServiceImpl.class);

    //注入百度语音sdk
    @Autowired
    private AipSpeech aipSpeech;

    /**
     * 语音合成
     * @param message 要合成的信息
     * @return
     * @throws IOException
     */
    @Override
    public Object phoneticContract(String message, HttpServletResponse response) throws IOException {
        TtsResponse res = aipSpeech.synthesis(message, "zh", 1, null);
        if(res.getResult() != null) {
            logger.warn("百度语音合成失败，文本信息：{}，错误信息：{}", message, res.getResult());
            return null;
        }
        return res.getData();
    }
}
