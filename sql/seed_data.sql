-- =============================================
-- 传智健康 - 心理量表种子数据
-- 数据库名: health
-- =============================================

USE health;

-- ----------------------------
-- 1. PHQ-9 抑郁症筛查量表（9题）
-- ----------------------------
INSERT INTO `assessment` VALUES (1, 'PHQ9', '患者健康问卷-9项', 'PHQ-9是临床上最常用的抑郁症筛查量表，通过9个问题评估过去两周内的抑郁症状严重程度。', 27, '[{"level":"正常","min":0,"max":4,"desc":"情绪良好，无需担心"},{"level":"轻度","min":5,"max":9,"desc":"可能有轻微抑郁情绪，建议关注自我调节"},{"level":"中度","min":10,"max":14,"desc":"存在中等程度抑郁症状，建议寻求专业帮助"},{"level":"中重度","min":15,"max":19,"desc":"抑郁症状较明显，建议尽快咨询心理专业人士"},{"level":"重度","min":20,"max":27,"desc":"抑郁症状严重，建议立即就医"}]', 10, 'psychological', '0', 'admin', NOW(), 'admin', NOW(), NULL);

INSERT INTO `assessment_question` (`id`, `assessment_id`, `question_no`, `question_text`, `question_type`, `options`, `reverse_score`, `sort_order`, `create_by`, `create_time`)
VALUES
(1, 1, 1, '做事时提不起劲或没有兴趣', 'single', '[{"value":0,"text":"完全不会(1天以内)"},{"value":1,"text":"有几天"},{"value":2,"text":"一半以上天数"},{"value":3,"text":"几乎每天"}]', '0', 1, NOW()),
(2, 1, 2, '感到心情低落、沮丧或绝望', 'single', '[{"value":0,"text":"完全不会(1天以内)"},{"value":1,"text":"有几天"},{"value":2,"text":"一半以上天数"},{"value":3,"text":"几乎每天"}]', '0', 2, NOW()),
(3, 1, 3, '入睡困难、睡不安稳或睡眠过多', 'single', '[{"value":0,"text":"完全不会(1天以内)"},{"value":1,"text":"有几天"},{"value":2,"text":"一半以上天数"},{"value":3,"text":"几乎每天"}]', '0', 3, NOW()),
(4, 1, 4, '感觉疲倦或没有活力', 'single', '[{"value":0,"text":"完全不会(1天以内)"},{"value":1,"text":"有几天"},{"value":2,"text":"一半以上天数"},{"value":3,"text":"几乎每天"}]', '0', 4, NOW()),
(5, 1, 5, '食欲不振或吃太多', 'single', '[{"value":0,"text":"完全不会(1天以内)"},{"value":1,"text":"有几天"},{"value":2,"text":"一半以上天数"},{"value":3,"text":"几乎每天"}]', '0', 5, NOW()),
(6, 1, 6, '觉得自己很糟糕，或觉得自己很失败，让自己或家人失望', 'single', '[{"value":0,"text":"完全不会(1天以内)"},{"value":1,"text":"有几天"},{"value":2,"text":"一半以上天数"},{"value":3,"text":"几乎每天"}]', '0', 6, NOW()),
(7, 1, 7, '对事物专注有困难，例如看报纸或看电视时', 'single', '[{"value":0,"text":"完全不会(1天以内)"},{"value":1,"text":"有几天"},{"value":2,"text":"一半以上天数"},{"value":3,"text":"几乎每天"}]', '0', 7, NOW()),
(8, 1, 8, '行动或说话速度缓慢到他人可以察觉，或正好相反，烦躁或坐立不安', 'single', '[{"value":0,"text":"完全不会(1天以内)"},{"value":1,"text":"有几天"},{"value":2,"text":"一半以上天数"},{"value":3,"text":"几乎每天"}]', '0', 8, NOW()),
(9, 1, 9, '有不如死掉或用某种方式伤害自己的念头', 'single', '[{"value":0,"text":"完全不会(1天以内)"},{"value":1,"text":"有几天"},{"value":2,"text":"一半以上天数"},{"value":3,"text":"几乎每天"}]', '0', 9, NOW());

-- ----------------------------
-- 2. GAD-7 焦虑症筛查量表（7题）
-- ----------------------------
INSERT INTO `assessment` VALUES (2, 'GAD7', '广泛性焦虑量表-7项', 'GAD-7是广泛性焦虑障碍的筛查工具，通过7个问题评估焦虑症状的严重程度。', 21, '[{"level":"无明显焦虑","min":0,"max":4,"desc":"焦虑水平正常"},{"level":"轻度","min":5,"max":9,"desc":"可能有轻度焦虑，建议自我调节"},{"level":"中度","min":10,"max":14,"desc":"存在中等程度焦虑，建议寻求专业帮助"},{"level":"重度","min":15,"max":21,"desc":"焦虑症状较明显，建议尽快咨询心理专业人士"}]', 7, 'psychological', '0', 'admin', NOW(), 'admin', NOW(), NULL);

