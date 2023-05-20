package com.intecsec.java.basic.json;

import java.io.File;
import java.io.FileReader;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

/**
 * 采用Google GSON来处理JSON
 * @author Tom
 *
 */
public class GsonTest {
	public static void main(String[] args) {
		testJsonObject();
		System.out.println("=========华丽丽的分割线==============");
		testJsonFile();
	}
	public static void testJsonObject() {
		//构造对象
    	Person p = new Person();
    	p.setName("Tom");
    	p.setAge(20);
    	p.setScores(Arrays.asList(60,70,80));
		
    	//从Java对象到JSON字符串
		Gson gson = new Gson();
		String s = gson.toJson(p);
		System.out.println(s); //{"name":"Tom","age":20,"scores":[60,70,80]}
		
		//从JSON字符串到Java对象
		Person p2 = gson.fromJson(s, Person.class);
		System.out.println(p2.getName());  //Tom
		System.out.println(p2.getAge());   //20
		System.out.println(p2.getScores());//[60, 70, 80]
		
		//调用GSON的JsonObject
		JsonObject json = gson.toJsonTree(p).getAsJsonObject(); //将整个json解析为一颗树
		System.out.println(json.get("name"));  //"Tom"
		System.out.println(json.get("age"));   //20
		System.out.println(json.get("scores"));//[60,70,80]
		
	}
	
	public static void testJsonFile() {
		Gson gson = new Gson();
		File file = new File("java-basic/json/books2.json");
		
        try (FileReader reader = new FileReader(file)) {
        	List<Book> books = gson.fromJson(reader, new TypeToken<List<Book>>(){}.getType());
            
        	for(Book book : books)
        	{
        		System.out.println(book.getAuthor() + ",  " + book.getTitle());
        	}
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
}
