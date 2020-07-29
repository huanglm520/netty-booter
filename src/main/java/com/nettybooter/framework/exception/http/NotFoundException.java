package com.nettybooter.framework.exception.http;

/**
 * date: 2020-07-29
 * time: 23:02
 * author: huanglm520
 * see: https://github.com/huanglm520
 */
public final class NotFoundException extends HttpResponseException {

    public NotFoundException() {
        super(404, "NOT FOUND");
    }
}
