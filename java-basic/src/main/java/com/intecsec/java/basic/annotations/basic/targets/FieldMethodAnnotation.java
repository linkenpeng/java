package com.intecsec.java.basic.annotations.basic.targets;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;

import java.lang.annotation.Target;

@Target({ FIELD, METHOD })
public @interface FieldMethodAnnotation {

}
