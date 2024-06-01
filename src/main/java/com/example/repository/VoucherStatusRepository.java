package com.example.repository;

import com.example.entity.VoucherStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface VoucherStatusRepository extends JpaRepository<VoucherStatusEntity, Integer> {

    @Query(value = "select * from voucher_status where voucher_status.id = :id", nativeQuery = true)
    VoucherStatusEntity findOneById(Integer id);
}
