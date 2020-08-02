package com.nettybooter.framework.handler.mapping;

import com.nettybooter.framework.ApplicationContext;
import com.nettybooter.framework.exception.DuplicateURLException;
import com.nettybooter.framework.exception.InvalidURLException;
import com.nettybooter.framework.exception.http.NotFoundException;
import com.nettybooter.framework.handler.BeanHandler;
import com.nettybooter.framework.model.BeanInfo;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * date: 2020-07-29
 * time: 23:16
 * author: huanglm520
 * see: https://github.com/huanglm520
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class URLMappingHandler implements BeanHandler {

    private static final URLMappingHandler urlMappingHandler = new URLMappingHandler();

    public static URLMappingHandler getInstance() {
        return urlMappingHandler;
    }

    private final Map<String, MappingInfo> mappingContainer = new HashMap<>();
    private final ApplicationContext applicationContext = ApplicationContext.getInstance();

    @Override
    public void doRegister(BeanInfo beanInfo, Map<Object, Object> extra) {
        String url = (String) extra.get("url");
        if (url == null) {
            throw new InvalidURLException(null);
        }
        if (mappingContainer.containsKey(url)) {
            throw new DuplicateURLException(url);
        }
        Method method = (Method) extra.get("method");
        MappingInfo mappingInfo = new MappingInfo();
        mappingInfo.beanInfo = beanInfo;
        mappingInfo.method = method;
        mappingInfo.url = url;
        mappingContainer.put(url, mappingInfo);
        applicationContext.doRegisterBean(beanInfo);
    }

    public MappingInfo getMappingInfo(String url) {
        MappingInfo mappingInfo = mappingContainer.get(url);
        if (mappingInfo == null) {
            throw new NotFoundException();
        }
        return mappingInfo;
    }

    @Getter
    public static class MappingInfo {
        private String url;
        private BeanInfo beanInfo;
        private Method method;
    }
}
