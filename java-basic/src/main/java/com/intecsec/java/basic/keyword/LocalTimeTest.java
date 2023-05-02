package com.intecsec.java.basic.keyword;

import java.time.LocalTime;
import java.time.ZoneId;

/**
 * @description:
 * @author: peter.peng
 * @create: 2023-05-02 18:13
 **/
public class LocalTimeTest {

    public static void main(String[] args) {
        localTime();
    }

    public static void localTime() {
        LocalTime test = LocalTime.now();
        System.out.println(test);

        LocalTime testOf = LocalTime.of(12, 20, 28, 20);
        System.out.println(testOf);

        LocalTime todayBeiJing = LocalTime.now(ZoneId.of("Asia/Shanghai"));
        System.out.println(todayBeiJing);

        LocalTime ofSecondOfDay = LocalTime.ofSecondOfDay(3600);
        System.out.println(ofSecondOfDay);
    }
    
}
