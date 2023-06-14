package com.intecsec.spring.cust.my.aop;
import java.lang.reflect.Method;

import com.intecsec.spring.cust.core.annotations.After;
import com.intecsec.spring.cust.core.annotations.Aspect;
import com.intecsec.spring.cust.core.annotations.Before;
import com.intecsec.spring.cust.core.annotations.Component;



@Aspect
@Component
public class GreetingServiceAspect {
	
	
	@Before(value = "com.intecsec.spring.cust.my.di.GreetingServiceImpl.greet")
	public void beforeAdvice(Method method, Object... args) {
		System.out.println("Before method:" + method);
	}
	

	@After(value = "com.intecsec.spring.cust.my.di.GreetingServiceImpl.greet")
	public void afterAdvice(Method method, Object... args) {
		System.out.println("After method:" + method);
	}
}