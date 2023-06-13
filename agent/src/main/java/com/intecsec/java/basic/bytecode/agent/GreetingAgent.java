package com.intecsec.java.basic.bytecode.agent;


import java.lang.instrument.Instrumentation;

public class GreetingAgent {

	public static void premain(String agentArgument, Instrumentation instrumentation) {
		SimpleClassTransformer sct = new SimpleClassTransformer();
		instrumentation.addTransformer(sct);
		System.out.println("Hello Agent");
	}
}
