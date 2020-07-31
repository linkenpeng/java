package com.intecsec.java.springboot.service;

import com.intecsec.java.springboot.dao.UserDao;
import com.intecsec.java.springboot.entity.User;
import org.springframework.stereotype.Service;

/**
 * @author peter.peng
 * @date 2020/7/31
 */
@Service
public class UserService {

	private final UserDao userDao;

	public UserService(UserDao userDao) {
		this.userDao = userDao;
	}

	public User findUserById(Integer id) {
		return userDao.findUserById(id);
	}
}


