package com.intecsec.java.basic.annotations.basic.document;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface DocumentedAnnotation {
	String value();
}
