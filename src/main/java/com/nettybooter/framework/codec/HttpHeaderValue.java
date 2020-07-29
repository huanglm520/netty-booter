package com.nettybooter.framework.codec;

import io.netty.handler.codec.http.HttpHeaderValues;
import io.netty.util.AsciiString;

/**
 * date: 2020-07-29
 * time: 23:10
 * author: huanglm520
 * see: https://github.com/huanglm520
 */
public enum HttpHeaderValue {

    APPLICATION_JSON(HttpHeaderValues.APPLICATION_JSON),
    APPLICATION_X_WWW_FORM_URLENCODED(HttpHeaderValues.APPLICATION_X_WWW_FORM_URLENCODED),
    APPLICATION_OCTET_STREAM(HttpHeaderValues.APPLICATION_OCTET_STREAM),
    MULTIPART_FORM_DATA(HttpHeaderValues.MULTIPART_FORM_DATA),
    MULTIPART_MIXED(HttpHeaderValues.MULTIPART_MIXED),
    TEXT_PLAIN(HttpHeaderValues.TEXT_PLAIN);

    private AsciiString string;

    HttpHeaderValue(AsciiString s) {
        string = s;
    }
}
