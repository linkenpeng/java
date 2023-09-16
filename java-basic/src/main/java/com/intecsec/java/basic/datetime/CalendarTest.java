package com.intecsec.java.basic.datetime;

import java.util.Calendar;

/**
 * @description:
 * @author: peter.peng
 * @create: 2023-05-02 17:33
 **/
public class CalendarTest {
    public static Calendar cal = Calendar.getInstance();
    // public static Calendar cal = new GregorianCalendar();

    public static void test1() {
        print();
    }

    private static void print() {
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
        int hour = cal.get(Calendar.HOUR);
        int minute = cal.get(Calendar.MINUTE);
        int second = cal.get(Calendar.SECOND);
        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);

        System.out.println(year + "年" + month + "月" + dayOfMonth + "日" + hour + "点"
        + minute + "分" + second + "秒 星期" + (dayOfWeek-1));
    }

    public static void test2() {
        cal.add(Calendar.YEAR, 1);
        print();
    }

    // 获取任意一个月的最后一天
    public static void test3() {
        int currentMonth = 6;
        cal.set(cal.get(Calendar.YEAR), currentMonth, 1);
        cal.add(Calendar.DATE, -1);
        int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
        System.out.println(currentMonth + "月的最后一天是：" + dayOfMonth + "号");
    }

    public static void test4() {
        cal.set(2022, 8, 1);
        print();
    }

    public static void test5() {
        cal.set(2022, 8, 10);
        print();

        cal.add(Calendar.DAY_OF_MONTH, -11);
        print();

        cal.set(2022, 8, 10);
        print();

        cal.roll(Calendar.DAY_OF_MONTH, -11);
        print();
    }

    public static void main(String[] args) {
        test5();
    }
}
