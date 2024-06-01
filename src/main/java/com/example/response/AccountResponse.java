package com.example.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AccountResponse {
    private Integer id;
    private String email;
    private String name;
    private String role;
    private String lastLogin;
    private Boolean isActive;
    private String createdAt;
}
