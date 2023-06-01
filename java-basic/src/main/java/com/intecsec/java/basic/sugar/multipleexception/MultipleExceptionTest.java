package com.intecsec.java.basic.sugar.multipleexception;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

public class MultipleExceptionTest {

	public static void main(String[] args) throws IOException {
		try
		{
			test();
		}
		catch(IOException ex)
		{
			//异常处理
		}
		catch(SQLException ex)
		{
			//异常处理
		}
		
		
		try
		{
			test();
		}
		catch(IOException | SQLException ex)
		{
			//JDK7开始，支持一个catch写多个异常
			//异常处理
		}
		
		try
		{
			test2();
		}
		// 异常不能有继承关系
		// catch(IOException | FileNotFoundException ex)
		catch(IOException e)
		{
			//JDK7开始，支持一个catch写多个异常
			//异常处理
		}

	}
	
	public static void test() throws IOException, SQLException {
    } 
	public static void test2() throws IOException, FileNotFoundException {
    } 
}
