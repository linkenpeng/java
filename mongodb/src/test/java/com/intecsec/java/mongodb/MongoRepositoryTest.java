package com.intecsec.java.mongodb;

import com.intecsec.java.mongodb.entity.User;
import com.intecsec.java.mongodb.repository.UserRepository;
import com.intecsec.java.util.JsonUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description: MongoDbAppTest
 * @author: peter.peng
 * @create: 2023-03-26 21:47
 **/
@SpringBootTest
public class MongoRepositoryTest {

    @Resource
    private UserRepository repository;

    @Test
    public void create() {
        User user = new User();
        user.setAge(20);
        user.setName("li");
        user.setEmail("abc@qq.com");
        User insert = repository.save(user);
        System.out.println(JsonUtils.toJson(insert));
    }

    @Test
    public void findAll() {
        List<User> all = repository.findAll();
        System.out.println(all);
    }

    @Test
    public void findId() {
        User user = repository.findById("642374453fb3160f09c75d78").get();
        System.out.println(user);
    }

    @Test
    public void findUserList() {
        User user = new User();
        user.setAge(20);
        user.setName("li");
        Example example = Example.of(user);
        List all = repository.findAll(example);
        System.out.println(all);
    }

    @Test
    public void findLikeUserList() {
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                .withIgnoreCase(true);
        User user = new User();
        user.setAge(20);
        user.setName("l");
        Example example = Example.of(user, matcher);
        List all = repository.findAll(example);
        System.out.println(all);
    }

    @Test
    public void findPageUserList() {
        int pageNo = 0;
        int pageSize = 3;
        Pageable pageRequest = PageRequest.of(pageNo, pageSize);

        User user = new User();
        user.setName("li");
        Example example = Example.of(user);
        Page<User> all = repository.findAll(example, pageRequest);
        System.out.println(all);
    }

    @Test
    public void updateUser() {
        User user = repository.findById("642374453fb3160f09c75d78").get();
        user.setName("lili");
        user.setAge(30);
        user.setEmail("lili@qq.com");
        User save = repository.save(user);
        System.out.println(save);
    }

    @Test
    public void deleteUser() {
        repository.deleteById("642374453fb3160f09c75d78");
    }
}
