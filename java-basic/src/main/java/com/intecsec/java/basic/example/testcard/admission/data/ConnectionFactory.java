package com.intecsec.java.basic.example.testcard.admission.data;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;


public class ConnectionFactory{

	
	private static DruidDataSource dataSource = null;
	/**
	 * 初始化连接池
	 * @throws Exception
	 */
	public static void init() throws Exception {
		Properties properties = new Properties();
		
		InputStream in = ConnectionFactory.class.getClassLoader().getResourceAsStream("druid.properties");  
		properties.load(in); 		
		dataSource = (DruidDataSource)DruidDataSourceFactory.createDataSource(properties);		
		
		in.close();
	}
	/**
	 * 获取链接
	 * @return
	 * @throws Exception
	 */
	public static Connection getConnection() throws Exception {
		if(null == dataSource)
		{
			init();
		}
        return dataSource.getConnection();
    }    
}
