package com.intecsec.java.basic.bytecode.agent;


import com.intecsec.java.basic.bytecode.test.Greeting;

public class Test {

	public static void main(String[] args) throws Exception {
    	Greeting foo = new Greeting();
        for (; ; ) {
            Thread.sleep(2000);
            foo.hello();
        }
    }

}
