package com.health.assessment.service;

import java.util.List;
import com.health.assessment.domain.Assessment;
import com.health.assessment.domain.AssessmentQuestion;
import com.health.assessment.domain.AssessmentResult;

/**
 * 心理测评Service接口
 *
 * @author ruoyi
 * @date 2026-07-08
 */
public interface IAssessmentService
{
    /**
     * 查询量表列表
     */
    public List<Assessment> selectAssessmentList(Assessment assessment);

    /**
     * 查询量表详情
     */
    public Assessment selectAssessmentById(Long id);

    /**
     * 根据编码查询量表
     */
    public Assessment selectAssessmentByCode(String code);

    /**
     * 新增量表
     */
    public int insertAssessment(Assessment assessment);

    /**
     * 修改量表
     */
    public int updateAssessment(Assessment assessment);

    /**
     * 删除量表
     */
    public int deleteAssessmentById(Long id);

    /**
     * 查询量表题目列表
     */
    public List<AssessmentQuestion> selectQuestionsByAssessmentId(Long assessmentId);

    /**
     * 查询用户测评结果列表
     */
    public List<AssessmentResult> selectResultsByUserId(Long userId);

    /**
     * 查询测评结果详情
     */
    public AssessmentResult selectResultById(Long id);

    /**
     * 新增测评结果
     */
    public int insertResult(AssessmentResult result);
}
