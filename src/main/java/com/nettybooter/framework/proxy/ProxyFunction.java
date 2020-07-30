package com.nettybooter.framework.proxy;

import java.lang.reflect.Method;

/**
 * date: 2020/7/30
 * time: 9:45 下午
 * author: huanglm520
 * see: https://github.com/huanglm520
 */
public interface ProxyFunction {

    Object apply(Method method, Object object, Object[] args);
}
