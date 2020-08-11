import com.intecsec.java.springboot.Application;
import com.intecsec.java.springboot.starter.UserInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author peter.peng
 * @date 2020/7/30
 */
@SpringBootTest(classes = Application.class)
public class UserInfoTest {

	@Autowired
	private UserInfo userInfo;

	@Test
	public void testStarter() {
		userInfo.print();
	}
}
