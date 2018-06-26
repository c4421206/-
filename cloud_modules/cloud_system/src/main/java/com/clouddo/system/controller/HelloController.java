package com.clouddo.system.controller;

import com.cloudd.commons.auth.annotation.RequiresPermissions;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author zhongming
 * @since 3.0
 * 2018/5/16下午4:17
 */
@Controller
@RefreshScope
public class HelloController {


    @RequiresPermissions("test")
    @ResponseBody
    @RequestMapping("/hello")
    public String hello() {
        return "hello world";
    }

    @GetMapping("/testRedict")
    public String testRedict() {
        return "redirect:/hello";
    }

}
