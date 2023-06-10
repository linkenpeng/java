package com.intecsec.java.basic.java8.stream.op;

import java.util.ArrayList;
import java.util.stream.Stream;

public class StreamDistinct {

	public static void main(String[] args) {
		System.out.println("======基本类型包装类对象去重================");
		
		Stream<Integer> s1 = Stream.of(1, 1, 2, 2, 3, 3);
		Stream<Integer> s2 = s1.distinct();
		s2.forEach(System.out::println);
		// 1, 2, 3
		
		System.out.println("======自定义对象的去重================");

		ArrayList<Student> students = new ArrayList<Student>();
		students.add(new Student("Tom", 20));
		students.add(new Student("Tom", 20));
		students.add(new Student("Jerry", 20));
		students.add(new Student("Jerry", 18));

		// 先对象的hashCode再调用equals方法进行判重
		Stream<Student> s3 = students.stream().distinct();
		s3.forEach(System.out::println);

	}
}

class Student {
	private String name;
	private int age;

	public Student(String name, int age) {
		this.name = name;
		this.age = age;
	}

	@Override
	public int hashCode() {
		return name.hashCode() * 1000 + age;
	}
	
	@Override
	public boolean equals(Object o) {
		Student s = (Student) o;
		if ((this.age == s.age) 
				&& this.name.equals(s.name)) {
			return true;
		} else {
			return false;
		}
	}

	
	public String toString() {
		return "name:" + name + ", age:" + age;
	}

}
