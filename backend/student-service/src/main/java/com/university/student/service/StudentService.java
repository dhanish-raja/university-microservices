package com.university.student_service.service;

import org.springframework.data.domain.Page;
import com.university.student_service.dto.*;

public interface StudentService {

    StudentResponse createStudent(StudentRequest request);

    StudentResponse getStudentById(Long id);

    Page<StudentResponse> getAllStudents(int page, int size);

    StudentResponse updateStudent(Long id, StudentRequest request);

    void deleteStudent(Long id);
}
