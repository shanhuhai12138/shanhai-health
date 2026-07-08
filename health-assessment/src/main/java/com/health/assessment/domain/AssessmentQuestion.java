package com.health.assessment.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.health.common.annotation.Excel;

/**
 * 量表题目对象 assessment_question
 *
 * @author ruoyi
 * @date 2026-07-08
 */
public class AssessmentQuestion
{
    private static final long serialVersionUID = 1L;

    /** 题目ID */
    private Long id;

    /** 关联量表ID */
    @Excel(name = "量表ID")
    private Long assessmentId;

    /** 题号 */
    @Excel(name = "题号")
    private Integer questionNo;

    /** 题干 */
    @Excel(name = "题干")
    private String questionText;

    /** 题型：single/multiple/text */
    @Excel(name = "题型")
    private String questionType;

    /** 选项JSON */
    private String options;

    /** 是否反向计分（0否 1是） */
    @Excel(name = "反向计分")
    private String reverseScore;

    /** 排序 */
    @Excel(name = "排序")
    private Integer sortOrder;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }

    public void setAssessmentId(Long assessmentId)
    {
        this.assessmentId = assessmentId;
    }

    public Long getAssessmentId()
    {
        return assessmentId;
    }

    public void setQuestionNo(Integer questionNo)
    {
        this.questionNo = questionNo;
    }

    public Integer getQuestionNo()
    {
        return questionNo;
    }

    public void setQuestionText(String questionText)
    {
        this.questionText = questionText;
    }

    public String getQuestionText()
    {
        return questionText;
    }

    public void setQuestionType(String questionType)
    {
        this.questionType = questionType;
    }

    public String getQuestionType()
    {
        return questionType;
    }

    public void setOptions(String options)
    {
        this.options = options;
    }

    public String getOptions()
    {
        return options;
    }

    public void setReverseScore(String reverseScore)
    {
        this.reverseScore = reverseScore;
    }

    public String getReverseScore()
    {
        return reverseScore;
    }

    public void setSortOrder(Integer sortOrder)
    {
        this.sortOrder = sortOrder;
    }

    public Integer getSortOrder()
    {
        return sortOrder;
    }

    public void setCreateTime(Date createTime)
    {
        this.createTime = createTime;
    }

    public Date getCreateTime()
    {
        return createTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("assessmentId", getAssessmentId())
            .append("questionNo", getQuestionNo())
            .append("questionText", getQuestionText())
            .append("questionType", getQuestionType())
            .append("options", getOptions())
            .append("reverseScore", getReverseScore())
            .append("sortOrder", getSortOrder())
            .append("createTime", getCreateTime())
            .toString();
    }
}
