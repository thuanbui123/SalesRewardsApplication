package com.example.service.impl;

import com.example.entity.LoyaltyPointsEntity;
import com.example.entity.PointsTransactionsEntity;
import com.example.mapper.PointsMapper;
import com.example.repository.LoyaltyPointsRepository;
import com.example.repository.PointsTransactionsRepository;
import com.example.response.UserPointsSummaryResponse;
import com.example.service.IPointsService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Data
public class PointsService implements IPointsService {
    @Autowired
    private PointsTransactionsRepository pointsTransactionsRepository;

    @Autowired
    private LoyaltyPointsRepository loyaltyPointsRepository;

    @Override
    public List<UserPointsSummaryResponse> findAll() {
        List<LoyaltyPointsEntity> loyaltyPointsEntity = loyaltyPointsRepository.findAll();
        List<UserPointsSummaryResponse> userPointsSummaryResponses = new ArrayList<>();
        for(LoyaltyPointsEntity loyaltyPoints : loyaltyPointsEntity) {
            PointsTransactionsEntity pointsTransactions = pointsTransactionsRepository.findExpiringPointsByUserId(loyaltyPoints.getAccountEntity().getId());
            userPointsSummaryResponses.add(PointsMapper.convertToSummaryResponse(loyaltyPoints, pointsTransactions));
        }
        return userPointsSummaryResponses;
    }
}
