package com.nettybooter.framework.proxy;

import reactor.core.publisher.Mono;

import java.lang.reflect.Method;

/**
 * date: 2020-07-29
 * time: 22:43
 * author: huanglm520
 * see: https://github.com/huanglm520
 */
public interface MethorProxy {

    Mono<Object> intercept();
}
