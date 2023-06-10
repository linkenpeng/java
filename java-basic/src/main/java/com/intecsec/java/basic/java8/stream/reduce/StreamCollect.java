package com.intecsec.java.basic.java8.stream.reduce;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamCollect {

	public static void main(String[] args) {
		Integer[] a = new Integer[] {2,4,6,8};
		
		//将流存储为List
		Stream<Integer> s1 = Stream.of(a);
		List<Integer> list1 = s1.collect(Collectors.toList());
		
		//将流存储为指定的LinkedList
		Stream<Integer> s2 = Stream.of(a);
		List<Integer> list2 = s2.collect(Collectors.toCollection(LinkedList::new));
		
		//将流存储为Set
		Stream<Integer> s3 = Stream.of(a);
		Set<Integer> set1 = s3.collect(Collectors.toSet());
		
		//将流变换为字符流，并连接起来
		Stream<Integer> s4 = Stream.of(a);
		String result = s4.map(String::valueOf).collect(Collectors.joining());
		System.out.println(result); //2468
		
		//将流变换为字符流，并连接起来
		Stream<Integer> s5 = Stream.of(a);
		String result2 = s5.map(String::valueOf).collect(Collectors.joining(","));
		System.out.println(result2); //2,4,6,8
		
		
		
		List<Person> persons = new ArrayList<Person>();
		persons.add(new Person(1, "Jerry"));
		persons.add(new Person(2, "Tom"));
		
		//将流存储为Map
		Stream<Person> s6 = persons.stream();
		Map<Integer, String> map1 = s6.collect(Collectors.toMap(Person::getId, Person::getName));
		for(Integer i:map1.keySet())
		{
			System.out.println("id:" + i + ", name:" + map1.get(i));
		}
		
	}

}

class Person
{
	int id;
	String name;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Person(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}	
}