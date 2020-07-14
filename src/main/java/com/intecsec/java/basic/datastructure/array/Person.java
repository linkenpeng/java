package com.intecsec.java.basic.datastructure.array;

/**
 * @description:
 * @author: peter.peng
 * @create: 2019-01-22 18:33
 **/
public class Person {

    private String lastName;
    private String firstName;
    private int age;

    public Person(String lastName, String firstName, int age) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.age = age;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void displayPerson() {
        System.out.print("Last name:" + lastName);
        System.out.print(", First name:" + firstName);
        System.out.println(", age:" + age);
    }
}
