package com.nettybooter.framework.codec.http;

import com.nettybooter.framework.codec.MultiValueMap;
import com.nettybooter.framework.codec.ServerRequest;
import com.nettybooter.framework.exception.http.BadRequestException;
import io.netty.buffer.ByteBuf;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpHeaderValues;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpVersion;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 * date: 2020-07-29
 * time: 23:44
 * author: huanglm520
 * see: https://github.com/huanglm520
 */
public class HttpServerRequest implements ServerRequest {

    private static final Pattern FORM_CHECK = Pattern.compile("(.*=.*&?)+");

    private final String uri;
    private final HttpMethod method;
    private final MultiValueMap headers;
    private final MultiValueMap params;
    private final MultiValueMap forms;
    private final byte[] body;
    @Getter private final HttpVersion httpVersion;

    public HttpServerRequest(FullHttpRequest request) {
        uri = parseURI(request);
        method = request.method();
        httpVersion = request.protocolVersion();
        headers = parseHeaders(request);
        params = parseParams(request);
        forms = parseForm(request);
        body = parseBody(request);
    }

    private static String parseURI(FullHttpRequest request) {
        return request.uri().split("\\?")[0];
    }

    private static MultiValueMap parseHeaders(FullHttpRequest request) {
        MultiValueMap headers = new MultiValueMap();
        HttpHeaders httpHeaders = request.headers();
        httpHeaders.names().stream()
                .map(k -> Pair.of(k, httpHeaders.getAllAsString(k)))
                .forEach(pair -> headers.put(pair.getKey(), pair.getValue()));
        return headers;
    }

    private static MultiValueMap parseParams(FullHttpRequest request) {
        String[] uris = request.uri().split("\\?");
        if (uris.length < 2) {
            return new MultiValueMap();
        }
        return parseRequestData(uris[1]);
    }

    private static MultiValueMap parseForm(FullHttpRequest request) {
        MultiValueMap forms = new MultiValueMap();
        if (!request.headers().get(HttpHeaderNames.CONTENT_TYPE)
                .equalsIgnoreCase(HttpHeaderValues.APPLICATION_X_WWW_FORM_URLENCODED.toString())
        ) {
            return forms;
        }
        byte[] content = parseBody(request);
        String formData = new String(content, StandardCharsets.UTF_8);
        if (!FORM_CHECK.matcher(formData).matches()) {
            throw new BadRequestException("Can not resolve body data.");
        }
        return parseRequestData(formData);
    }

    private static MultiValueMap parseRequestData(String data) {
        if (StringUtils.isBlank(data)) {
            return new MultiValueMap();
        }
        MultiValueMap params = new MultiValueMap();
        Stream.of(data.split("&"))
                .map(s -> s.split("="))
                .map(arr -> Pair.of(arr.length <= 0 ? null : arr[0], arr.length >= 2 ? arr[1] : null))
                .filter(p -> p.getKey() != null)
                .forEach(p -> params.add(p.getKey(), p.getValue()));
        return params;
    }

    private static byte[] parseBody(FullHttpRequest request) {
        ByteBuf byteBuf = request.content();
        byte[] body = new byte[byteBuf.readableBytes()];
        byteBuf.readBytes(body);
        return body;
    }

    @Override
    public String getUri() {
        return uri;
    }

    @Override
    public HttpMethod getMethod() {
        return method;
    }

    @Override
    public MultiValueMap getHeaders() {
        return headers;
    }

    @Override
    public List<String> getHeader(String key) {
        return headers.get(key);
    }

    @Override
    public String getHeaderFirst(String key) {
        return headers.getFirst(key);
    }

    @Override
    public MultiValueMap getQueryParams() {
        return params;
    }

    @Override
    public List<String> getQueryParam(String key) {
        return params.get(key);
    }

    @Override
    public String getQueryParamFirst(String key) {
        return params.getFirst(key);
    }

    @Override
    public MultiValueMap getFormDatas() {
        return forms;
    }

    @Override
    public List<String> getFormData(String key) {
        return forms.get(key);
    }

    @Override
    public String getFormDataFirst(String key) {
        return forms.getFirst(key);
    }

    @Override
    public byte[] getBody() {
        return body;
    }
}
