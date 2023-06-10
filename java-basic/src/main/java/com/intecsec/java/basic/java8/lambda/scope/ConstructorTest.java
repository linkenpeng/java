package com.intecsec.java.basic.java8.lambda.scope;

import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Supplier;

public class ConstructorTest {

	public static void main(String[] args) {
		Supplier<Person> s = Person::new;
		Person p = s.get();
		System.out.println(p.getName());
		
		
		IntFunction<int[]> intArray = int[]::new;		
		int[] nums = intArray.apply(10); 
		
		Function<Integer, Person[]> personArray = Person[]::new;
		Person[] persons = personArray.apply(5);
	}
}

class Person
{
	private String name;
	private int age;	
	
	public Person() {		
		this.name = "Tom";
		this.age = 18;
	}
	public String getName() {
		return name;
	}
	public int getAge() {
		return age;
	}	
}


