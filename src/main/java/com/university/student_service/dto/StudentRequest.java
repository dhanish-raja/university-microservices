package com.university.student_service.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentRequest {

    @NotBlank
    private String name;

    @Email
    @NotBlank
    private String email;

    @Min(3)
    private int age;
}