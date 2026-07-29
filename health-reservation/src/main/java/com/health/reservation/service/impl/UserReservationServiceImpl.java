package com.health.reservation.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.health.common.exception.ServiceException;
import com.health.common.utils.DateUtils;
import com.health.reservation.domain.TSetmeal;
import com.health.reservation.domain.TOrdersetting;
import com.health.reservation.domain.TCheckgroup;
import com.health.reservation.domain.TCheckitem;
import com.health.reservation.domain.TReport;
import com.health.reservation.mapper.UserReservationMapper;
import com.health.reservation.mapper.TSetmealMapper;
import com.health.reservation.mapper.TCheckgroupMapper;
import com.health.reservation.mapper.TCheckitemMapper;
import com.health.reservation.mapper.TReportMapper;
import com.health.reservation.service.IUserReservationService;
import com.health.reservation.service.INotificationService;

/**
 * 用户端预约Service业务层处理
 */
@Service
public class UserReservationServiceImpl implements IUserReservationService
{
    @Autowired
    private UserReservationMapper userReservationMapper;

    @Autowired
    private TSetmealMapper tSetmealMapper;

    @Autowired
    private TCheckgroupMapper tCheckgroupMapper;

    @Autowired
    private TCheckitemMapper tCheckitemMapper;

    @Autowired
    private TReportMapper tReportMapper;

    @Autowired
    private INotificationService notificationService;

    @Override
    public List<TSetmeal> getAvailableSetmeals(String sex, String age)
    {
        // 查询所有套餐
        List<TSetmeal> setmeals = userReservationMapper.selectAvailableSetmeals();

        // 按性别/年龄过滤
        if (sex != null && !sex.isEmpty())
        {
            setmeals = setmeals.stream()
                .filter(s -> "2".equals(s.getSex()) || sex.equals(s.getSex()))
                .collect(Collectors.toList());
        }
        if (age != null && !age.isEmpty())
        {
            setmeals = setmeals.stream()
                .filter(s -> "不限".equals(s.getAge()) || age.equals(s.getAge()))
                .collect(Collectors.toList());
        }

        return setmeals;
    }

    @Override
    public Map<String, Object> getSetmealDetails(Long setmealId)
    {
        // 1. 查询套餐基本信息
        TSetmeal setmeal = tSetmealMapper.selectTSetmealById(setmealId);
        if (setmeal == null)
        {
            throw new ServiceException("套餐不存在");
        }

        // 2. 查询套餐关联的检查组
        List<Long> checkgroupIds = userReservationMapper.selectCheckgroupIdsBySetmealId(setmealId);
        List<TCheckgroup> checkgroups = new ArrayList<>();
        if (checkgroupIds != null && !checkgroupIds.isEmpty())
        {
            for (Long cgId : checkgroupIds)
            {
                TCheckgroup cg = tCheckgroupMapper.selectTCheckgroupById(cgId);
                if (cg != null)
                {
                    // 查询该检查组下的检查项
                    List<Long> checkitemIds = userReservationMapper.selectCheckitemIdsByCheckgroupId(cgId);
                    List<TCheckitem> checkitems = new ArrayList<>();
                    if (checkitemIds != null && !checkitemIds.isEmpty())
                    {
                        for (Long ciId : checkitemIds)
                        {
                            TCheckitem ci = tCheckitemMapper.selectTCheckitemById(ciId);
                            if (ci != null)
                            {
                                checkitems.add(ci);
                            }
                        }
                    }
                    cg.setCheckItemIds(checkitemIds != null ? checkitemIds.toArray(new Long[0]) : new Long[0]);
                    checkgroups.add(cg);
                }
            }
        }

        // 3. 组装返回结果
        Map<String, Object> detail = new LinkedHashMap<>();
        detail.put("setmeal", setmeal);
        detail.put("checkgroups", checkgroups);

        // 计算总价
        BigDecimal totalPrice = setmeal.getPrice() != null
            ? new BigDecimal(setmeal.getPrice())
            : BigDecimal.ZERO;
        detail.put("totalPrice", totalPrice);

        return detail;
    }

    @Override
    public List<Map<String, Object>> getAvailableDates(Long setmealId)
    {
        return userReservationMapper.selectAvailableDates();
    }

