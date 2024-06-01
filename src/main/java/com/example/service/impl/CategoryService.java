package com.example.service.impl;

import com.example.entity.CategoryEntity;
import com.example.mapper.CategoryMapper;
import com.example.model.CategoryModel;
import com.example.repository.CategoryRepository;
import com.example.service.ICategoryService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
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

    private static final Logger LOGGER = LogManager.getLogger(CategoryService.class);

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
    public CategoryEntity findOneById(Integer id) {
        CategoryEntity categoryEntity = categoryRepository.findOneById(id);
        if (categoryEntity != null) {
            return categoryEntity;
        }
        return null;
    }

    @Override
    public int addCategory(CategoryModel categoryModel) {
        try {
            CategoryEntity categoryEntity = CategoryMapper.mapToEntity(categoryModel);
            boolean isExists = categoryRepository.existsByName(categoryEntity.getName());
            if (!isExists) {
                CategoryEntity saveCategory = categoryRepository.save(categoryEntity);
                return 1;
            }
            return 0;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return -1;
        }
    }

    @Override
    @Transactional
    public int updateCategory(CategoryModel categoryModel, Integer id) {
        try {
            CategoryEntity categoryEntity = CategoryMapper.mapToEntity(categoryModel);
            CategoryEntity existsCategory = categoryRepository.findOneById(id);
            if (existsCategory != null) {
                existsCategory.setName(categoryEntity.getName());
                existsCategory.setDescription(categoryEntity.getDescription());
                categoryRepository.save(existsCategory);
                return 1;
            }
            return 0;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return -1;
        }
    }

    @Override
    @Transactional
    public int deleteCategory(Integer id) {
        try {
            CategoryEntity categoryEntity = categoryRepository.findOneById(id);
            if (categoryEntity != null) {
                categoryRepository.delete(categoryEntity);
                CategoryEntity afterDelete = categoryRepository.findOneById(id);
                if (afterDelete != null) {
                    return 0;
                }
                return 1;
            }
            return 2;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return -1;
        }
    }

    public ResponseEntity<?> findData (String prefix, String query, Integer id) {
        
        if (prefix.equals("find-all") && query == null && id == null) {
            List<CategoryModel> categoryModels = findAll();
            if (categoryModels == null) {
                return new ResponseEntity<>("", HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(categoryModels, HttpStatus.OK);
        } else if (prefix.equals("search") && query != null && id == null) {
            List<CategoryModel> categoryModels = findByName(query);
            if (categoryModels == null) {
                return new ResponseEntity<>("", HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(categoryModels, HttpStatus.OK);
        } else if (prefix.equals("search") && query == null && id != null) {
            CategoryEntity categoryEntity = categoryRepository.findOneById(id);
            if (categoryEntity == null) {
                return new ResponseEntity<>("", HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(CategoryMapper.mapToModel(categoryEntity), HttpStatus.OK);
        }else {
            return new ResponseEntity<>("Not found api", HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<?> addData (CategoryModel categoryModel) {
        int addCategory = addCategory(categoryModel);
        if (addCategory == -1) {
            return new ResponseEntity<>("System error", HttpStatus.INTERNAL_SERVER_ERROR);
        } else if (addCategory == 0) {
            return new ResponseEntity<>("False", HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>("True", HttpStatus.CREATED);
    }

    @Transactional
    public ResponseEntity<?> updateData(CategoryModel categoryModel, Integer id) {
        int updated = updateCategory(categoryModel, id);
        if (updated == -1) {
            return new ResponseEntity<>("System error", HttpStatus.INTERNAL_SERVER_ERROR);
        } else if (updated == 0) {
            return new ResponseEntity<>("False", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("True", HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<?> deleteData (Integer id) {
        int deleted = deleteCategory(id);
        if (deleted == -1) {
            return new ResponseEntity<>("System error", HttpStatus.INTERNAL_SERVER_ERROR);
        } else if (deleted == 0) {
            return new ResponseEntity<>("fail", HttpStatus.BAD_REQUEST);
        } else if (deleted == 1) {
            return new ResponseEntity<>("true", HttpStatus.OK);
        }
        return new ResponseEntity<>("not found", HttpStatus.NOT_FOUND);
    }
}
