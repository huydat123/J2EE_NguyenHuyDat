package com.example.lab3.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String home(Model model) {
        // Truyền biến "message" với giá trị là chuỗi tiếng Việt
        model.addAttribute("message", "Xin chào Thymeleaf");
        return "index"; // Trả về file index.html
    }
}