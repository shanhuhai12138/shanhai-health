package com.health.reservation.mapper;

import java.util.Date;
import java.util.List;
import com.health.reservation.domain.TOrdersetting;
import org.apache.ibatis.annotations.Param;

/**
 * 预约设置Mapper接口
 *
 * @author ruoyi
 * @date 2026-06-30
 */
public interface TOrdersettingMapper
{
    /**
     * 查询预约设置
     *
     * @param id 预约设置主键
     * @return 预约设置
     */
    public TOrdersetting selectTOrdersettingById(Long id);

    /**
     * 查询预约设置列表
     *
     * @param tOrdersetting 预约设置
     * @return 预约设置集合
     */
    public List<TOrdersetting> selectTOrdersettingList(TOrdersetting tOrdersetting);

    /**
     * 新增预约设置
     *
     * @param tOrdersetting 预约设置
     * @return 结果
     */
    public int insertTOrdersetting(TOrdersetting tOrdersetting);

    /**
     * 修改预约设置
     *
     * @param tOrdersetting 预约设置
     * @return 结果
     */
    public int updateTOrdersetting(TOrdersetting tOrdersetting);

    /**
     * 删除预约设置
     *
     * @param id 预约设置主键
     * @return 结果
     */
    public int deleteTOrdersettingById(Long id);

    /**
     * 批量删除预约设置
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTOrdersettingByIds(Long[] ids);

    /**
     * 根据年月查询预约设置（例如 2025-10）
     *
     * @param yearMonth 年月，格式 YYYY-MM
     * @return 预约设置集合
     */
    public List<TOrdersetting> selectByYearMonth(@Param("yearMonth") String yearMonth);

    /**
     * 根据预约日期查询预约设置
     *
     * @param orderDate 预约日期
     * @return 预约设置
     */
    public TOrdersetting selectByOrderDate(Date orderDate);

    /**
     * 批量插入预约设置
     *
     * @param list 预约设置列表
     * @return 结果
     */
    public int insertBatch(List<TOrdersetting> list);
}
