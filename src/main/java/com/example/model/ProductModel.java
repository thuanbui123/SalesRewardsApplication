package com.example.model;

import lombok.Data;

@Data
public class ProductModel {
    private Integer id;
    private String name;
    private String description;
    private Integer price;
    private Integer stockQuantity;
    private Integer rewardPercentage;
    private String nameCategory;
}
