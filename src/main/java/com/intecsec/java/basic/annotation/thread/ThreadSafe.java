package com.intecsec.java.basic.annotation.thread;

import java.lang.annotation.*;

/**
 * 线程安全的
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ThreadSafe {

}