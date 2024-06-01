package com.example.controller;

import com.example.DTO.ProductDTO;
import com.example.mapper.ProductMapper;
import com.example.service.impl.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/products")
@CrossOrigin(origins = "http://localhost:3000")
public class ProductController {

    @Autowired
    private ProductService productService;
    @GetMapping("/{prefix}")
    public ResponseEntity<?> findData(@PathVariable(name = "prefix") String prefix, @RequestParam(required = false) String query, @RequestParam(required = false) Integer id){
        return productService.findData(prefix, query, id);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addData(@RequestBody ProductDTO productDTO) {
        return productService.addData(productDTO);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateData(@RequestBody ProductDTO productDTO, @PathVariable(name = "id") Integer id) {
        return productService.updateData(productDTO, id);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteData(@PathVariable(name = "id") Integer id) {
        return productService.deleteData(id);
    }
}
