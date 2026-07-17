package com.health.reservation.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.health.reservation.mapper.DashboardMapper;
import com.health.reservation.service.IDashboardService;

/**
 * 仪表盘统计Service业务层处理
 *
 * @author ruoyi
 * @date 2026-07-14
 */
@Service
public class DashboardServiceImpl implements IDashboardService
{
    @Autowired
    private DashboardMapper dashboardMapper;

    /**
     * 获取仪表盘统计数据
     */
    @Override
    public Map<String, Object> getDashboardStats()
    {
        Map<String, Object> stats = new HashMap<>();
        stats.put("todayAppointments", dashboardMapper.countTodayAppointments());
        stats.put("monthlyCheckups", dashboardMapper.countMonthCheckups());
        stats.put("activeCounselors", dashboardMapper.countActiveCounselors());
        stats.put("monthlyAssessments", dashboardMapper.countMonthAssessments());
        stats.put("totalMoodRecords", dashboardMapper.countTotalMoodRecords());
        return stats;
    }

    /**
     * 获取预约趋势数据
     */
    @Override
    public List<Map<String, Object>> getAppointmentTrend(Integer days)
    {
        return dashboardMapper.selectAppointmentTrend(days);
    }

    /**
     * 获取套餐销售排行
     */
    @Override
    public List<Map<String, Object>> getSetmealRanking()
    {
        return dashboardMapper.selectSetmealRanking();
    }
}