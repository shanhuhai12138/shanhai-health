package com.health.report.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

public class HealthReport
{
    private static final long serialVersionUID = 1L;
    private Long id;
    private String reportNo;
    private Long userId;
    private String reportType;
    private String assessmentScores;
    private String moodSummary;
    private String aiAnalysis;
    private String aiRecommendations;
    private String reportStatus;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") private Date generateTime;
    private Date createTime;
    private Date updateTime;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getReportNo() { return reportNo; }
    public void setReportNo(String reportNo) { this.reportNo = reportNo; }
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    public String getReportType() { return reportType; }
    public void setReportType(String reportType) { this.reportType = reportType; }
    public String getAssessmentScores() { return assessmentScores; }
    public void setAssessmentScores(String assessmentScores) { this.assessmentScores = assessmentScores; }
    public String getMoodSummary() { return moodSummary; }
    public void setMoodSummary(String moodSummary) { this.moodSummary = moodSummary; }
    public String getAiAnalysis() { return aiAnalysis; }
    public void setAiAnalysis(String aiAnalysis) { this.aiAnalysis = aiAnalysis; }
    public String getAiRecommendations() { return aiRecommendations; }
    public void setAiRecommendations(String aiRecommendations) { this.aiRecommendations = aiRecommendations; }
    public String getReportStatus() { return reportStatus; }
    public void setReportStatus(String reportStatus) { this.reportStatus = reportStatus; }
    public Date getGenerateTime() { return generateTime; }
    public void setGenerateTime(Date generateTime) { this.generateTime = generateTime; }
    public Date getCreateTime() { return createTime; }
    public void setCreateTime(Date createTime) { this.createTime = createTime; }
    public Date getUpdateTime() { return updateTime; }
    public void setUpdateTime(Date updateTime) { this.updateTime = updateTime; }
}
