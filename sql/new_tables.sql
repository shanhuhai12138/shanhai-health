-- =============================================
-- 传智健康 - 心理测评/情绪/咨询/报告模块建表
-- 数据库名: health
-- =============================================

-- ----------------------------
-- 1、心理量表表
-- ----------------------------
DROP TABLE IF EXISTS `assessment`;
CREATE TABLE `assessment` (
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '量表ID',
  `code` VARCHAR(50) NOT NULL COMMENT '量表编码，如 PHQ9/GAD7/SAS',
  `name` VARCHAR(100) NOT NULL COMMENT '量表名称',
  `description` TEXT COMMENT '量表说明',
  `total_score` INT NOT NULL COMMENT '满分',
  `severity_levels` TEXT COMMENT '严重程度分级JSON',
  `estimated_duration` INT DEFAULT 10 COMMENT '预计完成时间(分钟)',
  `category` VARCHAR(50) DEFAULT 'psychological' COMMENT '分类',
  `status` CHAR(1) DEFAULT '0' COMMENT '状态（0启用 1禁用）',
  `create_by` VARCHAR(64) DEFAULT '' COMMENT '创建者',
  `create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
  `update_by` VARCHAR(64) DEFAULT '' COMMENT '更新者',
  `update_time` DATETIME DEFAULT NULL COMMENT '更新时间',
  `remark` VARCHAR(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_code` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='心理量表表';

-- ----------------------------
-- 2、量表题目表
-- ----------------------------
DROP TABLE IF EXISTS `assessment_question`;
CREATE TABLE `assessment_question` (
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '题目ID',
  `assessment_id` BIGINT NOT NULL COMMENT '关联量表ID',
  `question_no` INT NOT NULL COMMENT '题号',
  `question_text` TEXT NOT NULL COMMENT '题干',
  `question_type` VARCHAR(20) DEFAULT 'single' COMMENT '题型：single/multiple/text',
  `options` TEXT COMMENT '选项JSON',
  `reverse_score` CHAR(1) DEFAULT '0' COMMENT '是否反向计分（0否 1是）',
  `sort_order` INT DEFAULT 0 COMMENT '排序',
  `create_time` DATETIME DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_assessment_id` (`assessment_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='量表题目表';

-- ----------------------------
-- 3、测评结果表
-- ----------------------------
DROP TABLE IF EXISTS `assessment_result`;
CREATE TABLE `assessment_result` (
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '结果ID',
  `user_id` BIGINT NOT NULL COMMENT '用户ID',
  `assessment_id` BIGINT NOT NULL COMMENT '量表ID',
  `total_score` INT NOT NULL COMMENT '总分',
  `severity_level` VARCHAR(50) COMMENT '严重程度等级',
  `severity_desc` TEXT COMMENT '结果描述',
  `answers` TEXT COMMENT '答案JSON',
  `ai_analysis` TEXT COMMENT 'AI分析结果',
  `ai_suggestion` TEXT COMMENT 'AI建议',
  `duration_seconds` INT COMMENT '答题耗时(秒)',
  `create_time` DATETIME DEFAULT NULL,
  `update_time` DATETIME DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_user_id` (`user_id`),
  KEY `idx_assessment_id` (`assessment_id`),
  KEY `idx_create_time` (`create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='测评结果表';

-- ----------------------------
-- 4、情绪记录表
-- ----------------------------
DROP TABLE IF EXISTS `mood_record`;
CREATE TABLE `mood_record` (
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '记录ID',
  `user_id` BIGINT NOT NULL COMMENT '用户ID',
  `mood_score` INT NOT NULL COMMENT '情绪评分 1-10（1极差 10极好）',
  `mood_label` VARCHAR(50) COMMENT '情绪标签',
  `mood_emoji` VARCHAR(10) DEFAULT '😐' COMMENT '情绪表情',
  `energy_level` INT DEFAULT 5 COMMENT '精力水平 1-10',
  `sleep_hours` DECIMAL(3,1) COMMENT '睡眠时长(小时)',
  `exercise_minutes` INT DEFAULT 0 COMMENT '运动时长(分钟)',
  `notes` TEXT COMMENT '日记内容',
  `tags` VARCHAR(500) COMMENT '标签，逗号分隔',
  `record_time` DATETIME NOT NULL COMMENT '记录时间',
  `create_time` DATETIME DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_user_id` (`user_id`),
  KEY `idx_record_time` (`record_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='情绪记录表';

-- ----------------------------
-- 5、咨询师信息表
-- ----------------------------
DROP TABLE IF EXISTS `counselor`;
CREATE TABLE `counselor` (
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '咨询师ID',
  `user_id` BIGINT NOT NULL COMMENT '关联系统用户ID',
  `real_name` VARCHAR(50) NOT NULL COMMENT '真实姓名',
  `avatar` VARCHAR(500) COMMENT '头像路径',
  `title` VARCHAR(100) COMMENT '职称',
  `specialties` TEXT COMMENT '擅长领域JSON',
  `experience_years` INT DEFAULT 0 COMMENT '从业年限',
  `satisfaction_rate` DECIMAL(4,2) DEFAULT 95.00 COMMENT '好评率%',
  `consultation_count` INT DEFAULT 0 COMMENT '累计咨询次数',
  `bio` TEXT COMMENT '个人简介',
  `education` TEXT COMMENT '教育背景',
  `hourly_rate` INT DEFAULT 200 COMMENT '时薪(元)',
  `status` CHAR(1) DEFAULT '0' COMMENT '状态（0在职 1离职 2休假）',
  `is_featured` CHAR(1) DEFAULT '0' COMMENT '是否推荐',
  `sort_order` INT DEFAULT 0,
  `create_time` DATETIME DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='咨询师信息表';

-- ----------------------------
-- 6、咨询师排班表
-- ----------------------------
DROP TABLE IF EXISTS `counselor_schedule`;
CREATE TABLE `counselor_schedule` (
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '排班ID',
  `counselor_id` BIGINT NOT NULL COMMENT '咨询师ID',
  `schedule_date` DATE NOT NULL COMMENT '排班日期',
  `start_time` TIME NOT NULL COMMENT '开始时间',
  `end_time` TIME NOT NULL COMMENT '结束时间',
  `slot_duration` INT DEFAULT 50 COMMENT '每个时段分钟数',
  `total_slots` INT NOT NULL COMMENT '总时段数',
  `available_slots` INT DEFAULT 0 COMMENT '剩余可约时段',
  `is_available` CHAR(1) DEFAULT '0' COMMENT '是否可用',
  `create_time` DATETIME DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_counselor_date` (`counselor_id`, `schedule_date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='咨询师排班表';

-- ----------------------------
-- 7、咨询预约表
-- ----------------------------
DROP TABLE IF EXISTS `appointment`;
CREATE TABLE `appointment` (
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '预约ID',
  `user_id` BIGINT NOT NULL COMMENT '用户ID',
  `counselor_id` BIGINT NOT NULL COMMENT '咨询师ID',
  `schedule_id` BIGINT NOT NULL COMMENT '排班ID',
  `appointment_time` DATETIME NOT NULL COMMENT '预约时间',
  `duration_minutes` INT DEFAULT 50 COMMENT '咨询时长(分钟)',
  `status` CHAR(1) DEFAULT '0' COMMENT '状态（0待确认 1已确认 2已完成 3已取消）',
  `consultation_type` VARCHAR(20) DEFAULT 'online' COMMENT '咨询方式：online/offline',
  `notes` TEXT COMMENT '用户备注',
  `create_time` DATETIME DEFAULT NULL,
  `update_time` DATETIME DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_user_id` (`user_id`),
  KEY `idx_counselor_id` (`counselor_id`),
  KEY `idx_status` (`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='咨询预约表';

-- ----------------------------
-- 8、综合健康报告表
-- ----------------------------
DROP TABLE IF EXISTS `health_report`;
CREATE TABLE `health_report` (
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '报告ID',
  `report_no` VARCHAR(50) NOT NULL COMMENT '报告编号',
  `user_id` BIGINT NOT NULL COMMENT '用户ID',
  `report_type` VARCHAR(20) DEFAULT 'comprehensive' COMMENT '报告类型',
  `assessment_scores` TEXT COMMENT '各量表得分JSON',
  `mood_summary` TEXT COMMENT '情绪统计摘要JSON',
  `ai_analysis` TEXT COMMENT 'AI综合分析',
  `ai_recommendations` TEXT COMMENT 'AI个性化建议',
  `report_status` CHAR(1) DEFAULT '0' COMMENT '状态（0待生成 1已审核 2已发布）',
  `generate_time` DATETIME DEFAULT NULL COMMENT '报告生成时间',
  `create_time` DATETIME DEFAULT NULL,
  `update_time` DATETIME DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_report_no` (`report_no`),
  KEY `idx_user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='综合健康报告表';
