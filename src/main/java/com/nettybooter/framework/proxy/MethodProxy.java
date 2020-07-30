package com.nettybooter.framework.proxy;

import com.nettybooter.framework.model.MethodProxyInfo;
import com.nettybooter.framework.util.ReflectUtils;

import java.lang.reflect.Method;
import java.util.function.Function;

/**
 * date: 2020-07-29
 * time: 22:43
 * author: huanglm520
 * see: https://github.com/huanglm520
 */
public abstract class MethodProxy {

    protected MethodProxyInfo methodProxyInfo;

    protected MethodProxy(MethodProxyInfo methodProxyInfo) {
        this.methodProxyInfo = methodProxyInfo;
    }

    public static MethodProxy choose(MethodProxyInfo methodProxyInfo) {
        return methodProxyInfo.isExtendInterfaces()
                ? new JdkDynamicProxy(methodProxyInfo)
                : new CglibProxy(methodProxyInfo);
    }

    protected Object defaultProcess(Method method, Object obj, Object[] args) {
        Object result;
        if ((result = ReflectUtils.equalsResult(method, methodProxyInfo.getObject(), args[0])) != null) {
            return result;
        }
        if ((result = ReflectUtils.hashCodeResult(method, methodProxyInfo.getObject())) != null) {
            return result;
        }
        result = ReflectUtils.toStringResult(method, methodProxyInfo.getObject());
        return result;
    }

    public abstract Object createProxyObject(ProxyFunction mapper);
}
