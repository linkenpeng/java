package com.intecsec.java.util;

import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: peter.peng
 * @create: 2019-07-19 14:22
 **/
public class ThreadLocalContext {


    private ThreadLocalContext() {
    }

    // ####
    // ## 一些通用的KEY
    // ####

    /** http请求者IP */
    public static final String REQ_CLIENT_IP = "REQ_CIP";

    /** http pathInfo */
    public static final String REQ_PATH_INFO = "REQ_PATH_INFO";

    /** http header language code */
    public static final String REQ_HEADER_PARAM_LANG = "REQ_HEADER_PARAM_LANG";// 多语言


    /**
     * 设置对象值，如果null==value则 表示删除value。
     *
     * <p>
     * 不建议使用这个方法。
     * </p>
     *
     * <p>
     * <strong> 注：线程结束后 value 不会被销毁，一直存在于内存中！如果下一个请求使用同样的线程 ID，使用的是同一个对象！
     * </strong>
     * </p>
     *
     */
    // @Deprecated
    public static void setGlobalValue(String key, Object value) {
        if (null != value)
            publicLocal.get().put(key, value);
        else
            publicLocal.get().remove(key);
    }

    /**
     * 取得一个对象
     */
    public static <T> T getGlobalValue(String key) {
        return getGlobalValue(key, null);
    }

    /**
     * 取得一个对象
     */
    public static <T> T getGlobalValue(String key, T def) {
        Object v = publicLocal.get().get(key);
        return (null == v ? def : (T) v);
    }

    // ####
    // ## private ThreadLocal -- 每个请求者，需要在end时自行调用 clearThreadValue() 方法，用于清理数据
    // ####

    public static void clearThreadValue() {
        // privateLocal.set(new HashMap<String, Object>()); >> initialValue()
        privateLocal.get().clear();
        privateLocal.remove();
    }

    /**
     * 设置对象值，如果null==value则 表示删除value <strong>该 value
     * 只在线程内有效，线程运行完毕即销毁。</strong>
     */
    public static void putThreadValue(String key, Object value) {
        if (null != value)
            privateLocal.get().put(key, value);
        else
            privateLocal.get().remove(key);
    }

    /**
     * 取得一个对象
     */
    public static <T> T getThreadValue(String key) {
        return getThreadValue(key, null);
    }

    public static <T> T getThreadValue(String key, T def) {
        Object v = privateLocal.get().get(key);
        return (null == v ? def : (T) v);
    }

    // ####
    // ## static init
    // ####

    /**
     * 在当前线程中，所有的 运行对象都共享的
     *
     * 将来改成 SoftReference
     */
    private static final ThreadLocal<Map<String, Object>> publicLocal = new ThreadLocal<Map<String, Object>>() {

        /*
         * （非 Javadoc）
         *
         * @see java.lang.ThreadLocal#initialValue()
         */
        @Override
        protected Map<String, Object> initialValue() {
            // Thread currentThread = Thread.currentThread();
            // Runtime.getRuntime().addShutdownHook(new
            // ThreadClean(currentThread));
            return new HashMap<String, Object>();
        }

    };

    /**
     * 当前线程中，只在当前运行对象中共享<br>
     * 当前请求执行完了，线程继续执行下一个请求，那么就不共享了
     */
    private static final ThreadLocal<Map<String, Object>> privateLocal = new ThreadLocal<Map<String, Object>>() {

        /*
         * （非 Javadoc）
         *
         * @see java.lang.ThreadLocal#initialValue()
         */
        @Override
        protected Map<String, Object> initialValue() {
            return new HashMap<String, Object>();
        }

    };

}
