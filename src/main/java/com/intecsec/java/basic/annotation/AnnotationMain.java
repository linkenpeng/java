package com.intecsec.java.basic.annotation;

import com.intecsec.java.basic.annotation.thread.GuardeBy;

import java.io.FileNotFoundException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class AnnotationMain {

    @GuardeBy("Hello")
    public static void main(String[] args) throws Exception {
        Class cls = AnnotationMain.class;
        Method method = cls.getMethod("main", String[].class);
        GuardeBy guardeBy = method.getAnnotation(GuardeBy.class);
        System.out.println(guardeBy.value());

        System.out.println(method.getDeclaredAnnotations());

        System.out.println(guardeBy);
        System.out.println(method.getDeclaredAnnotation(GuardeBy.class));

        System.out.println(method.isAnnotationPresent(GuardeBy.class));
        System.out.println(cls.isAnnotationPresent(GuardeBy.class));
    }

    @Override
    @AnnotationTest(author = "Pankaj", comments = "Main method", date = "Nov 17 2012", revision = 1)
    public String toString() {
        return "Overriden toString method";
    }

    @Deprecated
    @AnnotationTest(comments = "deprecatd method", date="Nov 17 2012")
    public static void oldMethod() {
        System.out.println("old method, don't use it.");
    }

    @SuppressWarnings({"unchecked", "deprecation"})
    @AnnotationTest(author = "peng", comments = "Main method", date = "Feb 22 2018",revision=1)
    public static void genericsTest() throws FileNotFoundException {
        List l = new ArrayList();
        l.add("abd");
        oldMethod();
    }
}
