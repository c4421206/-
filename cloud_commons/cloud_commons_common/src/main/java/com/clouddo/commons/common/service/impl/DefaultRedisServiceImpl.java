package com.clouddo.commons.common.service.impl;

import com.clouddo.commons.common.service.RedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisStringCommands;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.types.Expiration;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * 默认的redis服务接口实现类
 * @author zhongming
 * @since 3.0
 * 2018/3/27下午4:06
 */
public class DefaultRedisServiceImpl implements RedisService {

    private static Logger logger = LoggerFactory.getLogger(DefaultRedisServiceImpl.class);

    private static final String DEFAULT_CACHE_KEY = "default_cache";

    private RedisTemplate<Object, Object> redisTemplate;


    /**
     * 使用构造注入的方式注入组件
     */
    public DefaultRedisServiceImpl(RedisTemplate<Object, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }


    /**
     * 写入缓存
     * @param key 缓存的key
     * @param value 缓存的value
     */
    @Override
    public void put(String key, Object value) {
        ValueOperations<Object, Object> valueOperations = redisTemplate.opsForValue();
        valueOperations.set(key, value);
    }

    /**
     * 写入缓存，并设置有效时间
     * @param key 缓存的key
     * @param value 缓存的value
     * @param timeout 缓存的有效时间（单位：秒）
     */
    @Override
    public void put(String key, Object value, long timeout) {
        ValueOperations<Object, Object> valueOperations = redisTemplate.opsForValue();
        valueOperations.set(key, value, timeout, TimeUnit.SECONDS);
    }

    /**
     * 写入缓存，并设置过期时间
     * @param key 缓存的key
     * @param value 缓存的value
     * @param expireTime 缓存过期时间
     */
    @Override
    public void put(String key, Object value, Date expireTime) {
        this.put(key, value);
        redisTemplate.expireAt(key, expireTime);
    }

    /**
     * 写入缓存（仅当不存在是写入）
     * @param key 缓存的key
     * @param value 缓存的value
     */
    @Override
    public void putIfAbsent(String key, Object value) {
        this.redisTemplate.opsForValue().setIfAbsent(key, value);
    }

    /**
     * 写入缓存,并设置有效时间（仅当不存在是写入）
     * @param key 缓存的key
     * @param value 缓存的value
     * @param timeout 缓存的有效时间（单位：秒）
     */
    @Override
    public void putIfAbsent(String key, Object value, long timeout) {
        this.redisTemplate.opsForValue().setIfAbsent(key, value);
        redisTemplate.expire(key, timeout, TimeUnit.SECONDS);
    }

    /**
     * 写入缓存,并设置过期时间（仅当不存在是写入）
     * @param key 缓存的key
     * @param value 缓存的value
     * @param expireTime 缓存过期时间
     */
    @Override
    public void putIfAbsent(String key, Object value, Date expireTime) {
        this.redisTemplate.opsForValue().setIfAbsent(key, value);
        redisTemplate.expireAt(key, expireTime);
    }

    /**
     * 批量写入缓存
     * @param keyValues 缓存键值对
     */
    @Override
    public void batchPut(Map<String, Object> keyValues) {
        this.redisTemplate.opsForValue().multiSet(keyValues);
    }

    /**
     * 批量写入缓存,并设置有效时间
     * @param keyValues 缓存键值对
     * @param timeout 缓存有效时间（单位：秒）
     */
    @Override
    public void batchPut(Map<String, Object> keyValues, long timeout) {
        redisTemplate.executePipelined((RedisCallback) connection -> {
            RedisSerializer keySerializer = redisTemplate.getKeySerializer();
            RedisSerializer valueSerializer = redisTemplate.getValueSerializer();
            for (Map.Entry<String, Object> entry : keyValues.entrySet()) {
                connection.set(keySerializer.serialize(entry.getKey()), valueSerializer.serialize(entry.getValue()),
                        Expiration.seconds(timeout), RedisStringCommands.SetOption.UPSERT);
            }
            return null;
        });
    }

