package com.intecsec.java.basic.annotations.source;
import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.SOURCE)
public @interface ToString {
    boolean includeName() default true;    
}