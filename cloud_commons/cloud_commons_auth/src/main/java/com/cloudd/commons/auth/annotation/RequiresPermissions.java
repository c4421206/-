package com.cloudd.commons.auth.annotation;


import com.cloudd.commons.auth.util.Logical;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 方法需要的权限，标识在controlller层标记该方法需要相应的权限
 * @author zhongming
 * @since 3.0
 * 2018/5/24上午11:16
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RequiresPermissions {
    String[] value();

    Logical logical() default Logical.AND;
}
