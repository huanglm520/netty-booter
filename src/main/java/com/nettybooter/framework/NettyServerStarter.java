package com.nettybooter.framework;

import io.netty.bootstrap.ServerBootstrap;
import io.vavr.control.Try;
import lombok.extern.slf4j.Slf4j;

import java.lang.management.ManagementFactory;

/**
 * date: 2020-07-29
 * time: 20:55
 * author: huanglm520
 * see: https://github.com/huanglm520
 */
@Slf4j
public class NettyServerStarter {

    public static int start(Class<? extends NettyServer> clazz) {
        return start(clazz, null);
    }

    public static int start(Class<? extends NettyServer> clazz, String[] args) {
        return Try.of(clazz::newInstance).flatMapTry(server -> {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            server.initServer(serverBootstrap);
            return Try.of(() -> ManagementFactory.getRuntimeMXBean().getName())
                    .mapTry(name -> name.split("@")[0])
                    .mapTry(Integer::valueOf);
        }).getOrElseGet(t -> {
            log.error("ApplicationContext run failed, there has an exception during starting: {}", t.getMessage(), t);
            System.exit(4);
            return -1;
        });
    }
}
