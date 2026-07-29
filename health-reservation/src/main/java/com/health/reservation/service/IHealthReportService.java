package com.health.reservation.service;

import java.util.List;
import com.health.reservation.domain.HealthReport;

/**
 * 健康报告Service接口
 *
 * @author ruoyi
 * @date 2026-07-09
 */
public interface IHealthReportService
{
    /**
     * 查询健康报告
     *
     * @param id 健康报告主键
     * @return 健康报告
     */
    public HealthReport selectHealthReportById(Long id);

    /**
     * 查询健康报告列表
     *
     * @param healthReport 健康报告
     * @return 健康报告集合
     */
    public List<HealthReport> selectHealthReportList(HealthReport healthReport);

    /**
     * 新增健康报告
     *
     * @param healthReport 健康报告
     * @return 结果
     */
    public int insertHealthReport(HealthReport healthReport);

    /**
     * 修改健康报告
     *
     * @param healthReport 健康报告
     * @return 结果
     */
    public int updateHealthReport(HealthReport healthReport);

    /**
     * 批量删除健康报告
     *
     * @param ids 需要删除的健康报告主键集合
     * @return 结果
     */
    public int deleteHealthReportByIds(Long[] ids);

    /**
     * 删除健康报告信息
     *
     * @param id 健康报告主键
     * @return 结果
     */
    public int deleteHealthReportById(Long id);

    /**
     * 生成综合健康分析报告
     * 聚合测评结果、情绪数据和AI分析
     *
     * @param userId 用户ID
     * @return 报告编号
     */
    public String generateComprehensiveReport(Long userId);

    /**
     * 重新生成AI分析和建议内容
     *
     * @param reportId 报告ID
     */
    public void regenerateAiContent(Long reportId);
}
