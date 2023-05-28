package com.intecsec.java.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;

public class TransactionTest {

	public static void main(String[] args) throws Exception {
		// 构建Java和数据库之间的桥梁介质
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("注册驱动成功!");
		} catch (ClassNotFoundException e1) {
			System.out.println("注册驱动失败!");
			e1.printStackTrace();
		}

		String url = "jdbc:mysql://localhost:3306/test";
		Connection conn = null;
		try {
			// 构建Java和数据库之间的桥梁：URL，用户名，密码
			conn = DriverManager.getConnection(url, "root", "123456");
			conn.setAutoCommit(false);

			insertBook(conn, "insert into t_book values(101, 'aaaa', 10)");
			insertBook(conn, "insert into t_book values(102, 'bbbb', 10)");
			insertBook(conn, "insert into t_book values(103, 'cccc', 10)");
			Savepoint phase1 = conn.setSavepoint(); //设置一个保存点
			insertBook(conn, "insert into t_book values(104, 'cccc', 10)");
			insertBook(conn, "insert into t_book values(105, 'cccc', 10)");
			conn.rollback(phase1);  //回滚到phase1保存点，即上面2行无效
			conn.commit();

			System.out.println("操作成功");

		} catch (SQLException e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			if (null != conn) {
				conn.close();
			}
		}
	}

	public static void insertBook(Connection conn, String sql) {
		try {
			// 构建数据库执行者
			Statement stmt = conn.createStatement();
			//System.out.print("创建Statement成功！");

			// 执行SQL语句
			int result = stmt.executeUpdate(sql);

			stmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
