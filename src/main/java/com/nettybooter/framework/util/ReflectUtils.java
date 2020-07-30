package com.nettybooter.framework.util;

import io.vavr.control.Try;

import java.lang.reflect.Method;

/**
 * date: 2020/7/30
 * time: 9:13 下午
 * author: huanglm520
 * see: https://github.com/huanglm520
 */
public abstract class ReflectUtils {

    private static final Class<Object> OBJECT_CLASS = Object.class;
    private static final Method TO_STRING_METHOD = Try.of(() -> OBJECT_CLASS)
            .mapTry(objectClass -> objectClass.getDeclaredMethod("toString"))
            .get();
    private static final Method EQUALS_METHOD = Try.of(() -> OBJECT_CLASS)
            .mapTry(objectClass -> objectClass.getDeclaredMethod("equals", Object.class))
            .get();
    private static final Method HASH_CODE_METHOD = Try.of(() -> OBJECT_CLASS)
            .mapTry(objectClass -> objectClass.getDeclaredMethod("hashCode"))
            .get();

    /**
     * 执行equals计算
     * @param method 待执行的方法
     * @param o 执行主体
     * @param a 被比较对象
     * @return 如果method是equals方法则返回equals方法结果，否则返回null
     */
    public static Boolean equalsResult(Method method, Object o, Object a) {
        return EQUALS_METHOD.equals(method) ? o.equals(a) : null;
    }

    /**
     * 执行toString计算
     * @param method 待执行方法
     * @param o 执行主体
     * @return 如果method是toString方法则返回toString方法结果，否则返回null
     */
    public static String toStringResult(Method method, Object o) {
        return TO_STRING_METHOD.equals(method) ? o.toString() : null;
    }

    /**
     * 执行hashCode计算
     * @param method 待执行方法
     * @param o 执行主体
     * @return 如果method是hashCode方法则返回hashCode方法结果，否则返回null
     */
    public static Integer hashCodeResult(Method method, Object o) {
        return HASH_CODE_METHOD.equals(method) ? o.hashCode() : null;
    }
}
