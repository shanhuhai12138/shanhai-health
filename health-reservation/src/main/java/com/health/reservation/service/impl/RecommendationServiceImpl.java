package com.health.reservation.service.impl;

import com.alibaba.fastjson2.JSONObject;
import com.health.reservation.domain.AssessmentResult;
import com.health.reservation.domain.RecommendationRule;
import com.health.reservation.mapper.AssessmentResultMapper;
import com.health.reservation.mapper.RecommendationRuleMapper;
import com.health.reservation.service.IRecommendationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RecommendationServiceImpl implements IRecommendationService {

    private static final Logger log = LoggerFactory.getLogger(RecommendationServiceImpl.class);

    @Autowired
    private RecommendationRuleMapper recommendationRuleMapper;

    @Autowired
    private AssessmentResultMapper assessmentResultMapper;

    @Override
    public List<Map<String, Object>> getRecommendations(Long userId) {
        // 1. 查询所有启用的推荐规则
        List<RecommendationRule> rules = recommendationRuleMapper.selectEnabledRules();
        if (rules == null || rules.isEmpty()) {
            return Collections.emptyList();
        }

        // 2. 查询用户最近的测评结果（关联量表名称）
        List<AssessmentResult> results = assessmentResultMapper.selectAssessmentHistoryWithNames(userId);
        if (results == null || results.isEmpty()) {
            return Collections.emptyList();
        }

        // 3. 对每条规则进行匹配
        List<Map<String, Object>> matchedList = new ArrayList<>();

        for (RecommendationRule rule : rules) {
            String matchReason = matchRule(rule, results);
            if (matchReason != null) {
                Map<String, Object> item = new LinkedHashMap<>();
                item.put("ruleName", rule.getRuleName());
                item.put("recommendType", rule.getRecommendType());
                item.put("recommendId", rule.getRecommendId());
                item.put("priority", rule.getPriority());
                item.put("matchReason", matchReason);
                matchedList.add(item);
            }
        }

        // 4. 按 priority 降序排列
        matchedList.sort((a, b) -> {
            Integer p1 = (Integer) a.get("priority");
            Integer p2 = (Integer) b.get("priority");
            if (p1 == null && p2 == null) return 0;
            if (p1 == null) return 1;
            if (p2 == null) return -1;
            return p2 - p1; // 降序
        });

        return matchedList;
    }

    /**
     * 尝试将规则与用户的测评结果进行匹配
     *
     * @param rule    推荐规则
     * @param results 用户的测评结果列表
     * @return 匹配成功则返回匹配理由，否则返回 null
     */
    private String matchRule(RecommendationRule rule, List<AssessmentResult> results) {
        String conditionType = rule.getConditionType();
        String conditionField = rule.getConditionField();
        String conditionValueStr = rule.getConditionValue();

        if (conditionType == null || conditionField == null || conditionValueStr == null) {
            return null;
        }

        JSONObject conditionValue;
        try {
            conditionValue = JSONObject.parseObject(conditionValueStr);
        } catch (Exception e) {
            log.warn("规则 {} conditionValue 解析失败: {}", rule.getId(), conditionValueStr);
            return null;
        }

        if (conditionValue == null) {
            return null;
        }

        for (AssessmentResult result : results) {
            String reason = evaluateCondition(conditionType, conditionField, conditionValue, result);
            if (reason != null) {
                return reason;
            }
        }

        return null;
    }

    /**
     * 评估单条规则条件是否匹配某个测评结果
     *
     * @param conditionType  条件类型（range、equals 等）
     * @param conditionField 条件字段（totalScore、severityLevel、assessmentCode 等）
     * @param conditionValue 条件值 JSON
     * @param result         测评结果
     * @return 匹配理由，不匹配返回 null
     */
    private String evaluateCondition(String conditionType, String conditionField,
                                     JSONObject conditionValue, AssessmentResult result) {
        Object fieldValue = getFieldValue(result, conditionField);

        if (fieldValue == null) {
            return null;
        }

        switch (conditionType.toUpperCase()) {
            case "RANGE": {
                // conditionValue 格式: {"min": 10, "max": 20} 或只有 min/max
                if (fieldValue instanceof Number) {
                    double val = ((Number) fieldValue).doubleValue();
                    Double min = conditionValue.getDouble("min");
                    Double max = conditionValue.getDouble("max");

                    boolean matched = true;
                    if (min != null && val < min) {
                        matched = false;
                    }
                    if (max != null && val > max) {
                        matched = false;
                    }

                    if (matched) {
                        StringBuilder sb = new StringBuilder();
                        sb.append("测评总分 ").append(fieldValue);
                        if (min != null && max != null) {
                            sb.append(" 在范围 [").append(min).append(", ").append(max).append("] 内");
                        } else if (min != null) {
                            sb.append(" ≥ ").append(min);
                        } else if (max != null) {
                            sb.append(" ≤ ").append(max);
                        }
                        return sb.toString();
                    }
                }
                return null;
            }
            case "EQUALS": {
                // conditionValue 格式: {"value": "严重"}
                String targetValue = conditionValue.getString("value");
                if (targetValue != null && targetValue.equals(String.valueOf(fieldValue))) {
                    return "测评结果 " + conditionField + " 等于 \"" + targetValue + "\"";
                }
                return null;
            }
            case "CONTAINS": {
                // conditionValue 格式: {"value": "抑郁"}
                String targetValue = conditionValue.getString("value");
                if (targetValue != null && String.valueOf(fieldValue).contains(targetValue)) {
                    return "测评结果 " + conditionField + " 包含 \"" + targetValue + "\"";
                }
                return null;
            }
            default:
                log.debug("未知的 conditionType: {}", conditionType);
                return null;
        }
    }

    /**
     * 根据字段名获取 AssessmentResult 的对应字段值
     */
    private Object getFieldValue(AssessmentResult result, String field) {
        if (field == null) {
            return null;
        }
        switch (field) {
            case "totalScore":
                return result.getTotalScore();
            case "severityLevel":
                return result.getSeverityLevel();
            case "severityDesc":
                return result.getSeverityDesc();
            case "assessmentName":
                return result.getAssessmentName();
            case "assessmentCode":
                return result.getAssessmentCode();
            case "id":
                return result.getId();
            case "userId":
                return result.getUserId();
            case "assessmentId":
                return result.getAssessmentId();
            default:
                log.debug("未识别的条件字段: {}", field);
                return null;
        }
    }
}
