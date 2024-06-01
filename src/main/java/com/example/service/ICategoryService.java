package com.example.service;

import com.example.entity.CategoryEntity;
import com.example.model.CategoryModel;

import java.util.List;

public interface ICategoryService {
    List<CategoryModel> findAll();
    List<CategoryModel> findByName(String name);

    CategoryEntity findOneById(Integer id);

    int addCategory (CategoryModel categoryModel);

    int updateCategory (CategoryModel categoryModel, Integer id);

    int deleteCategory (Integer id);
}
