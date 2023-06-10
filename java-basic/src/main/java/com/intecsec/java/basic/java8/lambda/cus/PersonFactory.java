package com.intecsec.java.basic.java8.lambda.cus;

/**
 * @description:
 * @author: peter.peng
 * @create: 2023-03-21 22:24
 **/
interface PersonFactory<P extends Person> {
    P create(String firstName, String lastName);
}
