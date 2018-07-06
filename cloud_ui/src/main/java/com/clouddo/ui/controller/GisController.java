package com.clouddo.ui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author zhongming
 * @since 3.0
 * 2018/7/1下午8:13
 */
@Controller
@RequestMapping("/gis")
public class GisController {

    @RequestMapping
    public Object gis() {
        return new ModelAndView("service/gis/gis");
    }
}
