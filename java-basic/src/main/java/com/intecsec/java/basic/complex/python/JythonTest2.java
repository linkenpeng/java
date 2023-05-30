package com.intecsec.java.basic.complex.python;

import org.python.core.PyInteger;
import org.python.core.PyObject;
import org.python.util.PythonInterpreter;

public class JythonTest2 {

	public static void main(String[] args) {
		PythonInterpreter pi = new PythonInterpreter();
	    pi.execfile("java-basic/files/python/hello.py");
	    pi.cleanup();
	    pi.close();

	}
}
