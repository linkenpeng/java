package com.intecsec.java.basic.lambda.basic;

@FunctionalInterface
//系统自带的函数式接口注解，用于编译器检查

public interface StringChecker {
	public boolean test(String s);
	//public boolean test2(String s);
}


