package com.intecsec.java.basic.datatype;

import com.intecsec.java.util.PriceUtil;

import java.math.BigDecimal;

/**
 * @description:
 * @author: peter.peng
 * @create: 2019-02-11 10:20
 **/
public class FloatTest {

    public static void main(String[] args) {
        equals();
    }

    public static void div() {
        Float actualPrice = 0f;
        Long price = 1L;
        Integer radix = 5;
        if(price != null) {
            Double priceUnit = PriceUtil.div(price, radix, 7);
            System.out.println(priceUnit);
            actualPrice = PriceUtil.fenToYuanFloor4Point(new BigDecimal(priceUnit.toString()));
        }
        System.out.println(actualPrice);
    }

    public static void equals() {
        float f1 = 122.999999999999999999999999999999f;
        float f2 = 122.9f;

        System.out.println(f1 == f2);
        System.out.println(f1/2*2 == f2);

        String s1 = String.valueOf(f1);
        String s2 = String.valueOf(f2);
        System.out.println(s1);
        System.out.println(s2);

        System.out.println(s1.equals(s2));

        System.out.println(new BigDecimal(f1).longValue());

    }


    public static void str() {
        String str = "113.31585050423376";
        System.out.println(new BigDecimal(str).setScale(4, BigDecimal.ROUND_HALF_UP).floatValue());
    }
}
