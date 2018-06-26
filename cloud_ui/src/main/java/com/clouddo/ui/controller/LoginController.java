package com.clouddo.ui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 登录控制器
 * @author zhongming
 * @since 3.0
 * 2018/5/31下午3:54
 */
@Controller
public class LoginController {

    @GetMapping({ "/", "" })
    String welcome(Model model) {

        return "redirect:/blog";
    }

    /**
     * 跳转到登录页
     * @return
     */
    @GetMapping("/login")
    String login() {
        return "login";
    }

    /**
     * 跳转到首页
     * @return
     */
    @GetMapping("/index")
    String index() {
        return "index_v1";
    }

    /**
     * 跳转到主页
     * @return
     */
    @GetMapping("/main")
    String main() {
        return "main";
    }
}
