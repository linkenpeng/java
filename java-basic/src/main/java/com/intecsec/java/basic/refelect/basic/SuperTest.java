package com.intecsec.java.basic.refelect.basic;

public class SuperTest {

	public static void main(String[] args) {
		Son son = new Son();
		Class c = son.getClass();
		
		Class father = c.getSuperclass();
		System.out.println(father.getName());
		
		Class[] inters = c.getInterfaces();
		for(Class inter : inters)
		{
			System.out.println(inter.getName());
		}

	}

}

class Father { }

class Son extends Father 
  implements Cloneable, Comparable
{
	protected Object clone() throws CloneNotSupportedException
	{
		return super.clone();
	}

	public int compareTo(Object o) {
		return 0;
	}
}
