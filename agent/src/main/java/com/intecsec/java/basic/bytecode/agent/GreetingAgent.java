package com.intecsec.java.basic.bytecode.agent;


import java.lang.instrument.Instrumentation;

public class GreetingAgent {

	public static void agentmain(String agentArgument, Instrumentation instrumentation) throws Exception {
		System.out.println("AgentMain Agent");
		AgentMainTransformer sct = new AgentMainTransformer();
		//only true can transform class
		instrumentation.addTransformer(sct, true);
		instrumentation.retransformClasses(Class.forName("com.intecsec.java.basic.bytecode.test.Greeting"));
	}

	public static void premain(String agentArgument, Instrumentation instrumentation) {
		System.out.println("PreMain Agent");
		PreTransformer sct = new PreTransformer();
		instrumentation.addTransformer(sct);
	}
}