INSERT INTO `assessment_question` (`id`, `assessment_id`, `question_no`, `question_text`, `question_type`, `options`, `reverse_score`, `sort_order`, `create_by`, `create_time`)
VALUES
(10, 2, 1, '感到紧张、焦虑或急切', 'single', '[{"value":0,"text":"完全不会(1天以内)"},{"value":1,"text":"有几天"},{"value":2,"text":"一半以上天数"},{"value":3,"text":"几乎每天"}]', '0', 1, NOW()),
(11, 2, 2, '不能够停止或控制担忧', 'single', '[{"value":0,"text":"完全不会(1天以内)"},{"value":1,"text":"有几天"},{"value":2,"text":"一半以上天数"},{"value":3,"text":"几乎每天"}]', '0', 2, NOW()),
(12, 2, 3, '对各种各样的事情担忧过多', 'single', '[{"value":0,"text":"完全不会(1天以内)"},{"value":1,"text":"有几天"},{"value":2,"text":"一半以上天数"},{"value":3,"text":"几乎每天"}]', '0', 3, NOW()),
(13, 2, 4, '很难放松下来', 'single', '[{"value":0,"text":"完全不会(1天以内)"},{"value":1,"text":"有几天"},{"value":2,"text":"一半以上天数"},{"value":3,"text":"几乎每天"}]', '0', 4, NOW()),
(14, 2, 5, '由于不安而无法静坐', 'single', '[{"value":0,"text":"完全不会(1天以内)"},{"value":1,"text":"有几天"},{"value":2,"text":"一半以上天数"},{"value":3,"text":"几乎每天"}]', '0', 5, NOW()),
(15, 2, 6, '变得容易烦恼或急躁', 'single', '[{"value":0,"text":"完全不会(1天以内)"},{"value":1,"text":"有几天"},{"value":2,"text":"一半以上天数"},{"value":3,"text":"几乎每天"}]', '0', 6, NOW()),
(16, 2, 7, '感到似乎将有可怕的事情发生', 'single', '[{"value":0,"text":"完全不会(1天以内)"},{"value":1,"text":"有几天"},{"value":2,"text":"一半以上天数"},{"value":3,"text":"几乎每天"}]', '0', 7, NOW());

-- ----------------------------
-- 3. SAS 焦虑自评量表（20题简版用3题演示）
-- ----------------------------
INSERT INTO `assessment` VALUES (3, 'SAS', '焦虑自评量表', 'SAS是由Zung编制的焦虑自评量表，用于评估焦虑症状的轻重程度。标准分=整数部分×100/20。', 80, '[{"level":"正常","min":20,"max":50,"desc":"焦虑水平正常"},{"level":"轻度","min":51,"max":59,"desc":"有轻度焦虑症状"},{"level":"中度","min":60,"max":69,"desc":"有明显焦虑症状，建议寻求专业帮助"},{"level":"重度","min":70,"max":80,"desc":"焦虑症状严重，建议尽快就医"}]', 15, 'psychological', '0', 'admin', NOW(), 'admin', NOW(), NULL);

INSERT INTO `assessment_question` (`id`, `assessment_id`, `question_no`, `question_text`, `question_type`, `options`, `reverse_score`, `sort_order`, `create_by`, `create_time`)
VALUES
(17, 3, 1, '我觉得比平常容易紧张和着急', 'single', '[{"value":1,"text":"没有或很少时间"},{"value":2,"text":"少部分时间"},{"value":3,"text":"大部分时间"},{"value":4,"text":"绝大部分或全部时间"}]', '0', 1, NOW()),
(18, 3, 2, '我觉得无事可引起烦恼和惊慌', 'single', '[{"value":1,"text":"没有或很少时间"},{"value":2,"text":"少部分时间"},{"value":3,"text":"大部分时间"},{"value":4,"text":"绝大部分或全部时间"}]', '1', 2, NOW()),
(19, 3, 3, '我感到容易苦恼和焦虑', 'single', '[{"value":1,"text":"没有或很少时间"},{"value":2,"text":"少部分时间"},{"value":3,"text":"大部分时间"},{"value":4,"text":"绝大部分或全部时间"}]', '0', 3, NOW());
