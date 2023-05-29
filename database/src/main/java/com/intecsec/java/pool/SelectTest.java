package com.intecsec.java.pool;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectTest {
    public static void main(String[] args){    	
    	  
        Connection conn = null;
        try {
        	//从c3p0获取
            //conn = C3p0Factory1.getConnection();
            //conn = C3p0Factory2.getConnection();
            
            //从Druid获取
            //conn = DruidFactory1.getConnection();
            conn = DruidFactory2.getConnection();
            
            //构建数据库执行者
            Statement stmt = conn.createStatement();
            System.out.println("创建Statement成功！");      
            
            //执行SQL语句并返回结果到ResultSet
            ResultSet rs = stmt.executeQuery("select bookid, bookname, price from t_book order by bookid");
                        
            //开始遍历ResultSet数据
            while(rs.next())
            {
            	System.out.println(rs.getInt(1) + "," + rs.getString(2) + "," + rs.getInt("price"));
            }
            
            rs.close();
            stmt.close();
            
        } catch (Exception e){
            e.printStackTrace();
        } finally {
        	try	{
        		if(null != conn) {
            		conn.close();
            	}
        	} catch (SQLException e){
                e.printStackTrace();
        	}        	
        }
    }
}