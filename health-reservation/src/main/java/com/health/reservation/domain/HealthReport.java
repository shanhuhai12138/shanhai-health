package com.health.reservation.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.health.common.annotation.Excel;
import com.health.common.core.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * 健康报告对象 health_report
 *
 * @author ruoyi
 * @date 2026-07-09
 */
public class HealthReport extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 报告ID */
    private Long id;

    /** 报告编号 */
    @Excel(name = "报告编号")
    private String reportNo;

    /** 用户ID */
    @Excel(name = "用户ID")
    private Long userId;

    /** 报告类型（0综合 1测评 2情绪 3咨询） */
    @Excel(name = "报告类型", readConverterExp = "0=综合,1=测评,2=情绪,3=咨询")
    private String reportType;

    /** 测评得分JSON */
    @Excel(name = "测评得分")
    private String assessmentScores;

    /** 情绪摘要 */
    @Excel(name = "情绪摘要")
    private String moodSummary;

    /** AI分析 */
    @Excel(name = "AI分析")
    private String aiAnalysis;

    /** AI建议 */
    @Excel(name = "AI建议")
    private String aiRecommendations;

    /** 报告状态（0待生成 1已审核 2已发布） */
    @Excel(name = "报告状态", readConverterExp = "0=待生成,1=已审核,2=已发布")
    private String reportStatus;

    /** 生成时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "生成时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date generateTime;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }

    public void setReportNo(String reportNo)
    {
        this.reportNo = reportNo;
    }

    public String getReportNo()
    {
        return reportNo;
    }

    public void setUserId(Long userId)
    {
        this.userId = userId;
    }

    public Long getUserId()
    {
        return userId;
    }

    public void setReportType(String reportType)
    {
        this.reportType = reportType;
    }

    public String getReportType()
    {
        return reportType;
    }

    public void setAssessmentScores(String assessmentScores)
    {
        this.assessmentScores = assessmentScores;
    }

    public String getAssessmentScores()
    {
        return assessmentScores;
    }

    public void setMoodSummary(String moodSummary)
    {
        this.moodSummary = moodSummary;
    }

    public String getMoodSummary()
    {
        return moodSummary;
    }

    public void setAiAnalysis(String aiAnalysis)
    {
        this.aiAnalysis = aiAnalysis;
    }

    public String getAiAnalysis()
    {
        return aiAnalysis;
    }

    public void setAiRecommendations(String aiRecommendations)
    {
        this.aiRecommendations = aiRecommendations;
    }

    public String getAiRecommendations()
    {
        return aiRecommendations;
    }

    public void setReportStatus(String reportStatus)
    {
        this.reportStatus = reportStatus;
    }

    public String getReportStatus()
    {
        return reportStatus;
    }

    public void setGenerateTime(Date generateTime)
    {
        this.generateTime = generateTime;
    }

    public Date getGenerateTime()
    {
        return generateTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("reportNo", getReportNo())
            .append("userId", getUserId())
            .append("reportType", getReportType())
            .append("assessmentScores", getAssessmentScores())
            .append("moodSummary", getMoodSummary())
            .append("aiAnalysis", getAiAnalysis())
            .append("aiRecommendations", getAiRecommendations())
            .append("reportStatus", getReportStatus())
            .append("generateTime", getGenerateTime())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
