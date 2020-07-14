package com.intecsec.java.tx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class BookShopDaoImpl implements BookShopDao {
	
	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public double getBookPrice(int bookId) {
		String sql = "select * from book where book_id=?";
		RowMapper<BookEntity> rowMapper = new BeanPropertyRowMapper<>(BookEntity.class);
		BookEntity bookEntity = jdbcTemplate.queryForObject(sql, rowMapper, bookId);
		return bookEntity.getPrice();
	}

	@Override
	public void updateBookStop(int bookId, int number) {
		String sql2 = "select number from book where book_id=?";
		int stock = jdbcTemplate.queryForObject(sql2, Integer.class, bookId);
		if(stock < number) {
			throw new BookStockException("库存不足");
		}
		
		String sql="update book set number=number-? WHERE book_id=?";
		jdbcTemplate.update(sql, number, bookId);
	}

	@Override
	public void updateUserAccount(double money, int uid) {
		String sql2 = "select balance from user where uid=?";
		double balance = jdbcTemplate.queryForObject(sql2, Double.class, uid);
		if(balance <= money) {
			throw new UserAccountException("余额不足");
		}
		
		
		String sql="update user set balance=balance-? WHERE uid=?";
		jdbcTemplate.update(sql, money, uid);
	}

}
