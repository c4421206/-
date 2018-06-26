package com.clouddo.ai.server.voice.assistant.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * 语音听写服务层
 * @author zhongming
 * @since 3.0
 * 2018/6/6下午2:05
 */
public interface VoiceDictationServcie {
    /**
     * 语音听写
     * @param voice
     * @return
     * @throws Exception
     */
    String voiceDictation(MultipartFile voice) throws Exception;
}
