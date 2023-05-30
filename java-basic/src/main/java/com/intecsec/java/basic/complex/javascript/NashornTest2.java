package com.intecsec.java.basic.complex.javascript;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.script.SimpleBindings;

public class NashornTest2 {

	public static void main(String[] args) throws ScriptException {
		//获取nashorn
		ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn"); 
		SimpleBindings simpleBindings = new SimpleBindings(); // 传递参数到js
		simpleBindings.put("a", 100); 
		simpleBindings.put("b", 200); 
		
		Object result = engine.eval("load('java-basic/files/js/sum2.js')", simpleBindings);
		System.out.println(result);
	}

}
