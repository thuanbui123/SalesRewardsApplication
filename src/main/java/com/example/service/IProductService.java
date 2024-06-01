package com.example.service;

import com.example.DTO.ProductDTO;
import com.example.model.ProductModel;

import java.util.List;

public interface IProductService {
    List<ProductModel> findAll();
    List<ProductModel> findByName(String name);

    int addProduct(ProductDTO productDTO);
    int updateProduct(ProductDTO productDTO, Integer id);
    int deleteProduct(Integer id);
}
