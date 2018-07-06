package com.clouddo.commons.meteo.config;

import com.clouddo.commons.meteo.config.model.IsosurfaceConfig;

/**
 * 创建等值面配置
 * @author zhongming
 * @since 1.0
 * 2018/7/2上午9:10
 */
public class IsosurfaceConfigCreater {


    /**
     * 创建默认的等值面配置
     * @return 默认的等值面配置
     */
    public static IsosurfaceConfig createDefaultIsosurfaceConfig() {
        return new IsosurfaceConfig(20, 20, 2, 1);
    }

}
