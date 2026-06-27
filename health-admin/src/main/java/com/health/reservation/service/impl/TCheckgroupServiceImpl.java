package com.health.reservation.service.impl;

import java.util.List;
import com.health.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.health.reservation.mapper.TCheckgroupMapper;
import com.health.reservation.domain.TCheckgroup;
import com.health.reservation.service.ITCheckgroupService;

/**
 * 检查组管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2026-06-27
 */
@Service
public class TCheckgroupServiceImpl implements ITCheckgroupService 
{
    @Autowired
    private TCheckgroupMapper tCheckgroupMapper;

    /**
     * 查询检查组管理
     * 
     * @param id 检查组管理主键
     * @return 检查组管理
     */
    @Override
    public TCheckgroup selectTCheckgroupById(Long id)
    {
        return tCheckgroupMapper.selectTCheckgroupById(id);
    }

    /**
     * 查询检查组管理列表
     * 
     * @param tCheckgroup 检查组管理
     * @return 检查组管理
     */
    @Override
    public List<TCheckgroup> selectTCheckgroupList(TCheckgroup tCheckgroup)
    {
        return tCheckgroupMapper.selectTCheckgroupList(tCheckgroup);
    }

    /**
     * 新增检查组管理
     * 
     * @param tCheckgroup 检查组管理
     * @return 结果
     */
    @Override
    public int insertTCheckgroup(TCheckgroup tCheckgroup)
    {
        tCheckgroup.setCreateTime(DateUtils.getNowDate());
        return tCheckgroupMapper.insertTCheckgroup(tCheckgroup);
    }

    /**
     * 修改检查组管理
     * 
     * @param tCheckgroup 检查组管理
     * @return 结果
     */
    @Override
    public int updateTCheckgroup(TCheckgroup tCheckgroup)
    {
        tCheckgroup.setUpdateTime(DateUtils.getNowDate());
        return tCheckgroupMapper.updateTCheckgroup(tCheckgroup);
    }

    /**
     * 批量删除检查组管理
     * 
     * @param ids 需要删除的检查组管理主键
     * @return 结果
     */
    @Override
    public int deleteTCheckgroupByIds(Long[] ids)
    {
        return tCheckgroupMapper.deleteTCheckgroupByIds(ids);
    }

    /**
     * 删除检查组管理信息
     * 
     * @param id 检查组管理主键
     * @return 结果
     */
    @Override
    public int deleteTCheckgroupById(Long id)
    {
        return tCheckgroupMapper.deleteTCheckgroupById(id);
    }
}
