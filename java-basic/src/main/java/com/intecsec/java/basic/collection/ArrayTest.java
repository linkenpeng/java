package com.intecsec.java.basic.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArrayTest {

	public static void main(String[] args) {
		Student[] students = new Student[] { new Student(1, "zhangsan"), new Student(3, "wangwu"),
				new Student(2, "lisi"), new Student(3, "mybole") };

		for (int i = 0; i < students.length; i++) {
			System.out.println(students[i]);
		}

		Arrays.sort(students);

		for (int i = 0; i < students.length; i++) {
			System.out.println(students[i]);
		}
		
		List<Long> ids = new ArrayList<Long>();
		ids.add(123L);
		ids.add(334L);
		ids.add(689L);
		System.out.println(ids.toString());

	}

}

class Student implements Comparable {
	int num;
	String name;

	public Student(int num, String name) {
		this.num = num;
		this.name = name;
	}

	@Override
	public int compareTo(Object o) {
		Student s = (Student) o;
		int result = num > s.num ? 1 : (num == s.num ? 0 : -1);
		if (result == 0) {
			result = name.compareTo(s.name);
		}
		return result;
	}

	@Override
	public String toString() {
		return "Student [num=" + num + ", name=" + name + "]";
	}

}