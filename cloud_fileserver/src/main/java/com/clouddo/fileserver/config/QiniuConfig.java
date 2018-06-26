package com.clouddo.fileserver.config;

import com.qiniu.common.Zone;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 七牛云配置
 * @author zhongming
 * @since 3.0
 * 2018/6/22上午10:00
 */
@Configuration
public class QiniuConfig {

    /**
     * 机房信息
     */
    @Value("${qiniu.bucket.zone}")
    private String zone;

    /**
     * 认证信息
     */
    @Value("${qiniu.auth.accessKey}")
    private String accessKey;
    @Value("${qiniu.auth.secretKey}")
    private String secretKey;


    /**
     * 创建上传管理器
     * @return
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    @Bean
    public UploadManager uploadManager() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        return new UploadManager(createConfiguration());
    }

    /**
     * 创建认证工具
     * @return
     */
    @Bean
    public Auth auth() {
        return Auth.create(this.accessKey, this.secretKey);
    }

    /**
     * 创建配置信息
     * 根据机房信息，采用反射生成配置信息
     * @return
     */
    private com.qiniu.storage.Configuration createConfiguration() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        //获取方法
        Method method = Zone.class.getMethod(this.zone);
        //执行方法
        return new com.qiniu.storage.Configuration((Zone) method.invoke(null, null));
    }
}











