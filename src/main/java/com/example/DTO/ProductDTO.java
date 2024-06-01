package com.example.DTO;

import lombok.Data;

@Data
public class ProductDTO {
    private String name;
    private String description;
    private Integer price;
    private Integer stockQuantity;
    private Integer rewardPercentage;
    private Integer categoryId;
}
