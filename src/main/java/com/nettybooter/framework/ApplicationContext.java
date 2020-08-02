package com.nettybooter.framework;

import com.nettybooter.framework.model.BeanInfo;
import com.nettybooter.framework.util.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * date: 2020-07-29
 * time: 22:23
 * author: huanglm520
 * see: https://github.com/huanglm520
 */
public class ApplicationContext {

    private static final ApplicationContext applicationContext = new ApplicationContext();

    private final List<BeanInfo> beans = new ArrayList<>();
    private ApplicationContext() {
        BeanInfo thisInfo = BeanInfo.builder()
                .bean(this)
                .beanClass(this.getClass())
                .classLoader(this.getClass().getClassLoader())
                .beanName(BeanUtils.beanName(this.getClass().getName()))
                .build();
        beans.add(thisInfo);
    }

    public static ApplicationContext getInstance() {
        return applicationContext;
    }

    public void doRegisterBean(BeanInfo beanInfo) {
        beans.add(beanInfo);
    }

    @SuppressWarnings("unchecked")
    public <T> List<T> getBeansOfType(Class<T> clazz) {
        return beans.stream().filter(beanInfo -> {
            Class<?> beanClass = beanInfo.getBeanClass();
            return clazz.equals(beanClass);
        }).map(beanInfo -> {
            Object bean = beanInfo.getBean();
            return (T) bean;
        }).collect(Collectors.toList());
    }
}