    @Override
    public TOrdersetting getOrdersettingByDate(Date orderDate)
    {
        return userReservationMapper.selectOrdersettingByDate(orderDate);
    }

    /**
     * 创建预约订单（含库存扣减与报告生成）
     *
     * 1. 验证套餐是否存在
     * 2. 检查预约日期是否有剩余号源
     * 3. 乐观锁扣减库存
     * 4. 创建体检报告记录（status=0 待录入）
     * 5. 返回报告编号
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public String createOrder(Long userId, Long setmealId, Date orderDate, Long[] checkgroupIds,
                              String memberName, String memberPhone, String memberIdcard)
    {
        // 1. 验证套餐是否存在
        TSetmeal setmeal = tSetmealMapper.selectTSetmealById(setmealId);
        if (setmeal == null)
        {
            throw new ServiceException("套餐不存在");
        }

        // 2. 查询该日期的预约设置
        TOrdersetting ordersetting = userReservationMapper.selectOrdersettingByDate(orderDate);
        if (ordersetting == null)
        {
            throw new ServiceException("该日期不可预约");
        }

        // 3. 检查剩余名额
        long availableCount = ordersetting.getNumber() - ordersetting.getReservations();
        if (availableCount <= 0)
        {
            throw new ServiceException("该日期已约满");
        }

        // 4. 乐观锁扣减库存
        int affected = userReservationMapper.increaseReservations(orderDate);
        if (affected == 0)
        {
            throw new ServiceException("该日期已约满");
        }

        // 5. 生成报告编号：TR + yyyyMMdd + 4位序号
        String dateStr = new SimpleDateFormat("yyyyMMdd").format(orderDate);
        String reportNo = generateReportNo(dateStr);

        // 6. 构建体检报告记录
        TReport report = new TReport();
        report.setReportNo(reportNo);
        report.setMemberName(memberName);
        report.setMemberPhone(memberPhone);
        report.setMemberIdcard(memberIdcard);
        report.setSetmealId(setmealId);
        report.setSetmealName(setmeal.getName());
        if (checkgroupIds != null && checkgroupIds.length > 0)
        {
            String idsStr = java.util.Arrays.stream(checkgroupIds)
                .map(String::valueOf)
                .collect(java.util.stream.Collectors.joining(","));
            report.setCheckgroupIds(idsStr);
        }
        report.setOrderDate(orderDate);
        report.setReportStatus("0"); // 待录入
        report.setCreateTime(DateUtils.getNowDate());

        tReportMapper.insertTReport(report);

        // 创建订单后发送通知给用户
        if (userId != null)
        {
            notificationService.createNotification(
                userId,
                "appointment",
                "预约成功",
                "您的预约已创建成功，报告编号：" + reportNo,
                report.getId(),
                "t_report"
            );
        }

        return reportNo;
    }

    /**
     * 生成报告编号
     * 格式：TR + yyyyMMdd + 4位序号
     */
    private String generateReportNo(String dateStr)
    {
        // 查询当日最大编号
        String maxReportNo = tReportMapper.selectMaxReportNoByDate(dateStr);
        int seq = 1;
        if (maxReportNo != null && maxReportNo.length() >= 14)
        {
            try
            {
                String seqStr = maxReportNo.substring(10);
                seq = Integer.parseInt(seqStr) + 1;
            }
            catch (NumberFormatException e)
            {
                seq = 1;
            }
        }
        return String.format("TR%s%04d", dateStr, seq);
    }

    /**
     * 取消预约（释放库存）
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void cancelOrder(String reportNo)
    {
        TReport report = tReportMapper.selectTReportByReportNo(reportNo);
        if (report == null)
        {
            throw new ServiceException("预约记录不存在");
        }
        if (!"0".equals(report.getReportStatus()))
        {
            throw new ServiceException("只有待录入状态的预约可以取消");
        }

        // 释放库存
        userReservationMapper.decreaseReservations(report.getOrderDate());

        // 更新报告状态为已取消
        report.setReportStatus("3");
        report.setUpdateTime(DateUtils.getNowDate());
        tReportMapper.updateTReport(report);
    }
}
