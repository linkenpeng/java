package com.intecsec.java.mongodb.repository;

import com.intecsec.java.mongodb.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @description:
 * @author: peter.peng
 * @create: 2023-03-29 07:07
 **/
public interface UserRepository extends MongoRepository<User, String> {
}
