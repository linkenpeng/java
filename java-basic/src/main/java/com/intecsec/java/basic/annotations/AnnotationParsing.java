package com.intecsec.java.basic.annotations;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 Annotation in Method 'public java.lang.String com.journaldev.annotations.AnnotationExample.toString()' : @com.journaldev.annotations.MethodInfo(author=Pankaj, revision=1, comments=Main method, date=Nov 17 2012)
 Method with revision no 1 = public java.lang.String com.journaldev.annotations.AnnotationExample.toString()
 Annotation in Method 'public static void com.journaldev.annotations.AnnotationExample.oldMethod()' : @java.lang.Deprecated()
 Annotation in Method 'public static void com.journaldev.annotations.AnnotationExample.oldMethod()' : @com.journaldev.annotations.MethodInfo(author=Pankaj, revision=1, comments=deprecated method, date=Nov 17 2012)
 Method with revision no 1 = public static void com.journaldev.annotations.AnnotationExample.oldMethod()
 Annotation in Method 'public static void com.journaldev.annotations.AnnotationExample.genericsTest() throws java.io.FileNotFoundException' : @com.journaldev.annotations.MethodInfo(author=Pankaj, revision=10, comments=Main method, date=Nov 17 2012)
 */
public class AnnotationParsing {
    public static void main(String[] args) {

        try {
            for (Method method : AnnotationParsing.class
                    .getClassLoader()
                    .loadClass("com.mycshop.basic.annotation.AnnotationMain")
                    .getMethods()) {

                if(method.isAnnotationPresent(AnnotationTest.class)) {
                    try {
                        for (Annotation annotation : method.getDeclaredAnnotations()) {
                            System.out.println("Annotation in Method: " + method + ", anno: " + annotation);
                        }

                        AnnotationTest annotationTest = method.getAnnotation(AnnotationTest.class);
                        if(annotationTest.revision() == 1) {
                            System.out.println("Method width revision no 1 = " + method);
                        }

                    } catch (Throwable ex) {
                        ex.printStackTrace();
                    }
                }

            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
