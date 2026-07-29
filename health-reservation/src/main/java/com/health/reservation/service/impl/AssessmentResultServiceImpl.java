package com.health.reservation.service.impl;

import java.util.List;
import com.health.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.health.reservation.mapper.AssessmentResultMapper;
import com.health.reservation.domain.AssessmentResult;
import com.health.reservation.service.IAssessmentResultService;

/**
 * 测评结果Service业务层处理
 *
 * @author ruoyi
 * @date 2026-07-09
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class AssessmentResultServiceImpl implements IAssessmentResultService
{
    @Autowired
    private AssessmentResultMapper assessmentResultMapper;

    /**
     * 查询测评结果
     *
     * @param id 测评结果主键
     * @return 测评结果
     */
    @Override
    public AssessmentResult selectAssessmentResultById(Long id)
    {
        return assessmentResultMapper.selectAssessmentResultById(id);
    }

    /**
     * 查询测评结果列表
     *
     * @param assessmentResult 测评结果
     * @return 测评结果
     */
    @Override
    public List<AssessmentResult> selectAssessmentResultList(AssessmentResult assessmentResult)
    {
        return assessmentResultMapper.selectAssessmentResultList(assessmentResult);
    }

    /**
     * 新增测评结果
     *
     * @param assessmentResult 测评结果
     * @return 结果
     */
    @Override
    public int insertAssessmentResult(AssessmentResult assessmentResult)
    {
        assessmentResult.setCreateTime(DateUtils.getNowDate());
        return assessmentResultMapper.insertAssessmentResult(assessmentResult);
    }

    /**
     * 修改测评结果
     *
     * @param assessmentResult 测评结果
     * @return 结果
     */
    @Override
    public int updateAssessmentResult(AssessmentResult assessmentResult)
    {
        assessmentResult.setUpdateTime(DateUtils.getNowDate());
        return assessmentResultMapper.updateAssessmentResult(assessmentResult);
    }

    /**
     * 批量删除测评结果
     *
     * @param ids 需要删除的测评结果主键
     * @return 结果
     */
    @Override
    public int deleteAssessmentResultByIds(Long[] ids)
    {
        return assessmentResultMapper.deleteAssessmentResultByIds(ids);
    }

    /**
     * 删除测评结果信息
     *
     * @param id 测评结果主键
     * @return 结果
     */
    @Override
    public int deleteAssessmentResultById(Long id)
    {
        return assessmentResultMapper.deleteAssessmentResultById(id);
    }

    @Override
    public List<AssessmentResult> selectAssessmentHistoryWithNames(Long userId)
    {
        return assessmentResultMapper.selectAssessmentHistoryWithNames(userId);
    }
}
