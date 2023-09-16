package com.intecsec.java.basic.datetime;

import java.time.*;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

/**
 * @description:
 * @author: peter.peng
 * @create: 2023-05-02 18:13
 **/
public class LocalDateTest {

    public static void main(String[] args) {
        dateUtil();
    }

    public static void dateUtil() {
        LocalDate today = LocalDate.now();

        System.out.println(today);
        System.out.println(today.getYear());
        System.out.println(today.isLeapYear());
        System.out.println(today.isBefore(LocalDate.of(2030, 1, 1)));
        System.out.println(today.atTime(LocalTime.now()));

        System.out.println("plus------------------------");
        System.out.println(today.plusDays(10));
        System.out.println(today.plusWeeks(1));
        System.out.println(today.plusMonths(2));
        System.out.println(today.plusYears(2));

        System.out.println("minus------------------------");
        System.out.println(today.minusDays(10));
        System.out.println(today.minusWeeks(1));
        System.out.println(today.minusMonths(1));
        System.out.println(today.minusYears(1));


        System.out.println(today.with(TemporalAdjusters.firstDayOfMonth()));
        LocalDate lastDayOfYear = today.with(TemporalAdjusters.lastDayOfYear());
        System.out.println(lastDayOfYear);

        Period period = today.until(lastDayOfYear);
        System.out.println(period);
        System.out.println(period.getMonths());
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
