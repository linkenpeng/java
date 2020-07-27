package com.intecsec.java.basic.exception;

public class Exception4SelfDefined {

	public static void main(String[] args) {
		try {
			throw new MyException("Myexp");
		} catch (MyException e) {
			System.out.println(e.getMsg());
		}
	}

}

class MyException extends Exception {
	private String mymsg;
	public MyException(String msg) {
		this.mymsg = msg;
	}
	
	public String getMsg() {
		return mymsg;
	}
}