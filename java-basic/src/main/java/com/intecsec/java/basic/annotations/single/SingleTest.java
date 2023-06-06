package com.intecsec.java.basic.annotations.single;
import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
//表示该注解会保留在class文件中
@Target(ElementType.METHOD)   
//表示该注解只能用于方法

public @interface SingleTest {
	int value() default 0;
	//String para();
}

