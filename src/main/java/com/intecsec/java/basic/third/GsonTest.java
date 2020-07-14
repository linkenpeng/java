package com.intecsec.java.basic.third;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.intecsec.java.beans.Car;
import org.apache.commons.lang3.StringEscapeUtils;

/**
 * @description:
 * @author: peter.peng
 * @create: 2019-07-12 14:32
 **/
public class GsonTest {

    private static Gson gson =  new GsonBuilder().
            setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .disableHtmlEscaping()
            .setDateFormat("yyyy-MM-dd HH:mm:ss").create();


    public static void main(String[] args) {
        String str = toJson();
        System.out.println(str);

        toObject(StringEscapeUtils.unescapeHtml4(str));
    }


    public static void toObject(String str) {
        Car car = gson.fromJson(str, Car.class);
        System.out.println(car);
    }

    public static String toJson() {
        Car car = new Car("ford%!@#$%^&*()", "xyx", 1230);
        String json = gson.toJson(car);
        return json;
    }


}
