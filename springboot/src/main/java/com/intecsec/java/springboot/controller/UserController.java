package com.intecsec.java.springboot.controller;

import com.intecsec.java.springboot.annotation.MyLog;
import com.intecsec.java.springboot.entity.User;
import com.intecsec.java.springboot.service.UserService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author peter.peng
 * @date 2020/7/31
 */
@RestController
public class UserController {

	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@MyLog("打点日志")
	@RequestMapping("user/{id}")
	public User findUser(@PathVariable("id") Integer id) {
		return userService.findUserById(id);
	}
}
