package com.intecsec.java.basic.generic.variance;

import java.util.ArrayList;
import java.util.List;

public class Test {

	//第一个函数是否能被第二个函数代替
	public void f1(Cat foo) {
		// code
	}

	public void f1(Animal foo) {
		// code
	}
	
	//第一句语句是否被第二句语句替换
	List<Cat> foos = new ArrayList<Cat>();

	//List<Animal> foos = new ArrayList<Cat>();
	
	
	//第一个函数是否能被第二个函数代替
	/*public Cat f2() {
		return new Cat();
	}
	
	public Animal f2() {
		return new Cat();
	}*/
}

class Animal {
}

class Cat extends Animal {
}
