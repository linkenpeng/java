package com.intecsec.java.pool;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;


public class DruidFactory2 {
	private static DruidDataSource dataSource = null;

	public static void init() throws Exception {
		Properties properties = new Properties();
		
		InputStream in = DruidFactory2.class.getClassLoader().getResourceAsStream("druid.properties");  
		properties.load(in); 		
		dataSource = (DruidDataSource)DruidDataSourceFactory.createDataSource(properties);		
		
		in.close();
	}
	
	public static Connection getConnection() throws Exception {
		if(null == dataSource)
		{
			init();
		}
        return dataSource.getConnection();
    }
}
