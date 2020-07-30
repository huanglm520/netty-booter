package com.nettybooter.framework.proxy;

import com.nettybooter.framework.model.MethodProxyInfo;

import java.lang.reflect.Proxy;

/**
 * date: 2020/7/30
 * time: 9:19 下午
 * author: huanglm520
 * see: https://github.com/huanglm520
 */
public class JdkDynamicProxy extends MethodProxy {

    JdkDynamicProxy(MethodProxyInfo methodProxyInfo) {
        super(methodProxyInfo);
    }

    @Override
    public Object createProxyObject(ProxyFunction mapper) {
        return Proxy.newProxyInstance(
                methodProxyInfo.getClassLoader(),
                methodProxyInfo.getInterfaces(),
                (proxy, method, args) -> {
                    Object result;
                    if ((result = super.defaultProcess(method, methodProxyInfo.getObject(), args)) != null) {
                        return result;
                    }
                    return mapper.apply(method, proxy, args);
                });
    }
}
