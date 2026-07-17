package com.health.reservation.mapper;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.health.reservation.domain.MoodRecord;

/**
 * 情绪记录Mapper接口
 *
 * @author ruoyi
 * @date 2026-07-09
 */
public interface MoodMapper
{
    /**
     * 查询情绪记录
     *
     * @param id 情绪记录主键
     * @return 情绪记录
     */
    public MoodRecord selectMoodRecordById(Long id);

    /**
     * 查询情绪记录列表
     *
     * @param moodRecord 情绪记录
     * @return 情绪记录集合
     */
    public List<MoodRecord> selectMoodRecordList(MoodRecord moodRecord);

    /**
     * 新增情绪记录
     *
     * @param moodRecord 情绪记录
     * @return 结果
     */
    public int insertMoodRecord(MoodRecord moodRecord);

    /**
     * 修改情绪记录
     *
     * @param moodRecord 情绪记录
     * @return 结果
     */
    public int updateMoodRecord(MoodRecord moodRecord);

    /**
     * 删除情绪记录
     *
     * @param id 情绪记录主键
     * @return 结果
     */
    public int deleteMoodRecordById(Long id);

    /**
     * 批量删除情绪记录
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMoodRecordByIds(Long[] ids);

    /**
     * 月度情绪统计
     *
     * @param userId 用户ID
     * @param yearMonth 年月 YYYY-MM
     * @return 月度统计
     */
    public Map<String, Object> selectMonthlyStats(@Param("userId") Long userId, @Param("yearMonth") String yearMonth);

    /**
     * 情绪趋势数据（按天分组）
     *
     * @param userId 用户ID
     * @param days 天数
     * @return 趋势列表
     */
    public List<Map<String, Object>> selectTrendData(@Param("userId") Long userId, @Param("days") Integer days);

    /**
     * 情绪分布统计
     *
     * @param userId 用户ID
     * @return 分布列表
     */
    public List<Map<String, Object>> selectDistribution(@Param("userId") Long userId);

    /**
     * 健康因素相关性分析
     *
     * @param userId 用户ID
     * @return 相关性数据
     */
    public Map<String, Object> selectHealthFactors(@Param("userId") Long userId);

    /**
     * 综合统计摘要
     *
     * @param userId 用户ID
     * @return 摘要数据
     */
    public Map<String, Object> selectSummary(@Param("userId") Long userId);
}
