package com.health.reservation.service.impl;

import java.util.List;
import java.util.Map;
import com.health.common.utils.DateUtils;
import com.health.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.health.reservation.mapper.MoodMapper;
import com.health.reservation.domain.MoodRecord;
import com.health.reservation.service.IMoodService;

/**
 * 情绪记录Service业务层处理
 *
 * @author ruoyi
 * @date 2026-07-09
 */
@Service
public class MoodServiceImpl implements IMoodService
{
    @Autowired
    private MoodMapper moodMapper;

    /**
     * 查询情绪记录
     *
     * @param id 情绪记录主键
     * @return 情绪记录
     */
    @Override
    public MoodRecord selectMoodRecordById(Long id)
    {
        return moodMapper.selectMoodRecordById(id);
    }

    /**
     * 查询情绪记录列表
     *
     * @param moodRecord 情绪记录
     * @return 情绪记录
     */
    @Override
    public List<MoodRecord> selectMoodRecordList(MoodRecord moodRecord)
    {
        return moodMapper.selectMoodRecordList(moodRecord);
    }

    /**
     * 新增情绪记录
     *
     * @param moodRecord 情绪记录
     * @return 结果
     */
    @Override
    public int insertMoodRecord(MoodRecord moodRecord)
    {
        moodRecord.setCreateTime(DateUtils.getNowDate());
        moodRecord.setCreateBy(SecurityUtils.getUsername());
        return moodMapper.insertMoodRecord(moodRecord);
    }

    /**
     * 修改情绪记录
     *
     * @param moodRecord 情绪记录
     * @return 结果
     */
    @Override
    public int updateMoodRecord(MoodRecord moodRecord)
    {
        moodRecord.setUpdateTime(DateUtils.getNowDate());
        moodRecord.setUpdateBy(SecurityUtils.getUsername());
        return moodMapper.updateMoodRecord(moodRecord);
    }

    /**
     * 批量删除情绪记录
     *
     * @param ids 需要删除的情绪记录主键
     * @return 结果
     */
    @Override
    public int deleteMoodRecordByIds(Long[] ids)
    {
        return moodMapper.deleteMoodRecordByIds(ids);
    }

    /**
     * 删除情绪记录信息
     *
     * @param id 情绪记录主键
     * @return 结果
     */
    @Override
    public int deleteMoodRecordById(Long id)
    {
        return moodMapper.deleteMoodRecordById(id);
    }

    /**
     * 月度情绪统计
     *
     * @param userId 用户ID
     * @param yearMonth 年月
     * @return 月度统计
     */
    @Override
    public Map<String, Object> selectMonthlyStats(Long userId, String yearMonth)
    {
        return moodMapper.selectMonthlyStats(userId, yearMonth);
    }

    /**
     * 情绪趋势数据
     *
     * @param userId 用户ID
     * @param days 天数
     * @return 趋势列表
     */
    @Override
    public List<Map<String, Object>> selectTrendData(Long userId, Integer days)
    {
        return moodMapper.selectTrendData(userId, days);
    }

    /**
     * 情绪分布统计
     *
     * @param userId 用户ID
     * @return 分布列表
     */
    @Override
    public List<Map<String, Object>> selectDistribution(Long userId)
    {
        return moodMapper.selectDistribution(userId);
    }

    /**
     * 健康因素相关性分析
     *
     * @param userId 用户ID
     * @return 相关性数据
     */
    @Override
    public Map<String, Object> selectHealthFactors(Long userId)
    {
        return moodMapper.selectHealthFactors(userId);
    }

    /**
     * 综合统计摘要
     *
     * @param userId 用户ID
     * @return 摘要数据
     */
    @Override
    public Map<String, Object> selectSummary(Long userId)
    {
        return moodMapper.selectSummary(userId);
    }
}
