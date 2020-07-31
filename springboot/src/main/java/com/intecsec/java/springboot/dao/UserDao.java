package com.intecsec.java.springboot.dao;

import com.intecsec.java.springboot.entity.User;
import org.springframework.stereotype.Component;

/**
 * @author peter.peng
 * @date 2020/7/31
 */
@Component
public class UserDao {

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
