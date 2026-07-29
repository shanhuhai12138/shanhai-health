-- =============================================
-- 传智健康 - 演示种子数据
-- 执行顺序：在 system.sql + reservation.sql + new_tables.sql + seed_data.sql + ai.sql 之后执行
-- 用途：为 admin 用户提供演示数据（测评结果/情绪记录/通知/报告等），登录即可见
-- 注意：所有数据通过 userId 关联，不涉及手机号
-- =============================================

USE health;

-- ----------------------------
-- 1. 测评结果（3条，关联 admin userId=1）
--    PHQ-9：12分（中度）
--    GAD-7：8分（轻度）
--    SAS：65分（中度）
-- ----------------------------
INSERT INTO `assessment_result` (`id`, `user_id`, `assessment_id`, `total_score`, `severity_level`, `severity_desc`, `answers`, `ai_analysis`, `ai_suggestion`, `duration_seconds`, `create_time`)
VALUES
(1, 1, 1, 12, '中度', '存在中等程度抑郁症状，建议寻求专业帮助',
 '[{"questionId":1,"score":1},{"questionId":2,"score":2},{"questionId":3,"score":2},{"questionId":4,"score":1},{"questionId":5,"score":1},{"questionId":6,"score":1},{"questionId":7,"score":2},{"questionId":8,"score":0},{"questionId":9,"score":1}]',
 '根据PHQ-9测评结果，总分12分，属于中度抑郁水平。其中感到心情低落和对事物专注有困难两项得分较高，提示近期可能存在持续的情绪低落和注意力问题。',
 '建议：1.保持规律作息，每天保证7-8小时睡眠；2.适当增加有氧运动，每周3次以上；3.与亲友多交流；4.如症状持续超过2周建议到心理科进一步评估。',
 245, NOW() - INTERVAL 3 DAY),

(2, 1, 2, 8, '轻度', '可能有轻度焦虑，建议自我调节',
 '[{"questionId":10,"score":1},{"questionId":11,"score":2},{"questionId":12,"score":1},{"questionId":13,"score":1},{"questionId":14,"score":0},{"questionId":15,"score":2},{"questionId":16,"score":1}]',
 'GAD-7测评总分8分，提示存在轻度焦虑症状，主要表现在紧张焦虑感和烦躁不安方面，属于可自我调节的范围。',
 '建议：1.尝试正念冥想或深呼吸练习，每天10分钟；2.减少咖啡因摄入；3.保证每周有固定的放松时间。',
 189, NOW() - INTERVAL 2 DAY),

(3, 1, 3, 7, '中度', '存在中等程度焦虑症状',
 '[{"questionId":17,"score":3},{"questionId":18,"score":2},{"questionId":19,"score":3},{"questionId":20,"score":2},{"questionId":21,"score":2},{"questionId":22,"score":3},{"questionId":23,"score":2},{"questionId":24,"score":2},{"questionId":25,"score":3},{"questionId":26,"score":2},{"questionId":27,"score":2},{"questionId":28,"score":3},{"questionId":29,"score":2},{"questionId":30,"score":3},{"questionId":31,"score":2},{"questionId":32,"score":3},{"questionId":33,"score":2},{"questionId":34,"score":3},{"questionId":35,"score":2},{"questionId":36,"score":3}]',
 'SAS测评总分65分（标准分），提示存在中度焦虑症状。各条目得分较均匀，建议结合PHQ-9和GAD-7结果整体关注心理健康。',
 '建议建立规律的生活方式，保持适度运动，必要时寻求专业心理咨询。',
 312, NOW() - INTERVAL 1 DAY);

-- ----------------------------
-- 2. 情绪记录（过去7天，每天1条，关联 admin）
-- ----------------------------
INSERT INTO `mood_record` (`id`, `user_id`, `mood_label`, `mood_emoji`, `mood_score`, `energy_level`, `sleep_hours`, `exercise_minutes`, `notes`, `record_time`, `create_time`)
VALUES
(1, 1, '开心', 'happy', 8, 4, 7.5, 30, '今天天气很好，出去散步了', NOW() - INTERVAL 6 DAY, NOW() - INTERVAL 6 DAY),
(2, 1, '平静', 'calm', 7, 3, 7.0, 20, '工作正常，心情平稳', NOW() - INTERVAL 5 DAY, NOW() - INTERVAL 5 DAY),
(3, 1, '疲惫', 'tired', 4, 2, 6.0, 0, '加班到很晚，有点累', NOW() - INTERVAL 4 DAY, NOW() - INTERVAL 4 DAY),
(4, 1, '焦虑', 'anxious', 3, 2, 5.5, 0, '有些工作上的压力', NOW() - INTERVAL 3 DAY, NOW() - INTERVAL 3 DAY),
(5, 1, '开心', 'happy', 9, 5, 8.0, 45, '周末和朋友聚会，很开心', NOW() - INTERVAL 2 DAY, NOW() - INTERVAL 2 DAY),
(6, 1, '平静', 'calm', 6, 3, 7.5, 30, '休息日，放松了一下', NOW() - INTERVAL 1 DAY, NOW() - INTERVAL 1 DAY),
(7, 1, '开心', 'happy', 7, 4, 7.0, 20, '新的一周，状态不错', NOW(), NOW());

