package com.clouddo.log.common.annotation;

import org.springframework.context.annotation.ComponentScan;

import java.lang.annotation.*;

/**
 * 添加日志扫描注解
 * @author zhongming
 * @since 3.0
 * 2018/5/30下午8:27
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@ComponentScan(basePackages = {
        "com.clouddo.log.common.config"
})
public @interface EnableLog {
}
