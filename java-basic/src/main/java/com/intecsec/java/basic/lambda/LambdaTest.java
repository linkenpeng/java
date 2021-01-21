package com.intecsec.java.basic.lambda;

import com.google.common.collect.Lists;
import com.intecsec.java.vo.Person;
import com.intecsec.java.vo.Students;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LambdaTest {

	public static void main(String[] args) {
		list();
	}
	
	private static void feature() {
		Stream.iterate(1, item -> item + 1).limit(10).forEach(System.out::println);
		
		List<Integer> nums = Lists.newArrayList(1, 1, null, 2, 3, 4, null, 5, 6, 7, 8, 9, 10);
		System.out.println(
					"sum is:" + nums.stream()
					.filter(num -> num != null)
					.distinct()
					.mapToInt(num -> num * 2)
					.peek(System.out::println)
					.skip(2)
					.limit(4)
					.sum()
		);
		
		// 汇聚
		List<Integer> nums2 = Lists.newArrayList(1, 1, null, 2, 3, 4, null, 5, 6, 7, 8, 9, 10);
		List<Integer> numsWithoutNull = 
				nums.stream()
				.filter(num2 -> num2 != null)
				.collect(() -> new ArrayList<Integer>(),
				(list, item) -> list.add(item), (list1, list2) -> list1.addAll(list2));
		System.out.println(numsWithoutNull);
		
		// 简化版
		List<Integer> numsWithoutNull2 = nums2.stream().filter(num2 -> num2 != null).
				                collect(Collectors.toList());
		System.out.println(numsWithoutNull2);

		
		// reduce
		List<Integer> ints = Lists.newArrayList(1,2,3,4,5,6,7,8,9,10);
		System.out.println("ints sum is:" + ints.stream().reduce((sum, item) -> sum + item).get());
		System.out.println("ints sum is:" + ints.stream().reduce(0, (sum, item) -> sum + item));
		System.out.println(ints.stream().allMatch(item -> item < 100));
		ints.stream().max(Integer::compareTo).ifPresent(System.out::println);
	}
	
	private static void set2list() {
		Set<String> set = new HashSet<>();
		List<String> name = new ArrayList<>();
		
		List<Students> students = new ArrayList<>();
		students.add(new Students("a", 1));
		students.add(new Students("a", 2));
		students.add(new Students("b", 3));
		students.add(new Students("b", 3));
		students.add(new Students("c", 4));
		students.add(new Students("c", 5));
		System.out.println(students);
		students.stream().forEach(student -> set.add(student.getName()));
		System.out.println(set);
		name = new ArrayList<>(set);
		System.out.println(name);
	}

	private static void sum() {
		List<Person> personList = new ArrayList<>();
		personList.add(new Person("a", 1, null));
		personList.add(new Person("b", 2, null));
		personList.add(new Person("c", 3, null));
		int total = personList.stream().mapToInt(i->i.getAge()).sum();
		System.out.println(total);
	}

	private static void list() {
		List<Person> personList = new ArrayList<>();
		personList.add(new Person("a", 1, null));
		personList.add(new Person("b", 2, null));
		personList.add(new Person("c", 3, null));
		List<Integer> ages = personList.stream().map(Person::getAge).collect(Collectors.toList());
		Map<Integer, Integer> agesMap = personList.stream().collect(Collectors.toMap(Person::getAge, Person::getAge));
		System.out.println(ages);
		System.out.println(agesMap);
	}

	private static void list2map() {
		List<Long> list = new ArrayList<>();
		list.add(1L);
		list.add(2L);
		list.add(3L);
		list.add(3L);
		Map<Long, Long> map = list.stream().collect(Collectors.toMap(k->k, v->v, (k1, k2) -> k2));
		System.out.println(map);
	}

}


