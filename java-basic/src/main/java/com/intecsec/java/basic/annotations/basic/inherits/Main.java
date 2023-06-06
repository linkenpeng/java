package com.intecsec.java.basic.annotations.basic.inherits;

public class Main {

	public static void main(String[] args) throws Exception {
		Class a1 = Class.forName("inherits.CommonFather");
		Class a2 = Class.forName("inherits.CommonSon");
		System.out.println(a1.getAnnotations().length);  //1
		System.out.println(a2.getAnnotations().length);  //0
		
		
		Class a3 = Class.forName("inherits.InheritFather"); 
		Class a4 = Class.forName("inherits.InheritSon");    
		System.out.println(a3.getAnnotations().length);  //1
		System.out.println(a4.getAnnotations().length);  //1
		//Inherit注释过的Annotation，会保持继承

	}

}
