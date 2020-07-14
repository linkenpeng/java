package com.intecsec.java.basic.annotation;

import java.lang.annotation.*;

@Documented
@Target(ElementType.METHOD)
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface AnnotationTest {
    String author() default "sindoc";
    String date();
    int revision() default 1;
    String comments();

}
