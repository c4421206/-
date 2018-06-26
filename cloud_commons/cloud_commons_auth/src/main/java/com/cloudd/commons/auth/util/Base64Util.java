package com.cloudd.commons.auth.util;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

/**
 * @author zhongming
 * @since 3.0
 * 2018/5/31下午3:13
 */
public class Base64Util {

    //解码工具
    private static final Base64.Decoder DECODER = Base64.getDecoder();

    //转码个工具
    private static final Base64.Encoder ENCODER = Base64.getEncoder();

    /**
     * base64编码
     * @param str 要编码的字符串
     * @return 编码后的字符串
     */
    public static String encoder(String str) throws UnsupportedEncodingException {
        byte[] textByte = str.getBytes("UTF-8");
        return encoder(textByte);
    }

    /**
     * base64编码
     * @param bytes 要编码的字节数组
     * @return 编码后的字符串
     */
    public static String encoder(byte[] bytes) {
        return ENCODER.encodeToString(bytes);
    }

    /**
     * base64解码
     * @param str 要解码的字符串
     * @return 解码后的字符串
     * @throws UnsupportedEncodingException
     */
    public static String decoder(String str) throws UnsupportedEncodingException {
        return new String(DECODER.decode(str), "UTF-8");
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        System.out.println(encoder("你好啊！！！！"));

        System.out.println(decoder("5L2g5aW95ZWK77yB77yB77yB77yB"));
    }
}
