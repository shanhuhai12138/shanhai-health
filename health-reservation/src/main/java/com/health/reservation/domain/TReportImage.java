package com.health.reservation.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.health.common.annotation.Excel;
import com.health.common.core.domain.BaseEntity;

/**
 * 体检报告图像对象 t_report_image
 * 
 * @author ruoyi
 * @date 2026-07-17
 */
public class TReportImage extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 图片主键ID */
    private Long id;

    /** 关联体检报告ID */
    @Excel(name = "体检报告ID")
    private Long reportId;

    /** 关联检查项ID */
    @Excel(name = "检查项ID")
    private Long checkitemId;

    /** 图片路径 */
    @Excel(name = "图片路径")
    private String imagePath;

    /** 图片描述 */
    @Excel(name = "图片描述")
    private String imageDesc;

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

    public void setImagePath(String imagePath)
    {
        this.imagePath = imagePath;
    }

    public String getImagePath()
    {
        return imagePath;
    }

    public void setImageDesc(String imageDesc)
    {
        this.imageDesc = imageDesc;
    }

    public String getImageDesc()
    {
        return imageDesc;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("reportId", getReportId())
            .append("checkitemId", getCheckitemId())
            .append("imagePath", getImagePath())
            .append("imageDesc", getImageDesc())
            .append("createTime", getCreateTime())
            .toString();
    }
}
