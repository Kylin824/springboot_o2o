package com.example.springboot_o2o;

import com.example.springboot_o2o.util.RedisUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author kylin
 * @create 2020/6/4 14:40
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {

    @Autowired
    private RedisUtils redisUtils;

    @Test
    public void testRedisUtil() {
        System.out.println(redisUtils.set("ky", "sysu"));
        System.out.println(redisUtils.get("sandy"));
    }
}
