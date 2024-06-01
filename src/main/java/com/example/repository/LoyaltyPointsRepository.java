package com.example.repository;

import com.example.entity.LoyaltyPointsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LoyaltyPointsRepository extends JpaRepository<LoyaltyPointsEntity, Integer> {

    @Query(value = "select * from loyalty_points where id = :id", nativeQuery = true)
    LoyaltyPointsEntity findOneById(@Param("id") Integer id);


}
