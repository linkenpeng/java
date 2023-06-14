package com.intecsec.java.basic.bytecode.premain;


import com.intecsec.java.basic.bytecode.test.Greeting;

/**
 * -javaagent:-javaagent:agent/target/agent-0.0.1-SNAPSHOT.jar
 * Greeting类的包名和类名、方法名需要一模一样
 * 
 * 
 */
public class Test2 {

	public static void main(String[] args) {
		Greeting foo = new Greeting();
		foo.hello();
	}

}
