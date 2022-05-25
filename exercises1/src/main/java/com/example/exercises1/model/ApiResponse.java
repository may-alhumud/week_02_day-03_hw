package com.example.exercises1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.validation.FieldError;

@AllArgsConstructor @Data
public class ApiResponse {
    private String message;
    private Integer statuse;
    private FieldError filedError;
}
