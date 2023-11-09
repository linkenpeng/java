import com.intecsec.java.cache.localcache.MapCacheUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @description:
 * @author: peter.peng
 * @create: 2023-10-21 20:38
 **/
@Slf4j
public class LocalCacheTest {

    @Test
    public void testCM() {
        MapCacheUtil.setCache("a", "a");
        Object o = getCache();
        System.out.println(o);
    }

    private Object getCache() {
        return MapCacheUtil.getCache("a");
    }

}
