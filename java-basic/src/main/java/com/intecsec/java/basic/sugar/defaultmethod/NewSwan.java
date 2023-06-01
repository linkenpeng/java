package com.intecsec.java.basic.sugar.defaultmethod;

public class NewSwan extends NewFlyAnimal implements NewLandAnimal{

	public static void main(String[] args) {
		new NewSwan().move();
		//当父类和父接口都有同名的方法，以父类的为主
		//这样可以兼容JDK7及以前的代码

	}

}
