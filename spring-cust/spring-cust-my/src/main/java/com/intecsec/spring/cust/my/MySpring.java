package com.intecsec.spring.cust.my;

import com.intecsec.spring.cust.core.CommandLineRunner;
import com.intecsec.spring.cust.core.MySpringApplication;
import com.intecsec.spring.cust.core.annotations.Autowired;
import com.intecsec.spring.cust.core.annotations.Component;
import com.intecsec.spring.cust.my.di.GreetingService;

//标识当前类对象的生命周期是由容器管理
@Component
public class MySpring implements CommandLineRunner {
	//标识当前成员变量由容器自动装配合适对象
	@Autowired
	private GreetingService greetingService;

	
	public static void main(String[] args) {
		MySpringApplication.ENABLE_LOG = false;
		MySpringApplication.run(MySpring.class);
	}

	
	@Override
	public void run() {
		System.out.println("Now the application is running");
		System.out.println("This is my spring");
		greetingService.greet();
	}

}
