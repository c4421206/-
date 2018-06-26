package com.clouddo.commons.common.service;

import java.util.Date;

/**
 * 第一缓存服务接口
 * @author zhongming
 * @since 3.0
 * 2018/6/19下午5:06
 */
public interface CacheService {

    /**
     * 写入缓存
     * @param key 缓存的key
     * @param value 缓存的value
     */
    void put(final String key, final Object value);


    /**
     * 读取缓存
     * @param key 键
     * @return 值
     */
    Object get(String key);

    /**
     * 读取缓存
     * @param key 键
     * @param clazz 值类型
     * @param <T> 值类型
     * @return 值
     */
    <T> T get(String key, Class<T> clazz);

    /**
     * 删除缓存
     * @param key 键
     */
    void delete(String key);

    /**
     * 写入缓存，并设置有效时间
     * @param key 缓存的key
     * @param value 缓存的value
     * @param timeout 缓存的有效时间（单位：秒）
     */
    void put(final String key, final Object value, final long timeout);

    /**
     * 写入缓存，并设置过期时间
     * @param key 缓存的key
     * @param value 缓存的value
     * @param expireTime 缓存过期时间
     */
    void put(final String key, final Object value, final Date expireTime);
}
