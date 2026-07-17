package com.health.reservation.mapper;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

/**
 * 仪表盘统计Mapper接口
 *
 * @author ruoyi
 * @date 2026-07-14
 */
public interface DashboardMapper
{
    /**
     * 统计今日预约数
     */
    Long countTodayAppointments();

    /**
     * 统计本月体检人次
     */
    Long countMonthCheckups();

    /**
     * 统计活跃咨询师数
     */
    Long countActiveCounselors();

    /**
     * 统计本月测评次数
     */
    Long countMonthAssessments();

    /**
     * 统计情绪记录总数
     */
    Long countTotalMoodRecords();

    /**
     * 获取预约趋势数据
     */
    List<Map<String, Object>> selectAppointmentTrend(@Param("days") Integer days);

    /**
     * 获取套餐销售排行
     */
    List<Map<String, Object>> selectSetmealRanking();
}