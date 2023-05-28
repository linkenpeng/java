package com.intecsec.java.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;



public class InsertTest {
	
	public static void main(String[] a)
	{
		//concatInsertBook();
		//unsafeConcatInsertBook();
		//safeInsertBook();	
		batchInsertBook();
	}

	public static void concatInsertBook()
	{
		//构建Java和数据库之间的桥梁介质
        try{            
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("注册驱动成功!");
        }catch(ClassNotFoundException e1){
            System.out.println("注册驱动失败!");
            e1.printStackTrace();
        }
        
        String url="jdbc:mysql://localhost:3306/test";        
        Connection conn = null;
        try {
        	//构建Java和数据库之间的桥梁：URL，用户名，密码
            conn = DriverManager.getConnection(url, "root", "123456");
            
            //构建数据库执行者
            Statement stmt = conn.createStatement(); 
            System.out.println("创建Statement成功！");      
            
            //执行SQL语句
            int bookid = 10;
            String bookName = "Effective Java";
            int price = 50;
            
            //values(1, 'Effective Java', 50)
            String sql = "insert into t_book(bookid,bookname,price) values(" 
               + bookid + ", '" + bookName + "', " + price + ")";
            int result = stmt.executeUpdate(sql);
                        
            stmt.close();
            
            System.out.println("操作成功");
            
        } catch (SQLException e){
            e.printStackTrace();
        }
        finally
        {
        	try
        	{
        		if(null != conn)
        		{
            		conn.close();
            	}
        	}
        	catch (SQLException e){
                e.printStackTrace();
        	}        	
        }
    }
	
	public static void unsafeConcatInsertBook()
	{
		//构建Java和数据库之间的桥梁介质
        try{            
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("注册驱动成功!");
        }catch(ClassNotFoundException e1){
            System.out.println("注册驱动失败!");
            e1.printStackTrace();
        }
        
        String url="jdbc:mysql://localhost:3306/test?allowMultiQueries=true";        
        Connection conn = null;
        try {
        	//构建Java和数据库之间的桥梁：URL，用户名，密码
            conn = DriverManager.getConnection(url, "root", "123456");
            
            //构建数据库执行者
            Statement stmt = conn.createStatement(); 
            System.out.println("创建Statement成功！");      
            
            //执行SQL语句
            int bookid = 10;
            String bookName = "Effective Java',50);delete from t_book;insert into t_book values(101, 'faked book";
            int price = 50;
            
            //values(1, 'Effective Java', 50)
            String sql = "insert into t_book(bookid,bookname,price) values(" 
               + bookid + ", '" + bookName + "', " + price + ");";
            System.out.println(sql);
            int result = stmt.executeUpdate(sql);
                        
            stmt.close();
            
            System.out.println("操作成功");
            
        } catch (SQLException e){
            e.printStackTrace();
        }
        finally
        {
        	try
        	{
        		if(null != conn)
        		{
            		conn.close();
            	}
        	}
        	catch (SQLException e){
                e.printStackTrace();
        	}        	
        }
    }


	public static void safeInsertBook()
	{
		//构建Java和数据库之间的桥梁介质
        try{            
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("注册驱动成功!");
        }catch(ClassNotFoundException e1){
            System.out.println("注册驱动失败!");
            e1.printStackTrace();
        }
        
        String url="jdbc:mysql://localhost:3306/test?allowMultiQueries=true";        
        Connection conn = null;
        try {
        	//构建Java和数据库之间的桥梁：URL，用户名，密码
            conn = DriverManager.getConnection(url, "root", "123456");
            
            String sql = "insert into t_book(bookid,bookname,price) values(?,?,?)";
            
            //构建数据库执行者
            PreparedStatement pstmt = conn.prepareStatement(sql);
            
            //执行SQL语句
            int bookid = 10;
            String bookName = "Effective Java',50);delete from t_book;insert into t_book values(101, 'faked book";
            int price = 50;
            
            //values(1, 'Effective Java', 50)
            pstmt.setInt(1, bookid);
            pstmt.setString(2, bookName);
            pstmt.setInt(3, price);
            
            int result = pstmt.executeUpdate();
                        
            pstmt.close();
            
            System.out.println("操作成功");
            
        } catch (SQLException e){
            e.printStackTrace();
        }
        finally
        {
        	try
        	{
        		if(null != conn)
        		{
            		conn.close();
            	}
        	}
        	catch (SQLException e){
                e.printStackTrace();
        	}        	
        }
    }

	public static void batchInsertBook()
	{
		//构建Java和数据库之间的桥梁介质
        try{            
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("注册驱动成功!");
        }catch(ClassNotFoundException e1){
            System.out.println("注册驱动失败!");
            e1.printStackTrace();
        }
        
        String url="jdbc:mysql://localhost:3306/test?allowMultiQueries=true";        
        Connection conn = null;
        try {
        	//构建Java和数据库之间的桥梁：URL，用户名，密码
            conn = DriverManager.getConnection(url, "root", "123456");
            
            String sql = "insert into t_book(bookid,bookname,price) values(?,?,?)";
            
            //构建数据库执行者
            PreparedStatement pstmt = conn.prepareStatement(sql);
            
            //执行SQL语句
            
            String bookName = "aaaaaaaaaaaaaaaa";
            int price = 50;
            
            //values(1, 'Effective Java', 50)
            for(int i=200;i<210;i++)
            {
            	pstmt.setInt(1, i);
                pstmt.setString(2, bookName);
                pstmt.setInt(3, price);
                pstmt.addBatch();
            }            
            
            pstmt.executeBatch();
                        
            pstmt.close();
            
            System.out.println("操作成功");
            
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
        	try {
        		if(null != conn) {
            		conn.close();
            	}
        	}
        	catch (SQLException e){
                e.printStackTrace();
        	}        	
        }
    }
}


