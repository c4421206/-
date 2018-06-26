package com.clouddo.commons.common.service;

import java.util.*;
import java.util.concurrent.Callable;

/**
 * redis 服务接口定义
 * @author zhongming
 * @since 3.0
 * 2018/3/27下午4:06
 */
public interface RedisService extends CacheService {





   /**
    * 写入缓存（仅当不存在是写入）
    * @param key 缓存的key
    * @param value 缓存的value
    */
   void putIfAbsent(final String key, final Object value);


   /**
    * 写入缓存,并设置有效时间（仅当不存在是写入）
    * @param key 缓存的key
    * @param value 缓存的value
    * @param timeout 缓存的有效时间（单位：秒）
    */
   void putIfAbsent(final String key, final Object value, final long timeout);

   /**
    * 写入缓存,并设置过期时间（仅当不存在是写入）
    * @param key 缓存的key
    * @param value 缓存的value
    * @param expireTime 缓存过期时间
    */
   void putIfAbsent(final String key, final Object value, final Date expireTime);

   /**
    * 批量写入缓存
    * @param keyValues 缓存键值对
    */
   void batchPut(final Map<String, Object> keyValues);

   /**
    * 批量写入缓存,并设置有效时间
    * @param keyValues 缓存键值对
    * @param timeout 缓存有效时间（单位：秒）
    */
   void batchPut(final Map<String, Object> keyValues, final long timeout);

   /**
    * 批量写入缓存,并设置过期时间
    * @param keyValues 缓存键值对
    * @param expireTime 缓存过期时间
    */
   void batchPut(final Map<String, Object> keyValues, final Date expireTime);

   /**
    * 批量写入缓存（仅当不存在是写入）
    * @param keyValues 缓存键值对
    */
   void batchPutIfAbsent(final Map<String, Object> keyValues);

   /**
    * 批量写入缓存，并设置有效时间（仅当不存在是写入）
    * @param keyValues 缓存键值对
    * @param timeout 缓存有效时间（单位：秒）
    */
   void batchPutIfAbsent(final Map<String, Object> keyValues, final long timeout);

   /**
    * 批量写入缓存，并设置过期时间（仅当不存在是写入）
    * @param keyValues 缓存键值对
    * @param expireTime 缓存过期时间
    */
   void batchPutIfAbsent(final Map<String, Object> keyValues, final Date expireTime);


   /**
    * 读取缓存（不存在时调用接口获取，并存入缓存）
    * @param key 键
    * @param callable 缓存不存在时获取缓存的接口
    * @param <T> 值的类型
    * @return 值
    */
   <T> T get(String key, Callable<T> callable);

   /**
    * 批量读取缓存
    * @param keys 键集合
    * @return 值集合
    */
   List<Object> batchGet(Collection<Object> keys);

   /**
    * 批量量读取缓存
    * @param keys 键集合
    * @param cls 值的类型
    * @param <T> 值的类型
    * @return 值
    */
   <T> List<T> batchGet(Collection<Object> keys, Class<T> cls);



   /**
    * 批量删除
    * @param keys 键集合
    */
   void batchDelete(Collection<Object> keys);

   /**
    * 设置缓存有效时间
    * @param key 键
    * @param timeout 有效时间（单位：秒）
    */
   void expire(String key, final long timeout);

   /**
    * 设置缓存过期时间
    * @param key 键
    * @param expireTime 过期时间
    */
   void expireAt(String key, final Date expireTime);

   /**
    * 批量设置缓存有效时间
    * @param keys 键集合
    * @param timeout 有效时间（单位：秒）
    */
   void batchExpire(final Collection<String> keys, final long timeout);

   /**
    * 批量设置过期时间
    * @param keys 键集合
    * @param expireTime 过期时间
    */
   void batchExpireAt(final Collection<String> keys, final Date expireTime);

   /**
    * 缓存是否存在
    * @param key 键
    * @return 存在true  不存在false
    */
   boolean exist(String key);

   /**
    * 清除所有缓存
    */
   void clear();

   /**
    * 写入hash缓存
    * @param key 键
    * @param hashKey hash键
    * @param value 值
    */
   void hashPut(String key, String hashKey, Object value);

   /**
    * 读取hash缓存
    * @param key 键
    * @param hashKey hash键
    * @return 值
    */
   Object hashGet(String key, String hashKey);

   /**
    * 读取hash缓存
    * @param key 键
    * @param hashKey 键
    * @param clazz 值类型
    * @param <T> 值类型
    * @return 值
    */
   <T> T hashGet(String key, String hashKey, Class<T> clazz);

   /**
    * 判断hash值是否存在
    * @param key 键
    * @param hashKey hash键
    * @return 存在true  不存在false
    */
   boolean hashExist(String key, String hashKey);

   /**
    * 删除hash值
    * @param key 键
    * @param hashKey hash键
    */
   void hashDelete(String key, String hashKey);

   /**
    * 批量删除hash值
    * @param key 键
    * @param hashKeys hash键集合
    */
   void hashMultiDelete(String key, List<String> hashKeys);

   /**
    * 获取缓存的过期时间
    * @param key 键
    * @return 过期时间
    */
   long getTTL(String key);

   /**
    * 获取指定key的hashkey
    * @param key 键
    * @return hash键集合
    */
   Set<Object> hashKeys(String key);

   /**
    * 设置set缓存
    * @param key 键
    * @param values 值集合
    */
   void setAdd(Object key, Object... values);

   /**
    * 移除set缓存
    * @param key 键
    * @param values 值
    * @return 移除的数量
    */
   Long setRemove(Object key, Object... values);

   /**
    * 获取set缓存中的所有成员
    * @param key 键
    * @return set缓存的所有成员
    */
   Set<Object> getSet(Object key);

   /**
    * 匹配删除
    * @param prefixKey
    */
   void matchDelete(Object prefixKey);

   Set<Object> keys(Object prefixKey);
}
