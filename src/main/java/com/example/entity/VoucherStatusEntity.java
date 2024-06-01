package com.example.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "voucher_status")
@Data
public class VoucherStatusEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", length = 100)
    private String name;


}
