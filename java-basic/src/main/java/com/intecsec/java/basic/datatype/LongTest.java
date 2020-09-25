package com.intecsec.java.basic.datatype;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class LongTest {
    public static void main(String[] args) {
        double a = 101.12234;
        long b = 10;
        System.out.println(BigDecimal.valueOf(a).divide(BigDecimal.valueOf(b)).setScale(0, BigDecimal.ROUND_CEILING).intValue());
    }

    public static void addTest() {
        Long l = 10L;
        Map<String, Long> t = new HashMap<>();
        t.put("total", l);
        add(t);
        System.out.println(t.get("total"));
    }

    public static void add(Map<String, Long> t) {
        Long add = t.get("total") + 20L;
        t.put("total", add);
    }

    public static void divisor() {
        long employDiscountDiff = 1 - 0;
        long avgDiff = employDiscountDiff / 3;
        System.out.println(avgDiff);

        long a = 10L;
        Long b = 60L;
        long c = 7L;
        long d = (long) (a * c / b) ;
        System.out.println(d);
        System.out.println((double) 10 / 30);
    }
}
