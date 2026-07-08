package com.health.reservation.mapper;

import java.util.List;
import com.health.reservation.domain.TReportItem;
import org.apache.ibatis.annotations.Param;

/**
 * 体检报告明细Mapper接口
 *
 * @author ruoyi
 * @date 2026-07-02
 */
public interface TReportItemMapper
{
    /**
     * 查询体检报告明细
     *
     * @param id 体检报告明细主键
     * @return 体检报告明细
     */
    public TReportItem selectTReportItemById(Long id);

    /**
     * 查询体检报告明细列表
     *
     * @param tReportItem 体检报告明细
     * @return 体检报告明细集合
     */
    public List<TReportItem> selectTReportItemList(TReportItem tReportItem);

    /**
     * 新增体检报告明细
     *
     * @param tReportItem 体检报告明细
     * @return 结果
     */
    public int insertTReportItem(TReportItem tReportItem);

    /**
     * 修改体检报告明细
     *
     * @param tReportItem 体检报告明细
     * @return 结果
     */
    public int updateTReportItem(TReportItem tReportItem);

    /**
     * 删除体检报告明细
     *
     * @param id 体检报告明细主键
     * @return 结果
     */
    public int deleteTReportItemById(Long id);

    /**
     * 批量删除体检报告明细
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTReportItemByIds(Long[] ids);

    /**
     * 批量新增体检报告明细
     *
     * @param list 体检报告明细列表
     * @return 结果
     */
    public int batchInsertTReportItem(@Param("list") List<TReportItem> list);

    /**
     * 根据报告ID删除明细
     *
     * @param reportId 报告ID
     * @return 结果
     */
    public int deleteTReportItemByReportId(Long reportId);

    /**
     * 根据报告ID查询明细列表（含检查项参考范围）
     *
     * @param reportId 报告ID
     * @return 体检报告明细集合
     */
    public List<TReportItem> selectTReportItemByReportIdWithDetail(Long reportId);

    /**
     * 根据报告ID查询明细列表
     *
     * @param reportId 报告ID
     * @return 体检报告明细集合
     */
    public List<TReportItem> selectTReportItemByReportId(Long reportId);
}
