package com.ker.springboot;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/debug")
public class Debug {

    @GetMapping("/getString")
    public String returnString(){
        return  "test";
    }
}

