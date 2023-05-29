package com.intecsec.java.pool;

import java.sql.Connection;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class C3p0Factory2 {
	
	private static ComboPooledDataSource dataSource = null;

	public static void init() throws Exception {
		
		dataSource = new ComboPooledDataSource();
		//dataSource 自动加载c3p0-config.xml文件	
		
		// The DataSource dataSource is now a fully configured and usable pooled DataSource

	}
	
	public static Connection getConnection() throws Exception {
		if(null == dataSource)
		{
			init();
		}
        return dataSource.getConnection();
    }
}
