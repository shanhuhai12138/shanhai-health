package com.health.assessment.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.health.assessment.domain.Assessment;
import com.health.assessment.domain.AssessmentQuestion;
import com.health.assessment.domain.AssessmentResult;
import com.health.assessment.mapper.AssessmentMapper;
import com.health.assessment.service.IAssessmentService;

/**
 * 心理测评Service业务层处理
 *
 * @author ruoyi
 * @date 2026-07-08
 */
@Service
public class AssessmentServiceImpl implements IAssessmentService
{
    @Autowired
    private AssessmentMapper assessmentMapper;

    @Override
    public List<Assessment> selectAssessmentList(Assessment assessment)
    {
        return assessmentMapper.selectAssessmentList(assessment);
    }

    @Override
    public Assessment selectAssessmentById(Long id)
    {
        return assessmentMapper.selectAssessmentById(id);
    }

    @Override
    public Assessment selectAssessmentByCode(String code)
    {
        return assessmentMapper.selectAssessmentByCode(code);
    }

    @Override
    public int insertAssessment(Assessment assessment)
    {
        return assessmentMapper.insertAssessment(assessment);
    }

    @Override
    public int updateAssessment(Assessment assessment)
    {
        return assessmentMapper.updateAssessment(assessment);
    }

    @Override
    public int deleteAssessmentById(Long id)
    {
        return assessmentMapper.deleteAssessmentById(id);
    }

    @Override
    public List<AssessmentQuestion> selectQuestionsByAssessmentId(Long assessmentId)
    {
        return assessmentMapper.selectQuestionsByAssessmentId(assessmentId);
    }

    @Override
    public List<AssessmentResult> selectResultsByUserId(Long userId)
    {
        return assessmentMapper.selectResultsByUserId(userId);
    }

    @Override
    public AssessmentResult selectResultById(Long id)
    {
        return assessmentMapper.selectResultById(id);
    }

    @Override
    public int insertResult(AssessmentResult result)
    {
        return assessmentMapper.insertResult(result);
    }
}
