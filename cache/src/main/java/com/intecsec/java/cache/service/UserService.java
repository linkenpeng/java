package com.intecsec.java.cache.service;

import com.intecsec.java.vo.User;

/**
 * @author Peter.Peng
 * @date 2021/2/19
 */
public interface UserService {

	User saveUser(final User user);

	User getUser(String id);

	void deleteUser(String id);
}