    /**
     * 批量写入缓存,并设置过期时间
     * @param keyValues 缓存键值对
     * @param expireTime 缓存过期时间
     */
    @Override
    public void batchPut(Map<String, Object> keyValues, Date expireTime) {
        redisTemplate.executePipelined(new RedisCallback() {

            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer keySerializer = redisTemplate.getKeySerializer();
                RedisSerializer valueSerializer = redisTemplate.getValueSerializer();
                for (Map.Entry<String, Object> entry : keyValues.entrySet()) {
                    connection.set(keySerializer.serialize(entry.getKey()), valueSerializer.serialize(entry.getValue()),
                            Expiration.milliseconds(expireTime.getTime() - System.currentTimeMillis()),
                            RedisStringCommands.SetOption.UPSERT);
                }
                return null;
            }
        });
    }

    /**
     * 批量写入缓存（仅当不存在是写入）
     * @param keyValues 缓存键值对
     */
    @Override
    public void batchPutIfAbsent(Map<String, Object> keyValues) {
        this.redisTemplate.opsForValue().multiSetIfAbsent(keyValues);
    }

    /**
     * 批量写入缓存，并设置有效时间（仅当不存在是写入）
     * @param keyValues 缓存键值对
     * @param timeout 缓存有效时间（单位：秒）
     */
    @Override
    public void batchPutIfAbsent(Map<String, Object> keyValues, long timeout) {
        redisTemplate.executePipelined((RedisCallback) connection -> {
            RedisSerializer keySerializer = redisTemplate.getKeySerializer();
            RedisSerializer valueSerializer = redisTemplate.getValueSerializer();
            for (Map.Entry<String, Object> entry : keyValues.entrySet()) {
                connection.set(keySerializer.serialize(entry.getKey()), valueSerializer.serialize(entry.getValue()),
                        Expiration.seconds(timeout), RedisStringCommands.SetOption.SET_IF_ABSENT);
            }
            return null;
        });
    }

    /**
     * 批量写入缓存，并设置过期时间（仅当不存在是写入）
     * @param keyValues 缓存键值对
     * @param expireTime 缓存过期时间
     */
    @Override
    public void batchPutIfAbsent(Map<String, Object> keyValues, Date expireTime) {
        redisTemplate.executePipelined((RedisCallback) connection -> {
            RedisSerializer keySerializer = redisTemplate.getKeySerializer();
            RedisSerializer valueSerializer = redisTemplate.getValueSerializer();
            for (Map.Entry<String, Object> entry : keyValues.entrySet()) {
                connection.set(keySerializer.serialize(entry.getKey()), valueSerializer.serialize(entry.getValue()),
                        Expiration.milliseconds((expireTime.getTime() - System.currentTimeMillis())),
                        RedisStringCommands.SetOption.SET_IF_ABSENT);
            }
            return null;
        });
    }

    /**
     * 读取缓存
     * @param key 键
     * @return 值
     */
    @Override
    public Object get(String key) {
        return this.redisTemplate.opsForValue().get(key);
    }

    /**
     * 读取缓存
     * @param key 键
     * @param clazz 值类型
     * @param <T> 值类型
     * @return 值
     */
    @Override
    public <T> T get(String key, Class<T> clazz) {
        return (T) this.redisTemplate.opsForValue().get(key);
    }


    /**
     * 读取缓存（不存在时调用接口获取，并存入缓存）
     * @param key 键
     * @param callable 缓存不存在时获取缓存的接口
     * @param <T> 值的类型
     * @return 值
     */
    @Override
    public <T> T get(String key, Callable<T> callable) {
        return null;
    }

    /**
     * 批量读取缓存
     * @param keys 键集合
     * @return 值集合
     */
    @Override
    public List<Object> batchGet(Collection<Object> keys) {
        return this.redisTemplate.opsForValue().multiGet(keys);
    }

    /**
     * 批量量读取缓存
     * @param keys 键集合
     * @param cls 值的类型
     * @param <T> 值的类型
     * @return 值
     */
    @Override
    public <T> List<T> batchGet(Collection<Object> keys, Class<T> cls) {
        return (List<T>) this.redisTemplate.opsForValue().multiGet(keys);
    }

    /**
     * 删除缓存
     * @param key 键
     */
    @Override
    public void delete(String key) {
        this.redisTemplate.delete(key);
    }

    /**
     * 批量删除
     * @param keys 键集合
     */
    @Override
    public void batchDelete(Collection<Object> keys) {
        this.redisTemplate.delete(keys);
    }

    /**
     * 设置缓存有效时间
     * @param key 键
     * @param timeout 有效时间（单位：秒）
     */
    @Override
    public void expire(String key, long timeout) {
        this.redisTemplate.expire(key, timeout, TimeUnit.SECONDS);
    }

    /**
     * 设置缓存过期时间
     * @param key 键
     * @param expireTime 过期时间
     */
    @Override
    public void expireAt(String key, Date expireTime) {
        this.redisTemplate.expireAt(key, expireTime);
    }

    /**
     * 批量设置缓存有效时间
     * @param keys 键集合
     * @param timeout 有效时间（单位：秒）
     */
    @Override
    public void batchExpire(Collection<String> keys, long timeout) {
        redisTemplate.executePipelined((RedisCallback) connection -> {
            RedisSerializer keySerializer = redisTemplate.getKeySerializer();
            for (String key : keys) {
                connection.expire(keySerializer.serialize(key), timeout);
            }
            return null;
        });
    }

    /**
     * 批量设置过期时间
     * @param keys 键集合
     * @param expireTime 过期时间
     */
    @Override
    public void batchExpireAt(Collection<String> keys, Date expireTime) {
        redisTemplate.executePipelined((RedisCallback) connection -> {
            RedisSerializer keySerializer = redisTemplate.getKeySerializer();
            for (String key : keys) {
                connection.expireAt(keySerializer.serialize(key), expireTime.getTime() / 1000);
            }
            return null;
        });
    }

    /**
     * 缓存是否存在
     * @param key 键
     * @return 存在true  不存在false
     */
    @Override
    public boolean exist(String key) {
        return this.redisTemplate.hasKey(key);
    }

    @Override
    public void clear() {

    }

    /**
     * 写入hash缓存
     * @param key 键
     * @param hashKey hash键
     * @param value 值
     */
    @Override
    public void hashPut(String key, String hashKey, Object value) {
        this.redisTemplate.opsForHash().put(key, hashKey, value);
    }

    /**
     * 读取hash缓存
     * @param key 键
     * @param hashKey hash键
     * @return 值
     */
    @Override
    public Object hashGet(String key, String hashKey) {
        return this.redisTemplate.opsForHash().get(key, hashKey);
    }

    /**
     * 读取hash缓存
     * @param key 键
     * @param hashKey 键
     * @param clazz 值类型
     * @param <T> 值类型
     * @return 值
     */
    @Override
    public <T> T hashGet(String key, String hashKey, Class<T> clazz) {
        return (T) this.redisTemplate.opsForHash().get(key, hashKey);
    }

    /**
     * 判断hash值是否存在
     * @param key 键
     * @param hashKey hash键
     * @return 存在true  不存在false
     */
    @Override
    public boolean hashExist(String key, String hashKey) {
        return this.redisTemplate.opsForHash().hasKey(key, hashKey);
    }

    /**
     * 删除hash值
     * @param key 键
     * @param hashKey hash键
     */
    @Override
    public void hashDelete(String key, String hashKey) {
        this.redisTemplate.opsForHash().delete(key, hashKey);
    }

    /**
     * 批量删除hash值
     * @param key 键
     * @param hashKeys hash键集合
     */
    @Override
    public void hashMultiDelete(String key, List<String> hashKeys) {
        this.redisTemplate.opsForHash().delete(key, hashKeys);
    }

    /**
     * 获取缓存的过期时间
     * @param key 键
     * @return 过期时间
     */
    @Override
    public long getTTL(String key) {
        return this.redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }

    /**
     * 获取指定key的hashkey
     * @param key 键
     * @return hash键集合
     */
    @Override
    public Set<Object> hashKeys(String key) {
        return this.redisTemplate.opsForHash().keys(key);
    }

    /**
     * 设置set缓存
     * @param key 键
     * @param values 值集合
     */
    @Override
    public void setAdd(Object key, Object... values) {
        this.redisTemplate.opsForSet().add(key, values);
    }

    /**
     * 移除set缓存
     * @param key 键
     * @param values 值
     * @return 移除的数量
     */
    @Override
    public Long setRemove(Object key, Object... values) {
        return this.redisTemplate.opsForSet().remove(key, values);
    }

    /**
     * 获取set缓存中的所有成员
     * @param key 键
     * @return set缓存的所有成员
     */
    @Override
    public Set<Object> getSet(Object key) {
        return this.redisTemplate.opsForSet().members(key);
    }

    /**
     * 匹配删除
     * @param prefixKey
     */
    @Override
    public void matchDelete(Object prefixKey) {
        Set<Object> keys = this.redisTemplate.keys(prefixKey + "*");
        if(keys != null && keys.size() > 0) {
            this.batchDelete(keys);
        }
    }

    /**
     * 匹配获取所有key
     * @param prefixKey
     * @return
     */
    @Override
    public Set<Object> keys(Object prefixKey) {
        Set<Object> keys = this.redisTemplate.keys(prefixKey + "*");
        return keys;
    }
}
