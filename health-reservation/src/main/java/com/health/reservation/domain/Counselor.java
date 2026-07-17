package com.health.reservation.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.health.common.annotation.Excel;
import com.health.common.core.domain.BaseEntity;

/**
 * 咨询师对象 counselor
 *
 * @author ruoyi
 * @date 2026-07-09
 */
public class Counselor extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 咨询师ID */
    private Long id;

    /** 关联用户ID */
    @Excel(name = "用户ID")
    private Long userId;

    /** 真实姓名 */
    @Excel(name = "真实姓名")
    private String realName;

    /** 头像URL */
    @Excel(name = "头像URL")
    private String avatar;

    /** 职称 */
    @Excel(name = "职称")
    private String title;

    /** 擅长领域 */
    @Excel(name = "擅长领域")
    private String specialties;

    /** 从业年限 */
    @Excel(name = "从业年限")
    private Long experienceYears;

    /** 满意度 */
    @Excel(name = "满意度")
    private Double satisfactionRate;

    /** 咨询次数 */
    @Excel(name = "咨询次数")
    private Long consultationCount;

    /** 个人简介 */
    @Excel(name = "个人简介")
    private String bio;

    /** 教育背景 */
    @Excel(name = "教育背景")
    private String education;

    /** 每小时费率 */
    @Excel(name = "每小时费率")
    private Double hourlyRate;

    /** 状态（0正常 1停用） */
    @Excel(name = "状态", readConverterExp = "0=正常,1=停用")
    private String status;

    /** 是否推荐（0否 1是） */
    @Excel(name = "是否推荐", readConverterExp = "0=否,1=是")
    private String isFeatured;

    /** 排序 */
    @Excel(name = "排序")
    private Long sortOrder;

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

    public void setRealName(String realName)
    {
        this.realName = realName;
    }

    public String getRealName()
    {
        return realName;
    }

    public void setAvatar(String avatar)
    {
        this.avatar = avatar;
    }

    public String getAvatar()
    {
        return avatar;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getTitle()
    {
        return title;
    }

    public void setSpecialties(String specialties)
    {
        this.specialties = specialties;
    }

    public String getSpecialties()
    {
        return specialties;
    }

    public void setExperienceYears(Long experienceYears)
    {
        this.experienceYears = experienceYears;
    }

    public Long getExperienceYears()
    {
        return experienceYears;
    }

    public void setSatisfactionRate(Double satisfactionRate)
    {
        this.satisfactionRate = satisfactionRate;
    }

    public Double getSatisfactionRate()
    {
        return satisfactionRate;
    }

    public void setConsultationCount(Long consultationCount)
    {
        this.consultationCount = consultationCount;
    }

    public Long getConsultationCount()
    {
        return consultationCount;
    }

    public void setBio(String bio)
    {
        this.bio = bio;
    }

    public String getBio()
    {
        return bio;
    }

    public void setEducation(String education)
    {
        this.education = education;
    }

    public String getEducation()
    {
        return education;
    }

    public void setHourlyRate(Double hourlyRate)
    {
        this.hourlyRate = hourlyRate;
    }

    public Double getHourlyRate()
    {
        return hourlyRate;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getStatus()
    {
        return status;
    }

    public void setIsFeatured(String isFeatured)
    {
        this.isFeatured = isFeatured;
    }

    public String getIsFeatured()
    {
        return isFeatured;
    }

    public void setSortOrder(Long sortOrder)
    {
        this.sortOrder = sortOrder;
    }

    public Long getSortOrder()
    {
        return sortOrder;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("realName", getRealName())
            .append("avatar", getAvatar())
            .append("title", getTitle())
            .append("specialties", getSpecialties())
            .append("experienceYears", getExperienceYears())
            .append("satisfactionRate", getSatisfactionRate())
            .append("consultationCount", getConsultationCount())
            .append("bio", getBio())
            .append("education", getEducation())
            .append("hourlyRate", getHourlyRate())
            .append("status", getStatus())
            .append("isFeatured", getIsFeatured())
            .append("sortOrder", getSortOrder())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
