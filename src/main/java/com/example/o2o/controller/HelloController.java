package com.example.o2o.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class HelloController {
    @GetMapping("/hello")
    public String hello(@RequestParam("name")String name) {
        return "hello " + name;
    }
}
