package com.nettybooter.framework.model;

import lombok.Getter;

/**
 * date: 2020/7/30
 * time: 9:06 下午
 * author: huanglm520
 * see: https://github.com/huanglm520
 */
@Getter
public class MethodProxyInfo {

    private Class<?> objectClass;
    private Class<?>[] interfaces;
    private Object object;
    private boolean extendInterfaces;
    private ClassLoader classLoader;

    public static MethodProxyInfo create(Object object) {
        MethodProxyInfo info = new MethodProxyInfo();
        info.object = object;
        info.objectClass = object.getClass();
        info.classLoader = info.objectClass.getClassLoader();
        info.interfaces = info.objectClass.getInterfaces();
        info.extendInterfaces = info.interfaces.length > 0;
        return info;
    }
}
