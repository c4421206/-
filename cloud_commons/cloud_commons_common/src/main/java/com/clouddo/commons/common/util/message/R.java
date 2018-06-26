package com.clouddo.commons.common.util.message;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class R extends HashMap<String, Object> {

	private static final long serialVersionUID = -2731293044148864541L;

	private static R newInstance() {
		return new R();
	}

	private String code;

	private String message;

	private Object data;

	private Boolean success = true;

	public R() {
		put("code", 0);
		put("msg", "操作成功");
	}

	public static R error() {
		return error(1, "操作失败");
	}

	public static R error(String msg) {
		return error(500, msg);
	}

	public static R error(int code, String msg) {
		R r = new R();
		r.put("code", code);
		r.put("msg", msg);
		r.success = false;
		return r;
	}

	public static R ok(String msg) {
		R r = new R();
		r.put("msg", msg);
		return r;
	}

	public static R ok(Map<String, Object> map) {
		R r = new R();
		r.putAll(map);
		return r;
	}

	public static R ok() {
		return new R();
	}

	@Override
	public R put(String key, Object value) {
		super.put(key, value);
		return this;
	}

	public static R success(Object data) {
		R result = newInstance();
		result.put("data", data);
//		result.put("code", ResultCodeEnum.SUCCESS.getCode());
//		result.put("message", ResultCodeEnum.SUCCESS.getMsg());
//		result.setData(data);
//		result.setCode(ResultCodeEnum.SUCCESS.getCode());
//		result.setMessage(ResultCodeEnum.SUCCESS.getMsg());

		if (data == null) {
			result.setData("");
		} else {
			Field[] fields = data.getClass().getDeclaredFields();// 遇到没有属性的空类,防止JSON转换的时候异常
			if (fields.length == 0) {
//				result.setData("");
				result.put("data", "");
			}
		}
		return result;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}
}
