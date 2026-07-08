package com.health.counseling.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.health.common.annotation.Excel;
import com.health.common.core.domain.BaseEntity;

public class Counselor extends BaseEntity
{
    private static final long serialVersionUID = 1L;
    private Long id;
    @Excel(name = "用户ID") private Long userId;
    @Excel(name = "姓名") private String realName;
    @Excel(name = "头像路径") private String avatar;
    @Excel(name = "职称") private String title;
    private String specialties;
    @Excel(name = "从业年限") private Integer experienceYears;
    @Excel(name = "好评率") private Double satisfactionRate;
    @Excel(name = "累计咨询次数") private Integer consultationCount;
    private String bio;
    private String education;
    @Excel(name = "时薪") private Integer hourlyRate;
    @Excel(name = "状态") private String status;
    @Excel(name = "是否推荐") private String isFeatured;
    private Integer sortOrder;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    public String getRealName() { return realName; }
    public void setRealName(String realName) { this.realName = realName; }
    public String getAvatar() { return avatar; }
    public void setAvatar(String avatar) { this.avatar = avatar; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getSpecialties() { return specialties; }
    public void setSpecialties(String specialties) { this.specialties = specialties; }
    public Integer getExperienceYears() { return experienceYears; }
    public void setExperienceYears(Integer experienceYears) { this.experienceYears = experienceYears; }
    public Double getSatisfactionRate() { return satisfactionRate; }
    public void setSatisfactionRate(Double satisfactionRate) { this.satisfactionRate = satisfactionRate; }
    public Integer getConsultationCount() { return consultationCount; }
    public void setConsultationCount(Integer consultationCount) { this.consultationCount = consultationCount; }
    public String getBio() { return bio; }
    public void setBio(String bio) { this.bio = bio; }
    public String getEducation() { return education; }
    public void setEducation(String education) { this.education = education; }
    public Integer getHourlyRate() { return hourlyRate; }
    public void setHourlyRate(Integer hourlyRate) { this.hourlyRate = hourlyRate; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getIsFeatured() { return isFeatured; }
    public void setIsFeatured(String isFeatured) { this.isFeatured = isFeatured; }
    public Integer getSortOrder() { return sortOrder; }
    public void setSortOrder(Integer sortOrder) { this.sortOrder = sortOrder; }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId()).append("realName", getRealName())
            .append("title", getTitle()).append("experienceYears", getExperienceYears())
            .append("status", getStatus()).toString();
    }
}
