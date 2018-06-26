package com.clouddo.fileserver.service;

import java.io.InputStream;

/**
 * 文件下载服务
 * @author zhongming
 * @since 3.0
 * 2018/6/22下午2:21
 */
public interface FileDownService {

    /**
     * 通过ID下载文件
     * @param id 文件ID
     * @return 文件流信息
     */
    InputStream download(String id) throws Exception;
}
