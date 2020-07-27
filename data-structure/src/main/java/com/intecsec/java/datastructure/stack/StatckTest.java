package com.intecsec.java.datastructure.stack;

public class StatckTest {

	public static void main(String[] args) {
		test2();
	}
	
	public static void test1() {
		Stack<String> stack = new Stack<String>();
		for (String s : "My dog has fleas".split(" ")) {
			stack.push(s);
		}
		
		while (!stack.empty()) {
			System.out.print(stack.pop() + " ");
		}
	}
	
	public static void test2() {
		Stack<String> stack = new Stack<String>();
		String string = "+U+n+c---+e+r+t---+a-+i-+n+t+y---+-+r+u--+l+e+s---";
		String op = "";
		for (String s : string.split("")) {
			if(s.equals("+")) {
				op = "+";
			} else if (s.equals("-")) {
				op = "-";
				if(!stack.empty()) {
					System.out.print(stack.pop());
				}
			} else {
				if(op.equals("+")) {
					stack.push(s);
				}
			}
		}
	}

}
