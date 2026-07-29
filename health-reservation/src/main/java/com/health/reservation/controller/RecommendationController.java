package com.health.reservation.controller;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.health.common.core.controller.BaseController;
import com.health.common.core.domain.AjaxResult;
import com.health.common.utils.SecurityUtils;
import com.health.reservation.service.IRecommendationService;

@RestController
@RequestMapping("/reservation/recommendation")
public class RecommendationController extends BaseController {
    @Autowired
    private IRecommendationService recommendationService;

    @GetMapping
    public AjaxResult list() {
        Long userId = SecurityUtils.getUserId();
        List<Map<String, Object>> list = recommendationService.getRecommendations(userId);
        return success(list);
    }
}
