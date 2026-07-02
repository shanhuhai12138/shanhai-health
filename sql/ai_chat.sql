-- ----------------------------
-- AI 对话会话表
-- ----------------------------

USE health;
DROP TABLE IF EXISTS `ai_conversation`;
CREATE TABLE `ai_conversation` (
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '会话主键ID',
  `title` VARCHAR(200) NOT NULL DEFAULT '新对话' COMMENT '会话标题',
  `user_id` BIGINT NOT NULL COMMENT '创建用户ID，关联sys_user表',
  `model` VARCHAR(100) DEFAULT NULL COMMENT '使用的AI模型名称',
  `status` INT DEFAULT 1 COMMENT '会话状态：1-正常，0-已删除',
  `create_by` VARCHAR(64) DEFAULT NULL COMMENT '创建者',
  `create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
  `update_by` VARCHAR(64) DEFAULT NULL COMMENT '更新者',
  `update_time` DATETIME DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  INDEX `idx_user_id` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='AI对话会话表';

-- ----------------------------
-- AI 对话消息表
-- ----------------------------
DROP TABLE IF EXISTS `ai_message`;
CREATE TABLE `ai_message` (
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '消息主键ID',
  `conversation_id` BIGINT NOT NULL COMMENT '所属会话ID，关联ai_conversation表',
  `role` VARCHAR(20) NOT NULL COMMENT '消息角色：user-用户，assistant-AI回复',
  `content` TEXT NOT NULL COMMENT '消息正文内容，支持Markdown格式',
  `tokens` INT DEFAULT 0 COMMENT '消耗Token数量（AI回复时记录）',
  `create_time` DATETIME DEFAULT NULL COMMENT '消息创建时间',
  PRIMARY KEY (`id`),
  INDEX `idx_conversation_id` (`conversation_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='AI对话消息表';
