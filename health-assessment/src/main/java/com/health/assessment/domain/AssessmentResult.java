package com.health.assessment.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 测评结果对象 assessment_result
 *
 * @author ruoyi
 * @date 2026-07-08
 */
public class AssessmentResult
{
    private static final long serialVersionUID = 1L;

    /** 结果ID */
    private Long id;

    /** 用户ID */
    private Long userId;

    /** 量表ID */
    private Long assessmentId;

    /** 总分 */
    private Integer totalScore;

    /** 严重程度等级 */
    private String severityLevel;

    /** 结果描述 */
    private String severityDesc;

    /** 答案JSON */
    private String answers;

    /** AI分析结果 */
    private String aiAnalysis;

    /** AI建议 */
    private String aiSuggestion;

    /** 答题耗时(秒) */
    private Integer durationSeconds;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    public Long getAssessmentId() { return assessmentId; }
    public void setAssessmentId(Long assessmentId) { this.assessmentId = assessmentId; }
    public Integer getTotalScore() { return totalScore; }
    public void setTotalScore(Integer totalScore) { this.totalScore = totalScore; }
    public String getSeverityLevel() { return severityLevel; }
    public void setSeverityLevel(String severityLevel) { this.severityLevel = severityLevel; }
    public String getSeverityDesc() { return severityDesc; }
    public void setSeverityDesc(String severityDesc) { this.severityDesc = severityDesc; }
    public String getAnswers() { return answers; }
    public void setAnswers(String answers) { this.answers = answers; }
    public String getAiAnalysis() { return aiAnalysis; }
    public void setAiAnalysis(String aiAnalysis) { this.aiAnalysis = aiAnalysis; }
    public String getAiSuggestion() { return aiSuggestion; }
    public void setAiSuggestion(String aiSuggestion) { this.aiSuggestion = aiSuggestion; }
    public Integer getDurationSeconds() { return durationSeconds; }
    public void setDurationSeconds(Integer durationSeconds) { this.durationSeconds = durationSeconds; }
    public Date getCreateTime() { return createTime; }
    public void setCreateTime(Date createTime) { this.createTime = createTime; }
}
