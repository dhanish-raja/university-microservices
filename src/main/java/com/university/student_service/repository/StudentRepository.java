package com.university.student_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.university.student_service.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

    boolean existsByEmail(String email);
}