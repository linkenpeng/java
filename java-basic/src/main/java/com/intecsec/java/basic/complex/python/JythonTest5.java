package com.intecsec.java.basic.complex.python;

import org.python.core.Py;
import org.python.core.PyFunction;
import org.python.core.PyInteger;
import org.python.core.PyObject;
import org.python.util.PythonInterpreter;

public class JythonTest5 {

	public static void main(String[] args) {
		
		
	    PythonInterpreter pi = new PythonInterpreter();
	    pi.execfile("java-basic/files/python/calculator2.py");
	    
	    
	    //在Java中调用Python对象实例的方法
	    String pythonClassName = "Calculator";  // python类名	    
	    String pythonObjName = "cal"; // python对象名    
	    pi.exec(pythonObjName + "=" + pythonClassName + "()"); // 实例化python对象	    
	    PyObject pyObj = pi.get(pythonObjName); // 获取实例化的python对象
	    
	    // 调用python对象方法,传递参数并接收返回值
	    PyObject result = pyObj.invoke("power", new PyObject[] {Py.newInteger(2), Py.newInteger(3)}); 
	    double power = Py.py2double(result);
	    System.out.println(power);
	    
	    pi.cleanup();
	    pi.close();
	}
}
