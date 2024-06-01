package com.example.service.impl;

import com.example.entity.VoucherStatusEntity;
import com.example.mapper.VoucherStatusMapper;
import com.example.model.VoucherStatusModel;
import com.example.repository.VoucherStatusRepository;
import com.example.service.IVoucherStatusService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VoucherStatusService implements IVoucherStatusService {

    @Autowired
    private VoucherStatusRepository voucherStatusRepository;

    @Override
    public List<VoucherStatusModel> findAll() {
        List<VoucherStatusEntity> voucherStatusEntities = voucherStatusRepository.findAll();
        List<VoucherStatusModel> voucherStatusModels = new ArrayList<>();
        for(VoucherStatusEntity voucherStatusEntity : voucherStatusEntities) {
            voucherStatusModels.add(VoucherStatusMapper.mapToModel(voucherStatusEntity));
        }
        return voucherStatusModels;
    }

    @Override
    public VoucherStatusModel findOneById(Integer id) {
        VoucherStatusEntity voucherStatus = voucherStatusRepository.findOneById(id);
        return VoucherStatusMapper.mapToModel(voucherStatus);
    }

    public VoucherStatusEntity findById(Integer id) {
        return voucherStatusRepository.findOneById(id);
    }
}
