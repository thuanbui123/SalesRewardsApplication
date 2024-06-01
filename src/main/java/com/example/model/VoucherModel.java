package com.example.model;

import lombok.Data;

import java.util.List;


@Data
public class VoucherModel {
    private Integer id;
    private String code;
    private String name;
    private String description;
    private Integer discountValue;
    private Integer maxDiscount;
    private Integer pointsRequired;
    private String validityStart;
    private String validityEnd;

    private String nameVoucherStatus;
}
