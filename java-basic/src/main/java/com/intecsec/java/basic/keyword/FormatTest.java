package com.intecsec.java.basic.keyword;

import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @description:
 * @author: peter.peng
 * @create: 2023-05-03 11:21
 **/
public class FormatTest {

    public static void main(String[] args) {
        dateFormat();
    }

    public static void decimalFormat() {
        DecimalFormat df1, df2, df3, df4, df5, df6;
        df1 = new DecimalFormat("#.00");
        df2 = new DecimalFormat("0.00");
        System.out.println(df1.format(0.1));
        System.out.println(df2.format(0.1));

        System.out.println(df1.format(2));
        System.out.println(df2.format(2));

        System.out.println(df1.format(20));
        System.out.println(df2.format(20));

        System.out.println(df1.format(200));
        System.out.println(df2.format(200));

        df1 = new DecimalFormat("0.00");
        df2 = new DecimalFormat("0.##");

        System.out.println(df1.format(0.1));
        System.out.println(df2.format(0.1));

        System.out.println(df1.format(0.006));
        System.out.println(df2.format(0.006));

        System.out.println(" ");

        df1 = new DecimalFormat("0.0");
        df2 = new DecimalFormat("#.#");
        df3 = new DecimalFormat("000.000");
        df4 = new DecimalFormat("###.###");
        System.out.println(df1.format(12.34));
        System.out.println(df2.format(12.34));
        System.out.println(df3.format(12.34));
        System.out.println(df4.format(12.34));

        df5 = new DecimalFormat("0.00");
        double d1 = 123456789.654321;
        double d2 = 987654321.987654321;
        System.out.println(df5.format(d1));
        System.out.println(df5.format(d2));

        df6 = new DecimalFormat("#,##0.00");
        System.out.println(df6.format(d1));
        System.out.println(df6.format(d2));

    }

    public static void messageFormat() {
        String message = "{0}{1}{2}{3}{4}{5}{6}{7}{8}{9}{10}{11}{12}{13}{14}{15}{16}";
        Object[] array = new Object[] {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P","R"};
        String value = MessageFormat.format(message, array);
        System.out.println(value);

        message = "oh, {0,number,#.##} is a good number";
        array = new Object[]{new Double(3.14526)};
        value = MessageFormat.format(message, array);
        System.out.println(value);
    }

    public static void simpleDateFormat() {
        String dateStr = "2023-05-03 12:00:00.123";
        String pat1 = "yyyy-MM-dd HH:mm:ss.SSS";
        String pat2 = "yyyy年MM月dd日 HH时mm分ss秒SSS毫秒";
        SimpleDateFormat sdf1 = new SimpleDateFormat(pat1);
        SimpleDateFormat sdf2 = new SimpleDateFormat(pat2);
        Date d = null;
        try {
            d  = sdf1.parse(dateStr);
            System.out.println(d);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        System.out.println(sdf2.format(d));
    }

    public static void dateFormat() {
        String dateStr = "2023年05月12日";
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日");
        LocalDate date = LocalDate.parse(dateStr, dateFormatter);
        System.out.println(date.getYear() + " " + date.getMonthValue() + " " + date.getDayOfMonth());

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日 hh:mm:ss");
        String nowStr = now.format(formatter);
        System.out.println(nowStr);
    }

}
