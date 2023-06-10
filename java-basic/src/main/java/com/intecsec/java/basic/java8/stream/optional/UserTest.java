package com.intecsec.java.basic.java8.stream.optional;

import java.util.ArrayList;
import java.util.Optional;

public class UserTest {

	public static void main(String[] args) {
		User u1 = oldMethod();
		if(u1 == null)
		{
			u1 = new User("noname", 10); 
		}
		System.out.println(u1.getName());
		
		Optional<User> u2 = newMethod();		
		System.out.println(u2.orElse(new User()).getName());
		
		
		if(u2.isPresent())
		{
			System.out.println(u2.get().getName());
		}
		else
		{
			//如果u2不存在，使用后面的Lambda表达式进行计算
			u2.orElseGet(()->new User());
			
			//如果u2不存在，爆发异常
			u2.orElseThrow(IllegalStateException::new);
		}
		
		u2.ifPresent(System.out::println);
		
		ArrayList<User> users = new ArrayList<>();
		users.add(new User(null, 10));
		
		//对Optional对象使用map操作
		String nameUppercase = u2.map(u->u.getName())
			.map(name->name.toLowerCase())
			.orElse("noname");
		System.out.println(nameUppercase);
		
	}
	
	public static User oldMethod()
	{
		try
		{
			return new User("Tom", 20);
		}
		catch(Exception ex)
		{
			return null;
		}
	}
	
	public static Optional<User> newMethod()
	{
		try
		{
			return Optional.of(new User("Tom", 20));
		}
		catch(Exception ex)
		{
			return Optional.empty();
		}
	}

}
