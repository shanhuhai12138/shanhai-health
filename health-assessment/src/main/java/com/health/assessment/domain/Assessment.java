package com.health.assessment.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.health.common.annotation.Excel;
import com.health.common.core.domain.BaseEntity;

/**
 * 心理量表对象 assessment
 *
 * @author ruoyi
 * @date 2026-07-08
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

    /** 量表说明 */
    private String description;

    /** 满分 */
    @Excel(name = "满分")
    private Integer totalScore;

    /** 预计完成时间(分钟) */
    @Excel(name = "预计完成时间")
    private Integer estimatedDuration;

    /** 分类 */
    @Excel(name = "分类")
    private String category;

    /** 状态（0启用 1禁用） */
    @Excel(name = "状态")
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

    public void setTotalScore(Integer totalScore)
    {
        this.totalScore = totalScore;
    }

    public Integer getTotalScore()
    {
        return totalScore;
    }

    public void setEstimatedDuration(Integer estimatedDuration)
    {
        this.estimatedDuration = estimatedDuration;
    }

    public Integer getEstimatedDuration()
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
