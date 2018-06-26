package com.clouddo.ai.server.voice.assistant.service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 语音合成服务定义
 * @author zhongming
 * @since 3.0
 * 2018/6/5下午2:31
 */
public interface PhoneticContractService {
    /**
     * 语音合成
     * @param message 要合成的信息
     * @return
     * @throws IOException
     */
    Object phoneticContract(String message, HttpServletResponse response) throws IOException;
}
