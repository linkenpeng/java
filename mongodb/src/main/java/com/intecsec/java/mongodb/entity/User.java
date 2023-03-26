package com.intecsec.java.mongodb.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @description:
 * @author: peter.peng
 * @create: 2023-03-26 21:43
 **/
@Data
@Document("User")
public class User {
    @Id
    private String id;
    private String name;
    private Integer age;
    private String email;
    private String createDate;
}
