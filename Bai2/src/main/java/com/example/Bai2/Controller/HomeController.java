package com.example.Bai2.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @GetMapping("Hello")
    public String xinChao(){
        return "Xin 5 chuc";
    }
}
