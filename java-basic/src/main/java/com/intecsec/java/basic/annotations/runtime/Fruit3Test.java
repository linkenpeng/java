package com.intecsec.java.basic.annotations.runtime;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class Fruit3Test implements Fruit3 {

	public static void main(String[] args) throws Exception {
		System.out.println(Fruit3Test.name);
		//Fruit3Test.name = "Orange";  error
		
		Field f = Fruit3.class.getField("name");
		System.out.println(f.getModifiers()); //25=16+8+1
		
		System.out.println(Modifier.FINAL);  //16
		System.out.println(Modifier.STATIC); //8
		System.out.println(Modifier.PUBLIC); //1
	}

}
