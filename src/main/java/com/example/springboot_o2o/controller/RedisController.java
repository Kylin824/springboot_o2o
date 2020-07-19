package com.example.springboot_o2o.controller;

import com.example.springboot_o2o.util.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/redis")
public class RedisController {

    @Autowired
    private RedisUtils redisUtil;

    @GetMapping("/set")
    public boolean set(@RequestParam("key") String key,
                       @RequestParam("value") String value){
        return redisUtil.set(key,value);
    }

    @GetMapping("/get")
    public Object redisget(@RequestParam("key") String key){
        return redisUtil.get(key);
    }
}
