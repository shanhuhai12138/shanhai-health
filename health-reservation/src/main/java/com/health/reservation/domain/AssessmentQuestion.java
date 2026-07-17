package com.health.reservation.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.health.common.annotation.Excel;
import com.health.common.core.domain.BaseEntity;

/**
 * 量表题目对象 assessment_question
 *
 * @author ruoyi
 * @date 2026-07-09
 */
public class AssessmentQuestion extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 题目ID */
    private Long id;

    /** 量表ID */
    @Excel(name = "量表ID")
    private Long assessmentId;

    /** 题号 */
    @Excel(name = "题号")
    private Integer questionNo;

    /** 题目内容 */
    @Excel(name = "题目内容")
    private String questionText;

    /** 题目类型（0单选 1多选 2填空） */
    @Excel(name = "题目类型", readConverterExp = "0=单选,1=多选,2=填空")
    private String questionType;

    /** 选项JSON */
    @Excel(name = "选项JSON")
    private String options;

    /** 是否反向计分（0否 1是） */
    @Excel(name = "是否反向计分", readConverterExp = "0=否,1=是")
    private String reverseScore;

    /** 排序 */
    @Excel(name = "排序")
    private Integer sortOrder;

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
