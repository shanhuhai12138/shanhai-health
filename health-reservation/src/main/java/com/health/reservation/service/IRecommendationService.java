package com.health.reservation.service;

import java.util.List;
import java.util.Map;

public interface IRecommendationService {
    List<Map<String, Object>> getRecommendations(Long userId);
}
