package com.nettybooter.framework.exception;

/**
 * date: 2020-07-29
 * time: 21:08
 * author: huanglm520
 * see: https://github.com/huanglm520
 */
public class InvalidServerConfigException extends RuntimeException {

    private InvalidServerConfigException(String msg, Throwable t) {
        super(msg, t);
    }

    public static void buildAndThrow(String configKey, Throwable t) {
        String msg = String.format("Read config property failed, " +
                "because there has an exception with cause: invalid config: '%s', " +
                "can not resolve value with exception: '%s'",
                configKey, t.getClass().getName()
        );
        throw new InvalidServerConfigException(msg, t);
    }
}
