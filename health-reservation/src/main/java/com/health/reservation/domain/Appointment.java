package com.health.reservation.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.health.common.annotation.Excel;
import com.health.common.core.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * 预约记录对象 appointment
 *
 * @author ruoyi
 * @date 2026-07-09
 */
public class Appointment extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 预约ID */
    private Long id;

    /** 用户ID */
    @Excel(name = "用户ID")
    private Long userId;

    /** 咨询师ID */
    @Excel(name = "咨询师ID")
    private Long counselorId;

    /** 排班ID */
    @Excel(name = "排班ID")
    private Long scheduleId;

    /** 预约时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "预约时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date appointmentTime;

    /** 时长(分钟) */
    @Excel(name = "时长")
    private Long durationMinutes;

    /** 状态（0待确认 1已确认 2已完成 3已取消） */
    @Excel(name = "状态", readConverterExp = "0=待确认,1=已确认,2=已完成,3=已取消")
    private String status;

    /** 咨询方式（0线上 1线下） */
    @Excel(name = "咨询方式", readConverterExp = "0=线上,1=线下")
    private String consultationType;

    /** 备注 */
    @Excel(name = "备注")
    private String notes;

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

    public void setCounselorId(Long counselorId)
    {
        this.counselorId = counselorId;
    }

    public Long getCounselorId()
    {
        return counselorId;
    }

    public void setScheduleId(Long scheduleId)
    {
        this.scheduleId = scheduleId;
    }

    public Long getScheduleId()
    {
        return scheduleId;
    }

    public void setAppointmentTime(Date appointmentTime)
    {
        this.appointmentTime = appointmentTime;
    }

    public Date getAppointmentTime()
    {
        return appointmentTime;
    }

    public void setDurationMinutes(Long durationMinutes)
    {
        this.durationMinutes = durationMinutes;
    }

    public Long getDurationMinutes()
    {
        return durationMinutes;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getStatus()
    {
        return status;
    }

    public void setConsultationType(String consultationType)
    {
        this.consultationType = consultationType;
    }

    public String getConsultationType()
    {
        return consultationType;
    }

    public void setNotes(String notes)
    {
        this.notes = notes;
    }

    public String getNotes()
    {
        return notes;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("counselorId", getCounselorId())
            .append("scheduleId", getScheduleId())
            .append("appointmentTime", getAppointmentTime())
            .append("durationMinutes", getDurationMinutes())
            .append("status", getStatus())
            .append("consultationType", getConsultationType())
            .append("notes", getNotes())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
