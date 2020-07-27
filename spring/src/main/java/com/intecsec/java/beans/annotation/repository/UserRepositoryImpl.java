package com.intecsec.java.beans.annotation.repository;

import org.springframework.stereotype.Repository;

@Repository("userRepository")
public class UserRepositoryImpl implements UserRepository {

	public void save() {
		System.out.println("UserRepository save...");
	}

}
