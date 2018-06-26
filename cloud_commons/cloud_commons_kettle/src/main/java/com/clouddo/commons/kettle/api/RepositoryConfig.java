package com.clouddo.commons.kettle.api;

/**
 * kettle 资源库配置
 * @author zhongming
 * @since 3.0
 * 2018/6/13下午6:49
 */
public interface RepositoryConfig {

    void setId(String id);

    String getId();

    void setName(String name);

    String getName();

    String getDataType();

    String getAccess();

    String getHost();

    String getDB();

    String getPort();

    String getUsername();

    String getPassword();
}
