package com.example.repository;

import com.example.entity.PointsTransactionsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PointsTransactionsRepository extends JpaRepository<PointsTransactionsEntity, Integer> {
    @Query(value = "SELECT pt.id as id, pt.points as points, pt.transaction_date as transaction_date, pt.expiration_date AS expiration_date, pt.is_used as is_used " +
            "FROM points_transactions pt INNER JOIN loyalty_points lp ON lp.id = pt.point_id " +
            "WHERE lp.user_id = :id " +
            "GROUP BY pt.id, pt.points, pt.transaction_date, pt.is_used", nativeQuery = true)
    PointsTransactionsEntity findExpiringPointsByUserId(@Param("id") Integer id);
}
