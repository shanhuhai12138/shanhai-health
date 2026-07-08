package com.health.reservation.service;

import java.util.List;
import com.health.reservation.domain.TReport;
import com.health.reservation.domain.TReportItem;
import com.health.reservation.vo.ReportDetailVO;

/**
 * 体检报告Service接口
 *
 * @author ruoyi
 * @date 2026-07-02
 */
public interface ITReportService
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
     * 批量删除体检报告
     *
     * @param ids 需要删除的体检报告主键集合
     * @return 结果
     */
    public int deleteTReportByIds(Long[] ids);

    /**
     * 删除体检报告信息
     *
     * @param id 体检报告主键
     * @return 结果
     */
    public int deleteTReportById(Long id);

    /**
     * 根据报告编号查询报告
     *
     * @param reportNo 报告编号
     * @return 体检报告
     */
    public TReport selectTReportByReportNo(String reportNo);

    /**
     * 根据手机号和身份证号查询报告列表（用户端）
     *
     * @param phone 手机号
     * @param idcard 身份证号
     * @return 体检报告集合
     */
    public List<TReport> selectReportByPhoneAndIdcard(String phone, String idcard);

    /**
     * 查询报告详情（含分组明细和图片）
     *
     * @param id 报告主键
     * @return 报告详情VO
     */
    public ReportDetailVO selectReportDetail(Long id);

    /**
     * 审核报告（状态 0→1）
     *
     * @param id 报告ID
     * @param reviewerId 审核医生ID
     * @param reviewerName 审核医生姓名
     * @return 结果
     */
    public int auditReport(Long id, Long reviewerId, String reviewerName);

    /**
     * 发布报告（状态 1→2）
     *
     * @param id 报告ID
     * @param publisherId 发布医生ID
     * @param publisherName 发布医生姓名
     * @return 结果
     */
    public int publishReport(Long id, Long publisherId, String publisherName);

    /**
     * 归档报告（状态 2→3）
     *
     * @param id 报告ID
     * @return 结果
     */
    public int archiveReport(Long id);

    /**
     * 生成报告编号
     * 格式：RP + yyyyMMdd + 4位序号
     *
     * @return 报告编号
     */
    public String generateReportNo();

    /**
     * 导入报告明细（批量插入）
     *
     * @param reportId 报告ID
     * @param items 明细列表
     * @return 结果
     */
    public void importReportItems(Long reportId, List<TReportItem> items);

    /**
     * 计算异常标记
     * 对比 result 与 normalRange，自动标记偏高/偏低
     *
     * @param reportId 报告ID
     */
    public void computeAbnormalFlags(Long reportId);
}
