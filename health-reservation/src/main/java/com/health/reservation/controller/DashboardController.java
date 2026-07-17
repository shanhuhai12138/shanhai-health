package com.health.reservation.controller;

import java.util.List;
import java.util.Map;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.health.common.core.controller.BaseController;
import com.health.common.core.domain.AjaxResult;
import com.health.reservation.service.IDashboardService;

/**
 * 仪表盘统计Controller
 *
 * @author ruoyi
 * @date 2026-07-13
 */
@RestController
@RequestMapping("/reservation/dashboard")
public class DashboardController extends BaseController
{
    @Autowired
    private IDashboardService dashboardService;

    /**
     * 获取仪表盘统计数据（5个统计卡片）
     */
    @PreAuthorize("@ss.hasPermi('reservation:ordersetting:list')")
    @GetMapping("/stats")
    public AjaxResult getStats()
    {
        Map<String, Object> stats = dashboardService.getDashboardStats();
        return success(stats);
    }

    /**
     * 获取预约趋势数据
     */
    @PreAuthorize("@ss.hasPermi('reservation:ordersetting:list')")
    @GetMapping("/appointment-trend")
    public AjaxResult getAppointmentTrend(@RequestParam(defaultValue = "7") Integer days)
    {
        List<Map<String, Object>> trend = dashboardService.getAppointmentTrend(days);
        return success(trend);
    }

    /**
     * 获取套餐销售排行
     */
    @PreAuthorize("@ss.hasPermi('reservation:ordersetting:list')")
    @GetMapping("/setmeal-ranking")
    public AjaxResult getSetmealRanking()
    {
        List<Map<String, Object>> ranking = dashboardService.getSetmealRanking();
        return success(ranking);
    }
}
