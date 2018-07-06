package com.clouddo.hydrology.controller;

import com.cloudd.commons.auth.controller.AuthController;
import com.clouddo.commons.common.util.message.Result;
import com.clouddo.hydrology.service.StAddvcdDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * 测试接口
 * @author zhongming
 * @since 1.0
 * 2018/7/5下午2:01
 */
@Controller
@RequestMapping("/test")
public class TestController extends AuthController {

    @Autowired
    private StAddvcdDService stAddvcdDService;

    @ResponseBody
    @RequestMapping("/listAddvcdRain")
    public Object listAddvcdRain(@RequestBody Map<String, Object> parameters) {
        try {
            return Result.success(stAddvcdDService.countAddvcdRain(parameters));
        } catch (Exception e) {
            e.printStackTrace();
            return Result.failure(e.getMessage());
        }
    }
}
