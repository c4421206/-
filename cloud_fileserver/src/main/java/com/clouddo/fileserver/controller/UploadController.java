package com.clouddo.fileserver.controller;

import com.cloudd.commons.auth.controller.AuthController;
import com.clouddo.fileserver.service.FileDownService;
import com.clouddo.fileserver.service.FileUploadService;
import com.github.tobato.fastdfs.service.TrackerClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 文件上传控制器
 * @author zhongming
 * @since 3.0
 * 2018/6/22下午1:42
 */
@Controller
@RequestMapping("/upload")
public class UploadController extends AuthController {

    @Autowired
    @Qualifier("qiniuFileUploadServiceImpl")
    private FileUploadService fileUploadService;

    @Autowired
    @Qualifier("qiniuFileDownServiceImpl")
    private FileDownService fileDownService;

    @Autowired
    private TrackerClient trackerClient;

    @PostMapping("/test")
    @ResponseBody
    public Object test() throws Exception {
//        this.fileUploadService.upload("/Users/ming/Documents/idea/clouddo/pom.xml", "pom.xml");
        this.fileDownService.download("1");
        return new Object();
    }
}
