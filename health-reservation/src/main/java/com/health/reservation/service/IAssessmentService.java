package com.health.reservation.service;

import java.util.List;
import java.util.Map;
import com.health.reservation.domain.Assessment;
import com.health.reservation.domain.AssessmentResult;

/**
 * 心理量表Service接口
 *
 * @author ruoyi
 * @date 2026-07-09
 */
public interface IAssessmentService
{
    /**
     * 查询心理量表
     *
     * @param id 心理量表主键
     * @return 心理量表
     */
    public Assessment selectAssessmentById(Long id);

    /**
     * 查询心理量表列表
     *
     * @param assessment 心理量表
     * @return 心理量表集合
     */
    public List<Assessment> selectAssessmentList(Assessment assessment);

    /**
     * 新增心理量表
     *
     * @param assessment 心理量表
     * @return 结果
     */
    public int insertAssessment(Assessment assessment);

    /**
     * 修改心理量表
     *
     * @param assessment 心理量表
     * @return 结果
     */
    public int updateAssessment(Assessment assessment);

    /**
     * 批量删除心理量表
     *
     * @param ids 需要删除的心理量表主键集合
     * @return 结果
     */
    public int deleteAssessmentByIds(Long[] ids);

    /**
     * 删除心理量表信息
     *
     * @param id 心理量表主键
     * @return 结果
     */
    public int deleteAssessmentById(Long id);

    /**
     * 提交量表测评并自动评分
     *
     * @param submitData 提交数据(assessmentId, answers)
     * @return 评分结果
     */
    public Map<String, Object> submitAssessment(Map<String, Object> submitData);

    /**
     * 获取量表及题目(用于答题)
     *
     * @param assessmentId 量表ID
     * @return 量表信息和题目列表
     */
    public Map<String, Object> getAssessmentForTake(Long assessmentId);

    /**
     * 查询用户测评历史
     *
     * @param userId 用户ID
     * @return 测评结果列表
     */
    public List<AssessmentResult> selectAssessmentHistory(Long userId);
}
