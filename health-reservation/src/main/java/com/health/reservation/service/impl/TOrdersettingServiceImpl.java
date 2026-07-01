package com.health.reservation.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.health.reservation.mapper.TOrdersettingMapper;
import com.health.reservation.domain.TOrdersetting;
import com.health.reservation.service.ITOrdersettingService;

/**
 * 预约设置Service业务层处理
 * 
 * @author ruoyi
 * @date 2026-06-30
 */
@Service
public class TOrdersettingServiceImpl implements ITOrdersettingService 
{
    @Autowired
    private TOrdersettingMapper tOrdersettingMapper;

    /**
     * 查询预约设置
     * 
     * @param id 预约设置主键
     * @return 预约设置
     */
    @Override
    public TOrdersetting selectTOrdersettingById(Long id)
    {
        return tOrdersettingMapper.selectTOrdersettingById(id);
    }

    /**
     * 查询预约设置列表
     * 
     * @param tOrdersetting 预约设置
     * @return 预约设置
     */
    @Override
    public List<TOrdersetting> selectTOrdersettingList(TOrdersetting tOrdersetting)
    {
        return tOrdersettingMapper.selectTOrdersettingList(tOrdersetting);
    }

    /**
     * 新增预约设置
     * 
     * @param tOrdersetting 预约设置
     * @return 结果
     */
    @Override
    public int insertTOrdersetting(TOrdersetting tOrdersetting)
    {
        return tOrdersettingMapper.insertTOrdersetting(tOrdersetting);
    }

    /**
     * 修改预约设置
     * 
     * @param tOrdersetting 预约设置
     * @return 结果
     */
    @Override
    public int updateTOrdersetting(TOrdersetting tOrdersetting)
    {
        return tOrdersettingMapper.updateTOrdersetting(tOrdersetting);
    }

    /**
     * 批量删除预约设置
     * 
     * @param ids 需要删除的预约设置主键
     * @return 结果
     */
    @Override
    public int deleteTOrdersettingByIds(Long[] ids)
    {
        return tOrdersettingMapper.deleteTOrdersettingByIds(ids);
    }

    /**
     * 删除预约设置信息
     * 
     * @param id 预约设置主键
     * @return 结果
     */
    @Override
    public int deleteTOrdersettingById(Long id)
    {
        return tOrdersettingMapper.deleteTOrdersettingById(id);
    }
}
