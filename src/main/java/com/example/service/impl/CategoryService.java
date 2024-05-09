package com.example.service.impl;

import com.example.entity.CategoryEntity;
import com.example.mapper.CategoryMapper;
import com.example.model.CategoryModel;
import com.example.repository.CategoryRepository;
import com.example.service.ICategoryService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryService implements ICategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<CategoryModel> findAll() {
        List<CategoryEntity> categoryEntityList = categoryRepository.findAll();
        if (categoryEntityList == null || categoryEntityList.isEmpty()) {
            return null;
        }

        List<CategoryModel> categoryModelList = new ArrayList<>();

        for (CategoryEntity categoryEntity : categoryEntityList) {
            categoryModelList.add(CategoryMapper.mapToModel(categoryEntity));
        }

        return categoryModelList;
    }

    @Override
    public List<CategoryModel> findByName(String name) {
        List<CategoryEntity> categoryEntityList = categoryRepository.findByName(name);
        if (categoryEntityList == null || categoryEntityList.isEmpty()) {
            return null;
        }

        List<CategoryModel> categoryModelList = new ArrayList<>();

        for (CategoryEntity categoryEntity : categoryEntityList) {
            categoryModelList.add(CategoryMapper.mapToModel(categoryEntity));
        }

        return categoryModelList;
    }

    @Override
    public CategoryModel findOneById(Integer id) {
        CategoryEntity categoryEntity = categoryRepository.findOneById(id);
        if (categoryEntity != null) {
            return CategoryMapper.mapToModel(categoryEntity);
        }
        return null;
    }

    public ResponseEntity<?> findData (String prefix, String query) {
        
        if (prefix.equals("find-all") && query == null) {
            List<CategoryModel> categoryModels = findAll();
            if (categoryModels == null) {
                return new ResponseEntity<>("", HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(categoryModels, HttpStatus.OK);
        } else if (prefix.equals("search") && query != null) {
            List<CategoryModel> categoryModels = findByName(query);
            if (categoryModels == null) {
                return new ResponseEntity<>("", HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(categoryModels, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Not found api", HttpStatus.NOT_FOUND);
        }
    }
}
