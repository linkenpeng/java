package com.intecsec.java.basic.keyword;

import java.text.DecimalFormat;

/**
 * @description:
 * @author: peter.peng
 * @create: 2023-05-07 19:37
 **/
public class Utils {
    public static void duration(long startTime) {
        long endTime = System.nanoTime();
        long duration = endTime - startTime;
        DecimalFormat df = new DecimalFormat("#,##0");
        System.out.println(df.format(duration) + "纳秒");
    }
}
