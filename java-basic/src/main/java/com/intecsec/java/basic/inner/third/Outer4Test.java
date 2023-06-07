package com.intecsec.java.basic.inner.third;


import com.intecsec.java.basic.inner.staticnested.Outer4;

public class Outer4Test {

	public static void main(String[] args) {
		//第三方类访问静态嵌套类
		Outer4.Inner4  obj1 = new Outer4.Inner4();
		obj1.getInnField1();
		
		Outer4.Inner4  obj2 = new Outer4.Inner4();
		System.out.println(obj1 == obj2);
		
		System.out.println("================");
		Outer4 obj3 = new Outer4();
		obj3.outFun2();
	}

}
