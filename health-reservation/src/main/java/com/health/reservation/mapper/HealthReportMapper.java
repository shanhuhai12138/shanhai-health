package com.health.reservation.mapper;

import java.util.List;
import com.health.reservation.domain.HealthReport;

/**
 * 健康报告Mapper接口
 *
 * @author ruoyi
 * @date 2026-07-09
 */
public interface HealthReportMapper
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
     * 删除健康报告
     *
     * @param id 健康报告主键
     * @return 结果
     */
    public int deleteHealthReportById(Long id);

    /**
     * 批量删除健康报告
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteHealthReportByIds(Long[] ids);
}
