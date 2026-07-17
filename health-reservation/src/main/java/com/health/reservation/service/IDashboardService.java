package com.health.reservation.service;

import java.util.List;
import java.util.Map;

/**
 * 仪表盘统计Service接口
 *
 * @author ruoyi
 * @date 2026-07-14
 */
public interface IDashboardService
{
    /**
     * 获取仪表盘统计数据（5个统计卡片）
     */
    Map<String, Object> getDashboardStats();

    /**
     * 获取预约趋势数据
     */
    List<Map<String, Object>> getAppointmentTrend(Integer days);

    /**
     * 获取套餐销售排行
     */
    List<Map<String, Object>> getSetmealRanking();
}