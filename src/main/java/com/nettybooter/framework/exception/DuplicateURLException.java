package com.nettybooter.framework.exception;

/**
 * date: 2020/8/2
 * time: 6:42 下午
 * author: huanglm520
 * see: https://github.com/huanglm520
 */
public class DuplicateURLException extends RuntimeException {

    public DuplicateURLException(String url) {
        super("Duplicate URL: " + url);
    }
}
