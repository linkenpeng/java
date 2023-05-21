package com.intecsec.java.basic.io.json;


import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 采用org.json包来解析JSON
 * @author Tom
 *
 */

public class OrgJsonTest {
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
    	
    	
    	//构造JSONObject对象
    	JSONObject obj = new JSONObject();
    	
        //string
    	obj.put("name", p.getName());
        //int
    	obj.put("age", p.getAge());
        //array
        obj.put("scores", p.getScores());
        //null
        //object.put("null", null);
        System.out.println(obj);        
        
        System.out.println("name: " + obj.getString("name"));
        System.out.println("age: " + obj.getInt("age"));
        System.out.println("scores: " + obj.getJSONArray("scores"));
    }

    public static void testJsonFile() {
    	File file = new File("java-basic/json/books.json");
        try (FileReader reader = new FileReader(file)) {
        	//读取文件内容到JsonObject对象中
            int fileLen = (int) file.length();
            char[] chars = new char[fileLen];
            reader.read(chars);
            String s = String.valueOf(chars);
            JSONObject jsonObject = new JSONObject(s);
            
            //开始解析JSONObject对象
            JSONArray books = jsonObject.getJSONArray("books");
            List<Book> bookList = new ArrayList<>();
            for (Object book : books) {
            	//获取单个JSONObject对象
                JSONObject bookObject = (JSONObject) book;
                Book book1 = new Book();
                book1.setAuthor(bookObject.getString("author"));
                book1.setYear(bookObject.getString("year"));
                book1.setTitle(bookObject.getString("title"));
                book1.setPrice(bookObject.getInt("price"));
                book1.setCategory(bookObject.getString("category"));
                bookList.add(book1);
            }
            
            for(Book book:bookList)
            {
            	System.out.println(book.getAuthor() + ",  " + book.getTitle());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
