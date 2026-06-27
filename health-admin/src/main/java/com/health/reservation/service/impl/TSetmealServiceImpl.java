package com.health.reservation.service.impl;

import java.util.List;
import com.health.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.health.reservation.mapper.TSetmealMapper;
import com.health.reservation.domain.TSetmeal;
import com.health.reservation.service.ITSetmealService;

/**
 * 套餐组Service业务层处理
 * 
 * @author ruoyi
 * @date 2026-06-27
 */
@Service
public class TSetmealServiceImpl implements ITSetmealService 
{
    @Autowired
    private TSetmealMapper tSetmealMapper;

    /**
     * 查询套餐组
     * 
     * @param id 套餐组主键
     * @return 套餐组
     */
    @Override
    public TSetmeal selectTSetmealById(Long id)
    {
        return tSetmealMapper.selectTSetmealById(id);
    }

    /**
     * 查询套餐组列表
     * 
     * @param tSetmeal 套餐组
     * @return 套餐组
     */
    @Override
    public List<TSetmeal> selectTSetmealList(TSetmeal tSetmeal)
    {
        return tSetmealMapper.selectTSetmealList(tSetmeal);
    }

    /**
     * 新增套餐组
     * 
     * @param tSetmeal 套餐组
     * @return 结果
     */
    @Override
    public int insertTSetmeal(TSetmeal tSetmeal)
    {
        tSetmeal.setCreateTime(DateUtils.getNowDate());
        return tSetmealMapper.insertTSetmeal(tSetmeal);
    }

    /**
     * 修改套餐组
     * 
     * @param tSetmeal 套餐组
     * @return 结果
     */
    @Override
    public int updateTSetmeal(TSetmeal tSetmeal)
    {
        tSetmeal.setUpdateTime(DateUtils.getNowDate());
        return tSetmealMapper.updateTSetmeal(tSetmeal);
    }

    /**
     * 批量删除套餐组
     * 
     * @param ids 需要删除的套餐组主键
     * @return 结果
     */
    @Override
    public int deleteTSetmealByIds(Long[] ids)
    {
        return tSetmealMapper.deleteTSetmealByIds(ids);
    }

    /**
     * 删除套餐组信息
     * 
     * @param id 套餐组主键
     * @return 结果
     */
    @Override
    public int deleteTSetmealById(Long id)
    {
        return tSetmealMapper.deleteTSetmealById(id);
    }
}
