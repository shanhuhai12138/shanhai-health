package com.health.reservation.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.health.common.annotation.Excel;

/**
 * 体检报告明细对象 t_report_item
 *
 * @author ruoyi
 * @date 2026-07-02
 */
public class TReportItem
{
    private static final long serialVersionUID = 1L;

    /** 报告明细主键ID */
    private Long id;

    /** 关联体检报告ID */
    private Long reportId;

    /** 关联检查项ID */
    private Long checkitemId;

    /** 关联检查组ID */
    private Long checkgroupId;

    /** 检查项名称 */
    @Excel(name = "检查项名称")
    private String checkitemName;

    /** 检查组名称 */
    @Excel(name = "检查组名称")
    private String checkgroupName;

    /** 检查结果/数值 */
    @Excel(name = "检查结果")
    private String result;

    /** 单位 */
    @Excel(name = "单位")
    private String unit;

    /** 参考范围 */
    @Excel(name = "参考范围")
    private String normalRange;

    /** 异常标记（0正常 1偏高 2偏低） */
    @Excel(name = "异常标记", readConverterExp = "0=正常,1=偏高,2=偏低")
    private String abnormalFlag;

    /** 异常标记文字（如：↑ 偏高） */
    private String abnormalMark;

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

    public void setReportId(Long reportId)
    {
        this.reportId = reportId;
    }

    public Long getReportId()
    {
        return reportId;
    }

    public void setCheckitemId(Long checkitemId)
    {
        this.checkitemId = checkitemId;
    }

    public Long getCheckitemId()
    {
        return checkitemId;
    }

    public void setCheckgroupId(Long checkgroupId)
    {
        this.checkgroupId = checkgroupId;
    }

    public Long getCheckgroupId()
    {
        return checkgroupId;
    }

    public void setCheckitemName(String checkitemName)
    {
        this.checkitemName = checkitemName;
    }

    public String getCheckitemName()
    {
        return checkitemName;
    }

    public void setCheckgroupName(String checkgroupName)
    {
        this.checkgroupName = checkgroupName;
    }

    public String getCheckgroupName()
    {
        return checkgroupName;
    }

    public void setResult(String result)
    {
        this.result = result;
    }

    public String getResult()
    {
        return result;
    }

    public void setUnit(String unit)
    {
        this.unit = unit;
    }

    public String getUnit()
    {
        return unit;
    }

    public void setNormalRange(String normalRange)
    {
        this.normalRange = normalRange;
    }

    public String getNormalRange()
    {
        return normalRange;
    }

    public void setAbnormalFlag(String abnormalFlag)
    {
        this.abnormalFlag = abnormalFlag;
    }

    public String getAbnormalFlag()
    {
        return abnormalFlag;
    }

    public void setAbnormalMark(String abnormalMark)
    {
        this.abnormalMark = abnormalMark;
    }

    public String getAbnormalMark()
    {
        return abnormalMark;
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
            .append("reportId", getReportId())
            .append("checkitemId", getCheckitemId())
            .append("checkgroupId", getCheckgroupId())
            .append("checkitemName", getCheckitemName())
            .append("checkgroupName", getCheckgroupName())
            .append("result", getResult())
            .append("unit", getUnit())
            .append("normalRange", getNormalRange())
            .append("abnormalFlag", getAbnormalFlag())
            .append("abnormalMark", getAbnormalMark())
            .append("createTime", getCreateTime())
            .toString();
    }
}
