package com.nettybooter.framework;

import io.netty.bootstrap.ServerBootstrap;

/**
 * date: 2020-07-29
 * time: 20:59
 * author: huanglm520
 * see: https://github.com/huanglm520
 */
public interface NettyServer {

    default int port() {
        return SystemConfig.port();
    }

    default String appName() {
        return SystemConfig.appName();
    }

    void initServer(ServerBootstrap serverBootstrap);
}
