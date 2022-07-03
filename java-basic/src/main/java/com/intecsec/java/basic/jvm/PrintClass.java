package com.intecsec.java.basic.jvm;

import lombok.Builder;
import lombok.Data;
import org.openjdk.jol.info.ClassLayout;

/**
 * @description:
 * @author: peter.peng
 * @create: 2022-05-17 23:34
 **/
public class PrintClass {

    @Data
    @Builder
    public static class Car {
        private int id;
        private String type;
        private double price;
        private char level;
    }

    public static void main(String[] args) {
        Car car = Car.builder()
                .id(1)
                .type("SUV")
                .level('A')
                .price(22.22)
                .build();

        System.out.println( ClassLayout.parseInstance(car).toPrintable() );

        int[] array = new int[3];
        array[0] = 11;
        array[1] = 22;
        array[2] = 33;
        System.out.println( ClassLayout.parseInstance(array).toPrintable() );

    }
}


