package com.intecsec.java.basic.complex.python;

import org.python.core.Py;
import org.python.core.PyFunction;
import org.python.core.PyInteger;
import org.python.core.PyObject;
import org.python.util.PythonInterpreter;

public class JythonTest4 {

	public static void main(String[] args) {
		
		PythonInterpreter pi = new PythonInterpreter();	    
	    pi.execfile("java-basic/files/python/calculator1.py");  // 加载python程序
	    
	    // 调用Python程序中的函数
	    PyFunction pf = pi.get("power", PyFunction.class);
	    PyObject result = pf.__call__(Py.newInteger(2), Py.newInteger(3)); //2^3
	    System.out.println(result);
	    pi.cleanup();
	    pi.close();    	    
	}
}
