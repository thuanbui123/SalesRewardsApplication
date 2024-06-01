package com.example.service;

import com.example.DTO.VoucherDTO;
import com.example.model.VoucherModel;

import java.util.List;

public interface IVoucherService {
    List<VoucherModel> findAll();
    List<VoucherModel> findByName(String name);
    List<VoucherModel> findByCode(String code);
    VoucherModel findOneById(Integer id);

    int addVoucher(VoucherDTO voucherDTO);
}
