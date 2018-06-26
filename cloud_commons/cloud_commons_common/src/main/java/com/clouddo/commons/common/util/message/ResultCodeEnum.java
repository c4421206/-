package com.clouddo.commons.common.util.message;

/**
 * Created by DaoTong on 2017/6/6.
 */
public enum ResultCodeEnum {

    /**
     * 成功
     */
    SUCCESS(200, "成功"),
    /**
     * 失败
     */
    FAILURE(500, "失败");


    ResultCodeEnum(Integer code, String msg) {
        this.msg = msg;
        this.code = code;
    }

    private String msg;

    private Integer code;

    public String getMsg() {
        return msg;
    }

    public Integer getCode() {
        return code;
    }

}