-- ----------------------------
-- 3. 综合健康报告（AI生成）
-- ----------------------------
INSERT INTO `health_report` (`id`, `report_no`, `user_id`, `report_type`, `report_status`, `ai_analysis`, `ai_recommendations`, `generate_time`, `create_time`)
VALUES
(1, 'HR-20260722-001', 1, '0', '2',
 '基于您近期的心理测评和情绪记录数据，综合分析如下：\n【心理测评概况】\n- PHQ-9抑郁测评得分12分（中度）\n- GAD-7焦虑测评得分8分（轻度）\n- SAS焦虑自评得分65分（中度）\n\n【情绪趋势分析】\n近7天情绪平均分6.3分，整体处于中等偏上水平。积极情绪占57%，消极情绪占29%。',
 '【综合建议】\n1.每周保持3次以上有氧运动，每次30分钟以上\n2.保持规律作息，目标睡眠时长7-8小时\n3.可尝试正念冥想，每天练习10-15分钟\n4.保持每周至少1次社交活动\n5.如症状持续超过2周建议到心理科评估',
 NOW(), NOW());

-- ----------------------------
-- 4. 通知（5条，使用 sys_message 表）
-- ----------------------------
INSERT INTO `sys_message` (`id`, `user_id`, `title`, `content`, `message_type`, `is_read`, `create_time`)
VALUES
(1, 1, '健康报告已生成', '您的综合健康报告已生成，点击查看详细分析内容。', 'report', '0', NOW() - INTERVAL 1 DAY),
(2, 1, '预约提醒', '您明日有一个体检预约，请提前做好准备。', 'appointment', '0', NOW() - INTERVAL 12 HOUR),
(3, 1, '测评结果通知', '您完成的SAS焦虑自评量表结果已出，点击查看。', 'assessment', '0', NOW() - INTERVAL 2 DAY),
(4, 1, '系统维护通知', '系统将于本周日凌晨2:00-4:00进行维护升级。', 'system', '0', NOW() - INTERVAL 3 DAY),
(5, 1, '新功能上线', 'AI健康助手已经上线，点击开始对话。', 'system', '0', NOW() - INTERVAL 5 DAY);

-- ----------------------------
-- 5. 咨询师（2位）
-- ----------------------------
INSERT INTO `counselor` (`id`, `user_id`, `real_name`, `title`, `specialties`, `bio`, `education`, `hourly_rate`, `satisfaction_rate`, `consultation_count`, `experience_years`, `avatar`, `status`, `create_time`)
VALUES
(1, 1, '张明', '高级心理咨询师',
 '["情绪管理","焦虑症","抑郁症","人际关系"]',
 '从事心理咨询工作12年，累计咨询时长超过5000小时，擅长认知行为疗法（CBT）和正念疗法。',
 '北京大学心理学硕士', 500, 98.00, 1200, 12, '', '0', NOW()),
(2, 2, '李华', '资深心理咨询师',
 '["婚姻家庭","亲子教育","职业规划","压力管理"]',
 '国家二级心理咨询师，拥有8年临床咨询经验。',
 '华东师范大学心理学博士', 400, 95.00, 800, 8, '', '0', NOW());

-- ----------------------------
-- 6. 咨询师排班（本周）
-- ----------------------------
INSERT INTO `counselor_schedule` (`id`, `counselor_id`, `schedule_date`, `start_time`, `end_time`, `total_slots`, `available_slots`, `is_available`, `create_time`)
VALUES
(1, 1, CURDATE() + INTERVAL 1 DAY, '09:00', '10:00', 10, 10, '1', NOW()),
(2, 1, CURDATE() + INTERVAL 1 DAY, '10:00', '11:00', 10, 10, '1', NOW()),
(3, 1, CURDATE() + INTERVAL 1 DAY, '14:00', '15:00', 10, 10, '1', NOW()),
(4, 1, CURDATE() + INTERVAL 2 DAY, '09:00', '10:00', 10, 10, '1', NOW()),
(5, 1, CURDATE() + INTERVAL 2 DAY, '10:00', '11:00', 10, 10, '1', NOW()),
(6, 2, CURDATE() + INTERVAL 1 DAY, '09:00', '10:00', 10, 10, '1', NOW()),
(7, 2, CURDATE() + INTERVAL 1 DAY, '14:00', '15:00', 10, 10, '1', NOW()),
(8, 2, CURDATE() + INTERVAL 3 DAY, '09:00', '10:00', 10, 10, '1', NOW()),
(9, 2, CURDATE() + INTERVAL 3 DAY, '10:00', '11:00', 10, 10, '1', NOW()),
(10, 2, CURDATE() + INTERVAL 3 DAY, '14:00', '15:00', 10, 10, '1', NOW());
