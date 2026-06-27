package com.health.reservation.service;

import java.util.List;
import com.health.reservation.domain.TSetmeal;

/**
 * 套餐组Service接口
 * 
 * @author ruoyi
 * @date 2026-06-27
 */
public interface ITSetmealService 
{
    /**
     * 查询套餐组
     * 
     * @param id 套餐组主键
     * @return 套餐组
     */
    public TSetmeal selectTSetmealById(Long id);

    /**
     * 查询套餐组列表
     * 
     * @param tSetmeal 套餐组
     * @return 套餐组集合
     */
    public List<TSetmeal> selectTSetmealList(TSetmeal tSetmeal);

    /**
     * 新增套餐组
     * 
     * @param tSetmeal 套餐组
     * @return 结果
     */
    public int insertTSetmeal(TSetmeal tSetmeal);

    /**
     * 修改套餐组
     * 
     * @param tSetmeal 套餐组
     * @return 结果
     */
    public int updateTSetmeal(TSetmeal tSetmeal);

    /**
     * 批量删除套餐组
     * 
     * @param ids 需要删除的套餐组主键集合
     * @return 结果
     */
    public int deleteTSetmealByIds(Long[] ids);

    /**
     * 删除套餐组信息
     * 
     * @param id 套餐组主键
     * @return 结果
     */
    public int deleteTSetmealById(Long id);
}
