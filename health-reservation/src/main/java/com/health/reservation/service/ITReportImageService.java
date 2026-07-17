package com.health.reservation.service;

import java.util.List;
import com.health.reservation.domain.TReportImage;

/**
 * 体检报告图像Service接口
 * 
 * @author ruoyi
 * @date 2026-07-17
 */
public interface ITReportImageService
{
    /**
     * 查询体检报告图像
     * 
     * @param id 体检报告图像主键
     * @return 体检报告图像
     */
    public TReportImage selectTReportImageById(Long id);

    /**
     * 查询体检报告图像列表
     * 
     * @param tReportImage 体检报告图像
     * @return 体检报告图像集合
     */
    public List<TReportImage> selectTReportImageList(TReportImage tReportImage);

    /**
     * 根据报告ID查询图像列表
     * 
     * @param reportId 报告ID
     * @return 体检报告图像集合
     */
    public List<TReportImage> selectTReportImageByReportId(Long reportId);

    /**
     * 新增体检报告图像
     * 
     * @param tReportImage 体检报告图像
     * @return 结果
     */
    public int insertTReportImage(TReportImage tReportImage);

    /**
     * 修改体检报告图像
     * 
     * @param tReportImage 体检报告图像
     * @return 结果
     */
    public int updateTReportImage(TReportImage tReportImage);

    /**
     * 批量删除体检报告图像
     * 
     * @param ids 需要删除的体检报告图像主键集合
     * @return 结果
     */
    public int deleteTReportImageByIds(Long[] ids);

    /**
     * 删除体检报告图像信息
     * 
     * @param id 体检报告图像主键
     * @return 结果
     */
    public int deleteTReportImageById(Long id);
}
