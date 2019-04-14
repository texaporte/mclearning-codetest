package com.mondido.mclearning.demo.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HomeController {

    @GetMapping("/api/timeofday")
    public String getTimeOfDay() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date());
    }

    @GetMapping("/")
    public String home(){
        return "Hello World!";
    }
    
}