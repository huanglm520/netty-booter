package com.nettybooter.framework.util;

/**
 * date: 2020/8/2
 * time: 6:26 下午
 * author: huanglm520
 * see: https://github.com/huanglm520
 */
public abstract class BeanUtils {

    public static String beanName(String className) {
        char[] chars = className.toCharArray();
        chars[0] = Character.toLowerCase(chars[0]);
        return new String(chars);
    }
}
