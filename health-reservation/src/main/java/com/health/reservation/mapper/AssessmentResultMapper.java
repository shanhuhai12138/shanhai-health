package com.health.reservation.mapper;

import java.util.List;
import com.health.reservation.domain.AssessmentResult;

/**
 * 测评结果Mapper接口
 *
 * @author ruoyi
 * @date 2026-07-09
 */
public interface AssessmentResultMapper
{
    /**
     * 查询测评结果
     *
     * @param id 测评结果主键
     * @return 测评结果
     */
    public AssessmentResult selectAssessmentResultById(Long id);

    /**
     * 查询测评结果列表
     *
     * @param assessmentResult 测评结果
     * @return 测评结果集合
     */
    public List<AssessmentResult> selectAssessmentResultList(AssessmentResult assessmentResult);

    /**
     * 新增测评结果
     *
     * @param assessmentResult 测评结果
     * @return 结果
     */
    public int insertAssessmentResult(AssessmentResult assessmentResult);

    /**
     * 修改测评结果
     *
     * @param assessmentResult 测评结果
     * @return 结果
     */
    public int updateAssessmentResult(AssessmentResult assessmentResult);

    /**
     * 删除测评结果
     *
     * @param id 测评结果主键
     * @return 结果
     */
    public int deleteAssessmentResultById(Long id);

    /**
     * 批量删除测评结果
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteAssessmentResultByIds(Long[] ids);

    /**
     * 查询用户测评历史(关联量表名称)
     *
     * @param userId 用户ID
     * @return 测评结果列表
     */
    public List<AssessmentResult> selectAssessmentHistoryWithNames(Long userId);
}
