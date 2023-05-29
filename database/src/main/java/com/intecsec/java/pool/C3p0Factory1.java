package com.intecsec.java.pool;

import java.sql.Connection;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class C3p0Factory1 {
	
	private static ComboPooledDataSource dataSource = null;

	public static void init() throws Exception {
		
		dataSource = new ComboPooledDataSource();
		dataSource.setDriverClass( "com.mysql.jdbc.Driver" );            
		dataSource.setJdbcUrl( "jdbc:mysql://localhost:3306/test" );
		dataSource.setUser("root");                                  
		dataSource.setPassword("123456");                                  
			
		// the settings below are optional -- c3p0 can work with defaults
		dataSource.setMinPoolSize(5);                                     
		dataSource.setAcquireIncrement(5);
		dataSource.setMaxPoolSize(20);
			
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
