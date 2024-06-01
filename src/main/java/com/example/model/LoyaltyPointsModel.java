package com.example.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class LoyaltyPointsModel {
    private Integer id;
    private Integer pointsBalance;
    private LocalDateTime lastUpdated;
    private AccountModel accountModel;
}
