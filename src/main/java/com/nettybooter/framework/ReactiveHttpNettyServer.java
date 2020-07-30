package com.nettybooter.framework;

import com.nettybooter.framework.handler.ReactiveHttpHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.vavr.control.Try;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;

/**
 * date: 2020-07-29
 * time: 21:23
 * author: huanglm520
 * see: https://github.com/huanglm520
 */
@Slf4j
public abstract class ReactiveHttpNettyServer implements NettyServer {

    @Override
    public void initServer(ServerBootstrap serverBootstrap) {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workGroup = new NioEventLoopGroup();
        Try.run(() -> Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            log.info("Shutting down server and close EventLoopGroup.");
            workGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
            log.info("EventLoopGroup is stop successfully.");
        }))).onFailure(t -> {
            log.error("There has an exception during add EventLoopGroup shutdown hook, cause is: {}", t.getMessage(), t);
            System.exit(2);
        });

        log.info("Starting application '{}' with port: {}", appName(), port());
        serverBootstrap.group(bossGroup, workGroup).channel(NioServerSocketChannel.class)
                .localAddress(new InetSocketAddress(port()))
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) {
                        socketChannel.pipeline()
                                .addLast("decoder", new HttpRequestDecoder())
                                .addLast("encoder", new HttpResponseEncoder())
                                .addLast("aggregator", new HttpObjectAggregator(512 * 1024))
                                .addLast(new ReactiveHttpHandler());
                    }
                });
        Try.of(() -> serverBootstrap.bind("0.0.0.0", port()))
                .mapTry(future -> future.channel().closeFuture())
                .orElseRun(t -> {
                    log.error("There has an exception during start server cause is: {}", t.getMessage(), t);
                    System.exit(3);
                });
        log.info("ApplicationContext run successfully with port: {}", port());
    }
}
