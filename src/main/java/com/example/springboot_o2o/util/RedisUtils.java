package com.example.springboot_o2o.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author kylin
 * @create 2020/6/4 14:50
 */

@Component
public class RedisUtils {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * set
     * @param key key
     * @param value value
     * @return true false
     */
    public boolean set(String key, Object value) {
        try {
            redisTemplate.opsForValue().set(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * get
     * @param key key
     * @return value or null
     */
    public Object get(String key) {
        return key == null ? null : redisTemplate.opsForValue().get(key);
    }

    /**
     * set with expire time
     * @param key key
     * @param value value
     * @param time <0则无限期
     * @return true or false
     */
    public boolean set(String key, Object value, long time) {
        try {
            if (time > 0) {
                redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
            } else {
                redisTemplate.opsForValue().set(key, value);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * incrby
     * @param key key
     * @param increment increment
     * @return
     */
    public long incrby(String key, long increment) {
        if (increment < 0) {
            throw new RuntimeException("increment must be larger than zero");
        }
        return redisTemplate.opsForValue().increment(key, increment);
    }

}
