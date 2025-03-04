package com.example.demo.model;

import lombok.Data;

@Data
public class User {
    private Long id;
    private String username;
    private String password;
    private String role; // ADMIN, TEACHER, STUDENT
    private String email;
    private String phone;
    private Boolean status;
}