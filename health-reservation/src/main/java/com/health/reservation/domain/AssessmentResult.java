package com.health.reservation.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.health.common.annotation.Excel;
import com.health.common.core.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * 测评结果对象 assessment_result
 *
 * @author ruoyi
 * @date 2026-07-09
 */
public class AssessmentResult extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 结果ID */
    private Long id;

    /** 用户ID */
    @Excel(name = "用户ID")
    private Long userId;

    /** 量表ID */
    @Excel(name = "量表ID")
    private Long assessmentId;

    /** 总分 */
    @Excel(name = "总分")
    private Long totalScore;

    /** 严重程度等级 */
    @Excel(name = "严重程度等级")
    private String severityLevel;

    /** 严重程度描述 */
    @Excel(name = "严重程度描述")
    private String severityDesc;

    /** 答题记录JSON */
    @Excel(name = "答题记录")
    private String answers;

    /** AI分析 */
    @Excel(name = "AI分析")
    private String aiAnalysis;

    /** AI建议 */
    @Excel(name = "AI建议")
    private String aiSuggestion;

    /** 用时(秒) */
    @Excel(name = "用时")
    private Long durationSeconds;


    /** 量表名称(查询结果附加字段) */
    private String assessmentName;

    /** 量表编码(查询结果附加字段) */
    private String assessmentCode;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }

    public void setUserId(Long userId)
    {
        this.userId = userId;
    }

    public Long getUserId()
    {
        return userId;
    }

    public void setAssessmentId(Long assessmentId)
    {
        this.assessmentId = assessmentId;
    }

    public Long getAssessmentId()
    {
        return assessmentId;
    }

    public void setTotalScore(Long totalScore)
    {
        this.totalScore = totalScore;
    }

    public Long getTotalScore()
    {
        return totalScore;
    }

    public void setSeverityLevel(String severityLevel)
    {
        this.severityLevel = severityLevel;
    }

    public String getSeverityLevel()
    {
        return severityLevel;
    }

    public void setSeverityDesc(String severityDesc)
    {
        this.severityDesc = severityDesc;
    }

    public String getSeverityDesc()
    {
        return severityDesc;
    }

    public void setAnswers(String answers)
    {
        this.answers = answers;
    }

    public String getAnswers()
    {
        return answers;
    }

    public void setAiAnalysis(String aiAnalysis)
    {
        this.aiAnalysis = aiAnalysis;
    }

    public String getAiAnalysis()
    {
        return aiAnalysis;
    }

    public void setAiSuggestion(String aiSuggestion)
    {
        this.aiSuggestion = aiSuggestion;
    }

    public String getAiSuggestion()
    {
        return aiSuggestion;
    }

    public void setDurationSeconds(Long durationSeconds)
    {
        this.durationSeconds = durationSeconds;
    }

    public Long getDurationSeconds()
    {
        return durationSeconds;
    }


    public void setAssessmentName(String assessmentName)
    {
        this.assessmentName = assessmentName;
    }

    public String getAssessmentName()
    {
        return assessmentName;
    }

    public void setAssessmentCode(String assessmentCode)
    {
        this.assessmentCode = assessmentCode;
    }

    public String getAssessmentCode()
    {
        return assessmentCode;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("assessmentId", getAssessmentId())
            .append("totalScore", getTotalScore())
            .append("severityLevel", getSeverityLevel())
            .append("severityDesc", getSeverityDesc())
            .append("answers", getAnswers())
            .append("aiAnalysis", getAiAnalysis())
            .append("aiSuggestion", getAiSuggestion())
            .append("durationSeconds", getDurationSeconds())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("assessmentName", getAssessmentName())
            .append("assessmentCode", getAssessmentCode())
            .toString();
    }
}
