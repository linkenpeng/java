import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static java.time.Duration.ofSeconds;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Peter.Peng
 * @date 2021/1/21
 */
public class Junit5Test {

	@BeforeAll
	public static void init() {
		System.out.println("初始化数据");
	}

	@AfterAll
	public static void cleanup() {
		System.out.println("清理数据");
	}

	@BeforeEach
	public void tearup() {
		System.out.println("当前测试方法开始");
	}

	@AfterEach
	public void tearDown() {
		System.out.println("当前测试方法结束");
	}

	@DisplayName("我的第一个测试")
	@Test
	void testFirstTest() {
		System.out.println("我的第一个测试开始测试");
	}

	@DisplayName("我的第二个测试")
	@Test
	void testSecondTest() {
		System.out.println("我的第二个测试开始测试");
	}


	// assertTrue`与`assertFalse`用来判断条件是否为`true`或`false
	@Test
	@DisplayName("测试断言equals")
	void testEquals() {
		assertTrue(3 < 4);
	}

	// assertNull`与`assertNotNull`用来判断条件是否为·`null
	@Test
	@DisplayName("测试断言NotNull")
	void testNotNull() {
		assertNotNull(new Object());
	}


	@Test
	@DisplayName("测试断言抛异常")
	void testThrows() {
		ArithmeticException arithExcep = assertThrows(ArithmeticException.class, () -> {
			int m = 5/0;
		});
		assertEquals("/ by zero", arithExcep.getMessage());
	}

	@Test
	@DisplayName("测试断言超时")
	void testTimeOut() {
		String actualResult = assertTimeout(ofSeconds(2), () -> {
			Thread.sleep(3000);
			return "a result";
		});
		System.out.println(actualResult);
	}

	@Test
	@DisplayName("测试组合断言")
	void testAll() {
		assertAll("测试item商品下单",
				() -> {
					//模拟用户余额扣减
					assertTrue(1 < 2, "余额不足");
				},
				() -> {
					//模拟item数据库扣减库存
					assertTrue(3 < 4);
				},
				() -> {
					//模拟交易流水落库
					assertNotNull(new Object());
				}
		);
	}

	@RepeatedTest(3)
	@DisplayName("重复测试")
	void repeatedTest() {
		System.out.println(System.currentTimeMillis() + " 调用");
	}


	@ParameterizedTest
	@ValueSource(ints = {1, 2, 3, 4})
	@DisplayName("参数化测试")
	void paramTest(int a) {
		assertTrue(a > 0 && a < 4);
	}


	@Nested
	@DisplayName("内嵌订单测试")
	class OrderTestClas {
		@Test
		@DisplayName("取消订单")
		void cancelOrder() {
			int status = -1;
			System.out.println("取消订单成功,订单状态为:"+status);
		}
	}
}
