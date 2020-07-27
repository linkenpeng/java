package test;

import com.intecsec.java.hibernate.entities.Book;
import com.intecsec.java.hibernate.service.BookShopService;
import com.intecsec.java.hibernate.service.Cashier;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:beans-hibernate.xml" })
public class HibernateTest {

	@Autowired
	private BookShopService bookShopService;

	@Autowired
	private Cashier cashier;

	private ApplicationContext context = null;

	@Test
	public void testDataSource() throws SQLException {
		context = new ClassPathXmlApplicationContext("applicationContext.xml");
		System.out.println(context);

		DataSource dataSource = context.getBean(DataSource.class);
		System.out.println(dataSource.getConnection().toString());

		SessionFactory sessionFactory = context.getBean(SessionFactory.class);
		System.out.println(sessionFactory);

		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();

		Book book = new Book("HeadFirst", "1004", 9800, 10);
		session.save(book);

		tx.commit();
		session.close();
	}

	@Test
	public void testCashier() {
		String username = "linken";
		List<String> isbns = Arrays.asList("1001", "1000");
		cashier.checkout(username, isbns);
	}

	@Test
	public void testBookShopService() {
		String username = "linken";
		String isbn = "1000";
		bookShopService.purchase(username, isbn);
	}
}
