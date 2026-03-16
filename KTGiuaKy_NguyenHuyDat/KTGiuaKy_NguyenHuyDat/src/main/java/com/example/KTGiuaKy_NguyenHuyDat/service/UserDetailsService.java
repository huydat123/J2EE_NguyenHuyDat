//package com.example.KTGiuaKy_NguyenHuyDat.service;
//
//import com.example.KTGiuaKy_NguyenHuyDat.entity.Student;
//import com.example.KTGiuaKy_NguyenHuyDat.repository.StudentRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.*;
//import org.springframework.stereotype.Service;
//import java.util.Collections;
//
//@Service
//public class StudentDetailsService implements UserDetailsService {
//
//    @Autowired
//    StudentRepository studentRepo;
//
//    @Override
//    public UserDetails loadUserByUsername(String username)
//            throws UsernameNotFoundException {
//
//        Student student = studentRepo.findByUsername(username);
//
//        if(student == null){
//            throw new UsernameNotFoundException("User not found");
//        }
//
//        return new User(
//                student.getUsername(),
//                student.getPassword(),
//                Collections.emptyList()
//        );
//    }
//}