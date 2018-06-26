package com.clouddo.log.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 日志注解，添加该注解的类或方法实现日志
 * 目前只支持controller层
 * @author zhongming
 * @since 3.0
 * 2018/5/29下午5:04
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Log {
    String value() default "";
}
