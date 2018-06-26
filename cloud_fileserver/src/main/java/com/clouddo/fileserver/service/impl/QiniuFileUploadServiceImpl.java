package com.clouddo.fileserver.service.impl;

import com.clouddo.fileserver.service.FileUploadService;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author zhongming
 * @since 3.0
 * 2018/6/22下午1:23
 */
@Service("qiniuFileUploadServiceImpl")
public class QiniuFileUploadServiceImpl implements FileUploadService {

    /**
     * 存储空间
     */
    @Value("${qiniu.bucket.name}")
    private String bucket;

    /**
     * 七牛云存储
     */
    @Autowired
    private Auth auth;

    /**
     * 七牛云上传管理器
     */
    @Autowired
    private UploadManager uploadManager;

    /**
     * 上传文件
     * @param filePath 文件路径
     * @param key 文件名
     * @throws QiniuException
     */
    @Override
    public void upload(String filePath, String key) throws QiniuException {
        String token = auth.uploadToken(bucket);
        Response response = uploadManager.put(filePath, key, token);
        System.out.println(response);
    }
}













