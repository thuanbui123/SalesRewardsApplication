package com.example.service.impl;

import com.example.DTO.ProductDTO;
import com.example.entity.ProductEntity;
import com.example.mapper.ProductMapper;
import com.example.model.ProductModel;
import com.example.repository.ProductRepository;
import com.example.service.IProductService;
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
public class ProductService implements IProductService {

    @Autowired
    private ProductRepository productRepository;

    private static final Logger LOGGER = LogManager.getLogger(ProductService.class);
    @Override
    public List<ProductModel> findAll() {
        List<ProductEntity> productEntities = productRepository.findAll();
        if (productEntities == null || productEntities.isEmpty()) {
            return null;
        }

        List<ProductModel> productModels = new ArrayList<>();

        for (ProductEntity productEntity : productEntities) {
            productModels.add(ProductMapper.mapToModel(productEntity));
        }

        return productModels;
    }

    @Override
    public List<ProductModel> findByName(String name) {
        List<ProductEntity> productEntities = productRepository.findByName(name);
        if (productEntities == null || productEntities.isEmpty()) {
            return null;
        }

        List<ProductModel> productModels = new ArrayList<>();

        for (ProductEntity productEntity : productEntities) {
            productModels.add(ProductMapper.mapToModel(productEntity));
        }

        return productModels;
    }

    @Override
    public int addProduct(ProductDTO productDTO) {
        try {
            ProductEntity productEntity = ProductMapper.mapDtoToEntity(productDTO);
            boolean isExists = productRepository.existsByName(productEntity.getName());
            if (!isExists) {
                productRepository.save(productEntity);
                return 1;
            }
            return 0;
        }catch (Exception e) {
            LOGGER.error(e.getMessage());
            return -1;
        }
    }

    @Override
    @Transactional
    public int updateProduct(ProductDTO productDTO, Integer id) {
        try {
            ProductEntity productEntity = ProductMapper.mapDtoToEntity(productDTO);
            ProductEntity existsProduct = productRepository.findOneById(id);
            if (existsProduct != null) {
                existsProduct.setName(productEntity.getName());
                existsProduct.setDescription(productEntity.getDescription());
                existsProduct.setPrice(productEntity.getPrice());
                existsProduct.setRewardPercentage(productEntity.getRewardPercentage());
                existsProduct.setStockQuantity(productEntity.getStockQuantity());
                existsProduct.setCategory(productEntity.getCategory());
                productRepository.save(existsProduct);
                return 1;
            }
            return 0;
        }catch (Exception e) {
            LOGGER.error(e.getMessage());
            return -1;
        }
    }

    @Override
    @Transactional
    public int deleteProduct(Integer id) {
        try {
            ProductEntity productEntity = productRepository.findOneById(id);
            if (productEntity!= null) {
                productRepository.delete(productEntity);
                ProductEntity afterDeleted = productRepository.findOneById(id);
                if(afterDeleted != null) {
                    return 0;
                }
                return 1;
            }
            return 2;
        }catch (Exception e) {
            LOGGER.error(e.getMessage());
            return -1;
        }
    }

    public ResponseEntity<?> findData(String prefix, String query, Integer id) {
        if (prefix.equals("find-all") && query == null && id == null) {
            List<ProductModel> productModels = findAll();
            if (productModels == null) {
                return new ResponseEntity<>("", HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(productModels, HttpStatus.OK);
        } else if (prefix.equals("search") && query != null && id == null) {
            List<ProductModel> productModels = findByName(query);
            if (productModels == null) {
                return new ResponseEntity<>("", HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(productModels, HttpStatus.OK);
        } else if (prefix.equals("search") && query == null && id != null) {
            ProductEntity productEntity = productRepository.findOneById(id);
            if (productEntity == null) {
                return new ResponseEntity<>("", HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(ProductMapper.mapToModel(productEntity), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Not found api", HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<?> addData (ProductDTO productDTO) {
        int addProduct = addProduct(productDTO);
        if (addProduct == -1) {
            return new ResponseEntity<>("System error", HttpStatus.INTERNAL_SERVER_ERROR);
        } else if (addProduct == 0) {
            return new ResponseEntity<>("Product already exists", HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>("True", HttpStatus.CREATED);
    }

    @Transactional
    public ResponseEntity<?> updateData (ProductDTO productDTO, Integer id) {
        int updateProduct = updateProduct(productDTO, id);
        if (updateProduct == -1) {
            return new ResponseEntity<>("System error", HttpStatus.INTERNAL_SERVER_ERROR);
        } else if (updateProduct == 0) {
            return new ResponseEntity<>("False", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("True", HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<?> deleteData (Integer id) {
        int deleteProduct = deleteProduct(id);
        if (deleteProduct == -1) {
            return new ResponseEntity<>("System error", HttpStatus.INTERNAL_SERVER_ERROR);
        } else if (deleteProduct == 0) {
            return new ResponseEntity<>("fail", HttpStatus.BAD_REQUEST);
        } else if (deleteProduct == 1) {
            return new ResponseEntity<>("true", HttpStatus.OK);
        }
        return new ResponseEntity<>("Product isn't exists", HttpStatus.NOT_FOUND);
    }
}
