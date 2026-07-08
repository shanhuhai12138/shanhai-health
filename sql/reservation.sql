-- =============================================
-- 传智健康 - 体检预约模块数据库
-- 数据库名: health
-- =============================================

-- ----------------------------
-- 1、套餐组表
-- ----------------------------
DROP TABLE IF EXISTS `t_setmeal`;
CREATE TABLE `t_setmeal` (
  `id`          BIGINT(20)  NOT NULL AUTO_INCREMENT COMMENT '套餐ID',
  `name`        VARCHAR(100) DEFAULT '' COMMENT '套餐名称',
  `code`        VARCHAR(50)  DEFAULT '' COMMENT '套餐编码',
  `help_code`   VARCHAR(100) DEFAULT '' COMMENT '助记码',
  `sex`         CHAR(1)      DEFAULT '2' COMMENT '适用性别（0男 1女 2不限）',
  `age`         VARCHAR(50)  DEFAULT '' COMMENT '年龄范围',
  `price`       BIGINT(20)   DEFAULT 0 COMMENT '价格',
  `attention`   TEXT         COMMENT '注意事项',
  `img`         VARCHAR(500) DEFAULT '' COMMENT '图片路径',
  `remark`      VARCHAR(500) DEFAULT NULL COMMENT '备注',
  `create_by`   VARCHAR(64)  DEFAULT '' COMMENT '创建者',
  `create_time` DATETIME     DEFAULT NULL COMMENT '创建时间',
  `update_by`   VARCHAR(64)  DEFAULT '' COMMENT '更新者',
  `update_time` DATETIME     DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='套餐组表';

INSERT INTO t_setmeal (name, code, help_code, sex, age, price, attention, create_by, create_time)
VALUES
('入职体检套餐', 'ZRJT', 'ZRTJ', '2', '18-50', 298, '体检前一天清淡饮食，空腹8-12小时', 'admin', NOW()),
('全面体检套餐', 'QMJT', 'QMTJ', '2', '25-60', 1280, '体检前一周避免剧烈运动，停服维生素C', 'admin', NOW()),
('婚前检查套餐', 'HHJC', 'HHJJ', '2', '22-45', 580, '女性避开月经期，检查前3天避免性生活', 'admin', NOW()),
('老年体检套餐', 'LNJT', 'LNTJ', '2', '60+', 1680, '建议携带既往体检报告，方便对比分析', 'admin', NOW()),
('女性专项体检', 'NXZX', 'NXZT', '1', '18-55', 980, '女性体检需避开月经期，检查前3天阴道用药者请停药', 'admin', NOW());

-- ----------------------------
-- 2、检查组表
-- ----------------------------
DROP TABLE IF EXISTS `t_checkgroup`;
CREATE TABLE `t_checkgroup` (
  `id`          BIGINT(20)  NOT NULL AUTO_INCREMENT COMMENT '检查组ID',
  `code`        VARCHAR(50)  DEFAULT '' COMMENT '检查组编码',
  `name`        VARCHAR(100) DEFAULT '' COMMENT '检查组名称',
  `help_code`   VARCHAR(50)  DEFAULT '' COMMENT '助记码',
  `sex`         CHAR(1)      DEFAULT '2' COMMENT '适用性别',
  `age`         VARCHAR(50)  DEFAULT '' COMMENT '适用年龄范围',
  `remark`      VARCHAR(500) DEFAULT NULL COMMENT '备注',
  `sort`        INT(4)       DEFAULT 0 COMMENT '排序',
  `create_by`   VARCHAR(64)  DEFAULT '' COMMENT '创建者',
  `create_time` DATETIME     DEFAULT NULL COMMENT '创建时间',
  `update_by`   VARCHAR(64)  DEFAULT '' COMMENT '更新者',
  `update_time` DATETIME     DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='检查组表';

INSERT INTO t_checkgroup (code, name, help_code, sex, age, sort, create_by, create_time)
VALUES
('XXCX', '血常规', 'XBX', '2', '0+', 1, 'admin', NOW()),
('ZXFX', '血脂分析', 'ZXF', '2', '18+', 2, 'admin', NOW()),
('GGHS', '肝功能试验', 'GHS', '2', '0+', 3, 'admin', NOW()),
('SNHS', '肾功能试验', 'SNH', '2', '0+', 4, 'admin', NOW()),
('XDCX', '心电图检查', 'DXC', '2', '18+', 5, 'admin', NOW()),
('FCJC', '腹部彩超', 'FBJ', '2', '0+', 6, 'admin', NOW());

-- ----------------------------
-- 3、检查项表
-- ----------------------------
DROP TABLE IF EXISTS `t_checkitem`;
CREATE TABLE `t_checkitem` (
  `id`            BIGINT(20)  NOT NULL AUTO_INCREMENT COMMENT '检查项ID',
  `code`          VARCHAR(50)  DEFAULT '' COMMENT '检查项编码',
  `name`          VARCHAR(100) DEFAULT '' COMMENT '检查项名称',
  `sex`           CHAR(1)      DEFAULT '2' COMMENT '适用性别',
  `age`           VARCHAR(50)  DEFAULT '' COMMENT '适用年龄',
  `gender`        CHAR(1)      DEFAULT '2' COMMENT '检查科室',
  `help_code`     VARCHAR(50)  DEFAULT '' COMMENT '助记码',
  `cate`          VARCHAR(50)  DEFAULT '' COMMENT '检查类别',
  `checkgroup_id` BIGINT(20)   DEFAULT NULL COMMENT '关联检查组ID',
  `notice`        VARCHAR(500) DEFAULT '' COMMENT '注意事项',
  `remark`        VARCHAR(500) DEFAULT NULL COMMENT '备注',
  `abstract`      VARCHAR(500) DEFAULT NULL COMMENT '检查摘要',
  `is_addin`      CHAR(1)      DEFAULT '0' COMMENT '是否加项',
  `price`         BIGINT(20)   DEFAULT 0 COMMENT '价格',
  `sort`          INT(4)       DEFAULT 0 COMMENT '排序',
  `created_by`    VARCHAR(64)  DEFAULT '' COMMENT '创建人',
  `create_time`   DATETIME     DEFAULT NULL COMMENT '创建时间',
  `updated_by`    VARCHAR(64)  DEFAULT '' COMMENT '更新人',
  `update_time`   DATETIME     DEFAULT NULL COMMENT '更新时间',
  `del_flag`      CHAR(1)      DEFAULT '0' COMMENT '删除标志',
  `unit`          VARCHAR(50)  DEFAULT '' COMMENT '单位',
  `normal_range`  VARCHAR(200) DEFAULT '' COMMENT '参考范围',
  PRIMARY KEY (`id`),
  KEY `idx_checkgroup_id` (`checkgroup_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='检查项表';

-- 血常规检查项
INSERT INTO t_checkitem (code, name, sex, age, unit, normal_range, sort, created_by, create_time)
VALUES ('XXC001', '白细胞', '2', '0+', '10^9/L', '3.5-9.5', 1, 'admin', NOW()),
       ('XXC002', '红细胞', '2', '0+', '10^12/L', '4.0-5.5', 2, 'admin', NOW()),
       ('XXC003', '血红蛋白', '2', '0+', 'g/L', '120-160', 3, 'admin', NOW()),
       ('XXC004', '血小板', '2', '0+', '10^9/L', '100-300', 4, 'admin', NOW());

-- 血脂分析检查项
INSERT INTO t_checkitem (code, name, sex, age, unit, normal_range, sort, created_by, create_time)
VALUES ('ZXF001', '总胆固醇', '2', '18+', 'mmol/L', '3.1-5.7', 1, 'admin', NOW()),
       ('ZXF002', '甘油三酯', '2', '18+', 'mmol/L', '0.56-1.7', 2, 'admin', NOW()),
       ('ZXF003', '高密度脂蛋白', '2', '18+', 'mmol/L', '1.0-1.9', 3, 'admin', NOW()),
       ('ZXF004', '低密度脂蛋白', '2', '18+', 'mmol/L', '0-3.37', 4, 'admin', NOW());

-- 肝功能检查项
INSERT INTO t_checkitem (code, name, sex, age, unit, normal_range, sort, created_by, create_time)
VALUES ('GHS001', '谷丙转氨酶', '2', '0+', 'U/L', '0-40', 1, 'admin', NOW()),
       ('GHS002', '谷草转氨酶', '2', '0+', 'U/L', '0-40', 2, 'admin', NOW()),
       ('GHS003', '总蛋白', '2', '0+', 'g/L', '65-85', 3, 'admin', NOW());

-- 肾功能检查项
INSERT INTO t_checkitem (code, name, sex, age, unit, normal_range, sort, created_by, create_time)
VALUES ('SNH001', '肌酐', '2', '0+', 'umol/L', '57-97', 1, 'admin', NOW()),
       ('SNH002', '尿素氮', '2', '0+', 'mmol/L', '2.9-8.2', 2, 'admin', NOW()),
       ('SNH003', '尿酸', '2', '0+', 'umol/L', '208-428', 3, 'admin', NOW());

-- 心电图检查项
INSERT INTO t_checkitem (code, name, sex, age, unit, normal_range, sort, created_by, create_time)
VALUES ('DXC001', '心电图', '2', '18+', '', '窦性心律', 1, 'admin', NOW());

-- 腹部彩超检查项
INSERT INTO t_checkitem (code, name, sex, age, unit, normal_range, sort, created_by, create_time)
VALUES ('FBJ001', '肝胆胰脾彩超', '2', '0+', '', '未见明显异常', 1, 'admin', NOW());

-- ----------------------------
-- 4、检查组-检查项关联表
-- ----------------------------
DROP TABLE IF EXISTS `t_checkgroup_checkitem`;
CREATE TABLE `t_checkgroup_checkitem` (
  `checkgroup_id` BIGINT(20) NOT NULL COMMENT '检查组ID',
  `checkitem_id`  BIGINT(20) NOT NULL COMMENT '检查项ID',
  PRIMARY KEY (`checkgroup_id`, `checkitem_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='检查组-检查项关联表';

-- 关联血常规检查项
INSERT INTO t_checkgroup_checkitem (checkgroup_id, checkitem_id)
SELECT id, (SELECT id FROM t_checkitem WHERE name = '白细胞') FROM t_checkgroup WHERE name = '血常规';
INSERT INTO t_checkgroup_checkitem (checkgroup_id, checkitem_id)
SELECT id, (SELECT id FROM t_checkitem WHERE name = '红细胞') FROM t_checkgroup WHERE name = '血常规';
INSERT INTO t_checkgroup_checkitem (checkgroup_id, checkitem_id)
SELECT id, (SELECT id FROM t_checkitem WHERE name = '血红蛋白') FROM t_checkgroup WHERE name = '血常规';
INSERT INTO t_checkgroup_checkitem (checkgroup_id, checkitem_id)
SELECT id, (SELECT id FROM t_checkitem WHERE name = '血小板') FROM t_checkgroup WHERE name = '血常规';

-- 关联血脂分析检查项
INSERT INTO t_checkgroup_checkitem (checkgroup_id, checkitem_id)
SELECT id, (SELECT id FROM t_checkitem WHERE name = '总胆固醇') FROM t_checkgroup WHERE name = '血脂分析';
INSERT INTO t_checkgroup_checkitem (checkgroup_id, checkitem_id)
SELECT id, (SELECT id FROM t_checkitem WHERE name = '甘油三酯') FROM t_checkgroup WHERE name = '血脂分析';
INSERT INTO t_checkgroup_checkitem (checkgroup_id, checkitem_id)
SELECT id, (SELECT id FROM t_checkitem WHERE name = '高密度脂蛋白') FROM t_checkgroup WHERE name = '血脂分析';
INSERT INTO t_checkgroup_checkitem (checkgroup_id, checkitem_id)
SELECT id, (SELECT id FROM t_checkitem WHERE name = '低密度脂蛋白') FROM t_checkgroup WHERE name = '血脂分析';

-- 关联肝功能检查项
INSERT INTO t_checkgroup_checkitem (checkgroup_id, checkitem_id)
SELECT id, (SELECT id FROM t_checkitem WHERE name = '谷丙转氨酶') FROM t_checkgroup WHERE name = '肝功能试验';
INSERT INTO t_checkgroup_checkitem (checkgroup_id, checkitem_id)
SELECT id, (SELECT id FROM t_checkitem WHERE name = '谷草转氨酶') FROM t_checkgroup WHERE name = '肝功能试验';
INSERT INTO t_checkgroup_checkitem (checkgroup_id, checkitem_id)
SELECT id, (SELECT id FROM t_checkitem WHERE name = '总蛋白') FROM t_checkgroup WHERE name = '肝功能试验';

-- 关联肾功能检查项
INSERT INTO t_checkgroup_checkitem (checkgroup_id, checkitem_id)
SELECT id, (SELECT id FROM t_checkitem WHERE name = '肌酐') FROM t_checkgroup WHERE name = '肾功能试验';
INSERT INTO t_checkgroup_checkitem (checkgroup_id, checkitem_id)
SELECT id, (SELECT id FROM t_checkitem WHERE name = '尿素氮') FROM t_checkgroup WHERE name = '肾功能试验';
INSERT INTO t_checkgroup_checkitem (checkgroup_id, checkitem_id)
SELECT id, (SELECT id FROM t_checkitem WHERE name = '尿酸') FROM t_checkgroup WHERE name = '肾功能试验';

-- 关联其他检查项
INSERT INTO t_checkgroup_checkitem (checkgroup_id, checkitem_id)
SELECT id, (SELECT id FROM t_checkitem WHERE name = '心电图') FROM t_checkgroup WHERE name = '心电图检查';
INSERT INTO t_checkgroup_checkitem (checkgroup_id, checkitem_id)
SELECT id, (SELECT id FROM t_checkitem WHERE name = '肝胆胰脾彩超') FROM t_checkgroup WHERE name = '腹部彩超';

-- ----------------------------
-- 5、体检报告表
-- ----------------------------
DROP TABLE IF EXISTS `t_report`;
CREATE TABLE `t_report` (
  `id`             BIGINT(20)  NOT NULL AUTO_INCREMENT COMMENT '体检报告主键ID',
  `report_no`      VARCHAR(50) NOT NULL COMMENT '体检报告编号',
  `member_name`    VARCHAR(50) DEFAULT '' COMMENT '体检人姓名',
  `member_phone`   VARCHAR(11) DEFAULT '' COMMENT '体检人手机号',
  `member_idcard`  VARCHAR(20) DEFAULT '' COMMENT '体检人身份证号',
  `member_sex`     CHAR(1)     DEFAULT '2' COMMENT '性别',
  `member_age`     INT(4)      DEFAULT 0 COMMENT '年龄',
  `setmeal_id`     BIGINT(20)  DEFAULT NULL COMMENT '关联套餐ID',
  `setmeal_name`   VARCHAR(100) DEFAULT '' COMMENT '套餐名称',
  `checkgroup_ids` VARCHAR(500) DEFAULT '' COMMENT '关联检查组ID列表',
  `order_date`     DATE        DEFAULT NULL COMMENT '体检日期',
  `report_status`  CHAR(1)     DEFAULT '0' COMMENT '报告状态（0待录入 1已审核 2已发布 3已归档）',
  `doctor_advice`  TEXT        COMMENT '医生建议/总结',
  `reviewer_id`    BIGINT(20)  DEFAULT NULL COMMENT '审核医生ID',
  `reviewer_name`  VARCHAR(50) DEFAULT '' COMMENT '审核医生姓名',
  `review_time`    DATETIME    DEFAULT NULL COMMENT '审核时间',
  `publisher_id`   BIGINT(20)  DEFAULT NULL COMMENT '发布医生ID',
  `publisher_name` VARCHAR(50) DEFAULT '' COMMENT '发布医生姓名',
  `publish_time`   DATETIME    DEFAULT NULL COMMENT '发布时间',
  `archived_time`  DATETIME    DEFAULT NULL COMMENT '归档时间',
  `del_flag`       CHAR(1)     DEFAULT '0' COMMENT '删除标志',
  `create_by`      VARCHAR(64) DEFAULT '' COMMENT '创建者',
  `create_time`    DATETIME    DEFAULT NULL COMMENT '创建时间',
  `update_by`      VARCHAR(64) DEFAULT '' COMMENT '更新者',
  `update_time`    DATETIME    DEFAULT NULL COMMENT '更新时间',
  `remark`         VARCHAR(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_report_no` (`report_no`),
  KEY `idx_member_phone` (`member_phone`),
  KEY `idx_order_date` (`order_date`),
  KEY `idx_report_status` (`report_status`),
  KEY `idx_setmeal_id` (`setmeal_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='体检报告表';

-- ----------------------------
-- 6、体检报告明细表
-- ----------------------------
DROP TABLE IF EXISTS `t_report_item`;
CREATE TABLE `t_report_item` (
  `id`              BIGINT(20)  NOT NULL AUTO_INCREMENT COMMENT '报告明细主键ID',
  `report_id`       BIGINT(20)  NOT NULL COMMENT '关联体检报告ID',
  `checkitem_id`    BIGINT(20)  DEFAULT NULL COMMENT '关联检查项ID',
  `checkgroup_id`   BIGINT(20)  DEFAULT NULL COMMENT '关联检查组ID',
  `checkitem_name`  VARCHAR(100) DEFAULT '' COMMENT '检查项名称',
  `checkgroup_name` VARCHAR(100) DEFAULT '' COMMENT '检查组名称',
  `result`          VARCHAR(500) DEFAULT '' COMMENT '检查结果/数值',
  `unit`            VARCHAR(50)  DEFAULT '' COMMENT '单位',
  `normal_range`    VARCHAR(200) DEFAULT '' COMMENT '参考范围',
  `abnormal_flag`   CHAR(1)      DEFAULT '0' COMMENT '异常标记（0正常 1偏高 2偏低）',
  `abnormal_mark`   VARCHAR(200) DEFAULT '' COMMENT '异常标记文字',
  `create_time`     DATETIME     DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `idx_report_id` (`report_id`),
  KEY `idx_checkitem_id` (`checkitem_id`),
  KEY `idx_checkgroup_id` (`checkgroup_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='体检报告明细表';

-- ----------------------------
-- 7、体检报告图片表
-- ----------------------------
DROP TABLE IF EXISTS `t_report_image`;
CREATE TABLE `t_report_image` (
  `id`           BIGINT(20)  NOT NULL AUTO_INCREMENT COMMENT '图片主键ID',
  `report_id`    BIGINT(20)  NOT NULL COMMENT '关联体检报告ID',
  `checkitem_id` BIGINT(20)  DEFAULT NULL COMMENT '关联检查项ID',
  `image_path`   VARCHAR(500) DEFAULT '' COMMENT '图片路径',
  `image_desc`   VARCHAR(200) DEFAULT '' COMMENT '图片描述',
  `create_time`  DATETIME     DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `idx_report_id` (`report_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='体检报告图片表';
