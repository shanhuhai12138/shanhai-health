package com.health.reservation.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import com.health.common.exception.ServiceException;
import com.health.common.utils.DateUtils;
import com.health.reservation.domain.TCheckgroup;
import com.health.reservation.domain.TReport;
import com.health.reservation.domain.TReportItem;
import com.health.reservation.domain.TSetmeal;
import com.health.reservation.domain.TSetmealCheckgroup;
import com.health.reservation.mapper.TCheckgroupMapper;
import com.health.reservation.mapper.TReportItemMapper;
import com.health.reservation.mapper.TReportMapper;
import com.health.reservation.mapper.TSetmealCheckgroupMapper;
import com.health.reservation.mapper.TSetmealMapper;
import com.health.reservation.service.IReportGenerationService;
import com.health.reservation.service.INotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 报告生成Service业务层处理
 *
 * @author ruoyi
 * @date 2026-07-14
 */
@Service
public class ReportGenerationServiceImpl implements IReportGenerationService
{
    @Autowired
    private TReportMapper tReportMapper;

    @Autowired
    private TReportItemMapper tReportItemMapper;

    @Autowired
    private TSetmealMapper tSetmealMapper;

    @Autowired
    private TCheckgroupMapper tCheckgroupMapper;

    @Autowired
    private TSetmealCheckgroupMapper tSetmealCheckgroupMapper;

    @Autowired
    private INotificationService notificationService;

    /**
     * 体检完成后自动生成报告
     * 流程：
     * 1. 生成报告编号 TR + yyyyMMdd + 4位序号
     * 2. 获取套餐信息及关联的检查组
     * 3. 创建 TReport 记录（状态 0-待录入）
     * 4. 初始化报告明细占位
     *
     * @param orderDate    体检日期
     * @param setmealId    套餐ID
     * @param memberName   体检人姓名
     * @param memberPhone  手机号
     * @param memberIdcard 身份证号
     * @return 报告编号
     */
    @Override
    @Transactional
    public String generateReportAfterCheckup(Long userId, Date orderDate, Long setmealId, String memberName, String memberPhone, String memberIdcard)
    {
        // 1. 校验套餐是否存在
        TSetmeal setmeal = tSetmealMapper.selectTSetmealById(setmealId);
        if (setmeal == null)
        {
            throw new ServiceException("套餐不存在，ID: " + setmealId);
        }

        // 2. 生成报告编号：TR + yyyyMMdd + 4位序号
        String reportNo = generateReportNo();

        // 3. 创建体检报告记录
        TReport report = new TReport();
        report.setReportNo(reportNo);
        report.setMemberName(memberName);
        report.setMemberPhone(memberPhone);
        report.setMemberIdcard(memberIdcard);
        report.setSetmealId(setmealId);
        report.setSetmealName(setmeal.getName());
        report.setOrderDate(orderDate);
        report.setReportStatus("0"); // 待录入
        report.setCreateTime(DateUtils.getNowDate());
        report.setCreateBy("system");

        tReportMapper.insertTReport(report);

        // 4. 查询套餐关联的检查组，为每个检查组创建占位明细
        List<TCheckgroup> relatedGroups = getCheckgroupsBySetmealId(setmealId);

        List<TReportItem> placeholderItems = new ArrayList<>();
        for (TCheckgroup group : relatedGroups)
        {
            TReportItem item = new TReportItem();
            item.setReportId(report.getId());
            item.setCheckgroupId(group.getId());
            item.setCheckgroupName(group.getName());
            item.setResult(null);
            item.setAbnormalFlag("0");
            item.setAbnormalMark("");
            item.setCreateTime(DateUtils.getNowDate());
            placeholderItems.add(item);
        }
        if (!placeholderItems.isEmpty())
        {
            tReportItemMapper.batchInsertTReportItem(placeholderItems);
        }

        // 报告生成后发送通知给用户
        if (userId != null)
        {
            notificationService.createNotification(
                userId,
                "report",
                "体检报告已生成",
                "您的体检报告已生成，请及时查看",
                report.getId(),
                "t_report"
            );
        }

        return reportNo;
    }

    /**
     * 根据套餐ID查询关联的检查组列表
     */
    private List<TCheckgroup> getCheckgroupsBySetmealId(Long setmealId)
    {
        List<TSetmealCheckgroup> relations = tSetmealCheckgroupMapper.selectTSetmealCheckgroupList(
            new TSetmealCheckgroup() {{ setSetmealId(setmealId); }});
        if (relations == null || relations.isEmpty())
        {
            return List.of();
        }
        Set<Long> checkgroupIds = relations.stream()
            .map(TSetmealCheckgroup::getCheckgroupId)
            .collect(Collectors.toSet());
        return tCheckgroupMapper.selectTCheckgroupByIds(checkgroupIds.toArray(new Long[0]));
    }

    /**
     * 生成报告编号：TR + yyyyMMdd + 4位序号
     */
    private String generateReportNo()
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String dateStr = sdf.format(new Date());
        String prefix = "TR" + dateStr;

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
}
