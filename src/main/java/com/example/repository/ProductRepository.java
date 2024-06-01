package com.example.repository;

import com.example.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {

    @Query(value = "select * from products where name like %:name%", nativeQuery = true)
    List<ProductEntity> findByName(@Param("name") String name);

    @Query(value = "select * from products where id = :id", nativeQuery = true)
    ProductEntity findOneById(@Param("id") Integer id);
    boolean existsByName(String name);
}
