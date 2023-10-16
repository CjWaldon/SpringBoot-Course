package com.springboot.mycoolapp.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FunRestController {
    // expose "/" that returns "Hellow World!"
    @GetMapping("/")
    public String sayHello(){
        return "Hello World!";
    }
}
