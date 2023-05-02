package com.intecsec.java.basic.keyword;

import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;

/**
 * @description:
 * @author: peter.peng
 * @create: 2023-05-02 18:13
 **/
public class LocalDateTest {

    public static void main(String[] args) {
        localDate();
    }

    public static void localDate() {
        LocalDate test = LocalDate.now();
        System.out.println(test);

        LocalDate testOf = LocalDate.of(2022, Month.FEBRUARY, 28);
        System.out.println(testOf);

        LocalDate todayBeiJing = LocalDate.now(ZoneId.of("Asia/Shanghai"));
        System.out.println(todayBeiJing);

        LocalDate base = LocalDate.ofEpochDay(365);
        System.out.println(base);

        LocalDate ofYearDay = LocalDate.ofYearDay(2023,100);
        System.out.println(ofYearDay);
    }

}
