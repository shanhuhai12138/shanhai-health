package com.health.reservation.controller;

import java.util.List;
import java.util.Map;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.health.common.annotation.Log;
import com.health.common.core.controller.BaseController;
import com.health.common.core.domain.AjaxResult;
import com.health.common.enums.BusinessType;
import com.health.reservation.domain.HealthReport;
import com.health.reservation.service.IHealthReportService;
import com.health.common.core.page.TableDataInfo;

/**
 * 综合健康报告Controller
 *
 * @author ruoyi
 * @date 2026-07-09
 */
@RestController
@RequestMapping("/reservation/healthReport")
public class HealthReportController extends BaseController
{
    @Autowired
    private IHealthReportService healthReportService;

    /**
     * 查询综合健康报告列表
     */
    @PreAuthorize("@ss.hasPermi('reservation:report:list')")
    @GetMapping("/list")
    public TableDataInfo list(HealthReport healthReport)
    {
        startPage();
        List<HealthReport> list = healthReportService.selectHealthReportList(healthReport);
        return getDataTable(list);
    }

    /**
     * 查询综合健康报告详情
     */
    @PreAuthorize("@ss.hasPermi('reservation:report:query')")
    @GetMapping("/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(healthReportService.selectHealthReportById(id));
    }

    /**
     * 生成综合健康分析报告
     * 聚合用户的测评结果（PHQ-9/GAD-7/SAS）和情绪统计数据
     *
     * @param params 请求参数 { "userId": xxx }
     * @return 报告编号
     */
    @PreAuthorize("@ss.hasPermi('reservation:report:add')")
    @Log(title = "综合健康报告", businessType = BusinessType.INSERT)
    @PostMapping("/generate")
    public AjaxResult generate(@RequestBody Map<String, Object> params)
    {
        @SuppressWarnings("unchecked")
        Map<String, Object> p = (Map<String, Object>) params;
        Long userId = Long.valueOf(p.get("userId").toString());
        String reportNo = healthReportService.generateComprehensiveReport(userId);
        return success("报告生成成功").put("reportNo", reportNo);
    }
}
