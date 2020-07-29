package com.nettybooter.framework;

import com.google.common.collect.ImmutableMap;
import com.nettybooter.framework.exception.InvalidServerConfigException;
import io.vavr.control.Try;

import java.util.Map;

/**
 * date: 2020-07-29
 * time: 21:01
 * author: huanglm520
 * see: https://github.com/huanglm520
 */
public abstract class SystemConfig {

    private static final Map<String, String> systemProperties;
    static {
        systemProperties = ImmutableMap.of();
    }

    /**
     * 获取配置文件中的属性
     * @param key 属性的key
     * @param ifNullDefault 属性值为null时采用的默认值
     * @return 配置文件中该key对应的值
     */
    public static String getProperty(String key, String ifNullDefault) {
        String value;
        return (value = systemProperties.get(key)) == null ? ifNullDefault : value;
    }

    /**
     * 获取server的端口
     * @return server的端口
     */
    public static int port() {
        String key = "server.port";
        return Try.of(() -> getProperty(key, "8080"))
                .mapTry(Integer::valueOf)
                .onFailure(t -> InvalidServerConfigException.buildAndThrow(key, t))
                .get();
    }

    public static String appName() {
        return getProperty("app.name", "Application");
    }
}
