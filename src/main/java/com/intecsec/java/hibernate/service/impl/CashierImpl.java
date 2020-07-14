package com.intecsec.java.hibernate.service.impl;

import java.util.List;

import com.intecsec.java.hibernate.service.BookShopService;
import com.intecsec.java.hibernate.service.Cashier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("cashier")
public class CashierImpl implements Cashier {
	
	@Autowired
	private BookShopService bookShopService;

	@Override
	public void checkout(String username, List<String> isbns) {
		for(String isbn:isbns) {
			bookShopService.purchase(username, isbn);
		}
	}

}
