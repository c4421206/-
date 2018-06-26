package com.clouddo.commons.common.util;

import java.util.UUID;

/**
 * UUID工具类
 * @author ming
 */
public class UUIDGenerator {
    public static String getUUID()
    {
        String s = UUID.randomUUID().toString();

        s = s.replace("-", "");
        return s;
    }

    public static String[] getUUID(int number)
    {
        if (number < 1) {
            return null;
        }
        String[] ss = new String[number];
        for (int i = 0; i < number; i++) {
            ss[i] = getUUID();
        }
        return ss;
    }
}
