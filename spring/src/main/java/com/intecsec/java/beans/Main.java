package com.intecsec.java.beans;

import com.intecsec.java.vo.Car;
import com.intecsec.java.vo.HelloWorld;
import com.intecsec.java.vo.Person;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

public class Main {

	public static void main(String[] args) {
		o2map();
	}

	public static void o2map() {
		Car car = new Car("fu", "te", 120);
		Map map = BeanMap.create(car);
		System.out.println(map);
	}

	public static void getBean() {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		HelloWorld helloWorld = (HelloWorld) context.getBean("helloWorld");
		helloWorld.sayHello();

		Car car = (Car) context.getBean("car");
		System.out.println(car);

		Car car2 = (Car) context.getBean("car2");
		System.out.println(car2);

		Person person = (Person) context.getBean("person2");
		System.out.println(person);
	}


}
