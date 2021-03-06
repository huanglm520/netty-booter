package com.nettybooter.framework.test;

import com.nettybooter.framework.ReactiveHttpNettyServer;
import com.nettybooter.framework.NettyServerStarter;

/**
 * date: 2020-07-29
 * time: 21:51
 * author: huanglm520
 * see: https://github.com/huanglm520
 */
public class NettyServerTestReactive extends ReactiveHttpNettyServer {

    @Override
    public String appName() {
        return "NettyTest";
    }

    @Override
    public int port() {
        return 18000;
    }

    public static void main(String[] args) {
        NettyServerStarter.start(NettyServerTestReactive.class);
    }
}
