package com.health.reservation.mapper;

import java.util.List;
import com.health.reservation.domain.TReport;
import org.apache.ibatis.annotations.Param;

/**
 * 体检报告Mapper接口
 *
 * @author ruoyi
 * @date 2026-07-02
 */
public interface TReportMapper
{
    /**
     * 查询体检报告
     *
     * @param id 体检报告主键
     * @return 体检报告
     */
    public TReport selectTReportById(Long id);

    /**
     * 查询体检报告列表
     *
     * @param tReport 体检报告
     * @return 体检报告集合
     */
    public List<TReport> selectTReportList(TReport tReport);

    /**
     * 新增体检报告
     *
     * @param tReport 体检报告
     * @return 结果
     */
    public int insertTReport(TReport tReport);

    /**
     * 修改体检报告
     *
     * @param tReport 体检报告
     * @return 结果
     */
    public int updateTReport(TReport tReport);

    /**
     * 删除体检报告
     *
     * @param id 体检报告主键
     * @return 结果
     */
    public int deleteTReportById(Long id);

    /**
     * 批量删除体检报告
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTReportByIds(Long[] ids);

    /**
     * 根据报告编号查询报告
     *
     * @param reportNo 报告编号
     * @return 体检报告
     */
    public TReport selectTReportByReportNo(String reportNo);

    /**
     * 根据手机号和身份证号查询报告列表
     *
     * @param phone 手机号
     * @param idcard 身份证号
     * @return 体检报告集合
     */
    public List<TReport> selectTReportByPhoneAndIdcard(@Param("phone") String phone, @Param("idcard") String idcard);

    /**
     * 更新报告状态
     *
     * @param id 报告ID
     * @param status 新状态
     * @return 结果
     */
    public int updateReportStatus(@Param("id") Long id, @Param("status") String status);

    /**
     * 查询今日最大报告编号（用于生成新编号）
     *
     * @param dateStr 今日日期 yyyyMMdd
     * @return 最大编号，不存在则返回 null
     */
    public String selectMaxReportNoByDate(@Param("dateStr") String dateStr);
}
