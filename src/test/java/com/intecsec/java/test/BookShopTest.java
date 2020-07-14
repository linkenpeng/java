package com.intecsec.java.test;

import com.intecsec.java.tx.BookShopService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:transaction.xml"})
public class BookShopTest {

	@Autowired
    BookShopService bookShopService;

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Test
	public void testPurchase() {
		bookShopService.purchase();
	}
}
