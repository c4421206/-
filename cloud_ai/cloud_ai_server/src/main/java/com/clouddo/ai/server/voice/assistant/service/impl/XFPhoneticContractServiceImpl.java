package com.clouddo.ai.server.voice.assistant.service.impl;

import com.clouddo.ai.server.config.AIConfig;
import com.clouddo.ai.server.voice.assistant.service.PhoneticContractService;
import com.clouddo.ai.server.voice.assistant.util.XFAIUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 讯飞语音合成实现类
 * @author zhongming
 * @since 3.0
 * 2018/6/5下午2:32
 */
@Service("xFPhoneticContractServiceImpl")
public class XFPhoneticContractServiceImpl implements PhoneticContractService {

    @Resource(name = "phoneticContractConfig")
    private AIConfig phoneticContractConfig;


    /**
     * 语音合成
     * @param message 要合成的信息
     * @return
     * @throws IOException
     */
    @Override
    public Object phoneticContract(String message, HttpServletResponse response) throws IOException {
        XFAIUtil.phoneticContract(message, phoneticContractConfig, response.getOutputStream());
        return null;
    }
}
