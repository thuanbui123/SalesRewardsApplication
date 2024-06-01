package com.example.mapper;

import com.example.entity.VoucherStatusEntity;
import com.example.model.VoucherStatusModel;

public class VoucherStatusMapper {
    public static VoucherStatusModel mapToModel(VoucherStatusEntity voucherStatusEntity) {
        VoucherStatusModel voucherStatusModel = new VoucherStatusModel();
        voucherStatusModel.setId(voucherStatusEntity.getId());
        voucherStatusModel.setName(voucherStatusEntity.getName());
        return voucherStatusModel;
    }
}
