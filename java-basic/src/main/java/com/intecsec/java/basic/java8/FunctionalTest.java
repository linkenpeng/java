package com.intecsec.java.basic.java8;

import com.intecsec.java.util.JsonUtils;

/**
 * @description:
 * @author: peter.peng
 * @create: 2023-03-21 22:09
 **/
public class FunctionalTest {

    public static void main(String[] args) {
        // 可以进一步简化为：Integer::valueOf;
        Converter<String, Integer> convert = from -> Integer.valueOf(from);
        Integer integer = convert.convert("123");
        Integer deft = convert.deft("123", 23);
        System.out.println(integer);
        System.out.println(deft);

        Something something = new Something();
        Converter<String, String> converter = something::startsWith;
        String converted = converter.convert("Java");
        System.out.println(converted);

        PersonFactory<Person> personFactory = Person::new;
        Person person = personFactory.create("Peter", "Parker");
        System.out.println(JsonUtils.toJson(person));
    }

}
