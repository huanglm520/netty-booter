package com.nettybooter.framework.exception.http;

/**
 * date: 2020-07-30
 * time: 00:20
 * author: huanglm520
 * see: https://github.com/huanglm520
 */
public final class BadRequestException extends HttpResponseException {

    public BadRequestException() {
        this("BAD REQUEST");
    }

    public BadRequestException(String msg) {
        super(400, msg);
    }
}
