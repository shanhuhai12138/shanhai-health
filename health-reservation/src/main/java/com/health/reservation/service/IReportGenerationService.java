package com.health.reservation.service;

import java.util.Date;

/**
 * 报告生成Service接口
 *
 * @author ruoyi
 * @date 2026-07-14
 */
public interface IReportGenerationService
{
    /**
     * 体检完成后自动生成报告
     *
     * @param userId     用户ID
     * @param orderDate  体检日期
     * @param setmealId  套餐ID
     * @param memberName 体检人姓名
     * @param memberPhone 手机号
     * @param memberIdcard 身份证号
     * @return 报告编号
     */
    public String generateReportAfterCheckup(Long userId, Date orderDate, Long setmealId, String memberName, String memberPhone, String memberIdcard);
}
