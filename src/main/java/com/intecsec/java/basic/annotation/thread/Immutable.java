package com.intecsec.java.basic.annotation.thread;

import java.lang.annotation.*;

/**
 * 表示类是不可变的， 包含了ThreadSafe的含义
 * Not-ThreadSafe是可选的
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Immutable {
}