package com.nettybooter.framework.handler.exception;

import com.nettybooter.framework.handler.BeanHandler;
import com.nettybooter.framework.model.BeanInfo;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * date: 2020/8/2
 * time: 6:08 下午
 * author: huanglm520
 * see: https://github.com/huanglm520
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ExceptionInterceptor implements BeanHandler {

    private static final ExceptionInterceptor exceptionInterceptor = new ExceptionInterceptor();

    public static ExceptionInterceptor getInstance() {
        return exceptionInterceptor;
    }

    @Override
    public void doRegister(BeanInfo beanInfo, Map<Object, Object> extra) {

    }
}
