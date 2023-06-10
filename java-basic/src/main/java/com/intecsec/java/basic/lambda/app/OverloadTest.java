package com.intecsec.java.basic.lambda.app;

public class OverloadTest {

	public static void main(String[] args) {		
		new OverloadTest().exec();
	}
	
	void exec()	{
		String s = invoke(() -> "done");
		System.out.println(s);
	}

	void invoke(Runnable r) {
	    r.run();
	}

	<T> T invoke(Callable<T> c) {
	    return c.call();
	}
}

interface Runnable {
    void run();
}

interface Callable<V> {
    V call();
}

