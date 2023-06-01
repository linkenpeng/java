package com.intecsec.java.basic.sugar.defaultmethod;

public class Lion implements Animal, NewAnimal {

	public static void main(String[] args) {
		new Lion().move();

	}

	//当实现的2个接口都含有同名方法，且至少有一个是默认方法
	//则子类需要重写该方法，以免歧义
	public void move() {
		NewAnimal.super.move();		
	}	

}
