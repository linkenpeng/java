package com.intecsec.java.basic.complex.python;

import javax.script.ScriptException;
import org.python.core.PyObject;
import org.python.util.PythonInterpreter;

public class JythonTest3 
{
	
	/**
	 * 随机输出cnt个随机数值，并计算和
	 * cnt由Java代码设置
	 * randomSum.py中使用java Random类生成随机数并计算和
	 * @param args
	 * @throws ScriptException
	 */
    public static void main( String[] args ) throws ScriptException
    {
    	try (PythonInterpreter pi = new PythonInterpreter()) {
	    	pi.set("cnt", 5);
	    	pi.execfile("java-basic/files/python/randomSum.py");
	    	PyObject sum = pi.get("sum");
	    	System.out.println("Sum is: " + sum);
    	}
    }    
}
