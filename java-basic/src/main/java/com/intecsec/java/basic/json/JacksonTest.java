package com.intecsec.java.basic.json;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.reflect.TypeToken;

/**
 * 采用Jackson来处理JSON
 * @author Tom
 *
 */

public class JacksonTest {

	public static void main(String[] args) throws Exception {
		testJsonObject();
		System.out.println("=========华丽丽的分割线==============");
		testJsonFile();
	}
	
	static void testJsonObject() throws IOException {
		ObjectMapper om = new ObjectMapper();
		
		//构造对象
    	Person p = new Person();
    	p.setName("Tom");
    	p.setAge(20);
    	p.setScores(Arrays.asList(60,70,80));
    	
    	//将对象解析为json字符串
		String jsonStr = om.writeValueAsString(p);
		System.out.println(jsonStr);
		
		//从json字符串重构对象
		Person p2 = om.readValue(jsonStr, Person.class);
		System.out.println(p2.getName());
		System.out.println(p2.getAge());
		System.out.println(p2.getScores());
		
		//从json字符串重构为JsonNode对象
		JsonNode node = om.readTree(jsonStr);
		System.out.println(node.get("name").asText());
		System.out.println(node.get("age").asText());
		System.out.println(node.get("scores"));		
	}
	
	static void testJsonFile() throws IOException {
		ObjectMapper om = new ObjectMapper();
		
		//从json文件中加载，并重构为java对象
		File json2 = new File("java-basic/json/books2.json");
		List<Book> books = om.readValue(json2, new TypeReference<List<Book>>(){});
		for (Book book : books) {
			System.out.println(book.getAuthor());
			System.out.println(book.getTitle());
		}
	}	
}


