package com.health.reservation.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.health.common.annotation.Excel;
import com.health.common.core.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * 咨询师排班对象 counselor_schedule
 *
 * @author ruoyi
 * @date 2026-07-09
 */
public class CounselorSchedule extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 排班ID */
    private Long id;

    /** 咨询师ID */
    @Excel(name = "咨询师ID")
    private Long counselorId;

    /** 排班日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "排班日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date scheduleDate;

    /** 开始时间 */
    @Excel(name = "开始时间")
    private String startTime;

    /** 结束时间 */
    @Excel(name = "结束时间")
    private String endTime;

    /** 每时段时长(分钟) */
    @Excel(name = "每时段时长")
    private Long slotDuration;

    /** 总时段数 */
    @Excel(name = "总时段数")
    private Long totalSlots;

    /** 可用时段数 */
    @Excel(name = "可用时段数")
    private Long availableSlots;

    /** 是否可用（0否 1是） */
    @Excel(name = "是否可用", readConverterExp = "0=否,1=是")
    private String isAvailable;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }

    public void setCounselorId(Long counselorId)
    {
        this.counselorId = counselorId;
    }

    public Long getCounselorId()
    {
        return counselorId;
    }

    public void setScheduleDate(Date scheduleDate)
    {
        this.scheduleDate = scheduleDate;
    }

    public Date getScheduleDate()
    {
        return scheduleDate;
    }

    public void setStartTime(String startTime)
    {
        this.startTime = startTime;
    }

    public String getStartTime()
    {
        return startTime;
    }

    public void setEndTime(String endTime)
    {
        this.endTime = endTime;
    }

    public String getEndTime()
    {
        return endTime;
    }

    public void setSlotDuration(Long slotDuration)
    {
        this.slotDuration = slotDuration;
    }

    public Long getSlotDuration()
    {
        return slotDuration;
    }

    public void setTotalSlots(Long totalSlots)
    {
        this.totalSlots = totalSlots;
    }

    public Long getTotalSlots()
    {
        return totalSlots;
    }

    public void setAvailableSlots(Long availableSlots)
    {
        this.availableSlots = availableSlots;
    }

    public Long getAvailableSlots()
    {
        return availableSlots;
    }

    public void setIsAvailable(String isAvailable)
    {
        this.isAvailable = isAvailable;
    }

    public String getIsAvailable()
    {
        return isAvailable;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("counselorId", getCounselorId())
            .append("scheduleDate", getScheduleDate())
            .append("startTime", getStartTime())
            .append("endTime", getEndTime())
            .append("slotDuration", getSlotDuration())
            .append("totalSlots", getTotalSlots())
            .append("availableSlots", getAvailableSlots())
            .append("isAvailable", getIsAvailable())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
