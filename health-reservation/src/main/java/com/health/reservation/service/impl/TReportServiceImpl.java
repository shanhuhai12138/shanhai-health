package com.health.reservation.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import com.health.common.utils.DateUtils;
import com.health.common.utils.SecurityUtils;
import com.health.reservation.domain.TCheckgroup;
import com.health.reservation.domain.TCheckitem;
import com.health.reservation.domain.TReport;
import com.health.reservation.domain.TReportItem;
import com.health.reservation.domain.TSetmeal;
import com.health.reservation.mapper.TCheckgroupMapper;
import com.health.reservation.mapper.TCheckitemMapper;
import com.health.reservation.mapper.TReportItemMapper;
import com.health.reservation.mapper.TReportMapper;
import com.health.reservation.mapper.TSetmealMapper;
import com.health.reservation.service.ITReportItemService;
import com.health.reservation.service.ITReportService;
import com.health.reservation.vo.ReportDetailVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.health.common.exception.ServiceException;

/**
 * 体检报告Service业务层处理
 *
 * @author ruoyi
 * @date 2026-07-02
 */
@Service
public class TReportServiceImpl implements ITReportService
{
    public static final String STATUS_DRAFT = "0";   // 待录入
    public static final String STATUS_AUDITED = "1";  // 已审核
    public static final String STATUS_PUBLISHED = "2";// 已发布
    public static final String STATUS_ARCHIVED = "3"; // 已归档

    @Autowired
    private TReportMapper tReportMapper;

    @Autowired
    private TReportItemMapper tReportItemMapper;

    @Autowired
    private TCheckitemMapper tCheckitemMapper;

    @Autowired
    private TCheckgroupMapper tCheckgroupMapper;

    @Autowired
    private TSetmealMapper tSetmealMapper;

    /**
     * 查询体检报告
     */
    @Override
    public TReport selectTReportById(Long id)
    {
        return tReportMapper.selectTReportById(id);
    }

    /**
     * 查询体检报告列表
     */
    @Override
    public List<TReport> selectTReportList(TReport tReport)
    {
        List<TReport> list = tReportMapper.selectTReportList(tReport);
        // 如果setmealName为空，回填套餐名称
        if (list != null)
        {
            for (TReport report : list)
            {
                if ((report.getSetmealName() == null || report.getSetmealName().isEmpty())
                    && report.getSetmealId() != null)
                {
                    TSetmeal setmeal = tSetmealMapper.selectTSetmealById(report.getSetmealId());
                    if (setmeal != null)
                    {
                        report.setSetmealName(setmeal.getName());
                    }
                }
            }
        }
        return list;
    }

    /**
     * 新增体检报告
     */
    @Override
    @Transactional
    public int insertTReport(TReport tReport)
    {
        // 自动生成报告编号
        if (tReport.getReportNo() == null || tReport.getReportNo().isEmpty())
        {
            tReport.setReportNo(generateReportNo());
        }
        // 默认状态为待录入
        if (tReport.getReportStatus() == null || tReport.getReportStatus().isEmpty())
        {
            tReport.setReportStatus(STATUS_DRAFT);
        }
        // 根据套餐ID回填套餐名称
        if (tReport.getSetmealId() != null)
        {
            TSetmeal setmeal = tSetmealMapper.selectTSetmealById(tReport.getSetmealId());
            if (setmeal != null)
            {
                tReport.setSetmealName(setmeal.getName());
            }
        }
        tReport.setCreateTime(DateUtils.getNowDate());
        tReport.setCreateBy(SecurityUtils.getUsername());
        int rows = tReportMapper.insertTReport(tReport);
        // 插入报告明细
        if (rows > 0 && tReport.getReportItems() != null && !tReport.getReportItems().isEmpty())
        {
            for (TReportItem item : tReport.getReportItems())
            {
                item.setReportId(tReport.getId());
                item.setCreateTime(DateUtils.getNowDate());
            }
            batchInsertReportItems(tReport.getReportItems());
        }
        return rows;
    }

    /**
     * 修改体检报告
     */
    @Override
    @Transactional
    public int updateTReport(TReport tReport)
    {
        tReport.setUpdateTime(DateUtils.getNowDate());
        tReport.setUpdateBy(SecurityUtils.getUsername());
        // 先删除原有明细，再重新插入
        tReportItemMapper.deleteTReportItemByReportId(tReport.getId());
        if (tReport.getReportItems() != null && !tReport.getReportItems().isEmpty())
        {
            for (TReportItem item : tReport.getReportItems())
            {
                item.setReportId(tReport.getId());
                item.setCreateTime(DateUtils.getNowDate());
            }
            batchInsertReportItems(tReport.getReportItems());
        }
        return tReportMapper.updateTReport(tReport);
    }

