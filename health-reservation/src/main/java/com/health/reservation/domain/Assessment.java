package com.health.reservation.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.health.common.annotation.Excel;
import com.health.common.core.domain.BaseEntity;

/**
 * 心理量表对象 assessment
 *
 * @author ruoyi
 * @date 2026-07-09
 */
public class Assessment extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 量表ID */
    private Long id;

    /** 量表编码 */
    @Excel(name = "量表编码")
    private String code;

    /** 量表名称 */
    @Excel(name = "量表名称")
    private String name;

    /** 量表描述 */
    @Excel(name = "量表描述")
    private String description;

    /** 总分 */
    @Excel(name = "总分")
    private Long totalScore;

    /** 严重程度分级 */
    @Excel(name = "严重程度分级")
    private String severityLevels;

    /** 预计用时(分钟) */
    @Excel(name = "预计用时")
    private Long estimatedDuration;

    /** 分类 */
    @Excel(name = "分类")
    private String category;

    /** 状态（0正常 1停用） */
    @Excel(name = "状态", readConverterExp = "0=正常,1=停用")
    private String status;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    public String getCode()
    {
        return code;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getDescription()
    {
        return description;
    }

    public void setTotalScore(Long totalScore)
    {
        this.totalScore = totalScore;
    }

    public Long getTotalScore()
    {
        return totalScore;
    }

    public void setSeverityLevels(String severityLevels)
    {
        this.severityLevels = severityLevels;
    }

    public String getSeverityLevels()
    {
        return severityLevels;
    }

    public void setEstimatedDuration(Long estimatedDuration)
    {
        this.estimatedDuration = estimatedDuration;
    }

    public Long getEstimatedDuration()
    {
        return estimatedDuration;
    }

    public void setCategory(String category)
    {
        this.category = category;
    }

    public String getCategory()
    {
        return category;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getStatus()
    {
        return status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("code", getCode())
            .append("name", getName())
            .append("description", getDescription())
            .append("totalScore", getTotalScore())
            .append("severityLevels", getSeverityLevels())
            .append("estimatedDuration", getEstimatedDuration())
            .append("category", getCategory())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
