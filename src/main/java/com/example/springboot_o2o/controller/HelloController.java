package com.example.springboot_o2o.controller;

import jdk.nashorn.internal.parser.JSONParser;
import org.springframework.web.bind.annotation.*;

/**
 * @author kylin
 * @create 2020/6/4 14:21
 */

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello(@RequestParam("name")String name) {
        return "hello " + name;
    }
}
