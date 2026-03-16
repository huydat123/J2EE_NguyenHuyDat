package com.example.KTGiuaKy_NguyenHuyDat.repository;
import org.springframework.data.domain.Pageable;
import com.example.KTGiuaKy_NguyenHuyDat.entity.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course,Long> {

    Page<Course> findByNameContaining(String keyword, Pageable pageable);

}
