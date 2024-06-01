package com.example.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "points_transactions")
@Data
public class PointsTransactionsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "points")
    private Integer points;

    @Column(name = "transaction_date")
    private LocalDateTime transactionDate;

    @Column(name = "expiration_date")
    private LocalDateTime expirationDate;

    @Column(name = "is_used")
    private Boolean isUsed;

    @PrePersist
    protected void onCreate() {
        transactionDate = LocalDateTime.now();
    }
}
