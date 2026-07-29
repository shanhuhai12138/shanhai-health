package com.health.reservation.mapper;

import java.util.List;
import com.health.reservation.domain.RecommendationRule;

public interface RecommendationRuleMapper {
    public List<RecommendationRule> selectEnabledRules();
}
