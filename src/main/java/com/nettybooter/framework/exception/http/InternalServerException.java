package com.nettybooter.framework.exception.http;

/**
 * date: 2020-07-29
 * time: 23:03
 * author: huanglm520
 * see: https://github.com/huanglm520
 */
public final class InternalServerException extends HttpResponseException {

    public InternalServerException() {
        this("INTERVAL SERVER ERROR");
    }

    public InternalServerException(String msg) {
        super(500, msg);
    }
}
