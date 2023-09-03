package com.intecsec.java.cache.service.impl;

import com.intecsec.java.cache.constans.CacheConstants;
import com.intecsec.java.cache.constans.CacheEnum;
import com.intecsec.java.cache.service.UserService;
import com.intecsec.java.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.caffeine.CaffeineCacheManager;
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

	@Autowired
	@Qualifier("getCaffeineCacheManager")
	private CaffeineCacheManager caffeineCacheManager;

	@Override
	public User saveUser(User user) {
		redisTemplate.opsForValue().set(k(user.getUid()), user);
		return user;
	}

	/**
	 * value：缓存key的前缀。
	 * key：缓存key的后缀。
	 * sync：设置如果缓存过期是不是只放一个请求去请求数据库，其他请求阻塞，默认是false（根据个人需求）。
	 * unless：不缓存空值,这里不使用，会报错
	 * 查询用户信息类
	 * 如果需要加自定义字符串，需要用单引号
	 * 如果查询为null，也会被缓存
	 */
	@Override
	@Cacheable(
			value = {CacheConstants.GET_USER},
			cacheManager = "getCaffeineCacheManager",
			key = "'user'+#uid",sync = true)
	@CacheEvict
	public User getUser(String uid) {
		User user =  (User) redisTemplate.opsForValue().get(k(uid));
		System.out.println("查询了Redis");
		return user;
	}

	@Override
	public void deleteUser(String uid) {
		redisTemplate.delete(k(uid));
	}

	private String k(String uid) {
		return "user:" + uid;
	}
}
