package com.intecsec.java.cache.service.impl;

import com.intecsec.java.cache.service.UserService;
import com.intecsec.java.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @author Peter.Peng
 * @date 2021/2/19
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private RedisTemplate redisTemplate;

	@Override
	public User saveUser(User user) {
		redisTemplate.opsForValue().set(k(user.getUid()), user);
		return user;
	}

	@Override
	public User getUser(String uid) {
		return (User) redisTemplate.opsForValue().get(k(uid));
	}

	@Override
	public void deleteUser(String uid) {
		redisTemplate.delete(k(uid));
	}

	private String k(String uid) {
		return "user:" + uid;
	}
}
