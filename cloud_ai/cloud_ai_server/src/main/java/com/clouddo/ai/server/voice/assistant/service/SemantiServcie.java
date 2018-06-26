package com.clouddo.ai.server.voice.assistant.service;

import com.clouddo.ai.server.voice.assistant.model.ResolveResult;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 语义分析接口
 * 定义语义分析各功能标准
 * @author zhongming
 * @since 3.0
 * 2018/6/4上午8:27
 */
public interface SemantiServcie {

    /**
     * 语义分析
     * @param message 一句话
     * @return 分析后的语义
     */
    List<ResolveResult> recognition(String message) throws Exception;


    /**
     * 语义分析，分析音频
     * @param voice base64编码后的音频信息
     * @return 分析后的语义
     */
    List<ResolveResult> recognition(MultipartFile voice) throws Exception;

}
