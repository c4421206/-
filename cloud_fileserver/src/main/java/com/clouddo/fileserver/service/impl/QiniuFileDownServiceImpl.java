package com.clouddo.fileserver.service.impl;

import com.clouddo.fileserver.model.CloudFile;
import com.clouddo.fileserver.service.CloudFileService;
import com.clouddo.fileserver.service.FileDownService;
import com.qiniu.util.Auth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.InputStream;
import java.net.URLEncoder;

/**
 * 七牛文件下载服务
 * @author zhongming
 * @since 3.0
 * 2018/6/22下午2:24
 */
@Service("qiniuFileDownServiceImpl")
public class QiniuFileDownServiceImpl implements FileDownService {

    private static final Logger LOGGER = LoggerFactory.getLogger(QiniuFileDownServiceImpl.class);

    @Value("${qiniu.bucket.downUrl}")
    private String baseDownloadUrl;

    /**
     * 文件服务层
     */
    @Autowired
    private CloudFileService cloudFileService;

    /**
     * 七牛云认证信息
     */
    @Autowired
    private Auth auth;

    /**
     * 通过ID下载文件
     * @param id 文件ID
     * @return 文件流信息
     */
    @Override
    public InputStream download(String id) throws Exception {
        //获取文件信息
        CloudFile cloudFile = this.cloudFileService.get(id);
        if(cloudFile == null || StringUtils.isEmpty(id)) {
            LOGGER.warn("下载文件失败，文件ID或文件信息为null，文件ID：{}", id);
            return null;
        }
        //格式化文件名
        String encodedFileName = URLEncoder.encode(cloudFile.getRealName(), "UTF-8");
        String publicUrl = String.format("%s/%s", baseDownloadUrl, encodedFileName);
        String finalUrl = auth.privateDownloadUrl(publicUrl, 3600);
        return null;
    }
}
