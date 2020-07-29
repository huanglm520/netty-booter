package com.nettybooter.framework.handler.exception;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * date: 2020-07-29
 * time: 22:19
 * author: huanglm520
 * see: https://github.com/huanglm520
 * 此注解用来标记一个类是异常处理器
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ExceptionHandler {
}
