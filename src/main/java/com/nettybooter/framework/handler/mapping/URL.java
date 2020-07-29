package com.nettybooter.framework.handler.mapping;

import com.nettybooter.framework.codec.HttpHeaderValue;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * date: 2020-07-29
 * time: 22:51
 * author: huanglm520
 * see: https://github.com/huanglm520
 * 此注解用来将一个类或方法进行URL映射
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface URL {

    String value() default "";

    HttpHeaderValue contentType() default HttpHeaderValue.APPLICATION_JSON;
}
