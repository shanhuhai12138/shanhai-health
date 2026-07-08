package com.health.reservation.service.impl;

import java.util.List;
import com.health.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.health.reservation.mapper.TReportItemMapper;
import com.health.reservation.domain.TReportItem;
import com.health.reservation.service.ITReportItemService;

/**
 * 体检报告明细Service业务层处理
 *
 * @author ruoyi
 * @date 2026-07-02
 */
@Service
public class TReportItemServiceImpl implements ITReportItemService
{
    @Autowired
    private TReportItemMapper tReportItemMapper;

    /**
     * 查询体检报告明细
     */
    @Override
    public TReportItem selectTReportItemById(Long id)
    {
        return tReportItemMapper.selectTReportItemById(id);
    }

    /**
     * 查询体检报告明细列表
     */
    @Override
    public List<TReportItem> selectTReportItemList(TReportItem tReportItem)
    {
        return tReportItemMapper.selectTReportItemList(tReportItem);
    }

    /**
     * 新增体检报告明细
     */
    @Override
    public int insertTReportItem(TReportItem tReportItem)
    {
        tReportItem.setCreateTime(DateUtils.getNowDate());
        return tReportItemMapper.insertTReportItem(tReportItem);
    }

    /**
     * 修改体检报告明细
     */
    @Override
    public int updateTReportItem(TReportItem tReportItem)
    {
        return tReportItemMapper.updateTReportItem(tReportItem);
    }

    /**
     * 批量删除体检报告明细
     */
    @Override
    public int deleteTReportItemByIds(Long[] ids)
    {
        return tReportItemMapper.deleteTReportItemByIds(ids);
    }

    /**
     * 删除体检报告明细信息
     */
    @Override
    public int deleteTReportItemById(Long id)
    {
        return tReportItemMapper.deleteTReportItemById(id);
    }

    /**
     * 批量新增体检报告明细
     */
    @Override
    public int batchInsertTReportItem(List<TReportItem> list)
    {
        return tReportItemMapper.batchInsertTReportItem(list);
    }

    /**
     * 根据报告ID删除明细
     */
    @Override
    public int deleteTReportItemByReportId(Long reportId)
    {
        return tReportItemMapper.deleteTReportItemByReportId(reportId);
    }

    /**
     * 根据报告ID查询明细列表
     */
    @Override
    public List<TReportItem> selectTReportItemByReportId(Long reportId)
    {
        return tReportItemMapper.selectTReportItemByReportId(reportId);
    }

    /**
     * 根据报告ID查询明细列表（含检查项参考范围）
     */
    @Override
    public List<TReportItem> selectTReportItemByReportIdWithDetail(Long reportId)
    {
        return tReportItemMapper.selectTReportItemByReportIdWithDetail(reportId);
    }
}
