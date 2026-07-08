package com.health.report.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.health.report.domain.HealthReport;
import com.health.report.service.IHealthReportService;

@Service
public class HealthReportServiceImpl implements IHealthReportService
{
    // TODO: 注入 Mapper 和 AI 服务
    // 当前为占位实现，后续接入 AI 分析能力

    @Override
    public List<HealthReport> selectReportList(HealthReport report) { return null; }
    @Override
    public HealthReport selectReportById(Long id) { return null; }
    @Override
    public int insertReport(HealthReport report) { return 0; }
    @Override
    public int updateReport(HealthReport report) { return 0; }
    @Override
    public int deleteReportById(Long id) { return 0; }
    @Override
    public int deleteReportByIds(Long[] ids) { return 0; }
    @Override
    public int generateReport(Long userId) { return 0; }
}
