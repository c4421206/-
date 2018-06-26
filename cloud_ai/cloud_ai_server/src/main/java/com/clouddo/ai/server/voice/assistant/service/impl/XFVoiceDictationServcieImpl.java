package com.clouddo.ai.server.voice.assistant.service.impl;

import com.clouddo.ai.server.config.AIConfig;
import com.clouddo.ai.server.voice.assistant.util.XFAIUtil;
import com.clouddo.ai.server.voice.assistant.service.VoiceDictationServcie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * 语音听写服务层
 * @author zhongming
 * @since 3.0
 * 2018/6/6下午2:05
 */
@Service("xFVoiceDictationServcieImpl")
public class XFVoiceDictationServcieImpl implements VoiceDictationServcie {

    @Qualifier("voiceDictationConfig")
    @Autowired
    private AIConfig aiConfig;

    /**
     * 语音听写
     * @param voice
     * @return
     * @throws Exception
     */
    @Override
    public String voiceDictation(MultipartFile voice) throws Exception {
        return XFAIUtil.voiceDictation(voice, aiConfig);
    }
}
