package com.example.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AccountModel {
    private Integer id;
    private String email;
    private String name;
    private String password;
    private String role;
    private String lastLogin;
    private Boolean isActive;
    private LocalDateTime createdAt = LocalDateTime.now();
}
