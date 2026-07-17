package com.health.reservation.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.health.common.annotation.Excel;
import com.health.common.core.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * 情绪记录对象 mood_record
 *
 * @author ruoyi
 * @date 2026-07-09
 */
public class MoodRecord extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 记录ID */
    private Long id;

    /** 用户ID */
    @Excel(name = "用户ID")
    private Long userId;

    /** 情绪评分 */
    @Excel(name = "情绪评分")
    private Long moodScore;

    /** 情绪标签 */
    @Excel(name = "情绪标签")
    private String moodLabel;

    /** 情绪表情 */
    @Excel(name = "情绪表情")
    private String moodEmoji;

    /** 精力水平 */
    @Excel(name = "精力水平")
    private Long energyLevel;

    /** 睡眠时长(小时) */
    @Excel(name = "睡眠时长")
    private Double sleepHours;

    /** 运动时长(分钟) */
    @Excel(name = "运动时长")
    private Long exerciseMinutes;

    /** 备注 */
    @Excel(name = "备注")
    private String notes;

    /** 标签JSON */
    @Excel(name = "标签")
    private String tags;

    /** 记录时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "记录时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date recordTime;

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

    public void setMoodScore(Long moodScore)
    {
        this.moodScore = moodScore;
    }

    public Long getMoodScore()
    {
        return moodScore;
    }

    public void setMoodLabel(String moodLabel)
    {
        this.moodLabel = moodLabel;
    }

    public String getMoodLabel()
    {
        return moodLabel;
    }

    public void setMoodEmoji(String moodEmoji)
    {
        this.moodEmoji = moodEmoji;
    }

    public String getMoodEmoji()
    {
        return moodEmoji;
    }

    public void setEnergyLevel(Long energyLevel)
    {
        this.energyLevel = energyLevel;
    }

    public Long getEnergyLevel()
    {
        return energyLevel;
    }

    public void setSleepHours(Double sleepHours)
    {
        this.sleepHours = sleepHours;
    }

    public Double getSleepHours()
    {
        return sleepHours;
    }

    public void setExerciseMinutes(Long exerciseMinutes)
    {
        this.exerciseMinutes = exerciseMinutes;
    }

    public Long getExerciseMinutes()
    {
        return exerciseMinutes;
    }

    public void setNotes(String notes)
    {
        this.notes = notes;
    }

    public String getNotes()
    {
        return notes;
    }

    public void setTags(String tags)
    {
        this.tags = tags;
    }

    public String getTags()
    {
        return tags;
    }

    public void setRecordTime(Date recordTime)
    {
        this.recordTime = recordTime;
    }

    public Date getRecordTime()
    {
        return recordTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("moodScore", getMoodScore())
            .append("moodLabel", getMoodLabel())
            .append("moodEmoji", getMoodEmoji())
            .append("energyLevel", getEnergyLevel())
            .append("sleepHours", getSleepHours())
            .append("exerciseMinutes", getExerciseMinutes())
            .append("notes", getNotes())
            .append("tags", getTags())
            .append("recordTime", getRecordTime())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
