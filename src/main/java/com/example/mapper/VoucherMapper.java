package com.example.mapper;

import com.example.DTO.VoucherDTO;
import com.example.entity.CategoryEntity;
import com.example.entity.VoucherEntity;
import com.example.entity.VoucherStatusEntity;
import com.example.model.CategoryModel;
import com.example.model.VoucherModel;
import com.example.service.impl.AccountService;
import com.example.service.impl.VoucherStatusService;
import com.example.utils.DateTimeUtil;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Service
public class VoucherMapper {

    @Autowired
    private AccountService accountService;
    @Autowired
    private VoucherStatusService voucherStatusService;

    public static VoucherModel mapToModel (VoucherEntity voucherEntity) {
        VoucherModel voucherModel = new VoucherModel();
        voucherModel.setId(voucherEntity.getId());
        voucherModel.setCode(voucherEntity.getCode());
        voucherModel.setName(voucherEntity.getName());
        voucherModel.setDescription(voucherEntity.getDescription());
        voucherModel.setDiscountValue(voucherEntity.getDiscountValue());
        voucherModel.setMaxDiscount(voucherEntity.getMaxDiscount());
        voucherModel.setPointsRequired(voucherEntity.getPointsRequired());
        voucherModel.setValidityStart(DateTimeUtil.formatLocalDate(voucherEntity.getValidityStart()));
        voucherModel.setValidityEnd(DateTimeUtil.formatLocalDate(voucherEntity.getValidityEnd()));
        voucherModel.setNameVoucherStatus(voucherEntity.getVoucherStatusEntity().getName());
        return voucherModel;
    }

    public VoucherEntity mapToEntity(VoucherDTO voucherDTO) {
        VoucherEntity voucherEntity = new VoucherEntity();
        voucherEntity.setCode(voucherDTO.getCode());
        voucherEntity.setName(voucherDTO.getName());
        voucherEntity.setDescription(voucherDTO.getDescription());
        voucherEntity.setDiscountValue(voucherDTO.getDiscountValue());
        voucherEntity.setMaxDiscount(voucherDTO.getMaxDiscount());
        voucherEntity.setPointsRequired(voucherDTO.getPointsRequired());
        voucherEntity.setValidityStart(voucherDTO.getValidityStart());
        voucherEntity.setValidityEnd(voucherDTO.getValidityEnd());
        VoucherStatusEntity voucherStatusEntity = voucherStatusService.findById(voucherDTO.getVoucherStatusId());
        voucherEntity.setVoucherStatusEntity(voucherStatusEntity);
        Set<CategoryEntity> categoryEntities = new HashSet<>();
        for (CategoryModel categoryModel: voucherDTO.getCategoryModels()) {
            categoryEntities.add(CategoryMapper.mapModelToEntity(categoryModel));
        }
        voucherEntity.setCategoryEntities(categoryEntities);
        voucherEntity.setAccountEntity(accountService.findOneById(voucherDTO.getAdminId()));
        return voucherEntity;
    }
}
