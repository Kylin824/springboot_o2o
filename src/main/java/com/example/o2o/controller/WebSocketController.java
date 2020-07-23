package com.example.o2o.controller;

import com.example.o2o.service.WebSocketServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class WebSocketController {

    @Autowired
    private WebSocketServer webSocketServer;

    @GetMapping("/webSocket")
    public ModelAndView socket() {
        ModelAndView mav = new ModelAndView("/webSocket");
        return mav;
    }
}
