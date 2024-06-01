package com.example.service;

import com.example.response.UserPointsSummaryResponse;

import java.util.List;

public interface IPointsService {
    List<UserPointsSummaryResponse> findAll();
}
