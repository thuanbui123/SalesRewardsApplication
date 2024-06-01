package com.example.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "vouchers")
@Data
public class VoucherEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "code", length = 50)
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "discount_value")
    private Integer discountValue;

    @Column(name = "max_discount")
    private Integer maxDiscount;

    @Column(name = "points_required")
    private Integer pointsRequired;

    @Column(name = "validity_start")
    private LocalDateTime validityStart;

    @Column(name = "validity_end")
    private LocalDateTime validityEnd;

    @OneToOne
    @JoinColumn(name = "voucher_status_id")
    private VoucherStatusEntity voucherStatusEntity;

    @ManyToOne
    @JoinColumn(name = "admin_id")
    @JsonBackReference
    private AccountEntity accountEntity;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "voucher_category",
            joinColumns = @JoinColumn(name = "voucher_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<CategoryEntity> categoryEntities = new HashSet<>();

}
