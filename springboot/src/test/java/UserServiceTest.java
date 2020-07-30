import com.intecsec.java.service.UserService;
import com.intecsec.java.springboot.Application;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author peter.peng
 * @date 2020/7/30
 */
@SpringBootTest(classes = Application.class)
public class UserServiceTest {

	@Autowired
	private UserService userService;

	@Test
	public void testStarter() {
		userService.print();
	}
}
