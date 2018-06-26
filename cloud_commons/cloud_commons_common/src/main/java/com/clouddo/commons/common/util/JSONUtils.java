package com.clouddo.commons.common.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Map;

/**
 * JSON转换工具类
 * @author zhongming
 * @since 3.0
 * 2018/5/7下午6:09
 */
public class JSONUtils {

    private static ObjectMapper objectMapper = new ObjectMapper();


    /**
     * 将bean转为json字符串
     * @param bean bean
     * @return json
     */
    public static  String beanToJson(Object bean) throws JsonProcessingException {
        return objectMapper.writeValueAsString(bean);
    }

    /**
     * 将json字符串转为bean
     * @param json
     * @param clazz
     * @param <T>
     * @return
     * @throws IOException
     */
    public static <T> T  jsonToBean(String json, Class<T> clazz) throws IOException {
        if(json == null) {
            return null;
        }
        return objectMapper.readValue(json, clazz);
    }


    /**
     * map转json
     * @param parameter
     * @return
     * @throws JsonProcessingException
     */
    public static String mapToJson(Map<String, Object> parameter) throws JsonProcessingException {
        return objectMapper.writeValueAsString(parameter);
    }

    /**
     * 将json转为map
     * @param json
     * @return
     * @throws IOException
     */
    public static Map jsonToMap(String  json) throws IOException {
        return jsonToBean(json, Map.class);
    }
}
