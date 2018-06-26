package com.clouddo.ai.server.controller;

import com.cloudd.commons.auth.controller.AuthController;
import com.clouddo.ai.server.voice.assistant.model.ResolveResult;
import com.clouddo.ai.server.voice.assistant.model.baidu.Conversation;
import com.clouddo.ai.server.voice.assistant.service.SemantiServcie;
import com.clouddo.ai.server.voice.assistant.util.BaiduUnitUtil;
import com.clouddo.commons.common.util.message.Result;
import com.clouddo.log.common.annotation.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * 语义识别工具类
 * @author zhongming
 * @since 3.0
 * 2018/6/1下午4:23
 */
@Controller
@RequestMapping("/semantic")
public class SemanticController extends AuthController {

    @Qualifier("xFSemantiServcieImpl")
    @Autowired
    private SemantiServcie semantiServcie;

    @Autowired
    private BaiduUnitUtil baiduUnitUtil;


    /**
     * 识别字符串
     * @param string 要识别的字符串
     * @return 识别后的结果
     */
    @Log("解析语义,参数为string")
    @PostMapping("/recognitionString")
    @ResponseBody
    Result recognitionString(@RequestBody String string) {
        try {
            Object object = this.semantiServcie.recognition(string);
            return Result.success(object);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.failure(e.getMessage());
        }
    }

    /**
     * 语义解析，识别语音
     * @param voice 要语义解析的语音
     * @return 解析后的结果
     */
    @Log("解析语义,参数为语音")
    @PostMapping("/recognitionVoice")
    @ResponseBody
    Result recognitionVoice(@RequestParam("voice") MultipartFile voice) {
        try {
            List<ResolveResult> resolveResults = this.semantiServcie.recognition(voice);
            return Result.success(resolveResults);
        } catch (Exception e) {
            return Result.failure(e.getMessage());
        }
    }


    /**
     * 语音助手对话功能
     * @param parameters 对话参数，第一次对话需要带
     * @return
     */
    @PostMapping("/conversation")
    @ResponseBody
    Result conversation(@RequestBody Map<String, Object> parameters) {
        try {
            String conversationId = (String) parameters.get("conversationId");
            String message = (String) parameters.get("message");
            String bootId = (String) parameters.get("bootId");
            Conversation conversation = baiduUnitUtil.conversation(conversationId, message, bootId);
            return Result.success(conversation.getSimpleData());
        } catch (Exception e) {
            e.printStackTrace();
            return Result.failure(e.getMessage());
        }
    }
}

















