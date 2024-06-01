package com.example.controller;

import com.example.entity.LoyaltyPointsEntity;
import com.example.repository.LoyaltyPointsRepository;
import com.example.response.UserPointsSummaryResponse;
import com.example.service.impl.PointsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/loyalty-points")
public class LoyaltyPointsController {
    @Autowired
    private PointsService pointsService;

    @GetMapping("/")
    public List<UserPointsSummaryResponse> findData() {
        return pointsService.findAll();
    }
}
