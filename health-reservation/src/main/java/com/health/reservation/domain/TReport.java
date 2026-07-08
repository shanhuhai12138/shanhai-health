package com.health.reservation.domain;

import java.util.Date;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.health.common.annotation.Excel;
import com.health.common.core.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 体检报告对象 t_report
 *
 * @author ruoyi
 * @date 2026-07-02
 */
public class TReport extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 体检报告主键ID */
    private Long id;

    /** 体检报告编号 */
    @Excel(name = "体检报告编号")
    private String reportNo;

    /** 体检人姓名 */
    @Excel(name = "体检人姓名")
    private String memberName;

    /** 体检人手机号 */
    @Excel(name = "体检人手机号")
    private String memberPhone;

    /** 体检人身份证号（列表页脱敏，详情展示原始值） */
    @Excel(name = "身份证号")
    @JsonIgnore
    private String memberIdcard;

    /** 性别（0男 1女 2不限） */
    @Excel(name = "性别", readConverterExp = "0=男,1=女,2=不限")
    private String memberSex;

    /** 年龄 */
    @Excel(name = "年龄")
    private Integer memberAge;

    /** 关联套餐ID */
    private Long setmealId;

    /** 套餐名称 */
    @Excel(name = "套餐名称")
    private String setmealName;

    /** 关联检查组ID列表（非数据库字段，用于前端回显） */
    @JsonIgnore
    private Long[] checkgroupIds;

    /** 体检日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "体检日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date orderDate;

    /** 报告状态（0待录入 1已审核 2已发布 3已归档） */
    @Excel(name = "报告状态", readConverterExp = "0=待录入,1=已审核,2=已发布,3=已归档")
    private String reportStatus;

    /** 医生建议/总结 */
    private String doctorAdvice;

    /** 审核医生ID */
    @JsonIgnore
    private Long reviewerId;

    /** 审核医生姓名 */
    @Excel(name = "审核医生")
    private String reviewerName;

    /** 审核时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "审核时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date reviewTime;

    /** 发布医生ID */
    @JsonIgnore
    private Long publisherId;

    /** 发布医生姓名 */
    @Excel(name = "发布医生")
    private String publisherName;

    /** 发布时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "发布时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date publishTime;

    /** 归档时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "归档时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date archivedTime;

    /** 报告明细列表（非数据库字段，用于组装详情响应） */
    @JsonIgnore
    private List<TReportItem> reportItems;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

    public void setDelFlag(String delFlag)
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag()
    {
        return delFlag;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }

    public void setReportNo(String reportNo)
    {
        this.reportNo = reportNo;
    }

    public String getReportNo()
    {
        return reportNo;
    }

    public void setMemberName(String memberName)
    {
        this.memberName = memberName;
    }

    public String getMemberName()
    {
        return memberName;
    }

    public void setMemberPhone(String memberPhone)
    {
        this.memberPhone = memberPhone;
    }

    public String getMemberPhone()
    {
        return memberPhone;
    }

    public void setMemberIdcard(String memberIdcard)
    {
        this.memberIdcard = memberIdcard;
    }

    public String getMemberIdcard()
    {
        return memberIdcard;
    }

    public void setMemberSex(String memberSex)
    {
        this.memberSex = memberSex;
    }

    public String getMemberSex()
    {
        return memberSex;
    }

    public void setMemberAge(Integer memberAge)
    {
        this.memberAge = memberAge;
    }

    public Integer getMemberAge()
    {
        return memberAge;
    }

    public void setSetmealId(Long setmealId)
    {
        this.setmealId = setmealId;
    }

    public Long getSetmealId()
    {
        return setmealId;
    }

    public void setSetmealName(String setmealName)
    {
        this.setmealName = setmealName;
    }

    public String getSetmealName()
    {
        return setmealName;
    }

    public void setCheckgroupIds(Long[] checkgroupIds)
    {
        this.checkgroupIds = checkgroupIds;
    }

    public Long[] getCheckgroupIds()
    {
        return checkgroupIds;
    }

    public void setOrderDate(Date orderDate)
    {
        this.orderDate = orderDate;
    }

    public Date getOrderDate()
    {
        return orderDate;
    }

    public void setReportStatus(String reportStatus)
    {
        this.reportStatus = reportStatus;
    }

    public String getReportStatus()
    {
        return reportStatus;
    }

    public void setDoctorAdvice(String doctorAdvice)
    {
        this.doctorAdvice = doctorAdvice;
    }

    public String getDoctorAdvice()
    {
        return doctorAdvice;
    }

    public void setReviewerId(Long reviewerId)
    {
        this.reviewerId = reviewerId;
    }

    public Long getReviewerId()
    {
        return reviewerId;
    }

    public void setReviewerName(String reviewerName)
    {
        this.reviewerName = reviewerName;
    }

    public String getReviewerName()
    {
        return reviewerName;
    }

    public void setReviewTime(Date reviewTime)
    {
        this.reviewTime = reviewTime;
    }

    public Date getReviewTime()
    {
        return reviewTime;
    }

    public void setPublisherId(Long publisherId)
    {
        this.publisherId = publisherId;
    }

    public Long getPublisherId()
    {
        return publisherId;
    }

    public void setPublisherName(String publisherName)
    {
        this.publisherName = publisherName;
    }

    public String getPublisherName()
    {
        return publisherName;
    }

    public void setPublishTime(Date publishTime)
    {
        this.publishTime = publishTime;
    }

    public Date getPublishTime()
    {
        return publishTime;
    }

    public void setArchivedTime(Date archivedTime)
    {
        this.archivedTime = archivedTime;
    }

    public Date getArchivedTime()
    {
        return archivedTime;
    }

    public void setReportItems(List<TReportItem> reportItems)
    {
        this.reportItems = reportItems;
    }

    public List<TReportItem> getReportItems()
    {
        return reportItems;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("reportNo", getReportNo())
            .append("memberName", getMemberName())
            .append("memberPhone", getMemberPhone())
            .append("memberSex", getMemberSex())
            .append("memberAge", getMemberAge())
            .append("setmealId", getSetmealId())
            .append("setmealName", getSetmealName())
            .append("orderDate", getOrderDate())
            .append("reportStatus", getReportStatus())
            .append("doctorAdvice", getDoctorAdvice())
            .append("reviewerName", getReviewerName())
            .append("reviewTime", getReviewTime())
            .append("publisherName", getPublisherName())
            .append("publishTime", getPublishTime())
            .append("archivedTime", getArchivedTime())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
