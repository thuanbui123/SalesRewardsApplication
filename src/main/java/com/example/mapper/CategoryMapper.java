package com.example.mapper;

import com.example.entity.CategoryEntity;
import com.example.model.CategoryModel;

public class CategoryMapper {
    public static CategoryModel mapToModel (CategoryEntity categoryEntity) {
        CategoryModel categoryModel = new CategoryModel();

        categoryModel.setId(categoryEntity.getId());
        categoryModel.setName(categoryEntity.getName());
        categoryModel.setDescription(categoryEntity.getDescription());

        return categoryModel;
    }

    public static CategoryEntity mapToEntity (CategoryModel categoryModel) {
        CategoryEntity categoryEntity = new CategoryEntity();

        categoryEntity.setName(categoryModel.getName());
        categoryEntity.setDescription(categoryModel.getDescription());

        return categoryEntity;
    }

    public static CategoryEntity mapModelToEntity(CategoryModel categoryModel) {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setId(categoryModel.getId());
        categoryEntity.setName(categoryModel.getName());
        categoryEntity.setDescription(categoryModel.getDescription());

        return categoryEntity;
    }
}
