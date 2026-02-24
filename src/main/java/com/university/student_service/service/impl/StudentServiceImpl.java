package com.university.student_service.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.*;

import com.university.student_service.entity.Student;
import com.university.student_service.repository.StudentRepository;
import com.university.student_service.service.StudentService;
import com.university.student_service.dto.*;
import com.university.student_service.exception.ResourceNotFoundException;

@Service
@RequiredArgsConstructor
@Transactional
public class StudentServiceImpl implements StudentService {

    private final StudentRepository repository;

    private StudentResponse mapToResponse(Student student) {
        return StudentResponse.builder()
                .id(student.getId())
                .name(student.getName())
                .email(student.getEmail())
                .age(student.getAge())
                .build();
    }

    @Override
    public StudentResponse createStudent(StudentRequest request) {

        if (repository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        Student student = Student.builder()
                .name(request.getName())
                .email(request.getEmail())
                .age(request.getAge())
                .build();

        return mapToResponse(repository.save(student));
    }

    @Override
    public StudentResponse getStudentById(Long id) {
        Student student = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));

        return mapToResponse(student);
    }

    @Override
    public Page<StudentResponse> getAllStudents(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return repository.findAll(pageable).map(this::mapToResponse);
    }

    @Override
    public StudentResponse updateStudent(Long id, StudentRequest request) {

        Student student = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));

        student.setName(request.getName());
        student.setEmail(request.getEmail());
        student.setAge(request.getAge());

        return mapToResponse(repository.save(student));
    }

    @Override
    public void deleteStudent(Long id) {
        Student student = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));

        repository.delete(student);
    }
}