package com.example.KTGiuaKy_NguyenHuyDat.controller;

import com.example.KTGiuaKy_NguyenHuyDat.entity.Enrollment;
import com.example.KTGiuaKy_NguyenHuyDat.repository.EnrollmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.security.core.Authentication;
import java.time.LocalDate;

@Controller
public class EnrollmentController {

    @Autowired
    EnrollmentRepository enrollRepo;

    @GetMapping("/enroll/{id}")
    public String enroll(@PathVariable Long id,
                         Authentication auth){

        Enrollment e = new Enrollment();

        e.setCourseId(id);

        e.setEnrollDate(LocalDate.now());

        enrollRepo.save(e);

        return "redirect:/home";
    }

}
