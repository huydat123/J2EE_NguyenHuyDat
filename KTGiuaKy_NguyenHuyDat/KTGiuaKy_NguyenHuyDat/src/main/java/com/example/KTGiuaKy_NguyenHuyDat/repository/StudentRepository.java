package com.example.KTGiuaKy_NguyenHuyDat.repository;

import com.example.KTGiuaKy_NguyenHuyDat.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Long> {

    Student findByUsername(String username);

}