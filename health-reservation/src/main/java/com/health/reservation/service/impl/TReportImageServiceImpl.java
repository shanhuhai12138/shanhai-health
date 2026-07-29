package com.health.reservation.service.impl;

import java.util.List;
import com.health.common.utils.DateUtils;
import com.health.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.health.reservation.mapper.TReportImageMapper;
import com.health.reservation.domain.TReportImage;
import com.health.reservation.service.ITReportImageService;

/**
 * 体检报告图像Service业务层处理
 * 
 * @author ruoyi
 * @date 2026-07-17
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class TReportImageServiceImpl implements ITReportImageService
{
    @Autowired
    private TReportImageMapper tReportImageMapper;

    /**
     * 查询体检报告图像
     * 
     * @param id 体检报告图像主键
     * @return 体检报告图像
     */
    @Override
    public TReportImage selectTReportImageById(Long id)
    {
        return tReportImageMapper.selectTReportImageById(id);
    }

    /**
     * 查询体检报告图像列表
     * 
     * @param tReportImage 体检报告图像
     * @return 体检报告图像
     */
    @Override
    public List<TReportImage> selectTReportImageList(TReportImage tReportImage)
    {
        return tReportImageMapper.selectTReportImageList(tReportImage);
    }

    /**
     * 根据报告ID查询图像列表
     * 
     * @param reportId 报告ID
     * @return 体检报告图像集合
     */
    @Override
    public List<TReportImage> selectTReportImageByReportId(Long reportId)
    {
        return tReportImageMapper.selectTReportImageByReportId(reportId);
    }

    /**
     * 新增体检报告图像
     * 
     * @param tReportImage 体检报告图像
     * @return 结果
     */
    @Override
    public int insertTReportImage(TReportImage tReportImage)
    {
        tReportImage.setCreateTime(DateUtils.getNowDate());
        tReportImage.setCreateBy(SecurityUtils.getUsername());
        return tReportImageMapper.insertTReportImage(tReportImage);
    }

    /**
     * 修改体检报告图像
     * 
     * @param tReportImage 体检报告图像
     * @return 结果
     */
    @Override
    public int updateTReportImage(TReportImage tReportImage)
    {
        tReportImage.setUpdateTime(DateUtils.getNowDate());
        tReportImage.setUpdateBy(SecurityUtils.getUsername());
        return tReportImageMapper.updateTReportImage(tReportImage);
    }

    /**
     * 批量删除体检报告图像
     * 
     * @param ids 需要删除的体检报告图像主键
     * @return 结果
     */
    @Override
    public int deleteTReportImageByIds(Long[] ids)
    {
        return tReportImageMapper.deleteTReportImageByIds(ids);
    }

    /**
     * 删除体检报告图像信息
     * 
     * @param id 体检报告图像主键
     * @return 结果
     */
    @Override
    public int deleteTReportImageById(Long id)
    {
        return tReportImageMapper.deleteTReportImageById(id);
    }
}
