package com.university.student_service.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;

import jakarta.validation.Valid;
import com.university.student_service.service.StudentService;
import com.university.student_service.dto.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/students")

public class StudentController {

    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }


    @PostMapping
    public StudentResponse create(@Valid @RequestBody StudentRequest request) {
        return service.createStudent(request);
    }

    @GetMapping("/{id}")
    public StudentResponse getById(@PathVariable Long id) {
        return service.getStudentById(id);
    }

    @GetMapping
    public Page<StudentResponse> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {

        return service.getAllStudents(page, size);
    }

    @PutMapping("/{id}")
    public StudentResponse update(
            @PathVariable Long id,
            @Valid @RequestBody StudentRequest request) {

        return service.updateStudent(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteStudent(id);
    }
}