package com.health.reservation.service;

import java.util.List;
import com.health.reservation.domain.CounselorSchedule;

/**
 * 咨询师排班Service接口
 *
 * @author ruoyi
 * @date 2026-07-09
 */
public interface ICounselorScheduleService
{
    /**
     * 查询咨询师排班
     *
     * @param id 咨询师排班主键
     * @return 咨询师排班
     */
    public CounselorSchedule selectCounselorScheduleById(Long id);

    /**
     * 查询咨询师排班列表
     *
     * @param counselorSchedule 咨询师排班
     * @return 咨询师排班集合
     */
    public List<CounselorSchedule> selectCounselorScheduleList(CounselorSchedule counselorSchedule);

    /**
     * 新增咨询师排班
     *
     * @param counselorSchedule 咨询师排班
     * @return 结果
     */
    public int insertCounselorSchedule(CounselorSchedule counselorSchedule);

    /**
     * 修改咨询师排班
     *
     * @param counselorSchedule 咨询师排班
     * @return 结果
     */
    public int updateCounselorSchedule(CounselorSchedule counselorSchedule);

    /**
     * 批量删除咨询师排班
     *
     * @param ids 需要删除的咨询师排班主键集合
     * @return 结果
     */
    public int deleteCounselorScheduleByIds(Long[] ids);

    /**
     * 删除咨询师排班信息
     *
     * @param id 咨询师排班主键
     * @return 结果
     */
    public int deleteCounselorScheduleById(Long id);

    /**
     * 根据咨询师ID查询排班列表
     *
     * @param counselorId 咨询师ID
     * @return 排班集合
     */
    public List<CounselorSchedule> selectScheduleByCounselorId(Long counselorId);

    /**
     * 根据日期范围查询排班列表
     *
     * @param beginDate 开始日期
     * @param endDate 结束日期
     * @return 排班集合
     */
    public List<CounselorSchedule> selectScheduleByDateRange(String beginDate, String endDate);

    /**
     * 查询某日可用排班
     *
     * @param date 排班日期
     * @return 可用排班集合
     */
    public List<CounselorSchedule> selectAvailableSchedulesByDate(String date);
}
