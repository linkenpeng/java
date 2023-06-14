package com.intecsec.java.basic.classloader;

import java.sql.*;

public class SelectTest {
	public static void main(String[] args) throws Exception {

		// 构建Java和数据库之间的桥梁介质
		Class.forName("com.mysql.jdbc.Driver");

		String url = "jdbc:mysql://localhost:3306/test";
		// 构建Java和数据库之间的桥梁：URL，用户名，密码
		Connection conn = DriverManager.getConnection(url, "root", "123456");

		// 构建数据库执行者
		Statement stmt = conn.createStatement();
		System.out.println("创建Statement成功！");

		// 执行SQL语句并返回结果到ResultSet
		ResultSet rs = stmt.executeQuery("select bookid, bookname, price from t_book order by bookid");

		// 开始遍历ResultSet数据
		while (rs.next()) {
			System.out.println(rs.getInt(1) + "," + rs.getString(2) + "," + rs.getInt("price"));
		}

		rs.close();
		stmt.close();
		conn.close();

	}
}