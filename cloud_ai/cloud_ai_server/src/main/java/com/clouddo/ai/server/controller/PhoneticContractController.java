package com.clouddo.ai.server.controller;

import com.clouddo.ai.server.voice.assistant.model.baidu.RequestModel;
import com.clouddo.ai.server.voice.assistant.model.baidu.response.ResponseModel;
import com.clouddo.ai.server.voice.assistant.service.PhoneticContractService;
import com.clouddo.ai.server.voice.assistant.util.BaiduAccessTokenUtil;
import com.clouddo.commons.common.util.GsonUtil;
import com.clouddo.commons.common.util.JSONUtils;
import com.clouddo.commons.common.util.http.RestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * 语音合成controller
 * @author zhongming
 * @since 3.0
 * 2018/6/5下午5:27
 */
@Controller
@RequestMapping("/phoneticContract")
public class PhoneticContractController {

    @Qualifier("baiduPhoneticContractServiceImpl")
    @Autowired
    private PhoneticContractService phoneticContractService;


    /**
     * 语音合成功能
     * @param message
     * @param response
     * @return
     */
    @GetMapping
    public void phoneticContract(@RequestParam String message, HttpServletResponse response) throws IOException {


        // TODO 测试代码
        RequestModel requestModel = new RequestModel(message, "4862");
        System.out.println(JSONUtils.beanToJson(requestModel));
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        String token = BaiduAccessTokenUtil.getToken();
        ResponseEntity responseEntity = RestUtil.restPost("https://aip.baidubce.com/rpc/2.0/unit/bot/chat?access_token=" + token, headers, JSONUtils.beanToJson(requestModel));

        Object result = ((Map<String, Object>)responseEntity.getBody()).get("result");
        ResponseModel responseModel = GsonUtil.jsonToBean(GsonUtil.toJson(result), ResponseModel.class);
        byte[] data = (byte[]) this.phoneticContractService.phoneticContract(message, null);
        OutputStream outputStream = response.getOutputStream();
        outputStream.write(data);
        outputStream.flush();
        outputStream.close();
    }

}
