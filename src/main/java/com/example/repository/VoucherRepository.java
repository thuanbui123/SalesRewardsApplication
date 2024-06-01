package com.example.repository;

import com.example.entity.VoucherEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VoucherRepository extends JpaRepository<VoucherEntity, Integer> {

    @Query(value = "select * from vouchers where name like %:name%", nativeQuery = true)
    List<VoucherEntity> findByName(@Param("name") String name);

    @Query(value = "select * from vouchers where code like %:code%", nativeQuery = true)
    List<VoucherEntity> findByCode(@Param("code") String code);

    @Query(value = "select * from vouchers where vouchers.id=?", nativeQuery = true)
    VoucherEntity findOneById(@Param("id") Integer id);

    @Query(value = "SELECT * FROM vouchers WHERE vouchers.code = :code LIMIT 1", nativeQuery = true)
    VoucherEntity findOneByCode(String code);

    boolean existsByCode(String code);
}
