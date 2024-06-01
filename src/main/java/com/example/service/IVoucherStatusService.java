package com.example.service;

import com.example.model.VoucherStatusModel;

import java.util.List;

public interface IVoucherStatusService {
    List<VoucherStatusModel> findAll ();
    VoucherStatusModel findOneById(Integer id);
}
