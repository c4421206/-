package com.clouddo.commons.common.util;

import com.google.gson.Gson;


/**
 * google gsonUtil工具类
 * @author zhongming
 * @since 3.0
 * 2018/6/15下午2:58
 */
public class GsonUtil {

    private static Gson gson = new Gson();


    /**
     * 将对象序列化为json字符串
     * @param object 对象
     * @return 序列化后的json字符串
     */
    public static String toJson(Object object) {
        return gson.toJson(object);
    }

    /**
     * json反序列化为对象
     * @param json json
     * @param clazz 对象类型
     * @param <T>
     * @return 反序列化后的对象
     */
    public static <T> T jsonToBean(String json, Class<T> clazz) {
        return gson.fromJson(json, clazz);
    }
}
