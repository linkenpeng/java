package com.intecsec.module2.p1;

import com.intecsec.module1.first.p1.Shoe;

import java.util.ServiceLoader;


public class ShoeTest {
	public static void main(String[] args)
	{
		
		Iterable<Shoe> objs = ServiceLoader.load(Shoe.class);
		for(Shoe obj:objs)
		{
			obj.walk();
			System.out.println(obj.hashCode());
		}
		
		//每次load，都产生新的实例
		objs = ServiceLoader.load(Shoe.class);
		for(Shoe obj:objs)
		{
			obj.walk();
			System.out.println(obj.hashCode());
		}
		
	//	Iterable<Shoe> objs = Shoe.getA1();
	//	System.out.println("aaaaaa");
	//	for(Shoe obj:objs)
	//	{
	//		obj.print();
	//		System.out.println(obj.getClass().getName());
	//	}
	//	System.out.println("bbbbbbbbbbb");
	}
}
