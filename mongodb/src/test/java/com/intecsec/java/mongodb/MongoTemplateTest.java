package com.intecsec.java.mongodb;

import com.intecsec.java.mongodb.entity.User;
import com.intecsec.java.util.JsonUtils;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import javax.annotation.Resource;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @description: MongoDbAppTest
 * @author: peter.peng
 * @create: 2023-03-26 21:47
 **/
@SpringBootTest
public class MongoTemplateTest {

    @Resource
    private MongoTemplate mongoTemplate;

    @Test
    public void create() {
        User user = new User();
        user.setAge(20);
        user.setName("li");
        user.setEmail("abc@qq.com");
        User insert = mongoTemplate.insert(user);
        System.out.println(JsonUtils.toJson(insert));
    }

    @Test
    public void findAll() {
        List<User> all = mongoTemplate.findAll(User.class);
        System.out.println(all);
    }

    @Test
    public void findId() {
        User byId = mongoTemplate.findById("642055458514680a623c27fe", User.class);
        System.out.println(byId);
    }

    @Test
    public void findUserList() {
        Query query = new Query(Criteria
                .where("name").is("li")
                .and("age").is(20));
        List<User> users = mongoTemplate.find(query, User.class);
        System.out.println(users);
    }

    @Test
    public void findLikeUserList() {
        String name = "li";
        String regex = String.format("%s%s%s", "^.*", name, ".*$");
        System.out.println(regex);
        Pattern compile = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Query query = new Query(Criteria.where("name").regex(compile));
        List<User> users = mongoTemplate.find(query, User.class);
        System.out.println(users);
    }

    @Test
    public void findPageUserList() {
        int pageNo = 1;
        int pageSize = 3;
        String name = "mon";
        String regex = String.format("%s%s%s", "^.*", name, ".*$");
        Pattern compile = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Query query = new Query(Criteria.where("name").regex(compile));

        long count = mongoTemplate.count(query, User.class);
        List<User> users = mongoTemplate.find(query
                .skip((pageNo - 1) * pageSize)
                .limit(pageSize), User.class);
        System.out.println(users);
    }

    @Test
    public void updateUser() {
        User user = mongoTemplate.findById("642055458514680a623c27fe", User.class);
        user.setName("lili");
        user.setAge(30);
        user.setEmail("lili@qq.com");

        Query query = new Query(Criteria
                .where("_id").is(user.getId()));
        Update update = new Update();
        update.set("name", user.getName());
        update.set("age", user.getAge());
        update.set("email", user.getEmail());
        UpdateResult updateResult = mongoTemplate.updateFirst(query, update, User.class);
        System.out.println(updateResult);
    }

    @Test
    public void deleteUser() {
        Query query = new Query(Criteria
                .where("_id").is("642055458514680a623c27fe"));
        DeleteResult deleteResult = mongoTemplate.remove(query, User.class);
        System.out.println(deleteResult.getDeletedCount());
    }
}
