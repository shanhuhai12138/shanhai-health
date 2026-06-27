package com.health.reservation.service;

import java.util.List;
import com.health.reservation.domain.TCheckgroup;

/**
 * 检查组管理Service接口
 * 
 * @author ruoyi
 * @date 2026-06-27
 */
public interface ITCheckgroupService 
{
    /**
     * 查询检查组管理
     * 
     * @param id 检查组管理主键
     * @return 检查组管理
     */
    public TCheckgroup selectTCheckgroupById(Long id);

    /**
     * 查询检查组管理列表
     * 
     * @param tCheckgroup 检查组管理
     * @return 检查组管理集合
     */
    public List<TCheckgroup> selectTCheckgroupList(TCheckgroup tCheckgroup);

    /**
     * 新增检查组管理
     * 
     * @param tCheckgroup 检查组管理
     * @return 结果
     */
    public int insertTCheckgroup(TCheckgroup tCheckgroup);

    /**
     * 修改检查组管理
     * 
     * @param tCheckgroup 检查组管理
     * @return 结果
     */
    public int updateTCheckgroup(TCheckgroup tCheckgroup);

    /**
     * 批量删除检查组管理
     * 
     * @param ids 需要删除的检查组管理主键集合
     * @return 结果
     */
    public int deleteTCheckgroupByIds(Long[] ids);

    /**
     * 删除检查组管理信息
     * 
     * @param id 检查组管理主键
     * @return 结果
     */
    public int deleteTCheckgroupById(Long id);
}
