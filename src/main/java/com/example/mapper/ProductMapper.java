package com.example.mapper;

import com.example.DTO.ProductDTO;
import com.example.entity.ProductEntity;
import com.example.model.ProductModel;
import com.example.service.impl.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    private static CategoryService categoryService;

    public ProductMapper(){}

    @Autowired
    public ProductMapper(CategoryService categoryService) {
        ProductMapper.categoryService = categoryService;
    }

    public static ProductEntity mapDtoToEntity (ProductDTO productDTO) {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setName(productDTO.getName());
        productEntity.setDescription(productDTO.getDescription());
        productEntity.setPrice(productDTO.getPrice());
        productEntity.setRewardPercentage(productDTO.getRewardPercentage());
        productEntity.setStockQuantity(productDTO.getStockQuantity());
        productEntity.setCategory(categoryService.findOneById(productDTO.getCategoryId()));
        return productEntity;
    }

    public static ProductModel mapToModel (ProductEntity productEntity) {
        ProductModel productModel = new ProductModel();
        productModel.setId(productEntity.getId());
        productModel.setName(productEntity.getName());
        productModel.setDescription(productEntity.getDescription());
        productModel.setPrice(productEntity.getPrice());
        productModel.setStockQuantity(productEntity.getStockQuantity());
        productModel.setRewardPercentage(productEntity.getRewardPercentage());
        productModel.setNameCategory(productEntity.getCategory().getName());
        return productModel;
    }
}
