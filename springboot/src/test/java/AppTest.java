import com.intecsec.java.springboot.Application;
import com.intecsec.java.springboot.starter.UserInfo;
import com.intecsec.java.springboot.thread.CompletableFutureParallel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @author peter.peng
 * @date 2020/7/30
 */
@SpringBootTest(classes = Application.class)
public class AppTest {

	@Resource
	private UserInfo userInfo;

	@Resource
	private CompletableFutureParallel completableFutureParallel;

	@Test
	public void testStarter() {
		userInfo.print();
	}

	@Test
	public void testCompletableFutureParallel() {
		completableFutureParallel.simpleParallel();
	}

	@Test
	public void testForkJoinPool() {
		completableFutureParallel.printForkJoinPool();
	}

}
