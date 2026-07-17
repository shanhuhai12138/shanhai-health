package com.health.reservation.service;

import java.util.List;
import com.health.reservation.domain.AssessmentQuestion;

/**
 * 量表题目Service接口
 *
 * @author ruoyi
 * @date 2026-07-09
 */
public interface IAssessmentQuestionService
{
    /**
     * 查询量表题目
     *
     * @param id 量表题目主键
     * @return 量表题目
     */
    public AssessmentQuestion selectAssessmentQuestionById(Long id);

    /**
     * 查询量表题目列表
     *
     * @param assessmentQuestion 量表题目
     * @return 量表题目集合
     */
    public List<AssessmentQuestion> selectAssessmentQuestionList(AssessmentQuestion assessmentQuestion);

    /**
     * 新增量表题目
     *
     * @param assessmentQuestion 量表题目
     * @return 结果
     */
    public int insertAssessmentQuestion(AssessmentQuestion assessmentQuestion);

    /**
     * 修改量表题目
     *
     * @param assessmentQuestion 量表题目
     * @return 结果
     */
    public int updateAssessmentQuestion(AssessmentQuestion assessmentQuestion);

    /**
     * 批量删除量表题目
     *
     * @param ids 需要删除的量表题目主键集合
     * @return 结果
     */
    public int deleteAssessmentQuestionByIds(Long[] ids);

    /**
     * 删除量表题目信息
     *
     * @param id 量表题目主键
     * @return 结果
     */
    public int deleteAssessmentQuestionById(Long id);
}
