package com.nettybooter.framework.handler.mapping;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * date: 2020-07-29
 * time: 22:54
 * author: huanglm520
 * see: https://github.com/huanglm520
 * 此注解用来将一个类定义为路由
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Router {
}
