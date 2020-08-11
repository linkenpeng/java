package com.intecsec.java.springboot.dao;

import com.intecsec.java.springboot.entity.User;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author peter.peng
 * @date 2020/7/31
 */
@Component
// 优先加载bean 方法三 此方法不行，因为应用程序里面声明的bean加载完之后，才会去加载第三方依赖包中声明的bean
// @DependsOn("userInfo")

// 但是可以控制应用程序内部的bean的初始化顺序
// @DependsOn("groupDao")

// @Order 注解在spring boot中是无效的 Order注解只有对aop的拦截顺序有效
// @Order(10)
public class UserDao {

	public UserDao() {
		System.out.println("init UserDao");
	}

	public User findUserById(Integer id) {

		System.out.println("查询id为[" + id + "]的用户");
		if(id > 10) {
			return null;
		}
		User user = new User(id, "user-" + id);
		System.out.println("返回的用户为[" + user.toString() + "]");
		return user;
	}
}
