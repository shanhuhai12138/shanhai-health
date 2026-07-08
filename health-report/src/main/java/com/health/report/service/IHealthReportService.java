package com.health.report.service;

import java.util.List;
import com.health.report.domain.HealthReport;

public interface IHealthReportService
{
    public List<HealthReport> selectReportList(HealthReport report);
    public HealthReport selectReportById(Long id);
    public int insertReport(HealthReport report);
    public int updateReport(HealthReport report);
    public int deleteReportById(Long id);
    public int deleteReportByIds(Long[] ids);
    public int generateReport(Long userId);
}
