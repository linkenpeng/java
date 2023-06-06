package com.intecsec.java.basic.annotations.hello;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SingleValue {
	String value() default "";
}

