package com.clouddo.ui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * blog页面跳转controller
 * @author zhongming
 * @since 3.0
 * 2018/5/31下午3:47
 */
@RequestMapping("/blog")
@Controller
public class BlogController {



    @GetMapping()
    String blog() {
        return "blog/index/main";
    }
}
