package com.intecsec.java.basic.annotations.hello;

public class DeprecatedTest {

	public static void main(String[] args) {
		Person p = new Person();
		System.out.println(p.name);
		System.out.println(p.getName());

	}

}

@Deprecated
class Person
{
	@Deprecated
	public String name = "abc";
	
	@Deprecated
	public String getName()	{
		return name;
	}
}


