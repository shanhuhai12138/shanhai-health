package com.health.reservation.service.impl;

import java.util.ArrayList;
import java.util.List;
import com.health.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.health.reservation.mapper.TCheckgroupMapper;
import com.health.reservation.mapper.TCheckgroupCheckitemMapper;
import com.health.reservation.domain.TCheckgroup;
import com.health.reservation.domain.TCheckgroupCheckitem;
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

    @Autowired
    private TCheckgroupCheckitemMapper tCheckgroupCheckitemMapper;

    /**
     * 查询检查组管理
     *
     * @param id 检查组管理主键
     * @return 检查组管理
     */
    @Override
    public TCheckgroup selectTCheckgroupById(Long id)
    {
        TCheckgroup checkgroup = tCheckgroupMapper.selectTCheckgroupById(id);
        if (checkgroup != null) {
            // 根据检查组ID查询关联的检查项
            TCheckgroupCheckitem queryParam = new TCheckgroupCheckitem();
            queryParam.setCheckgroupId(id);
            List<TCheckgroupCheckitem> relations = tCheckgroupCheckitemMapper.selectTCheckgroupCheckitemList(queryParam);
            List<Long> checkItemIds = new ArrayList<>();
            if (relations != null) {
                for (TCheckgroupCheckitem relation : relations) {
                    checkItemIds.add(relation.getCheckitemId());
                }
            }
            checkgroup.setCheckItemIds(checkItemIds.toArray(new Long[0]));
        }
        return checkgroup;
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
    @Transactional
    public int insertTCheckgroup(TCheckgroup tCheckgroup)
    {
        tCheckgroup.setCreateTime(DateUtils.getNowDate());
        int rows = tCheckgroupMapper.insertTCheckgroup(tCheckgroup);
        insertCheckgroupCheckitem(tCheckgroup);
        return rows;
    }

    /**
     * 修改检查组管理
     *
     * @param tCheckgroup 检查组管理
     * @return 结果
     */
    @Override
    @Transactional
    public int updateTCheckgroup(TCheckgroup tCheckgroup)
    {
        tCheckgroup.setUpdateTime(DateUtils.getNowDate());
        tCheckgroupCheckitemMapper.deleteTCheckgroupCheckitemByCheckgroupId(tCheckgroup.getId());
        insertCheckgroupCheckitem(tCheckgroup);
        return tCheckgroupMapper.updateTCheckgroup(tCheckgroup);
    }

    /**
     * 批量删除检查组管理
     *
     * @param ids 需要删除的检查组管理主键
     * @return 结果
     */
    @Override
    @Transactional
    public int deleteTCheckgroupByIds(Long[] ids)
    {
        tCheckgroupCheckitemMapper.deleteTCheckgroupCheckitemByCheckgroupIds(ids);
        return tCheckgroupMapper.deleteTCheckgroupByIds(ids);
    }

    /**
     * 删除检查组管理信息
     *
     * @param id 检查组管理主键
     * @return 结果
     */
    @Override
    @Transactional
    public int deleteTCheckgroupById(Long id)
    {
        tCheckgroupCheckitemMapper.deleteTCheckgroupCheckitemByCheckgroupId(id);
        return tCheckgroupMapper.deleteTCheckgroupById(id);
    }

    /**
     * 新增检查组检查项关联信息
     *
     * @param checkgroup 检查组对象
     */
    public void insertCheckgroupCheckitem(TCheckgroup checkgroup)
    {
        Long[] checkItemIds = checkgroup.getCheckItemIds();
        if (checkItemIds == null || checkItemIds.length == 0) {
            return;
        }
        Long checkgroupId = checkgroup.getId();
        for (Long checkItemId : checkItemIds) {
            TCheckgroupCheckitem checkgroupCheckitem = new TCheckgroupCheckitem();
            checkgroupCheckitem.setCheckgroupId(checkgroupId);
            checkgroupCheckitem.setCheckitemId(checkItemId);
            tCheckgroupCheckitemMapper.insertTCheckgroupCheckitem(checkgroupCheckitem);
        }
    }
}
