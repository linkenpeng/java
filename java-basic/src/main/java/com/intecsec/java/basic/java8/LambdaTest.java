package com.intecsec.java.basic.java8;

import com.google.common.collect.Lists;
import com.intecsec.java.util.JsonUtils;
import com.intecsec.java.vo.Person;
import com.intecsec.java.vo.Students;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Java 8 教程汇总 https://wizardforcel.gitbooks.io/java8-tutorials/content/index.html
 * Java8简明教程 gitbook https://wizardforcel.gitbooks.io/modern-java
 * Java8简明教程 https://github.com/wizardforcel/modern-java-zh
 * Java8新特性探究 https://wizardforcel.gitbooks.io/java8-new-features/content/
 */
public class LambdaTest {

	public static void main(String[] args) {
		groupBy();
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
		List<Integer> ints = Lists.newArrayList(1,2,3,4,5,6,7,8,9,10,11);
		System.out.println("ints sum is:" + ints.stream().reduce((sum, item) -> sum + item).get());
		System.out.println("ints sum is:" + ints.stream().reduce(0, (sum, item) -> sum + item));
		System.out.println(ints.stream().allMatch(item -> item < 9));
		ints.stream().max(Integer::compareTo).ifPresent(System.out::println);
	}

	private static void set2list() {
		Set<String> set = new HashSet<>();
		List<Students> students = genStudentsList();

		students.stream().forEach(student -> set.add(student.getName()));
		System.out.println(set);

		List<String> name = new ArrayList<>(set);
		System.out.println(name);
	}

	private static void sum() {
		List<Person> personList = genPersonList();
		int total = personList.stream().mapToInt(i->i.getAge()).sum();
		System.out.println(total);
	}

	private static void list() {
		List<Person> personList = genPersonList();
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

	private static void groupBy() {
		List<Students> students = genStudentsList();
		Map<Integer, List<Students>> listMap
				= students.stream().collect(Collectors.groupingBy(s -> s.getAge()));
		System.out.println(JsonUtils.toJson(listMap));

		Map<Integer, List<Students>> collect = students.stream()
				.collect(Collectors.groupingBy(s -> s.getId() % 6));
		System.out.println(JsonUtils.toJson(collect));

	}

	private static void partionBy() {
		List<Students> students = genStudentsList();
		Map<Boolean, List<Students>> collect = students.stream().collect(Collectors.partitioningBy(s -> s.getAge() > 30));
		System.out.println(JsonUtils.toJson(collect));
	}


	public static List<Students> genStudentsList() {
		List<Students> students = new ArrayList<>();
		students.add(new Students(1,"a", 25));
		students.add(new Students(2,"b", 25));
		students.add(new Students(3,"c", 35));
		students.add(new Students(4,"d", 45));
		students.add(new Students(5,"e", 40));
		students.add(new Students(6,"f", 50));
		students.add(new Students(7,"g", 20));
		students.add(new Students(8,"h", 30));
		students.add(new Students(9,"i", 32));
		students.add(new Students(10,"j", 29));
		students.add(new Students(11,"k", 19));
		return students;
	}

	public static List<Person> genPersonList() {
		List<Person> personList = new ArrayList<>();
		personList.add(new Person("a", 1, null));
		personList.add(new Person("b", 2, null));
		personList.add(new Person("c", 3, null));
		return personList;
	}

}


