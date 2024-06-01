package com.example.repository;

import com.example.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Integer> {

    @Query(value = "select * from categories where name like %:name%", nativeQuery = true)
    List<CategoryEntity> findByName(@Param("name") String name);

    @Query(value = "select * from categories where id = :id", nativeQuery = true)
    CategoryEntity findOneById (@Param("id") Integer id);

    boolean existsByName(String name);
}
