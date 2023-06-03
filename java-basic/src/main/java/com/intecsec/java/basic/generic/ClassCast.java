package com.intecsec.java.basic.generic;

import java.util.ArrayList;

public class ClassCast {

	public static void main(String[] args) {
		ArrayList list = new ArrayList();
		list.add(123);
		list.add("456");
		list.add(789);
		
		for(Object o:list)
		{
			//编译可以通过  运行报错
			System.out.println(((String) o));
		}
	}

}
