package com.health.report.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.health.common.core.controller.BaseController;
import com.health.common.core.domain.AjaxResult;
import com.health.common.core.page.TableDataInfo;
import com.health.report.domain.HealthReport;
import com.health.report.service.IHealthReportService;

@RestController
@RequestMapping("/report")
public class HealthReportController extends BaseController
{
    @Autowired
    private IHealthReportService reportService;

    @GetMapping("/list")
    public TableDataInfo list(HealthReport report)
    {
        report.setUserId(getUserId());
        List<HealthReport> list = reportService.selectReportList(report);
        return getDataTable(list != null ? list : new java.util.ArrayList<>());
    }

    @GetMapping("/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(reportService.selectReportById(id));
    }

    @PostMapping("/generate")
    public AjaxResult generate()
    {
        return toAjax(reportService.generateReport(getUserId()));
    }
}
