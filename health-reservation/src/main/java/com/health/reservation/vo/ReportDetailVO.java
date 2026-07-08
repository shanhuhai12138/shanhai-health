package com.health.reservation.vo;

import com.health.reservation.domain.TReportItem;
import java.util.List;

/**
 * 体检报告详情 VO
 * 包含报告头部信息 + 按检查组分组的明细 + 医生签名信息
 *
 * @author ruoyi
 * @date 2026-07-02
 */
public class ReportDetailVO
{
    /** 报告头部信息 */
    private com.health.reservation.domain.TReport report;

    /** 按检查组分组的报告明细 */
    private List<GroupedReportItem> groupedItems;

    /** 报告图片列表 */
    private List<ReportImage> images;

    public com.health.reservation.domain.TReport getReport()
    {
        return report;
    }

    public void setReport(com.health.reservation.domain.TReport report)
    {
        this.report = report;
    }

    public List<GroupedReportItem> getGroupedItems()
    {
        return groupedItems;
    }

    public void setGroupedItems(List<GroupedReportItem> groupedItems)
    {
        this.groupedItems = groupedItems;
    }

    public List<ReportImage> getImages()
    {
        return images;
    }

    public void setImages(List<ReportImage> images)
    {
        this.images = images;
    }

    /**
     * 按检查组分组的报告明细
     */
    public static class GroupedReportItem
    {
        /** 检查组名称 */
        private String checkgroupName;

        /** 检查组编码 */
        private String checkgroupCode;

        /** 该组内的检查项列表 */
        private List<TReportItem> items;

        public String getCheckgroupName()
        {
            return checkgroupName;
        }

        public void setCheckgroupName(String checkgroupName)
        {
            this.checkgroupName = checkgroupName;
        }

        public String getCheckgroupCode()
        {
            return checkgroupCode;
        }

        public void setCheckgroupCode(String checkgroupCode)
        {
            this.checkgroupCode = checkgroupCode;
        }

        public List<TReportItem> getItems()
        {
            return items;
        }

        public void setItems(List<TReportItem> items)
        {
            this.items = items;
        }
    }

    /**
     * 报告图片信息
     */
    public static class ReportImage
    {
        private Long id;
        private Long checkitemId;
        private String checkitemName;
        private String imagePath;
        private String imageDesc;

        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }
        public Long getCheckitemId() { return checkitemId; }
        public void setCheckitemId(Long checkitemId) { this.checkitemId = checkitemId; }
        public String getCheckitemName() { return checkitemName; }
        public void setCheckitemName(String checkitemName) { this.checkitemName = checkitemName; }
        public String getImagePath() { return imagePath; }
        public void setImagePath(String imagePath) { this.imagePath = imagePath; }
        public String getImageDesc() { return imageDesc; }
        public void setImageDesc(String imageDesc) { this.imageDesc = imageDesc; }
    }
}
