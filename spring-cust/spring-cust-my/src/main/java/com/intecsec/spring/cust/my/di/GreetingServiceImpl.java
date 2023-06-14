package com.intecsec.spring.cust.my.di;

import com.intecsec.spring.cust.core.annotations.Autowired;
import com.intecsec.spring.cust.core.annotations.Component;
import com.intecsec.spring.cust.core.annotations.PostConstruct;

@Component
public class GreetingServiceImpl implements GreetingService {

	@Autowired
	HelloWorld helloWorld;
	
	@PostConstruct
	public void post() {
		System.out.println("Greeting Service Impl is ready: " + helloWorld.hello());
	}

	@Override
	public void greet() {
		System.out.println("Simple greeting");
	}

}
