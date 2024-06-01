package com.example.mapper;

import com.example.entity.LoyaltyPointsEntity;
import com.example.entity.PointsTransactionsEntity;
import com.example.response.UserPointsSummaryResponse;
import com.example.utils.DateTimeUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class PointsMapper {
    public static UserPointsSummaryResponse convertToSummaryResponse(LoyaltyPointsEntity loyaltyPoints, PointsTransactionsEntity pointsTransactions) {
        UserPointsSummaryResponse response = new UserPointsSummaryResponse();
        response.setUserId(loyaltyPoints.getAccountEntity().getId());
        response.setUsername(loyaltyPoints.getAccountEntity().getName());
        response.setEmail(loyaltyPoints.getAccountEntity().getEmail());
        response.setTotalPoint(loyaltyPoints.getPointsBalance());
        Integer totalPointsUsed = 0;
        for(PointsTransactionsEntity pointsTransactionsEntity : loyaltyPoints.getPointsTransactionsEntities()) {
            if (pointsTransactionsEntity.getIsUsed()) {
                totalPointsUsed += pointsTransactionsEntity.getPoints();
            }
        }
        response.setUsedPoints(totalPointsUsed);
        if (pointsTransactions.getIsUsed()) {
            response.setExpiringPoints(0);
            response.setExpiryDate(null);
        }else {
            LocalDate targetDate = LocalDate.now().plusDays(15);
            LocalDate expirationDate = pointsTransactions.getExpirationDate().toLocalDate();
            if (expirationDate.isEqual(targetDate)) {
                response.setExpiringPoints(pointsTransactions.getPoints());
                response.setExpiryDate(DateTimeUtil.formatLocalDate(pointsTransactions.getExpirationDate()));
            } else {
                response.setExpiringPoints(0);
                response.setExpiryDate(null);
            }
        }
        return response;
    }
}