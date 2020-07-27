package com.intecsec.java.beans.annotation;

import com.intecsec.java.beans.annotation.controller.UserController;
import com.intecsec.java.beans.annotation.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("beans-annotation.xml");
		
		TestObject to = (TestObject) context.getBean("testObject");
		System.out.println(to);
		
		UserController userController = (UserController) context.getBean("userController");
		System.out.println(userController);
		
		UserService userService = (UserService) context.getBean("userService");
		System.out.println(userService);
		
//		UserRepositoryImpl userRepository = (UserRepositoryImpl) context.getBean("userRepository");
//		System.out.println(userRepository);

	}

}
