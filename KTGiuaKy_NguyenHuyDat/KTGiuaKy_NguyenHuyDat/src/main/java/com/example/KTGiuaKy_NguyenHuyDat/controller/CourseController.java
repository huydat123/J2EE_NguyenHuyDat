package com.example.KTGiuaKy_NguyenHuyDat.controller;

import com.example.KTGiuaKy_NguyenHuyDat.entity.Course;
import com.example.KTGiuaKy_NguyenHuyDat.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;
@Controller
public class CourseController {

    @Autowired
    CourseRepository courseRepo;

    @GetMapping("/home")
    public String home(Model model,
                       @RequestParam(defaultValue = "0") int page){

        Page<Course> courses =
                courseRepo.findAll(PageRequest.of(page,5));

        model.addAttribute("courses",courses);

        return "home";
    }
}
