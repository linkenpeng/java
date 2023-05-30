package com.intecsec.java.basic.complex.javascript;

import java.io.FileReader;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.script.SimpleBindings;

public class NashornTest3 {

	public static void main(String[] args) throws Exception {
		//获取nashorn
		ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn"); 
		FileReader scriptFile = new FileReader("java-basic/files/js/sum3.js");
		engine.eval(scriptFile);
        
        Invocable in = (Invocable) engine;
        String result = in.invokeFunction("sum",100,200).toString();
		System.out.println(result);
	}

}
