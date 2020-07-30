package com.nettybooter.framework.handler;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpHeaderValues;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import reactor.core.publisher.Mono;

/**
 * date: 2020-07-29
 * time: 21:41
 * author: huanglm520
 * see: https://github.com/huanglm520
 */
public class ReactiveHttpHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, FullHttpRequest fullHttpRequest) {
        Mono.<FullHttpRequest>create(sink -> {
            if (fullHttpRequest == null) {
                sink.error(new NullPointerException("FullHttpRequest is null!"));
            }
            sink.success(fullHttpRequest);
        }).map(request -> {
            DefaultFullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,
                    HttpResponseStatus.OK,
                    Unpooled.wrappedBuffer("hello netty".getBytes()));

            HttpHeaders heads = response.headers();
            heads.add(HttpHeaderNames.CONTENT_TYPE, HttpHeaderValues.TEXT_PLAIN + "; charset=UTF-8");
            heads.add(HttpHeaderNames.CONTENT_LENGTH, response.content().readableBytes()); // 3
            heads.add(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE);
            return response;
        }).onErrorResume(t -> {
            return Mono.error(new RuntimeException("error"));
        }).subscribe(channelHandlerContext::writeAndFlush);
    }
}
