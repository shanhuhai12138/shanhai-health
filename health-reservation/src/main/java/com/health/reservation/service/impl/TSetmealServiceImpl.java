package com.health.reservation.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import com.health.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.health.reservation.mapper.TSetmealMapper;
import com.health.reservation.mapper.TCheckgroupMapper;
import com.health.reservation.mapper.TCheckgroupCheckitemMapper;
import com.health.reservation.mapper.TSetmealCheckgroupMapper;
import com.health.reservation.domain.TSetmeal;
import com.health.reservation.domain.TCheckgroup;
import com.health.reservation.domain.TCheckgroupCheckitem;
import com.health.reservation.domain.TSetmealCheckgroup;
import com.health.reservation.service.ITSetmealService;

/**
 * 套餐组Service业务层处理
 * 
 * @author ruoyi
 * @date 2026-06-27
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class TSetmealServiceImpl implements ITSetmealService 
{
    @Autowired
    private TSetmealMapper tSetmealMapper;

    @Autowired
    private TCheckgroupMapper tCheckgroupMapper;

    @Autowired
    private TCheckgroupCheckitemMapper tCheckgroupCheckitemMapper;

    @Autowired
    private TSetmealCheckgroupMapper tSetmealCheckgroupMapper;

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

    /**
     * 查询套餐关联的检查组列表
     *
     * @param setmealId 套餐组主键
     * @return 检查组集合
     */
    @Override
    public List<TCheckgroup> selectTCheckgroupBySetmealId(Long setmealId)
    {
        List<TSetmealCheckgroup> relations = tSetmealCheckgroupMapper.selectTSetmealCheckgroupList(
            new TSetmealCheckgroup() {{ setSetmealId(setmealId); }});
        if (relations == null || relations.isEmpty())
        {
            return List.of();
        }
        Set<Long> checkgroupIds = relations.stream()
            .map(TSetmealCheckgroup::getCheckgroupId)
            .collect(Collectors.toSet());
        return tCheckgroupMapper.selectTCheckgroupByIds(checkgroupIds.toArray(new Long[0]));
    }

    /**
     * 批量设置套餐关联的检查组
     *
     * @param setmealId 套餐组主键
     * @param checkgroupIds 检查组主键数组
     * @return 结果
     */
    @Override
    public int batchSetCheckgroups(Long setmealId, Long[] checkgroupIds)
    {
        tSetmealCheckgroupMapper.deleteTSetmealCheckgroupBySetmealId(setmealId);
        if (checkgroupIds != null && checkgroupIds.length > 0)
        {
            List<TSetmealCheckgroup> list = new java.util.ArrayList<>();
            for (Long checkgroupId : checkgroupIds)
            {
                TSetmealCheckgroup scg = new TSetmealCheckgroup();
                scg.setSetmealId(setmealId);
                scg.setCheckgroupId(checkgroupId);
                list.add(scg);
            }
            tSetmealCheckgroupMapper.batchInsertTSetmealCheckgroup(list);
        }
        return 1;
    }

    /**
     * 查询套餐详情（含关联的检查组和检查项）
     *
     * @param setmealId 套餐组主键
     * @return 套餐详情Map，包含setmeal、checkgroups（嵌套checkitems）
     */
    @Override
    public Map<String, Object> selectSetmealDetail(Long setmealId)
    {
        Map<String, Object> result = new HashMap<>();
        TSetmeal setmeal = tSetmealMapper.selectTSetmealById(setmealId);
        result.put("setmeal", setmeal);

        List<TCheckgroup> checkgroups = selectTCheckgroupBySetmealId(setmealId);

        for (TCheckgroup cg : checkgroups)
        {
            TCheckgroupCheckitem param = new TCheckgroupCheckitem();
            param.setCheckgroupId(cg.getId());
            List<TCheckgroupCheckitem> items = tCheckgroupCheckitemMapper.selectTCheckgroupCheckitemList(param);
            cg.setCheckItemIds(items.stream().map(TCheckgroupCheckitem::getCheckitemId).toArray(Long[]::new));
        }

        result.put("checkgroups", checkgroups);
        return result;
    }
}
