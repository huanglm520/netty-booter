package com.nettybooter.framework.exception;

/**
 * date: 2020-07-30
 * time: 00:08
 * author: huanglm520
 * see: https://github.com/huanglm520
 */
public class InvalidCodecException extends RuntimeException {

    public InvalidCodecException(String msg, Throwable t) {
        super(msg, t);
    }
}
