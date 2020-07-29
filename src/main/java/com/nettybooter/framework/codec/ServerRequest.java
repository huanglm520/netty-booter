package com.nettybooter.framework.codec;

import io.netty.handler.codec.http.HttpMethod;

import java.util.List;

/**
 * date: 2020-07-29
 * time: 23:22
 * author: huanglm520
 * see: https://github.com/huanglm520
 * 该接口提供了客户端请求数据的封装协议
 */
public interface ServerRequest {

    /**
     * 获取请求URI
     * @return
     */
    String getUri();

    /**
     * 获取请求方法
     * @return
     */
    HttpMethod getMethod();

    HttpPr

    /**
     * 获取所有请求头
     * @return
     */
    MultiValueMap getHeaders();

    /**
     * 根据key获取请求头数据
     * @param key
     * @return
     */
    List<String> getHeader(String key);

    /**
     * 根据key获取第一个请求头
     * @param key
     * @return
     */
    String getHeaderFirst(String key);

    /**
     * 获取所有请求参数
     * @return
     */
    MultiValueMap getQueryParams();

    /**
     * 根据key获取请求参数
     * @param key
     * @return
     */
    List<String> getQueryParam(String key);

    /**
     * 根据key获取第一个请求参数
     * @param key
     * @return
     */
    String getQueryParamFirst(String key);

    /**
     * 获取所有表单数据
     * @return
     */
    MultiValueMap getFormDatas();

    /**
     * 根据key获取表单数据
     * @param key
     * @return
     */
    List<String> getFormData(String key);

    /**
     * 根据key获取第一个表单数据
     * @param key
     * @return
     */
    String getFormDataFirst(String key);

    /**
     * 获取请求体数据
     * @return
     */
    byte[] getBody();
}
