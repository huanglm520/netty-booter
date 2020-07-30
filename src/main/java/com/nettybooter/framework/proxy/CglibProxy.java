package com.nettybooter.framework.proxy;

import com.nettybooter.framework.model.MethodProxyInfo;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;

import java.lang.reflect.Method;

/**
 * date: 2020/7/30
 * time: 9:20 下午
 * author: huanglm520
 * see: https://github.com/huanglm520
 */
public class CglibProxy extends MethodProxy {

    CglibProxy(MethodProxyInfo methodProxyInfo) {
        super(methodProxyInfo);
    }

    @Override
    public Object createProxyObject(ProxyFunction mapper) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(methodProxyInfo.getObjectClass());
        enhancer.setCallback((MethodInterceptor) (o, method, objects, methodProxy) -> {
            Object result;
            if ((result = super.defaultProcess(method, o, objects)) != null) {
                return result;
            }
            return mapper.apply(method, o, objects);
        });
        return enhancer.create();
    }
}
