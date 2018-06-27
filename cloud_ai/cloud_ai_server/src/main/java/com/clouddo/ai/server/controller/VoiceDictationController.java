package com.clouddo.ai.server.controller;

import com.baidu.aip.speech.AipSpeech;
import com.cloudd.commons.auth.controller.AuthController;
import com.clouddo.ai.server.voice.assistant.service.VoiceDictationServcie;
import com.clouddo.commons.common.util.message.Result;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * 语音识别controller
 * @author zhongming
 * @since 3.0
 * 2018/6/6下午2:25
 */
@Controller
@RequestMapping("/voiceDictation")
public class VoiceDictationController extends AuthController {

    @Autowired
    @Resource
    @Qualifier("baiduVoiceDictationServcieImpl")
    private VoiceDictationServcie voiceDictationServcie;

    @Autowired
    private AipSpeech aipSpeech;

    @RequestMapping
    @ResponseBody
    public Result voiceDictation(@RequestParam("voice") MultipartFile voice) {
        try {
            return Result.success(this.voiceDictationServcie.voiceDictation(voice));
        } catch (Exception e) {
            e.printStackTrace();
            return Result.failure(e.getMessage());
        }
    }

    //TODO 测试代码
    @ResponseBody
    @RequestMapping("/test")
    public Result test() {
        try {
            JSONObject res = aipSpeech.asr("/Users/ming/Documents/测试/1/1528263906629_out.pcm","pcm", 16000, null);
            return Result.success(res.toMap());
        } catch (Exception e) {
            e.printStackTrace();
            return Result.failure(e.getMessage());
        }
    }
}
