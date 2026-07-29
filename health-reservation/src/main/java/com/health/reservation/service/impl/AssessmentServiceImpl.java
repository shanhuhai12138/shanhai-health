package com.health.reservation.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.health.common.exception.ServiceException;
import com.health.common.utils.DateUtils;
import com.health.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.health.reservation.mapper.AssessmentMapper;
import com.health.reservation.domain.Assessment;
import com.health.reservation.domain.AssessmentQuestion;
import com.health.reservation.domain.AssessmentResult;
import com.health.reservation.service.IAssessmentService;
import com.health.reservation.service.IAssessmentQuestionService;
import com.health.reservation.service.IAssessmentResultService;

/**
 * 心理量表Service业务层处理 *
 * @author ruoyi
 * @date 2026-07-09
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class AssessmentServiceImpl implements IAssessmentService
{
    @Autowired
    private AssessmentMapper assessmentMapper;

    @Autowired
    private IAssessmentQuestionService assessmentQuestionService;

    @Autowired
    private IAssessmentResultService assessmentResultService;

    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 查询心理量表
     *
     * @param id 心理量表主键
     * @return 心理量表
     */
    @Override
    public Assessment selectAssessmentById(Long id)
    {
        return assessmentMapper.selectAssessmentById(id);
    }

    /**
     * 查询心理量表列表
     *
     * @param assessment 心理量表
     * @return 心理量表
     */
    @Override
    public List<Assessment> selectAssessmentList(Assessment assessment)
    {
        return assessmentMapper.selectAssessmentList(assessment);
    }

    /**
     * 新增心理量表
     *
     * @param assessment 心理量表
     * @return 结果
     */
    @Override
    public int insertAssessment(Assessment assessment)
    {
        assessment.setCreateTime(DateUtils.getNowDate());
        assessment.setCreateBy(SecurityUtils.getUsername());
        return assessmentMapper.insertAssessment(assessment);
    }

    /**
     * 修改心理量表
     *
     * @param assessment 心理量表
     * @return 结果
     */
    @Override
    public int updateAssessment(Assessment assessment)
    {
        assessment.setUpdateTime(DateUtils.getNowDate());
        assessment.setUpdateBy(SecurityUtils.getUsername());
        return assessmentMapper.updateAssessment(assessment);
    }

    /**
     * 批量删除心理量表
     *
     * @param ids 需要删除的心理量表主键
     * @return 结果
     */
    @Override
    public int deleteAssessmentByIds(Long[] ids)
    {
        return assessmentMapper.deleteAssessmentByIds(ids);
    }

    /**
     * 删除心理量表信息
     *
     * @param id 心理量表主键
     * @return 结果
     */
    @Override
    public int deleteAssessmentById(Long id)
    {
        return assessmentMapper.deleteAssessmentById(id);
    }

    @Override
    public Map<String, Object> submitAssessment(Map<String, Object> submitData)
    {
        // 1. 获取量表信息
        Long assessmentId = Long.valueOf(submitData.get("assessmentId").toString());
        Assessment assessment = assessmentMapper.selectAssessmentById(assessmentId);
        if (assessment == null)
        {
            throw new ServiceException("量表不存在");
        }
        if (!"0".equals(assessment.getStatus()))
        {
            throw new ServiceException("该量表已停用");
        }

        // 2. 获取所有题目
        AssessmentQuestion questionQuery = new AssessmentQuestion();
        questionQuery.setAssessmentId(assessmentId);
        List<AssessmentQuestion> questions = assessmentQuestionService.selectAssessmentQuestionList(questionQuery);
        if (questions.isEmpty())
        {
            throw new ServiceException("该量表没有题目");
        }

        // 3. 解析答案
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> answerList = objectMapper.convertValue(
            submitData.get("answers"), new TypeReference<List<Map<String, Object>>>() {});

        // 4. 计算总分(含反向计分
        long totalScore = 0;
        List<Map<String, Object>> details = new ArrayList<>();
        for (Map<String, Object> answer : answerList)
        {
            Long questionId = Long.valueOf(answer.get("questionId").toString());
            int answerScore = Integer.parseInt(answer.get("score").toString());

            AssessmentQuestion question = questions.stream()
                .filter(q -> q.getId().equals(questionId))
                .findFirst()
                .orElse(null);
            if (question == null)
            {
                throw new ServiceException("题目ID " + questionId + " 不存在于该量表中");
            }

            int finalScore = answerScore;
            // 应用反向计分
            if ("1".equals(question.getReverseScore()))
            {
                finalScore = calculateReverseScore(answerScore, question);
            }
            totalScore += finalScore;

            Map<String, Object> detail = new HashMap<>();
            detail.put("questionId", questionId);
            detail.put("questionNo", question.getQuestionNo());
            detail.put("answerScore", answerScore);
            detail.put("finalScore", finalScore);
            detail.put("reverseScore", "1".equals(question.getReverseScore()));
            details.add(detail);
        }

        // 5. 确定严重程度等级
        Map<String, Object> levelInfo = determineLevel(assessment.getSeverityLevels(), totalScore);

        // 6. 保存测评结果
        // Support anonymous submission: prefer userId from submitData, fallback to 0L
        Long userId = null;
        if (submitData.containsKey("userId"))
        {
            userId = Long.valueOf(submitData.get("userId").toString());
        }
        else
        {
            try { userId = SecurityUtils.getUserId(); }
            catch (Exception ignored) { userId = 0L; }
        }
        AssessmentResult result = new AssessmentResult();
        result.setUserId(userId);
        result.setAssessmentId(assessmentId);
        result.setTotalScore(totalScore);
        result.setSeverityLevel((String) levelInfo.get("level"));
        result.setSeverityDesc((String) levelInfo.get("desc"));
        try
        {
            result.setAnswers(objectMapper.writeValueAsString(answerList));
        }
        catch (Exception e)
        {
            throw new ServiceException("答案序列化失败");
        }
        result.setCreateTime(DateUtils.getNowDate());

        assessmentResultService.insertAssessmentResult(result);

        // 7. 组装返回结果
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("score", totalScore);
        resultMap.put("level", levelInfo.get("level"));
        resultMap.put("desc", levelInfo.get("desc"));
        resultMap.put("details", details);
        resultMap.put("resultId", result.getId());
        return resultMap;
    }

    @Override
    public Map<String, Object> getAssessmentForTake(Long assessmentId)
    {
        Assessment assessment = assessmentMapper.selectAssessmentById(assessmentId);
        if (assessment == null)
        {
            throw new ServiceException("量表不存在");
        }

        AssessmentQuestion questionQuery = new AssessmentQuestion();
        questionQuery.setAssessmentId(assessmentId);
        List<AssessmentQuestion> questions = assessmentQuestionService.selectAssessmentQuestionList(questionQuery);

        Map<String, Object> result = new HashMap<>();
        result.put("assessment", assessment);
        result.put("questions", questions);
        return result;
    }

    @Override
    public List<AssessmentResult> selectAssessmentHistory(Long userId)
    {
        AssessmentResult query = new AssessmentResult();
        query.setUserId(userId);
        return assessmentResultService.selectAssessmentResultList(query);
    }

    /**
     * 计算反向计分
     */
    private int calculateReverseScore(int answerScore, AssessmentQuestion question)
    {
        // 解析选项JSON获取每个选项的分值范围
        try
        {
            List<Map<String, Object>> options = objectMapper.readValue(
                question.getOptions(),
                new TypeReference<List<Map<String, Object>>>() {});
            if (options.isEmpty())
            {
                return answerScore;
            }
            // 假设选项按顺序排列, 分值从1到n, 反向计为 n+1-score
            int maxScore = options.size();
            return maxScore + 1 - answerScore;
        }
        catch (Exception e)
        {
            return answerScore;
        }
    }

    /**
     * 根据分数段确定严重程度 */
    private Map<String, Object> determineLevel(String severityLevelsJson, long score)
    {
        Map<String, Object> result = new HashMap<>();
        result.put("level", "未知");
        result.put("desc", "");

        if (severityLevelsJson == null || severityLevelsJson.trim().isEmpty())
        {
            return result;
        }

        try
        {
            List<Map<String, Object>> levels = objectMapper.readValue(
                severityLevelsJson,
                new TypeReference<List<Map<String, Object>>>() {});

            for (Map<String, Object> level : levels)
            {
                int min = Integer.parseInt(level.get("min").toString());
                int max = Integer.parseInt(level.get("max").toString());
                if (score >= min && score <= max)
                {
                    result.put("level", level.get("level").toString());
                    result.put("desc", level.getOrDefault("desc", "").toString());
                    return result;
                }
            }
        }
        catch (Exception e)
        {
            // 解析失败时保持默认值
        }

        return result;
    }
}
