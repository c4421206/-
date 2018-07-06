package com.clouddo.commons.common.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 集合工具类
 * @author zhongming
 * @since 1.0
 * 2018/7/5下午1:40
 */
public class CollectionUtil {

    /**
     * 将list转为map
     * @param data 需要转换的数据
     * @param key 数据中的key
     * @return 转换后的数据
     */
    public static Map<String, Object> listToMap(List<Map<String, Object>> data, String key) {
        Map<String, Object> result = new HashMap<String, Object>();
        for(Map<String, Object> map : data) {
            result.put((String) map.get(key), map);
        }
        return result;
    }
}
