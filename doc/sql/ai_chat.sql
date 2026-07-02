-- ----------------------------
-- AI对话会话表
-- ----------------------------
DROP TABLE IF EXISTS `ai_conversation`;
CREATE TABLE `ai_conversation` (
  `id`               BIGINT(20)   NOT NULL AUTO_INCREMENT COMMENT '会话主键ID',
  `title`            VARCHAR(200) NOT NULL DEFAULT '新对话' COMMENT '会话标题',
  `user_id`          BIGINT(20)   NOT NULL COMMENT '创建用户ID',
  `model`            VARCHAR(100) DEFAULT NULL COMMENT '使用的AI模型名称',
  `status`           INT(1)       DEFAULT 1 COMMENT '会话状态 1正常 0已删除',
  `create_by`        VARCHAR(64)  DEFAULT '' COMMENT '创建者',
  `create_time`      DATETIME     DEFAULT NULL COMMENT '创建时间',
  `update_by`        VARCHAR(64)  DEFAULT '' COMMENT '更新者',
  `update_time`      DATETIME     DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_user_id` (`user_id`),
  KEY `idx_update_time` (`update_time`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='AI对话会话表';

-- ----------------------------
-- AI对话消息表
-- ----------------------------
DROP TABLE IF EXISTS `ai_message`;
CREATE TABLE `ai_message` (
  `id`               BIGINT(20)   NOT NULL AUTO_INCREMENT COMMENT '消息主键ID',
  `conversation_id`  BIGINT(20)   NOT NULL COMMENT '所属会话ID',
  `role`             VARCHAR(20)  NOT NULL COMMENT '消息角色 user/assistant',
  `content`          TEXT         NOT NULL COMMENT '消息正文内容',
  `tokens`           INT(10)      DEFAULT 0 COMMENT '消耗的Token数量',
  `create_time`      DATETIME     DEFAULT NULL COMMENT '消息创建时间',
  PRIMARY KEY (`id`),
  KEY `idx_conversation_id` (`conversation_id`),
  KEY `idx_create_time` (`create_time`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='AI对话消息表';
