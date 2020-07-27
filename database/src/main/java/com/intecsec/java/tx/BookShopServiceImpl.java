package com.intecsec.java.tx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("bookService")
public class BookShopServiceImpl implements BookShopService {
	
	@Autowired
	private BookShopDao bookShopDao;
	
	// 添加事务注解
	@Transactional
	@Override
	public void purchase() {
		int bookId = 1001;
		int number = 3;
		int uid = 1;
		// 1. 获取书本价格
		double price = bookShopDao.getBookPrice(bookId);
		// 2. 扣减库存
		bookShopDao.updateBookStop(bookId, number);
		double money = price * number;
		// 3. 扣减账户余额
		bookShopDao.updateUserAccount(money, uid);
	}

}
