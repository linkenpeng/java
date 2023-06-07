package com.intecsec.java.basic.annotations.runtime;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
public @interface Fruit {
	String name() default "";
}

