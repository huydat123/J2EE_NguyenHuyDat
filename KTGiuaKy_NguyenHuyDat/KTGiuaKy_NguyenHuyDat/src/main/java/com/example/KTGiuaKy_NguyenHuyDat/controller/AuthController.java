package com.example.KTGiuaKy_NguyenHuyDat.controller;

import com.example.KTGiuaKy_NguyenHuyDat.entity.Student;
import com.example.KTGiuaKy_NguyenHuyDat.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    @Autowired
    StudentRepository studentRepo;

    @GetMapping("/register")
    public String register(Model model){

        model.addAttribute("student", new Student());

        return "register";
    }

    @PostMapping("/register")
    public String save(Student student){

        studentRepo.save(student);

        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login(){

        return "login";
    }
}