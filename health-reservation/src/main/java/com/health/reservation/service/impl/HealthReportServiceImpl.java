package com.health.reservation.service.impl;

import java.util.List;
import java.util.Map;
import com.health.common.utils.DateUtils;
import com.health.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.health.reservation.mapper.HealthReportMapper;
import com.health.reservation.mapper.AssessmentResultMapper;
import com.health.reservation.mapper.MoodMapper;
import com.health.reservation.domain.HealthReport;
import com.health.reservation.domain.AssessmentResult;
import com.health.reservation.service.IHealthReportService;
import com.alibaba.fastjson2.JSONObject;

/**
 * 健康报告Service业务层处理
 *
 * @author ruoyi
 * @date 2026-07-09
 */
@Service
public class HealthReportServiceImpl implements IHealthReportService
{
    @Autowired
    private HealthReportMapper healthReportMapper;

    @Autowired
    private AssessmentResultMapper assessmentResultMapper;

    @Autowired
    private MoodMapper moodMapper;

    /**
     * 查询健康报告
     *
     * @param id 健康报告主键
     * @return 健康报告
     */
    @Override
    public HealthReport selectHealthReportById(Long id)
    {
        return healthReportMapper.selectHealthReportById(id);
    }

    /**
     * 查询健康报告列表
     *
     * @param healthReport 健康报告
     * @return 健康报告
     */
    @Override
    public List<HealthReport> selectHealthReportList(HealthReport healthReport)
    {
        return healthReportMapper.selectHealthReportList(healthReport);
    }

    /**
     * 新增健康报告
     *
     * @param healthReport 健康报告
     * @return 结果
     */
    @Override
    public int insertHealthReport(HealthReport healthReport)
    {
        healthReport.setCreateTime(DateUtils.getNowDate());
        healthReport.setCreateBy(SecurityUtils.getUsername());
        return healthReportMapper.insertHealthReport(healthReport);
    }

    /**
     * 修改健康报告
     *
     * @param healthReport 健康报告
     * @return 结果
     */
    @Override
    public int updateHealthReport(HealthReport healthReport)
    {
        healthReport.setUpdateTime(DateUtils.getNowDate());
        healthReport.setUpdateBy(SecurityUtils.getUsername());
        return healthReportMapper.updateHealthReport(healthReport);
    }

    /**
     * 批量删除健康报告
     *
     * @param ids 需要删除的健康报告主键
     * @return 结果
     */
    @Override
    public int deleteHealthReportByIds(Long[] ids)
    {
        return healthReportMapper.deleteHealthReportByIds(ids);
    }

    /**
     * 删除健康报告信息
     *
     * @param id 健康报告主键
     * @return 结果
     */
    @Override
    public int deleteHealthReportById(Long id)
    {
        return healthReportMapper.deleteHealthReportById(id);
    }

    /**
     * 生成综合健康分析报告
     * 聚合测评结果和情绪数据，组装JSON后创建报告记录
     *
     * @param userId 用户ID
     * @return 报告编号
     */
    @Override
    @Transactional
    public String generateComprehensiveReport(Long userId)
    {
        // 1. 查询用户最新测评结果（PHQ-9, GAD-7, SAS）
        JSONObject assessmentScores = new JSONObject();
        List<AssessmentResult> history = assessmentResultMapper.selectAssessmentHistoryWithNames(userId);
        if (history != null)
        {
            for (AssessmentResult result : history)
            {
                String code = result.getAssessmentCode();
                if (code != null)
                {
                    JSONObject scoreEntry = new JSONObject();
                    scoreEntry.put("score", result.getTotalScore());
                    scoreEntry.put("severityLevel", result.getSeverityLevel());
                    scoreEntry.put("severityDesc", result.getSeverityDesc());
                    scoreEntry.put("assessmentName", result.getAssessmentName());
                    scoreEntry.put("assessmentCode", code);
                    scoreEntry.put("createTime", result.getCreateTime());
                    assessmentScores.put(code, scoreEntry);
                }
            }
        }

        // 2. 查询用户最近30天情绪统计
        JSONObject moodSummary = new JSONObject();
        Map<String, Object> summary = moodMapper.selectSummary(userId);
        if (summary != null && !summary.isEmpty())
        {
            moodSummary.put("totalRecords", summary.get("totalRecords"));
            moodSummary.put("avgScore", summary.get("avgScore"));
            moodSummary.put("maxScore", summary.get("maxScore"));
            moodSummary.put("minScore", summary.get("minScore"));
            moodSummary.put("bestMood", summary.get("bestMood"));
            moodSummary.put("worstMood", summary.get("worstMood"));
            moodSummary.put("streakDays", summary.get("streakDays"));
        }

        // 3. 构建报告编号
        String reportNo = "HR-" + DateUtils.dateTimeNow()
                + "-" + String.format("%04d", userId % 10000);

        // 4. 创建健康报告记录
        HealthReport report = new HealthReport();
        report.setReportNo(reportNo);
        report.setUserId(userId);
        report.setReportType("comprehensive");
        report.setAssessmentScores(assessmentScores.toJSONString());
        report.setMoodSummary(moodSummary.toJSONString());
        report.setReportStatus("0");
        report.setGenerateTime(DateUtils.getNowDate());
        report.setCreateTime(DateUtils.getNowDate());
        report.setCreateBy(SecurityUtils.getUsername());

        healthReportMapper.insertHealthReport(report);

        // 5. 将报告ID回填到reportNo字段（如需）并返回
        return reportNo;
    }
}
