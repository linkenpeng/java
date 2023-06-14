package com.intecsec.spring.cust.my.di;

import com.intecsec.spring.cust.core.annotations.Component;

@Component
public class HelloWorld {
	
	public String hello() {
		return "Hello world";
	}
}
