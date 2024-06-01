package com.example.service.impl;

import com.example.DTO.VoucherDTO;
import com.example.entity.VoucherEntity;
import com.example.mapper.VoucherMapper;
import com.example.model.VoucherModel;
import com.example.repository.VoucherRepository;
import com.example.service.IVoucherService;
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
public class VoucherService implements IVoucherService {

    private Logger LOGGER = LogManager.getLogger(VoucherService.class);

    @Autowired
    private VoucherRepository voucherRepository;
    @Autowired
    private VoucherMapper voucherMapper;

    @Override
    public List<VoucherModel> findAll() {
        List<VoucherEntity> voucherEntities = voucherRepository.findAll();
        List<VoucherModel> voucherModels = new ArrayList<>();
        for (VoucherEntity voucherEntity : voucherEntities) {
            voucherModels.add(VoucherMapper.mapToModel(voucherEntity));
        }
        return voucherModels;
    }

    @Override
    public List<VoucherModel> findByName(String name) {
        List<VoucherEntity> voucherEntities = voucherRepository.findByName(name);
        List<VoucherModel> voucherModels = new ArrayList<>();
        for (VoucherEntity voucherEntity : voucherEntities) {
            voucherModels.add(VoucherMapper.mapToModel(voucherEntity));
        }
        return voucherModels;
    }

    @Override
    public List<VoucherModel> findByCode(String code) {
        List<VoucherEntity> voucherEntities = voucherRepository.findByCode(code);
        List<VoucherModel> voucherModels = new ArrayList<>();
        for (VoucherEntity voucherEntity : voucherEntities) {
            voucherModels.add(VoucherMapper.mapToModel(voucherEntity));
        }
        return voucherModels;
    }

    @Override
    public VoucherModel findOneById(Integer id) {
        VoucherEntity voucherEntity = voucherRepository.findOneById(id);
        return VoucherMapper.mapToModel(voucherEntity);
    }

    @Override
    public int addVoucher(VoucherDTO voucherDTO) {
        try {
            VoucherEntity voucherEntity = voucherMapper.mapToEntity(voucherDTO);
            boolean exists = voucherRepository.existsByCode(voucherEntity.getCode());
            if (!exists) {
                voucherRepository.save(voucherEntity);
                return 1; // Đã thêm voucher thành công
            } else {
                return 0; // Voucher đã tồn tại
            }
        } catch (Exception e) {
            LOGGER.error("Error: " + e.getMessage());
            return -1; // Lỗi khi thêm voucher
        }
    }

    public ResponseEntity<?> findData(String prefix, String query) {
        if (prefix.equals("find-all") && query == null) {
            List<VoucherModel> voucherModels = findAll();
            if (voucherModels == null) {
                return new ResponseEntity<>("", HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(voucherModels, HttpStatus.OK);
        } else if (prefix.equals("search") && query != null) {
            List<VoucherModel> voucherModels = findByCode(query);
            if (voucherModels == null) {
                return new ResponseEntity<>("", HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(voucherModels, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Not found api", HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<?> addData(VoucherDTO voucherDTO) {
        int addData = addVoucher(voucherDTO);
        if (addData == -1) {
            return new ResponseEntity<>("System error", HttpStatus.INTERNAL_SERVER_ERROR);
        } else if (addData == 0) {
            return new ResponseEntity<>("Voucher already exists", HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>("True", HttpStatus.CREATED);
    }
}
