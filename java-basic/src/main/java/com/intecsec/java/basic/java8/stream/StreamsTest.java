package com.intecsec.java.basic.java8.stream;

import com.google.common.collect.Lists;
import com.intecsec.java.util.JsonUtils;
import com.intecsec.java.vo.Person;
import com.intecsec.java.vo.Students;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Java 8 教程汇总 https://wizardforcel.gitbooks.io/java8-tutorials/content/index.html
 * Java8简明教程 gitbook https://wizardforcel.gitbooks.io/modern-java
 * Java8简明教程 https://github.com/wizardforcel/modern-java-zh
 * Java8新特性探究 https://wizardforcel.gitbooks.io/java8-new-features/content/
 */
public class StreamsTest {

	public static void main(String[] args) {
		str2list();
	}

	public static void str2list() {
		String str = "1,2,3,4|22";
		String[] strArr = StringUtils.split(str, "|");
		System.out.println(strArr.length);
		List<Long> list = Arrays.stream(StringUtils.split(strArr[0], ","))
				.map(Long::valueOf).collect(Collectors.toList());
		System.out.println(list);
	}

	public static void streamTime() {
		List<String> values = genStringList();

		// 纳秒
		long t0 = System.nanoTime();

		long count = values.stream().sorted().count();
		System.out.println(count);

		long t1 = System.nanoTime();

		// 纳秒转微秒 1035 ms
		long millis = TimeUnit.NANOSECONDS.toMillis(t1 - t0);
		System.out.println(String.format("顺序流排序耗时: %d ms", millis));


		long countParallelStream = values.parallelStream().sorted().count();
		System.out.println(countParallelStream);

		long t2 = System.nanoTime();

		// 纳秒转微秒 378 ms
		long millis1 = TimeUnit.NANOSECONDS.toMillis(t2 - t1);
		System.out.println(String.format("并行流排序耗时: %d ms", millis1));
	}
	
	private static void feature() {
		Stream.iterate(1, item -> item + 1).limit(10)
				.forEach(System.out::println);
		
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
		Map<Long, Long> map = list.stream()
				.collect(Collectors.toMap(k->k, v->v, (k1, k2) -> k2));
		System.out.println(map);
	}


	private static void match() {
		List<String> stringCollection = new ArrayList<>();
		stringCollection.add("ddd2");
		stringCollection.add("aaa2");
		stringCollection.add("bbb1");
		stringCollection.add("aaa1");
		stringCollection.add("bbb3");
		stringCollection.add("ccc");
		stringCollection.add("bbb2");
		stringCollection.add("ddd1");
		// 验证 list 中 string 是否有以 a 开头的, 匹配到第一个，即返回 true
		boolean anyStartsWithA =
				stringCollection
						.stream()
						.anyMatch((s) -> s.startsWith("a"));

		System.out.println(anyStartsWithA);      // true

		// 验证 list 中 string 是否都是以 a 开头的
		boolean allStartsWithA =
				stringCollection
						.stream()
						.allMatch((s) -> s.startsWith("a"));

		System.out.println(allStartsWithA);      // false

		// 验证 list 中 string 是否都不是以 z 开头的,
		boolean noneStartsWithZ =
				stringCollection
						.stream()
						.noneMatch((s) -> s.startsWith("z"));

		System.out.println(noneStartsWithZ);      // true
	}

	private static void groupingBy() {
		List<Students> students = genStudentsList();
		Map<Integer, List<Students>> listMap
				= students.stream().collect(Collectors.groupingBy(s -> s.getAge()));
		System.out.println(JsonUtils.toJson(listMap));

		Map<Integer, List<Students>> collect = students.stream()
				.collect(Collectors.groupingBy(s -> s.getId() % 6));
		System.out.println(JsonUtils.toJson(collect));
	}

	private static void partitioningBy() {
		List<Students> students = genStudentsList();
		Map<Boolean, List<Students>> collect = students.stream()
				.collect(Collectors.partitioningBy(s -> s.getAge() > 30));
		System.out.println(JsonUtils.toJson(collect));
	}

	public static List<String> genStringList() {
		long t0 = System.nanoTime();
		int max = 1000000;
		List<String> values = new ArrayList<>(max);
		for (int i = 0; i < max; i++) {
			UUID uuid = UUID.randomUUID();
			values.add(uuid.toString());
		}
		long t1 = System.nanoTime();
		long millis = TimeUnit.NANOSECONDS.toMillis(t1 - t0);
		System.out.println(String.format("生成数据耗时: %d ms", millis));
		return values;
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

	public static void listObj2Map() {
		List<Students> list = genStudentsList();
		Map<Integer, Students> result = list.stream().collect(Collectors.toMap(Students::getId,
				Function.identity(), (k1, k2) -> k1));
		System.out.println(JsonUtils.toJson(result));
	}

	public static List<Person> genPersonList() {
		List<Person> personList = new ArrayList<>();
		personList.add(new Person("a", 1, null));
		personList.add(new Person("b", 2, null));
		personList.add(new Person("c", 3, null));
		return personList;
	}

}