    /**
     * 批量删除体检报告
     */
    @Override
    public int deleteTReportByIds(Long[] ids)
    {
        // 级联删除明细
        for (Long id : ids)
        {
            tReportItemMapper.deleteTReportItemByReportId(id);
        }
        return tReportMapper.deleteTReportByIds(ids);
    }

    /**
     * 删除体检报告信息
     */
    @Override
    @Transactional
    public int deleteTReportById(Long id)
    {
        tReportItemMapper.deleteTReportItemByReportId(id);
        return tReportMapper.deleteTReportById(id);
    }

    /**
     * 根据报告编号查询报告
     */
    @Override
    public TReport selectTReportByReportNo(String reportNo)
    {
        return tReportMapper.selectTReportByReportNo(reportNo);
    }

    /**
     * 根据手机号和身份证号查询报告列表（用户端）
     */
    @Override
    public List<TReport> selectReportByPhoneAndIdcard(String phone, String idcard)
    {
        return tReportMapper.selectTReportByPhoneAndIdcard(phone, idcard);
    }

    /**
     * 查询报告详情（含分组明细）
     */
    @Override
    public ReportDetailVO selectReportDetail(Long id)
    {
        ReportDetailVO vo = new ReportDetailVO();
        TReport report = tReportMapper.selectTReportById(id);
        if (report == null)
        {
            return vo;
        }
        vo.setReport(report);

        // 查询明细（含检查项参考范围）
        List<TReportItem> items = tReportItemMapper.selectTReportItemByReportIdWithDetail(id);

        // 按检查组分组
        List<ReportDetailVO.GroupedReportItem> grouped = new ArrayList<>();
        List<TReportItem> itemsByGroup = new ArrayList<>(items);

        // 找出涉及的检查组
        List<Long> groupIds = itemsByGroup.stream()
            .map(TReportItem::getCheckgroupId)
            .filter(gid -> gid != null)
            .distinct()
            .collect(Collectors.toList());

        for (Long groupId : groupIds)
        {
            ReportDetailVO.GroupedReportItem group = new ReportDetailVO.GroupedReportItem();
            TCheckgroup checkgroup = tCheckgroupMapper.selectTCheckgroupById(groupId);
            if (checkgroup != null)
            {
                group.setCheckgroupName(checkgroup.getName());
                group.setCheckgroupCode(checkgroup.getCode());
            }
            List<TReportItem> groupItems = itemsByGroup.stream()
                .filter(item -> groupId.equals(item.getCheckgroupId()))
                .collect(Collectors.toList());
            group.setItems(groupItems);
            grouped.add(group);
        }
        vo.setGroupedItems(grouped);
        return vo;
    }

    /**
     * 审核报告（状态 0→1）
     */
    @Override
    @Transactional
    public int auditReport(Long id, Long reviewerId, String reviewerName)
    {
        TReport report = tReportMapper.selectTReportById(id);
        if (report == null)
        {
            throw new ServiceException("报告不存在");
        }
        if (!STATUS_DRAFT.equals(report.getReportStatus()))
        {
            throw new ServiceException("当前状态不允许审核，仅待录入状态的报告可以审核");
        }
        report.setReviewerId(reviewerId);
        report.setReviewerName(reviewerName);
        report.setReviewTime(DateUtils.getNowDate());
        report.setReportStatus(STATUS_AUDITED);
        report.setUpdateTime(DateUtils.getNowDate());
        report.setUpdateBy(SecurityUtils.getUsername());
        return tReportMapper.updateTReport(report);
    }

    /**
     * 发布报告（状态 1→2）
     */
    @Override
    @Transactional
    public int publishReport(Long id, Long publisherId, String publisherName)
    {
        TReport report = tReportMapper.selectTReportById(id);
        if (report == null)
        {
            throw new ServiceException("报告不存在");
        }
        if (!STATUS_AUDITED.equals(report.getReportStatus()))
        {
            throw new ServiceException("当前状态不允许发布，仅已审核状态的报告可以发布");
        }
        report.setPublisherId(publisherId);
        report.setPublisherName(publisherName);
        report.setPublishTime(DateUtils.getNowDate());
        report.setReportStatus(STATUS_PUBLISHED);
        report.setUpdateTime(DateUtils.getNowDate());
        report.setUpdateBy(SecurityUtils.getUsername());
        return tReportMapper.updateTReport(report);
    }

    /**
     * 归档报告（状态 2→3）
     */
    @Override
    @Transactional
    public int archiveReport(Long id)
    {
        TReport report = tReportMapper.selectTReportById(id);
        if (report == null)
        {
            throw new ServiceException("报告不存在");
        }
        if (!STATUS_PUBLISHED.equals(report.getReportStatus()))
        {
            throw new ServiceException("当前状态不允许归档，仅已发布状态的报告可以归档");
        }
        report.setArchivedTime(DateUtils.getNowDate());
        report.setReportStatus(STATUS_ARCHIVED);
        report.setUpdateTime(DateUtils.getNowDate());
        report.setUpdateBy(SecurityUtils.getUsername());
        return tReportMapper.updateTReport(report);
    }

