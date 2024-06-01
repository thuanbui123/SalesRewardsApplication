package com.example.controller;

import com.example.model.CategoryModel;
import com.example.service.impl.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/{prefix}")
    public ResponseEntity<?> findData (@PathVariable String prefix, @RequestParam(name = "query", required = false) String query, @RequestParam(name = "id", required = false) Integer id) {
        return categoryService.findData(prefix, query, id);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addData (@RequestBody CategoryModel categoryModel) {
        return categoryService.addData(categoryModel);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateData (@RequestBody CategoryModel categoryModel, @PathVariable Integer id) {
        return categoryService.updateData(categoryModel, id);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteData (@PathVariable Integer id) {
        return categoryService.deleteData(id);
    }
}
