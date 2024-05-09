package com.example.service;

import com.example.model.CategoryModel;

import java.util.List;

public interface ICategoryService {
    List<CategoryModel> findAll();
    List<CategoryModel> findByName(String name);

    CategoryModel findOneById(Integer id);


}
