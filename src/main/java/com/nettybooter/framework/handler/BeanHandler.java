package com.nettybooter.framework.handler;

import com.google.common.collect.ImmutableMap;
import com.nettybooter.framework.model.BeanInfo;

import java.util.HashMap;
import java.util.Map;

/**
 * date: 2020/8/2
 * time: 6:12 下午
 * author: huanglm520
 * see: https://github.com/huanglm520
 */
public interface BeanHandler {

    static final Map<Object, Object> EMPTY_EXTRA = ImmutableMap.of();

    void doRegister(BeanInfo beanInfo, Map<Object, Object> extra);
}
