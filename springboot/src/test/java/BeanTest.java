import com.intecsec.java.springboot.component.BeanLife;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 * @description:
 * @author: peter.peng
 * @create: 2023-09-23 19:11
 **/
public class BeanTest {

    @Test
    public void testBeanLife() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        BeanLife beanLife = applicationContext.getBean(BeanLife.class);
        ((ClassPathXmlApplicationContext) applicationContext).close();
        beanLife.query();
    }
}
