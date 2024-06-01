package com.example.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserPointsSummaryResponse {
    private Integer userId;
    private String username;
    private String email;
    private Integer totalPoint;
    private Integer usedPoints;
    private Integer expiringPoints;
    private String expiryDate;
}
