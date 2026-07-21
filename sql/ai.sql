-- =============================================
-- 传智健康 - AI 对话模块数据库
-- 数据库名: health
-- =============================================
use health;
-- ----------------------------
-- 1、AI 对话会话表
-- ----------------------------
DROP TABLE IF EXISTS `ai_conversation`;
CREATE TABLE `ai_conversation` (
  `id`          BIGINT(20)  NOT NULL AUTO_INCREMENT COMMENT '会话ID',
  `title`       VARCHAR(200) DEFAULT '' COMMENT '会话标题',
  `user_id`     BIGINT(20)  NOT NULL COMMENT '用户ID',
  `model`       VARCHAR(50)  DEFAULT '' COMMENT 'AI模型名称',
  `status`      INT(1)       DEFAULT 1 COMMENT '状态（1正常 0删除）',
  `create_by`   VARCHAR(64)  DEFAULT '' COMMENT '创建者',
  `create_time` DATETIME     DEFAULT NULL COMMENT '创建时间',
  `update_by`   VARCHAR(64)  DEFAULT '' COMMENT '更新者',
  `update_time` DATETIME     DEFAULT NULL COMMENT '更新时间',
  `remark`      VARCHAR(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  KEY `idx_user_id` (`user_id`),
  KEY `idx_update_time` (`update_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='AI对话会话表';

-- ----------------------------
-- 2、AI 对话消息表
-- ----------------------------
DROP TABLE IF EXISTS `ai_message`;
CREATE TABLE `ai_message` (
  `id`             BIGINT(20)  NOT NULL AUTO_INCREMENT COMMENT '消息ID',
  `conversation_id` BIGINT(20) NOT NULL COMMENT '会话ID',
  `role`           VARCHAR(20) DEFAULT '' COMMENT '角色（user/assistant）',
  `content`        TEXT        COMMENT '消息内容',
  `tokens`         INT         DEFAULT 0 COMMENT '消耗Token数',
  `create_time`    DATETIME    DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `idx_conversation_id` (`conversation_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='AI对话消息表';
