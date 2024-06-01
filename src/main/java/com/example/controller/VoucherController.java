package com.example.controller;

import com.example.DTO.VoucherDTO;
import com.example.service.impl.VoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/vouchers")
public class VoucherController {
    @Autowired
    private VoucherService voucherService;

    @GetMapping("/{prefix}")
    public ResponseEntity<?> findData(@PathVariable(name = "prefix") String prefix, @RequestParam(required = false) String query) {
        return voucherService.findData(prefix, query);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addData(@RequestBody VoucherDTO voucherDTO) {
        return voucherService.addData(voucherDTO);
    }
}
