package com.example.DTO;

import com.example.model.CategoryModel;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
public class VoucherDTO {
    private String code;
    private String name;
    private String description;
    private Integer discountValue;
    private Integer maxDiscount;
    private Integer pointsRequired;
    private LocalDateTime validityStart = LocalDateTime.now();
    private LocalDateTime validityEnd;
    private Integer voucherStatusId;
    private Integer adminId;
    private Set<CategoryModel> categoryModels;
}
