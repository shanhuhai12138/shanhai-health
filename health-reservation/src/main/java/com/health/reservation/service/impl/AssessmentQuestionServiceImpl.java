package com.health.reservation.service.impl;

import java.util.List;
import com.health.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.health.reservation.mapper.AssessmentQuestionMapper;
import com.health.reservation.domain.AssessmentQuestion;
import com.health.reservation.service.IAssessmentQuestionService;

/**
 * 量表题目Service业务层处理
 *
 * @author ruoyi
 * @date 2026-07-09
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class AssessmentQuestionServiceImpl implements IAssessmentQuestionService
{
    @Autowired
    private AssessmentQuestionMapper assessmentQuestionMapper;

    /**
     * 查询量表题目
     *
     * @param id 量表题目主键
     * @return 量表题目
     */
    @Override
    public AssessmentQuestion selectAssessmentQuestionById(Long id)
    {
        return assessmentQuestionMapper.selectAssessmentQuestionById(id);
    }

    /**
     * 查询量表题目列表
     *
     * @param assessmentQuestion 量表题目
     * @return 量表题目
     */
    @Override
    public List<AssessmentQuestion> selectAssessmentQuestionList(AssessmentQuestion assessmentQuestion)
    {
        return assessmentQuestionMapper.selectAssessmentQuestionList(assessmentQuestion);
    }

    /**
     * 新增量表题目
     *
     * @param assessmentQuestion 量表题目
     * @return 结果
     */
    @Override
    public int insertAssessmentQuestion(AssessmentQuestion assessmentQuestion)
    {
        assessmentQuestion.setCreateTime(DateUtils.getNowDate());
        return assessmentQuestionMapper.insertAssessmentQuestion(assessmentQuestion);
    }

    /**
     * 修改量表题目
     *
     * @param assessmentQuestion 量表题目
     * @return 结果
     */
    @Override
    public int updateAssessmentQuestion(AssessmentQuestion assessmentQuestion)
    {
        assessmentQuestion.setUpdateTime(DateUtils.getNowDate());
        return assessmentQuestionMapper.updateAssessmentQuestion(assessmentQuestion);
    }

    /**
     * 批量删除量表题目
     *
     * @param ids 需要删除的量表题目主键
     * @return 结果
     */
    @Override
    public int deleteAssessmentQuestionByIds(Long[] ids)
    {
        return assessmentQuestionMapper.deleteAssessmentQuestionByIds(ids);
    }

    /**
     * 删除量表题目信息
     *
     * @param id 量表题目主键
     * @return 结果
     */
    @Override
    public int deleteAssessmentQuestionById(Long id)
    {
        return assessmentQuestionMapper.deleteAssessmentQuestionById(id);
    }
}
