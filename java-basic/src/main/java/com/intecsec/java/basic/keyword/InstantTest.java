package com.intecsec.java.basic.keyword;

import java.time.Instant;
import java.util.Date;

/**
 * @description:
 * @author: peter.peng
 * @create: 2023-05-02 18:13
 **/
public class InstantTest {

    public static void main(String[] args) {
        instantTime();
    }

    public static void instantTime() {
        Instant test = Instant.now();
        System.out.println(test);

        Instant testOf = Instant.ofEpochMilli(test.toEpochMilli());
        System.out.println(testOf);

        Date date = Date.from(test);
        System.out.println(date);
        System.out.println(date.getTime());
    }
    
}
