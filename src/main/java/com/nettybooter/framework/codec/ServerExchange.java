package com.nettybooter.framework.codec;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * date: 2020-07-29
 * time: 23:22
 * author: huanglm520
 * see: https://github.com/huanglm520
 */
@Getter
@AllArgsConstructor
public class ServerExchange {

    private ServerRequest serverRequest;

    private ServerResponse serverResponse;
}
