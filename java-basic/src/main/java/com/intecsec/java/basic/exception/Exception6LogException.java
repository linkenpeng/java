package com.intecsec.java.basic.exception;

import java.util.logging.Logger;

public class Exception6LogException {

	public static void main(String[] args) {
		try {
			throw new MyException1("Myexp");
		} catch (MyException1 e) {
			System.out.println(e.getMsg());
		}
	}
}

class MyException1 extends Exception {
	private static Logger logger = Logger.getLogger("MyException1");
	private String mymsg;
	public MyException1(String msg) {
		this.mymsg = msg;
		logger.severe(msg);
	}
	
	public String getMsg() {
		return mymsg;
	}
}

class MyException2 extends Exception {
	private static Logger logger = Logger.getLogger("MyException2");
	private String mymsg;
	public MyException2(String msg) {
		this.mymsg = msg;
		logger.severe(msg);
	}
	
	public String getMsg() {
		return mymsg;
	}
}