package com.nettybooter.framework.exception.http;

import lombok.Getter;

/**
 * date: 2020-07-29
 * time: 22:59
 * author: huanglm520
 * see: https://github.com/huanglm520
 */
@Getter
public class HttpResponseException extends RuntimeException {

    private int code;

    private String msg;

    public HttpResponseException(int code, String msg) {
        super();
        this.code = code;
        this.msg = msg;
    }
}
