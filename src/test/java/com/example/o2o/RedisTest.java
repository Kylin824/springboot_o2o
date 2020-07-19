package com.example.o2o;

import com.example.o2o.entity.Area;
import com.example.o2o.util.RedisUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author kylin
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {

    @Autowired
    private RedisUtils redisUtils;

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");


    @Test
    public void testRedisUtil() {
        System.out.println(redisUtils.set("ky", "sysu"));
        System.out.println(redisUtils.get("sandy"));

        Area area = new Area();
        area.setAreaId(1);
        area.setAreaName("GZ");
//        area.setCreateTime(new Date());
        area.setLastEditTime(new Date());
        area.setPriority(1);

        System.out.println(redisUtils.set("area1", area));
        System.out.println(redisUtils.get("area1"));
    }
}
