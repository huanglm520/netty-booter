package com.nettybooter.framework.exception;

/**
 * date: 2020/8/2
 * time: 6:44 下午
 * author: huanglm520
 * see: https://github.com/huanglm520
 */
public class InvalidURLException extends RuntimeException {

    public InvalidURLException(String url) {
        super("Invalid URL: " + url);
    }
}
