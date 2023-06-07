package com.intecsec.java.basic.annotations.lombok;

public class Main {

	public static void main(String[] args) {
		Student student = new Student("Tom", 20);
		System.out.println(student.getName());
		System.out.println(student);
	}
}
