package com.clouddo.ai.server.voice.assistant.service.impl;

import com.clouddo.ai.server.config.AIConfig;
import com.clouddo.ai.server.voice.assistant.model.ResolveResult;
import com.clouddo.ai.server.voice.assistant.util.XFAIUtil;
import com.clouddo.ai.server.voice.assistant.service.SemantiServcie;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

/**
 * 讯飞语义分析服务
 * @author zhongming
 * @since 3.0
 * 2018/6/4上午8:32
 */
@Service("xFSemantiServcieImpl")
public class XFSemantiServcieImpl implements SemantiServcie {

    @Resource(name = "semantiConfig")
    private AIConfig xfSemantiConfig;


    /**
     * 语义分析
     * @param message 一句话
     * @return 分析后的语义
     */
    @Override
    public List<ResolveResult> recognition(String message) throws Exception {
        String result = XFAIUtil.recognition(message, xfSemantiConfig);
        //解析结果
        List<ResolveResult> results = XFAIUtil.resolveResult(result);
        return results;
    }

    /**
     * 语义分析，分析音频
     * @param voice base64编码后的音频信息
     * @return 分析后的语义
     */
    @Override
    public List<ResolveResult> recognition(MultipartFile voice) throws Exception {
        //解析语音
        String result = XFAIUtil.recognition(voice, xfSemantiConfig);
        //解析结果
        return XFAIUtil.resolveResult(result);
    }
}