    /**
     * 生成报告编号
     * 格式：RP + yyyyMMdd + 4位序号
     */
    @Override
    public String generateReportNo()
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String dateStr = sdf.format(new Date());
        String prefix = "RP" + dateStr;

        // 查询今日最大编号，循环递增直到找到可用编号
        int seq = 1;
        while (seq <= 9999)
        {
            String candidate = prefix + String.format("%04d", seq);
            if (tReportMapper.selectTReportByReportNo(candidate) == null)
            {
                return candidate;
            }
            seq++;
        }
        throw new ServiceException("今日报告编号已达上限");
    }

    /**
     * 导入报告明细（批量插入）
     */
    @Override
    public void importReportItems(Long reportId, List<TReportItem> items)
    {
        if (items == null || items.isEmpty())
        {
            return;
        }
        for (TReportItem item : items)
        {
            item.setReportId(reportId);
            item.setCreateTime(DateUtils.getNowDate());
        }
        batchInsertReportItems(items);
    }

    /**
     * 批量插入报告明细
     */
    private int batchInsertReportItems(List<TReportItem> items)
    {
        return tReportItemMapper.batchInsertTReportItem(items);
    }

    /**
     * 计算异常标记
     * 对比 result 与 normalRange，自动标记偏高/偏低
     */
    @Override
    @Transactional
    public void computeAbnormalFlags(Long reportId)
    {
        List<TReportItem> items = tReportItemMapper.selectTReportItemByReportId(reportId);
        for (TReportItem item : items)
        {
            if (item.getResult() == null || item.getResult().trim().isEmpty())
            {
                continue;
            }
            // 如果已有手动设置的异常标记，跳过自动计算
            if (item.getAbnormalFlag() != null && !"0".equals(item.getAbnormalFlag()))
            {
                continue;
            }
            String normalRange = item.getNormalRange();
            if (normalRange == null || normalRange.trim().isEmpty())
            {
                continue;
            }
            // 尝试解析为数值比较
            BigDecimal result = parseNumericResult(item.getResult());
            if (result != null)
            {
                Range bounds = parseRange(normalRange);
                if (bounds != null)
                {
                    if (result.compareTo(bounds.getUpper()) > 0)
                    {
                        item.setAbnormalFlag("1");
                        item.setAbnormalMark("↑ 偏高");
                    }
                    else if (result.compareTo(bounds.getLower()) < 0)
                    {
                        item.setAbnormalFlag("2");
                        item.setAbnormalMark("↓ 偏低");
                    }
                    else
                    {
                        item.setAbnormalFlag("0");
                        item.setAbnormalMark("");
                    }
                }
            }
            // 非数值结果（如阴性/阳性）保持默认
        }
        // 批量更新
        for (TReportItem item : items)
        {
            if (item.getAbnormalFlag() != null)
            {
                tReportItemMapper.updateTReportItem(item);
            }
        }
    }

    /**
     * 解析数值型检查结果
     */
    private BigDecimal parseNumericResult(String result)
    {
        if (result == null)
        {
            return null;
        }
        String trimmed = result.trim();
        // 去掉常见的单位后缀（如 mg/dL、mmol/L 等）
        trimmed = trimmed.replaceAll("[a-zA-Z/]+", "").trim();
        if (trimmed.isEmpty())
        {
            return null;
        }
        try
        {
            return new BigDecimal(trimmed);
        }
        catch (NumberFormatException e)
        {
            return null;
        }
    }

    /**
     * 解析参考范围（支持 "3.5-5.3" 或 "3.5~5.3" 格式）
     */
    private Range parseRange(String range)
    {
        if (range == null)
        {
            return null;
        }
        String trimmed = range.trim();
        // 尝试用 "-" 分割
        String[] parts = trimmed.split("-|~|～");
        if (parts.length != 2)
        {
            return null;
        }
        try
        {
            BigDecimal lower = new BigDecimal(parts[0].trim());
            BigDecimal upper = new BigDecimal(parts[1].trim());
            return new Range(lower, upper);
        }
        catch (NumberFormatException e)
        {
            return null;
        }
    }

    /**
     * 数值范围
     */
    private static class Range
    {
        private final BigDecimal lower;
        private final BigDecimal upper;

        Range(BigDecimal lower, BigDecimal upper)
        {
            this.lower = lower;
            this.upper = upper;
        }

        public BigDecimal getLower() { return lower; }
        public BigDecimal getUpper() { return upper; }
    }
}
