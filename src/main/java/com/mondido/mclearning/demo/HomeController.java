package com.mondido.mclearning.demo;

import java.util.Date;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HomeController {

    @GetMapping("/asdf")
    public String getTimeOfDay() {
        return String.valueOf(new Date().getTime());
    }

    @GetMapping("/")
    public String home(){
        return "Hello World!";
    }
    
}