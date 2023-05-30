package com.intecsec.java.basic.complex.python;

import org.python.core.PyInteger;
import org.python.core.PyObject;
import org.python.util.PythonInterpreter;

public class JythonTest1 {

	public static void main(String[] args) {
		PythonInterpreter pi = new PythonInterpreter();
	    // 执行Python程序语句
	    pi.exec("import sys");
	    pi.set("a", new PyInteger(42));
	    pi.exec("print a");
	    pi.exec("x = 2+2");
	    PyObject x = pi.get("x");
	    System.out.println("x: " + x);

	}

}
