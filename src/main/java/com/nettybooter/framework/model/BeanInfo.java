package com.nettybooter.framework.model;

import lombok.Builder;
import lombok.Getter;

/**
 * date: 2020/8/2
 * time: 6:15 下午
 * author: huanglm520
 * see: https://github.com/huanglm520
 */
@Getter
@Builder
public class BeanInfo {

    private final Object bean;
    private final Class<?> beanClass;
    private final ClassLoader classLoader;
    private final String beanName;
}
