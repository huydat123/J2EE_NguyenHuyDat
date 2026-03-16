package com.example.KTGiuaKy_NguyenHuyDat.controller;

import com.example.KTGiuaKy_NguyenHuyDat.entity.Course;
import com.example.KTGiuaKy_NguyenHuyDat.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    CourseRepository courseRepo;

    @GetMapping("/courses")
    public String list(Model model){

        model.addAttribute("courses",courseRepo.findAll());

        return "admin-course";
    }

    @GetMapping("/create")
    public String create(Model model){

        model.addAttribute("course", new Course());

        return "course-form";
    }

    @PostMapping("/save")
    public String save(Course course){

        courseRepo.save(course);

        return "redirect:/admin/courses";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id){

        courseRepo.deleteById(id);

        return "redirect:/admin/courses";
    }
    // EDIT PAGE
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model){

        Course course = courseRepo.findById(id).orElse(null);

        model.addAttribute("course",course);

        return "course-form";
    }

}
