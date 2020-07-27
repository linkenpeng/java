package com.intecsec.java.aop;

import org.springframework.stereotype.Component;

@Component("arithmeticCalculator")
public class ArithmeticCalculatorImpl implements ArithmeticCalculator {

	@Override
	public double add(int x, int y) {
		return x + y;
	}

	@Override
	public double minus(int x, int y) {
		return x - y;
	}

	@Override
	public double multi(int x, int y) {
		return x * y;
	}

	@Override
	public double divide(int x, int y) {
		return x / y;
	}

}
