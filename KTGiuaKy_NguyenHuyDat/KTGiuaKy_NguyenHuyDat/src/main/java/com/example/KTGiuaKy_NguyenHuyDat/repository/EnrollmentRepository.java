package com.example.KTGiuaKy_NguyenHuyDat.repository;

import com.example.KTGiuaKy_NguyenHuyDat.entity.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnrollmentRepository extends JpaRepository<Enrollment,Long> {

    List<Enrollment> findByStudentId(Long id);

}
