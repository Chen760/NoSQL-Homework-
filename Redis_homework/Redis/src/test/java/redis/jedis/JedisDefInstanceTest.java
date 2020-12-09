package redis.jedis;

import com.bjtu.redis.jedis.JedisInstance;
import org.junit.Test;
import redis.clients.jedis.Jedis;

public class JedisDefInstanceTest {

    /**
     * 基本使用
     */
    @Test
    public void test() {
        Jedis jedis = JedisInstance.getInstance().getResource();
        jedis.setex("name", 20, "test");
        String val = jedis.get("name");
        System.out.println(val);
    }


}
