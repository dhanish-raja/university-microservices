package com.university.student_service.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class StudentResponse {

    private Long id;
    private String name;
    private String email;
    private int age;
}