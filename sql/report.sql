-- ----------------------------
-- 体检报告模块
-- ----------------------------

-- ----------------------------
-- 体检报告表
-- ----------------------------
use health;
DROP TABLE IF EXISTS `t_report`;
CREATE TABLE `t_report` (
  `id`               BIGINT(20)   NOT NULL AUTO_INCREMENT COMMENT '体检报告主键ID',
  `report_no`        VARCHAR(50)  NOT NULL COMMENT '体检报告编号',
  `member_name`      VARCHAR(50)  DEFAULT '' COMMENT '体检人姓名',
  `member_phone`     VARCHAR(11)  DEFAULT '' COMMENT '体检人手机号',
  `member_idcard`    VARCHAR(20)  DEFAULT '' COMMENT '体检人身份证号',
  `member_sex`       CHAR(1)      DEFAULT '2' COMMENT '性别（0男 1女 2不限）',
  `member_age`       INT(4)       DEFAULT 0 COMMENT '年龄',
  `setmeal_id`       BIGINT(20)   DEFAULT NULL COMMENT '关联套餐ID',
  `setmeal_name`     VARCHAR(100) DEFAULT '' COMMENT '套餐名称（冗余，避免联表查询）',
  `checkgroup_ids`   VARCHAR(500) DEFAULT '' COMMENT '关联检查组ID列表（逗号分隔）',
  `order_date`       DATE         DEFAULT NULL COMMENT '体检日期',
  `report_status`    CHAR(1)      DEFAULT '0' COMMENT '报告状态（0待录入 1已审核 2已发布 3已归档）',
  `doctor_advice`    TEXT         COMMENT '医生建议/总结',
  `reviewer_id`      BIGINT(20)   DEFAULT NULL COMMENT '审核医生ID',
  `reviewer_name`    VARCHAR(50)  DEFAULT '' COMMENT '审核医生姓名',
  `review_time`      DATETIME     DEFAULT NULL COMMENT '审核时间',
  `publisher_id`     BIGINT(20)   DEFAULT NULL COMMENT '发布医生ID',
  `publisher_name`   VARCHAR(50)  DEFAULT '' COMMENT '发布医生姓名',
  `publish_time`     DATETIME     DEFAULT NULL COMMENT '发布时间',
  `archived_time`    DATETIME     DEFAULT NULL COMMENT '归档时间',
  `del_flag`         CHAR(1)      DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `create_by`        VARCHAR(64)  DEFAULT '' COMMENT '创建者',
  `create_time`      DATETIME     DEFAULT NULL COMMENT '创建时间',
  `update_by`        VARCHAR(64)  DEFAULT '' COMMENT '更新者',
  `update_time`      DATETIME     DEFAULT NULL COMMENT '更新时间',
  `remark`           VARCHAR(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_report_no` (`report_no`),
  KEY `idx_member_phone` (`member_phone`),
  KEY `idx_order_date` (`order_date`),
  KEY `idx_report_status` (`report_status`),
  KEY `idx_setmeal_id` (`setmeal_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='体检报告表';

-- ----------------------------
-- 体检报告明细表
-- ----------------------------
DROP TABLE IF EXISTS `t_report_item`;
CREATE TABLE `t_report_item` (
  `id`               BIGINT(20)   NOT NULL AUTO_INCREMENT COMMENT '报告明细主键ID',
  `report_id`        BIGINT(20)   NOT NULL COMMENT '关联体检报告ID',
  `checkitem_id`     BIGINT(20)   DEFAULT NULL COMMENT '关联检查项ID',
  `checkgroup_id`    BIGINT(20)   DEFAULT NULL COMMENT '关联检查组ID',
  `checkitem_name`   VARCHAR(100) DEFAULT '' COMMENT '检查项名称（冗余）',
  `checkgroup_name`  VARCHAR(100) DEFAULT '' COMMENT '检查组名称（冗余）',
  `result`           VARCHAR(500) DEFAULT '' COMMENT '检查结果/数值',
  `unit`             VARCHAR(50)  DEFAULT '' COMMENT '单位',
  `normal_range`     VARCHAR(200) DEFAULT '' COMMENT '参考范围',
  `abnormal_flag`    CHAR(1)      DEFAULT '0' COMMENT '异常标记（0正常 1偏高 2偏低）',
  `abnormal_mark`    VARCHAR(200) DEFAULT '' COMMENT '异常标记文字（如：↑ 偏高）',
  `create_time`      DATETIME     DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `idx_report_id` (`report_id`),
  KEY `idx_checkitem_id` (`checkitem_id`),
  KEY `idx_checkgroup_id` (`checkgroup_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='体检报告明细表';

-- ----------------------------
-- 体检报告图片表
-- ----------------------------
DROP TABLE IF EXISTS `t_report_image`;
CREATE TABLE `t_report_image` (
  `id`               BIGINT(20)   NOT NULL AUTO_INCREMENT COMMENT '图片主键ID',
  `report_id`        BIGINT(20)   NOT NULL COMMENT '关联体检报告ID',
  `checkitem_id`     BIGINT(20)   DEFAULT NULL COMMENT '关联检查项ID',
  `image_path`       VARCHAR(500) DEFAULT '' COMMENT '图片路径',
  `image_desc`       VARCHAR(200) DEFAULT '' COMMENT '图片描述',
  `create_time`      DATETIME     DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `idx_report_id` (`report_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='体检报告图片表';

-- ----------------------------
-- 体检报告菜单及权限 SQL
-- ----------------------------

-- 查询"预约管理"父菜单ID（根据实际数据库调整）
-- 注意：如果预约管理菜单不存在，请先执行 ordersettingMenu.sql
-- 此处假设预约管理父菜单ID为200，实际需根据 sys_menu 查询

-- 体检报告二级菜单（挂载在"预约管理"下）
-- 先查预约管理父菜单ID
SET @parentId = (SELECT menu_id FROM sys_menu WHERE menu_name = '预约管理' LIMIT 1);

-- 如果预约管理不存在，尝试使用"预约设置"的父菜单
SET @parentId = COALESCE(@parentId, (SELECT menu_id FROM sys_menu WHERE menu_name = '预约设置' LIMIT 1));

INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('体检报告', IFNULL(@parentId, '200'), '2', 'report', 'reservation/report/index', 1, 0, 'C', '0', '0', 'reservation:report:list', 'form', 'admin', NOW(), '', NULL, '体检报告菜单');

-- 获取刚插入的体检报告菜单ID
SELECT @reportId := LAST_INSERT_ID();

-- 按钮权限
INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('体检报告查询', @reportId, '1', '#', '', 1, 0, 'F', '0', '0', 'reservation:report:query', '#', 'admin', NOW(), '', NULL, '');

INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('体检报告新增', @reportId, '2', '#', '', 1, 0, 'F', '0', '0', 'reservation:report:add', '#', 'admin', NOW(), '', NULL, '');

INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('体检报告修改', @reportId, '3', '#', '', 1, 0, 'F', '0', '0', 'reservation:report:edit', '#', 'admin', NOW(), '', NULL, '');

INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('体检报告删除', @reportId, '4', '#', '', 1, 0, 'F', '0', '0', 'reservation:report:remove', '#', 'admin', NOW(), '', NULL, '');

INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('体检报告导出', @reportId, '5', '#', '', 1, 0, 'F', '0', '0', 'reservation:report:export', '#', 'admin', NOW(), '', NULL, '');

INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('体检报告审核', @reportId, '6', '#', '', 1, 0, 'F', '0', '0', 'reservation:report:audit', '#', 'admin', NOW(), '', NULL, '');

INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('体检报告发布', @reportId, '7', '#', '', 1, 0, 'F', '0', '0', 'reservation:report:publish', '#', 'admin', NOW(), '', NULL, '');

INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('体检报告归档', @reportId, '8', '#', '', 1, 0, 'F', '0', '0', 'reservation:report:archive', '#', 'admin', NOW(), '', NULL, '');

-- 分配给超级管理员角色（role_id = 1）
INSERT INTO sys_role_menu (role_id, menu_id) VALUES ('1', @reportId);
INSERT INTO sys_role_menu (role_id, menu_id) VALUES ('1', @reportId + 1);
INSERT INTO sys_role_menu (role_id, menu_id) VALUES ('1', @reportId + 2);
INSERT INTO sys_role_menu (role_id, menu_id) VALUES ('1', @reportId + 3);
INSERT INTO sys_role_menu (role_id, menu_id) VALUES ('1', @reportId + 4);
INSERT INTO sys_role_menu (role_id, menu_id) VALUES ('1', @reportId + 5);
INSERT INTO sys_role_menu (role_id, menu_id) VALUES ('1', @reportId + 6);
INSERT INTO sys_role_menu (role_id, menu_id) VALUES ('1', @reportId + 7);
INSERT INTO sys_role_menu (role_id, menu_id) VALUES ('1', @reportId + 8);
