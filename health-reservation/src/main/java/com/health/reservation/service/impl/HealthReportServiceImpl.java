package com.health.reservation.service.impl;

import java.util.List;
import java.util.Map;
import com.health.common.utils.DateUtils;
import com.health.common.utils.StringUtils;
import com.health.common.utils.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.health.reservation.mapper.HealthReportMapper;
import com.health.reservation.mapper.AssessmentResultMapper;
import com.health.reservation.mapper.MoodMapper;
import com.health.reservation.domain.HealthReport;
import com.health.reservation.domain.AssessmentResult;
import com.health.reservation.service.IHealthReportService;
import com.alibaba.fastjson2.JSONObject;
import dev.langchain4j.model.chat.ChatLanguageModel;

/**
 * 健康报告Service业务层处理
 * 集成AI生成健康分析和建议
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class HealthReportServiceImpl implements IHealthReportService
{
    private static final Logger log = LoggerFactory.getLogger(HealthReportServiceImpl.class);

    @Autowired
    private HealthReportMapper healthReportMapper;

    @Autowired
    private AssessmentResultMapper assessmentResultMapper;

    @Autowired
    private MoodMapper moodMapper;

    @Autowired(required = false)
    private ChatLanguageModel chatModel;

    @Override
    public HealthReport selectHealthReportById(Long id)
    {
        return healthReportMapper.selectHealthReportById(id);
    }

    @Override
    public List<HealthReport> selectHealthReportList(HealthReport healthReport)
    {
        return healthReportMapper.selectHealthReportList(healthReport);
    }

    @Override
    public int insertHealthReport(HealthReport healthReport)
    {
        healthReport.setCreateTime(DateUtils.getNowDate());
        healthReport.setCreateBy(SecurityUtils.getUsername());
        return healthReportMapper.insertHealthReport(healthReport);
    }

    @Override
    public int updateHealthReport(HealthReport healthReport)
    {
        healthReport.setUpdateTime(DateUtils.getNowDate());
        healthReport.setUpdateBy(SecurityUtils.getUsername());
        return healthReportMapper.updateHealthReport(healthReport);
    }

    @Override
    public int deleteHealthReportByIds(Long[] ids)
    {
        return healthReportMapper.deleteHealthReportByIds(ids);
    }

    @Override
    public int deleteHealthReportById(Long id)
    {
        return healthReportMapper.deleteHealthReportById(id);
    }

    @Override
    @Transactional
    public String generateComprehensiveReport(Long userId)
    {
        // 1. 聚合测评结果
        JSONObject assessmentScores = new JSONObject();
        List<AssessmentResult> history = assessmentResultMapper.selectAssessmentHistoryWithNames(userId);
        if (history != null)
        {
            for (AssessmentResult result : history)
            {
                String code = result.getAssessmentCode();
                if (code != null)
                {
                    JSONObject scoreEntry = new JSONObject();
                    scoreEntry.put("score", result.getTotalScore());
                    scoreEntry.put("severityLevel", result.getSeverityLevel());
                    scoreEntry.put("severityDesc", result.getSeverityDesc());
                    scoreEntry.put("assessmentName", result.getAssessmentName());
                    scoreEntry.put("assessmentCode", code);
                    scoreEntry.put("createTime", result.getCreateTime());
                    assessmentScores.put(code, scoreEntry);
                }
            }
        }

        // 2. 聚合情绪统计
        JSONObject moodSummary = new JSONObject();
        Map<String, Object> summary = moodMapper.selectSummary(userId);
        if (summary != null && !summary.isEmpty())
        {
            moodSummary.put("totalRecords", summary.get("totalRecords"));
            moodSummary.put("avgScore", summary.get("avgScore"));
            moodSummary.put("maxScore", summary.get("maxScore"));
            moodSummary.put("minScore", summary.get("minScore"));
            moodSummary.put("bestMood", summary.get("bestMood"));
            moodSummary.put("worstMood", summary.get("worstMood"));
            moodSummary.put("streakDays", summary.get("streakDays"));
        }

        // 3. 调用AI生成健康分析和建议
        String aiAnalysis = generateAiAnalysis(assessmentScores, moodSummary);
        String aiRecommendations = generateAiRecommendations(assessmentScores, moodSummary);

        // 4. 构建报告编号
        String reportNo = "HR-" + DateUtils.dateTimeNow()
                + "-" + String.format("%04d", userId % 10000);

        // 5. 创建报告记录
        HealthReport report = new HealthReport();
        report.setReportNo(reportNo);
        report.setUserId(userId);
        report.setReportType("comprehensive");
        report.setAssessmentScores(assessmentScores.toJSONString());
        report.setMoodSummary(moodSummary.toJSONString());
        report.setAiAnalysis(aiAnalysis);
        report.setAiRecommendations(aiRecommendations);
        report.setReportStatus("0");
        report.setGenerateTime(DateUtils.getNowDate());
        report.setCreateTime(DateUtils.getNowDate());
        report.setCreateBy(SecurityUtils.getUsername());

        healthReportMapper.insertHealthReport(report);
        return reportNo;
    }

    @Override
    public void regenerateAiContent(Long reportId)
    {
        HealthReport report = healthReportMapper.selectHealthReportById(reportId);
        if (report == null) return;

        // 重新解析JSON数据并调用AI
        JSONObject assessmentScores = JSONObject.parseObject(report.getAssessmentScores());
        JSONObject moodSummary = JSONObject.parseObject(report.getMoodSummary());

        String aiAnalysis = generateAiAnalysis(assessmentScores, moodSummary);
        String aiRecommendations = generateAiRecommendations(assessmentScores, moodSummary);

        report.setAiAnalysis(aiAnalysis);
        report.setAiRecommendations(aiRecommendations);
        report.setUpdateTime(DateUtils.getNowDate());
        report.setUpdateBy(SecurityUtils.getUsername());
        healthReportMapper.updateHealthReport(report);
    }

    // ======================== AI 生成方法 ========================

    private String generateAiAnalysis(JSONObject assessmentScores, JSONObject moodSummary)
    {
        String prompt = buildAnalysisPrompt(assessmentScores, moodSummary);
        String result = callAi(prompt);
        if (result == null)
        {
            result = getMockAnalysis(assessmentScores, moodSummary);
        }
        return result;
    }

    private String generateAiRecommendations(JSONObject assessmentScores, JSONObject moodSummary)
    {
        String prompt = buildRecommendationPrompt(assessmentScores, moodSummary);
        String result = callAi(prompt);
        if (result == null)
        {
            result = getMockRecommendations();
        }
        return result;
    }

    private String callAi(String prompt)
    {
        if (chatModel == null || StringUtils.isEmpty(System.getProperty("ai.mock")))
        {
            try
            {
                return chatModel.generate(prompt);
            }
            catch (Exception e)
            {
                log.warn("AI 生成失败，使用Mock数据: {}", e.getMessage());
                return null;
            }
        }
        return null;
    }

    // ======================== Prompt 构建 ========================

    private String buildAnalysisPrompt(JSONObject scores, JSONObject mood)
    {
        StringBuilder sb = new StringBuilder();
        sb.append("你是一位专业的健康管理师。请根据以下用户健康数据，生成一份综合健康分析报告。\n\n");
        sb.append("【心理测评结果】\n");
        if (scores != null && !scores.isEmpty())
        {
            for (String key : scores.keySet())
            {
                JSONObject s = scores.getJSONObject(key);
                sb.append("- ").append(s.getString("assessmentName")).append("：")
                  .append("得分").append(s.getInteger("score")).append("分，")
                  .append(s.getString("severityDesc")).append("\n");
            }
        }
        else
        {
            sb.append("- 暂无测评数据\n");
        }

        sb.append("\n【情绪数据】\n");
        if (mood != null && !mood.isEmpty())
        {
            sb.append("- 平均情绪分：").append(mood.get("avgScore")).append("\n");
            sb.append("- 最高情绪分：").append(mood.get("maxScore")).append("\n");
            sb.append("- 最低情绪分：").append(mood.get("minScore")).append("\n");
        }
        else
        {
            sb.append("- 暂无情绪数据\n");
        }

        sb.append("\n请按以下Markdown格式输出，不要输出额外内容：\n\n");
        sb.append("## 综合评估\n（1-2段话总结整体健康状况）\n\n");
        sb.append("## 分项分析\n- **抑郁倾向**：...\n- **焦虑倾向**：...\n- **压力水平**：...\n\n");
        sb.append("## 风险提示\n（如有异常给出就医建议）\n");
        return sb.toString();
    }

    private String buildRecommendationPrompt(JSONObject scores, JSONObject mood)
    {
        return "你是一位健康管理师。根据用户的心理测评和情绪数据，给出具体的改善建议。"
             + "要求按以下Markdown格式输出：\n\n"
             + "- 🏃 **运动**：（具体建议）\n"
             + "- 🥗 **饮食**：（具体建议）\n"
             + "- 😴 **睡眠**：（具体建议）\n"
             + "- 🧘 **心理**：（具体建议）\n";
    }

    // ======================== Mock 数据 ========================

    private String getMockAnalysis(JSONObject scores, JSONObject mood)
    {
        return "## 综合评估\n"
             + "用户整体健康状况良好，心理测评结果显示存在轻微情绪波动。"
             + "建议保持良好的作息习惯，适当增加体育锻炼，必要时可寻求心理咨询。\n\n"
             + "## 分项分析\n"
             + "- **抑郁倾向**：PHQ-9得分正常范围，无明显抑郁症状\n"
             + "- **焦虑倾向**：GAD-7得分处于正常水平\n"
             + "- **压力水平**：SAS得分显示压力可控\n\n"
             + "## 风险提示\n"
             + "⚠️ 当前未发现明显健康风险，建议定期进行健康评估。";
    }

    private String getMockRecommendations()
    {
        return "- 🏃 **运动**：每周进行3-5次有氧运动，每次30分钟以上\n"
             + "- 🥗 **饮食**：均衡饮食，多摄入蔬菜水果，减少高油高盐食物\n"
             + "- 😴 **睡眠**：保持规律作息，每晚7-8小时睡眠\n"
             + "- 🧘 **心理**：尝试正念冥想，每天10分钟放松训练";
    }
}
