package com.example.o2o.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class HelloController {

    @GetMapping("/hello")
    public String hello(@RequestParam("name")String name) {
//        return "hello " + name;
        return "redirect:http://www.baidu.com";
    }

    public static void main(String[] args) {
        System.out.println(HelloController.class.getResource(""));
        System.out.println(HelloController.class.getResource("/"));
    }
}
