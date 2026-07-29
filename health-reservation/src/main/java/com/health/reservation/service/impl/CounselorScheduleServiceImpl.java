package com.health.reservation.service.impl;

import java.util.List;
import com.health.common.utils.DateUtils;
import com.health.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.health.reservation.mapper.CounselorScheduleMapper;
import com.health.reservation.domain.CounselorSchedule;
import com.health.reservation.service.ICounselorScheduleService;

/**
 * 咨询师排班Service业务层处理
 *
 * @author ruoyi
 * @date 2026-07-09
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class CounselorScheduleServiceImpl implements ICounselorScheduleService
{
    @Autowired
    private CounselorScheduleMapper counselorScheduleMapper;

    /**
     * 查询咨询师排班
     *
     * @param id 咨询师排班主键
     * @return 咨询师排班
     */
    @Override
    public CounselorSchedule selectCounselorScheduleById(Long id)
    {
        return counselorScheduleMapper.selectCounselorScheduleById(id);
    }

    /**
     * 查询咨询师排班列表
     *
     * @param counselorSchedule 咨询师排班
     * @return 咨询师排班
     */
    @Override
    public List<CounselorSchedule> selectCounselorScheduleList(CounselorSchedule counselorSchedule)
    {
        return counselorScheduleMapper.selectCounselorScheduleList(counselorSchedule);
    }

    /**
     * 新增咨询师排班
     *
     * @param counselorSchedule 咨询师排班
     * @return 结果
     */
    @Override
    public int insertCounselorSchedule(CounselorSchedule counselorSchedule)
    {
        counselorSchedule.setCreateTime(DateUtils.getNowDate());
        counselorSchedule.setCreateBy(SecurityUtils.getUsername());
        return counselorScheduleMapper.insertCounselorSchedule(counselorSchedule);
    }

    /**
     * 修改咨询师排班
     *
     * @param counselorSchedule 咨询师排班
     * @return 结果
     */
    @Override
    public int updateCounselorSchedule(CounselorSchedule counselorSchedule)
    {
        counselorSchedule.setUpdateTime(DateUtils.getNowDate());
        counselorSchedule.setUpdateBy(SecurityUtils.getUsername());
        return counselorScheduleMapper.updateCounselorSchedule(counselorSchedule);
    }

    /**
     * 批量删除咨询师排班
     *
     * @param ids 需要删除的咨询师排班主键
     * @return 结果
     */
    @Override
    public int deleteCounselorScheduleByIds(Long[] ids)
    {
        return counselorScheduleMapper.deleteCounselorScheduleByIds(ids);
    }

    /**
     * 删除咨询师排班信息
     *
     * @param id 咨询师排班主键
     * @return 结果
     */
    @Override
    public int deleteCounselorScheduleById(Long id)
    {
        return counselorScheduleMapper.deleteCounselorScheduleById(id);
    }

    /**
     * 根据咨询师ID查询排班列表
     *
     * @param counselorId 咨询师ID
     * @return 排班集合
     */
    @Override
    public List<CounselorSchedule> selectScheduleByCounselorId(Long counselorId)
    {
        CounselorSchedule counselorSchedule = new CounselorSchedule();
        counselorSchedule.setCounselorId(counselorId);
        return counselorScheduleMapper.selectCounselorScheduleList(counselorSchedule);
    }

    /**
     * 根据日期范围查询排班列表
     *
     * @param beginDate 开始日期
     * @param endDate 结束日期
     * @return 排班集合
     */
    @Override
    public List<CounselorSchedule> selectScheduleByDateRange(String beginDate, String endDate)
    {
        CounselorSchedule counselorSchedule = new CounselorSchedule();
        counselorSchedule.getParams().put("beginDate", beginDate);
        counselorSchedule.getParams().put("endDate", endDate);
        return counselorScheduleMapper.selectCounselorScheduleList(counselorSchedule);
    }

    /**
     * 查询某日可用排班
     *
     * @param date 排班日期
     * @return 可用排班集合
     */
    @Override
    public List<CounselorSchedule> selectAvailableSchedulesByDate(String date)
    {
        CounselorSchedule counselorSchedule = new CounselorSchedule();
        counselorSchedule.setScheduleDate(DateUtils.parseDate(date));
        counselorSchedule.setIsAvailable("1");
        return counselorScheduleMapper.selectCounselorScheduleList(counselorSchedule);
    }
}
