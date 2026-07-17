package com.health.reservation.mapper;

import java.util.List;
import com.health.reservation.domain.CounselorSchedule;

/**
 * 咨询师排班Mapper接口
 *
 * @author ruoyi
 * @date 2026-07-09
 */
public interface CounselorScheduleMapper
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
     * 删除咨询师排班
     *
     * @param id 咨询师排班主键
     * @return 结果
     */
    public int deleteCounselorScheduleById(Long id);

    /**
     * 批量删除咨询师排班
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCounselorScheduleByIds(Long[] ids);
}
