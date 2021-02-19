package com.intecsec.java.cache.controller;

import com.intecsec.java.cache.service.UserService;
import com.intecsec.java.vo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Peter.Peng
 * @date 2021/2/19
 */
@RestController()
@RequestMapping("/user")
@Slf4j
public class UserController {
	@Resource
	private UserService userService;

	@GetMapping("/get/{uid}")
	public User getUser(@PathVariable String uid) {
		return userService.getUser(uid);
	}

	/**
	 * Content-Type:application/json
	 * data: {"uid":"1","devId":"2","nickName":"pp"}
	 * @param user
	 * @return
	 */
	@PostMapping("/save")
	public User saveUser(@RequestBody User user) {
		log.info("user:{}", user);
		return userService.saveUser(user);
	}

	@GetMapping("/delete/{uid}")
	public String deleteUser(@PathVariable String uid) {
		userService.deleteUser(uid);
		return "ok";
	}
}
