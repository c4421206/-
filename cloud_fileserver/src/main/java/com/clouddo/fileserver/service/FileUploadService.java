package com.clouddo.fileserver.service;

/**
 * 文件上传服务接口
 * @author zhongming
 * @since 3.0
 * 2018/6/22上午11:27
 */
public interface FileUploadService {

    void upload(String filePath, String key) throws Exception;
}
