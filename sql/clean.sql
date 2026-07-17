-- =============================================
-- 传智健康 - 数据库建表脚本（clean）
-- 数据库名: health
-- 执行顺序: 第1个执行（DROP + CREATE 所有表）
-- 包含模块: system / quartz / reservation / ai / assessment / mood / counseling / report
-- =============================================

SET SESSION sql_mode = '';

CREATE DATABASE IF NOT EXISTS `health` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `health`;

-- =============================================
-- 1. 系统管理模块（来自 system.sql，已修正）
-- =============================================

-- 1.1 部门表
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept` (
  `dept_id`       BIGINT(20)  NOT NULL AUTO_INCREMENT COMMENT '部门ID',
  `parent_id`     BIGINT(20)  DEFAULT 0 COMMENT '父部门ID',
  `ancestors`     VARCHAR(50) DEFAULT '' COMMENT '祖级列表',
  `dept_name`     VARCHAR(50) DEFAULT '' COMMENT '部门名称',
  `order_num`     INT(4)      DEFAULT 0 COMMENT '显示顺序',
  `leader`        VARCHAR(20) DEFAULT NULL COMMENT '负责人',
  `phone`         VARCHAR(11) DEFAULT NULL COMMENT '联系电话',
  `email`         VARCHAR(50) DEFAULT NULL COMMENT '邮箱',
  `status`        CHAR(1)     DEFAULT '0' COMMENT '部门状态（0正常 1停用）',
  `del_flag`      CHAR(1)     DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `create_by`     VARCHAR(64) DEFAULT '' COMMENT '创建者',
  `create_time`   DATETIME    DEFAULT NULL COMMENT '创建时间',
  `update_by`     VARCHAR(64) DEFAULT '' COMMENT '更新者',
  `update_time`   DATETIME    DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`dept_id`)
) ENGINE=InnoDB AUTO_INCREMENT=200 DEFAULT CHARSET=utf8mb4 COMMENT='部门表';

-- 1.2 用户信息表
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `user_id`       BIGINT(20)  NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `dept_id`       BIGINT(20)  DEFAULT NULL COMMENT '部门ID',
  `user_name`     VARCHAR(30) NOT NULL COMMENT '用户账号',
  `nick_name`     VARCHAR(30) NOT NULL COMMENT '用户昵称',
  `user_type`     VARCHAR(2)  DEFAULT '00' COMMENT '用户类型（00系统用户）',
  `email`         VARCHAR(50) DEFAULT '' COMMENT '用户邮箱',
  `phonenumber`   VARCHAR(11) DEFAULT '' COMMENT '手机号码',
  `sex`           CHAR(1)     DEFAULT '0' COMMENT '用户性别（0男 1女 2未知）',
  `avatar`        VARCHAR(100) DEFAULT '' COMMENT '头像地址',
  `password`      VARCHAR(100) DEFAULT '' COMMENT '密码',
  `status`        CHAR(1)     DEFAULT '0' COMMENT '账号状态（0正常 1停用）',
  `del_flag`      CHAR(1)     DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `login_ip`      VARCHAR(128) DEFAULT '' COMMENT '最后登录IP',
  `login_date`    DATETIME    DEFAULT NULL COMMENT '最后登录时间',
  `pwd_update_date` DATETIME  DEFAULT NULL COMMENT '密码最后更新时间',
  `create_by`     VARCHAR(64) DEFAULT '' COMMENT '创建者',
  `create_time`   DATETIME    DEFAULT NULL COMMENT '创建时间',
  `update_by`     VARCHAR(64) DEFAULT '' COMMENT '更新者',
  `update_time`   DATETIME    DEFAULT NULL COMMENT '更新时间',
  `remark`        VARCHAR(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8mb4 COMMENT='用户信息表';

-- 1.3 岗位信息表
DROP TABLE IF EXISTS `sys_post`;
CREATE TABLE `sys_post` (
  `post_id`     BIGINT(20)  NOT NULL AUTO_INCREMENT COMMENT '岗位ID',
  `post_code`   VARCHAR(64) NOT NULL COMMENT '岗位编码',
  `post_name`   VARCHAR(50) NOT NULL COMMENT '岗位名称',
  `post_sort`   INT(4)      NOT NULL COMMENT '显示顺序',
  `status`      CHAR(1)     NOT NULL COMMENT '状态（0正常 1停用）',
  `create_by`   VARCHAR(64) DEFAULT '' COMMENT '创建者',
  `create_time` DATETIME    DEFAULT NULL COMMENT '创建时间',
  `update_by`   VARCHAR(64) DEFAULT '' COMMENT '更新者',
  `update_time` DATETIME    DEFAULT NULL COMMENT '更新时间',
  `remark`      VARCHAR(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`post_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='岗位信息表';

-- 1.4 角色信息表
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `role_id`             BIGINT(20)  NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `role_name`           VARCHAR(30) NOT NULL COMMENT '角色名称',
  `role_key`            VARCHAR(100) NOT NULL COMMENT '角色权限字符串',
  `role_sort`           INT(4)      NOT NULL COMMENT '显示顺序',
  `data_scope`          CHAR(1)     DEFAULT '1' COMMENT '数据范围',
  `menu_check_strictly` TINYINT(1)  DEFAULT 1 COMMENT '菜单树选择项是否关联显示',
  `dept_check_strictly` TINYINT(1)  DEFAULT 1 COMMENT '部门树选择项是否关联显示',
  `status`              CHAR(1)     NOT NULL COMMENT '状态（0正常 1停用）',
  `del_flag`            CHAR(1)     DEFAULT '0' COMMENT '删除标志',
  `create_by`           VARCHAR(64) DEFAULT '' COMMENT '创建者',
  `create_time`         DATETIME    DEFAULT NULL COMMENT '创建时间',
  `update_by`           VARCHAR(64) DEFAULT '' COMMENT '更新者',
  `update_time`         DATETIME    DEFAULT NULL COMMENT '更新时间',
  `remark`              VARCHAR(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8mb4 COMMENT='角色信息表';

-- 1.5 菜单权限表
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `menu_id`       BIGINT(20)  NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
  `menu_name`     VARCHAR(50) NOT NULL COMMENT '菜单名称',
  `parent_id`     BIGINT(20)  DEFAULT 0 COMMENT '父菜单ID',
  `order_num`     INT(4)      DEFAULT 0 COMMENT '显示顺序',
  `path`          VARCHAR(200) DEFAULT '' COMMENT '路由地址',
  `component`     VARCHAR(255) DEFAULT NULL COMMENT '组件路径',
  `query`         VARCHAR(255) DEFAULT NULL COMMENT '路由参数',
  `route_name`    VARCHAR(50) DEFAULT '' COMMENT '路由名称',
  `is_frame`      INT(1)      DEFAULT 1 COMMENT '是否为外链',
  `is_cache`      INT(1)      DEFAULT 0 COMMENT '是否缓存',
  `menu_type`     CHAR(1)     DEFAULT '' COMMENT '菜单类型（M目录 C菜单 F按钮）',
  `visible`       CHAR(1)     DEFAULT '0' COMMENT '菜单状态（0显示 1隐藏）',
  `status`        CHAR(1)     DEFAULT '0' COMMENT '菜单状态（0正常 1停用）',
  `perms`         VARCHAR(100) DEFAULT NULL COMMENT '权限标识',
  `icon`          VARCHAR(100) DEFAULT '#' COMMENT '菜单图标',
  `create_by`     VARCHAR(64) DEFAULT '' COMMENT '创建者',
  `create_time`   DATETIME    DEFAULT NULL COMMENT '创建时间',
  `update_by`     VARCHAR(64) DEFAULT '' COMMENT '更新者',
  `update_time`   DATETIME    DEFAULT NULL COMMENT '更新时间',
  `remark`        VARCHAR(500) DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2000 DEFAULT CHARSET=utf8mb4 COMMENT='菜单权限表';

-- 1.6 用户和角色关联表
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `user_id` BIGINT(20) NOT NULL COMMENT '用户ID',
  `role_id` BIGINT(20) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`user_id`, `role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户和角色关联表';

-- 1.7 角色和菜单关联表
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `role_id` BIGINT(20) NOT NULL COMMENT '角色ID',
  `menu_id` BIGINT(20) NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`role_id`, `menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色和菜单关联表';

-- 1.8 角色和部门关联表
DROP TABLE IF EXISTS `sys_role_dept`;
CREATE TABLE `sys_role_dept` (
  `role_id` BIGINT(20) NOT NULL COMMENT '角色ID',
  `dept_id` BIGINT(20) NOT NULL COMMENT '部门ID',
  PRIMARY KEY (`role_id`, `dept_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色和部门关联表';

-- 1.9 用户与岗位关联表
DROP TABLE IF EXISTS `sys_user_post`;
CREATE TABLE `sys_user_post` (
  `user_id` BIGINT(20) NOT NULL COMMENT '用户ID',
  `post_id` BIGINT(20) NOT NULL COMMENT '岗位ID',
  PRIMARY KEY (`user_id`, `post_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户与岗位关联表';

-- 1.10 操作日志记录
DROP TABLE IF EXISTS `sys_oper_log`;
CREATE TABLE `sys_oper_log` (
  `oper_id`       BIGINT(20)  NOT NULL AUTO_INCREMENT COMMENT '日志主键',
  `title`         VARCHAR(50) DEFAULT '' COMMENT '模块标题',
  `business_type` INT(2)      DEFAULT 0 COMMENT '业务类型',
  `method`        VARCHAR(200) DEFAULT '' COMMENT '方法名称',
  `request_method` VARCHAR(10) DEFAULT '' COMMENT '请求方式',
  `operator_type` INT(1)      DEFAULT 0 COMMENT '操作类别',
  `oper_name`     VARCHAR(50) DEFAULT '' COMMENT '操作人员',
  `dept_name`     VARCHAR(50) DEFAULT '' COMMENT '部门名称',
  `oper_url`      VARCHAR(255) DEFAULT '' COMMENT '请求URL',
  `oper_ip`       VARCHAR(128) DEFAULT '' COMMENT '主机地址',
  `oper_location` VARCHAR(255) DEFAULT '' COMMENT '操作地点',
  `oper_param`    VARCHAR(2000) DEFAULT '' COMMENT '请求参数',
  `json_result`   VARCHAR(2000) DEFAULT '' COMMENT '返回参数',
  `status`        INT(1)      DEFAULT 0 COMMENT '操作状态',
  `error_msg`     VARCHAR(2000) DEFAULT '' COMMENT '错误消息',
  `oper_time`     DATETIME    DEFAULT NULL COMMENT '操作时间',
  `cost_time`     BIGINT(20)  DEFAULT 0 COMMENT '消耗时间',
  PRIMARY KEY (`oper_id`),
  KEY `idx_sys_oper_log_bt` (`business_type`),
  KEY `idx_sys_oper_log_s` (`status`),
  KEY `idx_sys_oper_log_ot` (`oper_time`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8mb4 COMMENT='操作日志记录';

-- 1.11 字典类型表
DROP TABLE IF EXISTS `sys_dict_type`;
CREATE TABLE `sys_dict_type` (
  `dict_id`     BIGINT(20)  NOT NULL AUTO_INCREMENT COMMENT '字典主键',
  `dict_name`   VARCHAR(100) DEFAULT '' COMMENT '字典名称',
  `dict_type`   VARCHAR(100) DEFAULT '' COMMENT '字典类型',
  `status`      CHAR(1)     DEFAULT '0' COMMENT '状态',
  `create_by`   VARCHAR(64) DEFAULT '' COMMENT '创建者',
  `create_time` DATETIME    DEFAULT NULL COMMENT '创建时间',
  `update_by`   VARCHAR(64) DEFAULT '' COMMENT '更新者',
  `update_time` DATETIME    DEFAULT NULL COMMENT '更新时间',
  `remark`      VARCHAR(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`dict_id`),
  UNIQUE KEY `uk_dict_type` (`dict_type`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8mb4 COMMENT='字典类型表';

-- 1.12 字典数据表
DROP TABLE IF EXISTS `sys_dict_data`;
CREATE TABLE `sys_dict_data` (
  `dict_code`   BIGINT(20)  NOT NULL AUTO_INCREMENT COMMENT '字典编码',
  `dict_sort`   INT(4)      DEFAULT 0 COMMENT '字典排序',
  `dict_label`  VARCHAR(100) DEFAULT '' COMMENT '字典标签',
  `dict_value`  VARCHAR(100) DEFAULT '' COMMENT '字典键值',
  `dict_type`   VARCHAR(100) DEFAULT '' COMMENT '字典类型',
  `css_class`   VARCHAR(100) DEFAULT NULL COMMENT '样式属性',
  `list_class`  VARCHAR(100) DEFAULT NULL COMMENT '表格回显样式',
  `is_default`  CHAR(1)     DEFAULT 'N' COMMENT '是否默认',
  `status`      CHAR(1)     DEFAULT '0' COMMENT '状态',
  `create_by`   VARCHAR(64) DEFAULT '' COMMENT '创建者',
  `create_time` DATETIME    DEFAULT NULL COMMENT '创建时间',
  `update_by`   VARCHAR(64) DEFAULT '' COMMENT '更新者',
  `update_time` DATETIME    DEFAULT NULL COMMENT '更新时间',
  `remark`      VARCHAR(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`dict_code`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8mb4 COMMENT='字典数据表';

-- 1.13 参数配置表
DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE `sys_config` (
  `config_id`    INT(5)      NOT NULL AUTO_INCREMENT COMMENT '参数主键',
  `config_name`  VARCHAR(100) DEFAULT '' COMMENT '参数名称',
  `config_key`   VARCHAR(100) DEFAULT '' COMMENT '参数键名',
  `config_value` VARCHAR(500) DEFAULT '' COMMENT '参数键值',
  `config_type`  CHAR(1)     DEFAULT 'N' COMMENT '系统内置',
  `create_by`    VARCHAR(64) DEFAULT '' COMMENT '创建者',
  `create_time`  DATETIME    DEFAULT NULL COMMENT '创建时间',
  `update_by`    VARCHAR(64) DEFAULT '' COMMENT '更新者',
  `update_time`  DATETIME    DEFAULT NULL COMMENT '更新时间',
  `remark`       VARCHAR(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`config_id`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8mb4 COMMENT='参数配置表';

-- 1.14 系统访问记录
DROP TABLE IF EXISTS `sys_logininfor`;
CREATE TABLE `sys_logininfor` (
  `info_id`      BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '访问ID',
  `user_name`    VARCHAR(50) DEFAULT '' COMMENT '用户账号',
  `ipaddr`       VARCHAR(128) DEFAULT '' COMMENT '登录IP地址',
  `login_location` VARCHAR(255) DEFAULT '' COMMENT '登录地点',
  `browser`      VARCHAR(50) DEFAULT '' COMMENT '浏览器类型',
  `os`           VARCHAR(50) DEFAULT '' COMMENT '操作系统',
  `status`       CHAR(1) DEFAULT '0' COMMENT '登录状态',
  `msg`          VARCHAR(255) DEFAULT '' COMMENT '提示消息',
  `login_time`   DATETIME DEFAULT NULL COMMENT '访问时间',
  PRIMARY KEY (`info_id`),
  KEY `idx_sys_logininfor_s` (`status`),
  KEY `idx_sys_logininfor_lt` (`login_time`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8mb4 COMMENT='系统访问记录';

-- 1.15 定时任务调度表
DROP TABLE IF EXISTS `sys_job`;
CREATE TABLE `sys_job` (
  `job_id`          BIGINT(20)  NOT NULL AUTO_INCREMENT COMMENT '任务ID',
  `job_name`        VARCHAR(64) DEFAULT '' COMMENT '任务名称',
  `job_group`       VARCHAR(64) DEFAULT 'DEFAULT' COMMENT '任务组名',
  `invoke_target`   VARCHAR(500) NOT NULL COMMENT '调用目标字符串',
  `cron_expression` VARCHAR(255) DEFAULT '' COMMENT 'cron执行表达式',
  `misfire_policy`  VARCHAR(20) DEFAULT '3' COMMENT '计划执行错误策略',
  `concurrent`      CHAR(1)     DEFAULT '1' COMMENT '是否并发执行',
  `status`          CHAR(1)     DEFAULT '0' COMMENT '状态',
  `create_by`       VARCHAR(64) DEFAULT '' COMMENT '创建者',
  `create_time`     DATETIME    DEFAULT NULL COMMENT '创建时间',
  `update_by`       VARCHAR(64) DEFAULT '' COMMENT '更新者',
  `update_time`     DATETIME    DEFAULT NULL COMMENT '更新时间',
  `remark`          VARCHAR(500) DEFAULT '' COMMENT '备注信息',
  PRIMARY KEY (`job_id`, `job_name`, `job_group`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8mb4 COMMENT='定时任务调度表';

-- 1.16 定时任务调度日志表
DROP TABLE IF EXISTS `sys_job_log`;
CREATE TABLE `sys_job_log` (
  `job_log_id`     BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '任务日志ID',
  `job_name`       VARCHAR(64) NOT NULL COMMENT '任务名称',
  `job_group`      VARCHAR(64) NOT NULL COMMENT '任务组名',
  `invoke_target`  VARCHAR(500) NOT NULL COMMENT '调用目标字符串',
  `job_message`    VARCHAR(500) DEFAULT NULL COMMENT '日志信息',
  `status`         CHAR(1) DEFAULT '0' COMMENT '执行状态',
  `exception_info` VARCHAR(2000) DEFAULT '' COMMENT '异常信息',
  `start_time`     DATETIME DEFAULT NULL COMMENT '执行开始时间',
  `end_time`       DATETIME DEFAULT NULL COMMENT '执行结束时间',
  `create_time`    DATETIME DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`job_log_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='定时任务调度日志表';

-- 1.17 通知公告表
DROP TABLE IF EXISTS `sys_notice`;
CREATE TABLE `sys_notice` (
  `notice_id`      INT(4)       NOT NULL AUTO_INCREMENT COMMENT '公告ID',
  `notice_title`   VARCHAR(50)  NOT NULL COMMENT '公告标题',
  `notice_type`    CHAR(1)      NOT NULL COMMENT '公告类型',
  `notice_content` LONGBLOB     DEFAULT NULL COMMENT '公告内容',
  `status`         CHAR(1)      DEFAULT '0' COMMENT '公告状态',
  `create_by`      VARCHAR(64)  DEFAULT '' COMMENT '创建者',
  `create_time`    DATETIME     DEFAULT NULL COMMENT '创建时间',
  `update_by`      VARCHAR(64)  DEFAULT '' COMMENT '更新者',
  `update_time`    DATETIME     DEFAULT NULL COMMENT '更新时间',
  `remark`         VARCHAR(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`notice_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COMMENT='通知公告表';

-- 1.18 公告已读记录表
DROP TABLE IF EXISTS `sys_notice_read`;
CREATE TABLE `sys_notice_read` (
  `read_id`     BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '已读主键',
  `notice_id`   INT(4)     NOT NULL COMMENT '公告id',
  `user_id`     BIGINT(20) NOT NULL COMMENT '用户id',
  `read_time`   DATETIME   NOT NULL COMMENT '阅读时间',
  PRIMARY KEY (`read_id`),
  UNIQUE KEY `uk_user_notice` (`user_id`, `notice_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='公告已读记录表';

-- 1.19 代码生成业务表
DROP TABLE IF EXISTS `gen_table`;
CREATE TABLE `gen_table` (
  `table_id`        BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `table_name`      VARCHAR(200) DEFAULT '' COMMENT '表名称',
  `table_comment`   VARCHAR(500) DEFAULT '' COMMENT '表描述',
  `sub_table_name`  VARCHAR(64)  DEFAULT NULL COMMENT '关联子表的表名',
  `sub_table_fk_name` VARCHAR(64) DEFAULT NULL COMMENT '子表关联的外键名',
  `class_name`      VARCHAR(100) DEFAULT '' COMMENT '实体类名称',
  `tpl_category`    VARCHAR(200) DEFAULT 'crud' COMMENT '使用的模板',
  `tpl_web_type`    VARCHAR(30)  DEFAULT '' COMMENT '前端模板类型',
  `package_name`    VARCHAR(100) DEFAULT NULL COMMENT '生成包路径',
  `module_name`     VARCHAR(30)  DEFAULT NULL COMMENT '生成模块名',
  `business_name`   VARCHAR(30)  DEFAULT NULL COMMENT '生成业务名',
  `function_name`   VARCHAR(50)  DEFAULT NULL COMMENT '生成功能名',
  `function_author` VARCHAR(50)  DEFAULT NULL COMMENT '生成功能作者',
  `form_col_num`    INT(1)       DEFAULT 1 COMMENT '表单布局',
  `gen_type`        CHAR(1)      DEFAULT '0' COMMENT '生成代码方式',
  `gen_path`        VARCHAR(200) DEFAULT '/' COMMENT '生成路径',
  `options`         VARCHAR(1000) DEFAULT NULL COMMENT '其它生成选项',
  `create_by`       VARCHAR(64)  DEFAULT '' COMMENT '创建者',
  `create_time`     DATETIME     DEFAULT NULL COMMENT '创建时间',
  `update_by`       VARCHAR(64)  DEFAULT '' COMMENT '更新者',
  `update_time`     DATETIME     DEFAULT NULL COMMENT '更新时间',
  `remark`          VARCHAR(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`table_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='代码生成业务表';

-- 1.20 代码生成业务表字段
DROP TABLE IF EXISTS `gen_table_column`;
CREATE TABLE `gen_table_column` (
  `column_id`     BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `table_id`      BIGINT(20) DEFAULT NULL COMMENT '归属表编号',
  `column_name`   VARCHAR(200) DEFAULT NULL COMMENT '列名称',
  `column_comment` VARCHAR(500) DEFAULT NULL COMMENT '列描述',
  `column_type`   VARCHAR(100) DEFAULT NULL COMMENT '列类型',
  `java_type`     VARCHAR(500) DEFAULT NULL COMMENT 'JAVA类型',
  `java_field`    VARCHAR(200) DEFAULT NULL COMMENT 'JAVA字段名',
  `is_pk`         CHAR(1)      DEFAULT NULL COMMENT '是否主键',
  `is_increment`  CHAR(1)      DEFAULT NULL COMMENT '是否自增',
  `is_required`   CHAR(1)      DEFAULT NULL COMMENT '是否必填',
  `is_insert`     CHAR(1)      DEFAULT NULL COMMENT '是否为插入字段',
  `is_edit`       CHAR(1)      DEFAULT NULL COMMENT '是否编辑字段',
  `is_list`       CHAR(1)      DEFAULT NULL COMMENT '是否列表字段',
  `is_query`      CHAR(1)      DEFAULT NULL COMMENT '是否查询字段',
  `query_type`    VARCHAR(200) DEFAULT 'EQ' COMMENT '查询方式',
  `html_type`     VARCHAR(200) DEFAULT NULL COMMENT '显示类型',
  `dict_type`     VARCHAR(200) DEFAULT '' COMMENT '字典类型',
  `sort`          INT          DEFAULT NULL COMMENT '排序',
  `create_by`     VARCHAR(64)  DEFAULT '' COMMENT '创建者',
  `create_time`   DATETIME     DEFAULT NULL COMMENT '创建时间',
  `update_by`     VARCHAR(64)  DEFAULT '' COMMENT '更新者',
  `update_time`   DATETIME     DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`column_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='代码生成业务表字段';


-- =============================================
-- 2. Quartz 定时任务集群表
-- =============================================

DROP TABLE IF EXISTS QRTZ_FIRED_TRIGGERS;
DROP TABLE IF EXISTS QRTZ_PAUSED_TRIGGER_GRPS;
DROP TABLE IF EXISTS QRTZ_SCHEDULER_STATE;
DROP TABLE IF EXISTS QRTZ_LOCKS;
DROP TABLE IF EXISTS QRTZ_SIMPLE_TRIGGERS;
DROP TABLE IF EXISTS QRTZ_SIMPROP_TRIGGERS;
DROP TABLE IF EXISTS QRTZ_CRON_TRIGGERS;
DROP TABLE IF EXISTS QRTZ_BLOB_TRIGGERS;
DROP TABLE IF EXISTS QRTZ_TRIGGERS;
DROP TABLE IF EXISTS QRTZ_JOB_DETAILS;
DROP TABLE IF EXISTS QRTZ_CALENDARS;

-- 2.1 任务详细信息表
CREATE table QRTZ_JOB_DETAILS (
    sched_name           varchar(120)    not null            comment '调度名称',
    job_name             varchar(200)    not null            comment '任务名称',
    job_group            varchar(200)    not null            comment '任务组名',
    description          varchar(250)    null                comment '相关介绍',
    job_class_name       varchar(250)    not null            comment '执行任务类名称',
    is_durable           varchar(1)      not null            comment '是否持久化',
    is_nonconcurrent     varchar(1)      not null            comment '是否并发',
    is_update_data       varchar(1)      not null            comment '是否更新数据',
    requests_recovery    varchar(1)      not null            comment '是否接受恢复执行',
    job_data             blob            null                comment '存放持久化job对象',
    primary key (sched_name, job_name, job_group)
) engine=innodb comment = '任务详细信息表';

-- 2.2 触发器详细信息表
CREATE table QRTZ_TRIGGERS (
    sched_name           varchar(120)    not null            comment '调度名称',
    trigger_name         varchar(200)    not null            comment '触发器的名字',
    trigger_group        varchar(200)    not null            comment '触发器所属组的名字',
    job_name             varchar(200)    not null            comment 'qrtz_job_details表job_name的外键',
    job_group            varchar(200)    not null            comment 'qrtz_job_details表job_group的外键',
    description          varchar(250)    null                comment '相关介绍',
    next_fire_time       bigint(13)      null                comment '上一次触发时间（毫秒）',
    prev_fire_time       bigint(13)      null                comment '下一次触发时间（默认为-1表示不触发）',
    priority             integer         null                comment '优先级',
    trigger_state        varchar(16)     not null            comment '触发器状态',
    trigger_type         varchar(8)      not null            comment '触发器的类型',
    start_time           bigint(13)      not null            comment '开始时间',
    end_time             bigint(13)      null                comment '结束时间',
    calendar_name        varchar(200)    null                comment '日程表名称',
    misfire_instr        smallint(2)     null                comment '补偿执行的策略',
    job_data             blob            null                comment '存放持久化job对象',
    primary key (sched_name, trigger_name, trigger_group),
    foreign key (sched_name, job_name, job_group) references QRTZ_JOB_DETAILS(sched_name, job_name, job_group)
) engine=innodb comment = '触发器详细信息表';

-- 2.3 简单触发器表
CREATE table QRTZ_SIMPLE_TRIGGERS (
    sched_name           varchar(120)    not null            comment '调度名称',
    trigger_name         varchar(200)    not null            comment 'qrtz_triggers表trigger_name的外键',
    trigger_group        varchar(200)    not null            comment 'qrtz_triggers表trigger_group的外键',
    repeat_count         bigint(7)       not null            comment '重复的次数统计',
    repeat_interval      bigint(12)      not null            comment '重复的间隔时间',
    times_triggered      bigint(10)      not null            comment '已经触发的次数',
    primary key (sched_name, trigger_name, trigger_group),
    foreign key (sched_name, trigger_name, trigger_group) references QRTZ_TRIGGERS(sched_name, trigger_name, trigger_group)
) engine=innodb comment = '简单触发器的信息表';

-- 2.4 Cron触发器表
CREATE table QRTZ_CRON_TRIGGERS (
    sched_name           varchar(120)    not null            comment '调度名称',
    trigger_name         varchar(200)    not null            comment 'qrtz_triggers表trigger_name的外键',
    trigger_group        varchar(200)    not null            comment 'qrtz_triggers表trigger_group的外键',
    cron_expression      varchar(200)    not null            comment 'cron表达式',
    time_zone_id         varchar(80)                         comment '时区',
    primary key (sched_name, trigger_name, trigger_group),
    foreign key (sched_name, trigger_name, trigger_group) references QRTZ_TRIGGERS(sched_name, trigger_name, trigger_group)
) engine=innodb comment = 'Cron类型的触发器表';

-- 2.5 Blob触发器表
CREATE table QRTZ_BLOB_TRIGGERS (
    sched_name           varchar(120)    not null            comment '调度名称',
    trigger_name         varchar(200)    not null            comment 'qrtz_triggers表trigger_name的外键',
    trigger_group        varchar(200)    not null            comment 'qrtz_triggers表trigger_group的外键',
    blob_data            blob            null                comment '存放持久化Trigger对象',
    primary key (sched_name, trigger_name, trigger_group),
    foreign key (sched_name, trigger_name, trigger_group) references QRTZ_TRIGGERS(sched_name, trigger_name, trigger_group)
) engine=innodb comment = 'Blob类型的触发器表';

-- 2.6 日历信息表
CREATE table QRTZ_CALENDARS (
    sched_name           varchar(120)    not null            comment '调度名称',
    calendar_name        varchar(200)    not null            comment '日历名称',
    calendar             blob            not null            comment '存放持久化calendar对象',
    primary key (sched_name, calendar_name)
) engine=innodb comment = '日历信息表';

-- 2.7 暂停的触发器表
CREATE table QRTZ_PAUSED_TRIGGER_GRPS (
    sched_name           varchar(120)    not null            comment '调度名称',
    trigger_group        varchar(200)    not null            comment 'qrtz_triggers表trigger_group的外键',
    primary key (sched_name, trigger_group)
) engine=innodb comment = '暂停的触发器表';

-- 2.8 已触发的触发器表
CREATE table QRTZ_FIRED_TRIGGERS (
    sched_name           varchar(120)    not null            comment '调度名称',
    entry_id             varchar(95)     not null            comment '调度器实例id',
    trigger_name         varchar(200)    not null            comment 'qrtz_triggers表trigger_name的外键',
    trigger_group        varchar(200)    not null            comment 'qrtz_triggers表trigger_group的外键',
    instance_name        varchar(200)    not null            comment '调度器实例名',
    fired_time           bigint(13)      not null            comment '触发的时间',
    sched_time           bigint(13)      not null            comment '定时器制定的时间',
    priority             integer         not null            comment '优先级',
    state                varchar(16)     not null            comment '状态',
    job_name             varchar(200)    null                comment '任务名称',
    job_group            varchar(200)    null                comment '任务组名',
    is_nonconcurrent     varchar(1)      null                comment '是否并发',
    requests_recovery    varchar(1)      null                comment '是否接受恢复执行',
    primary key (sched_name, entry_id)
) engine=innodb comment = '已触发的触发器表';

-- 2.9 调度器状态表
CREATE table QRTZ_SCHEDULER_STATE (
    sched_name           varchar(120)    not null            comment '调度名称',
    instance_name        varchar(200)    not null            comment '实例名称',
    last_checkin_time    bigint(13)      not null            comment '上次检查时间',
    checkin_interval     bigint(13)      not null            comment '检查间隔时间',
    primary key (sched_name, instance_name)
) engine=innodb comment = '调度器状态表';

-- 2.10 悲观锁信息表
CREATE table QRTZ_LOCKS (
    sched_name           varchar(120)    not null            comment '调度名称',
    lock_name            varchar(40)     not null            comment '悲观锁名称',
    primary key (sched_name, lock_name)
) engine=innodb comment = '存储的悲观锁信息表';

-- 2.11 同步机制的行锁表
CREATE table QRTZ_SIMPROP_TRIGGERS (
    sched_name           varchar(120)    not null            comment '调度名称',
    trigger_name         varchar(200)    not null            comment 'qrtz_triggers表trigger_name的外键',
    trigger_group        varchar(200)    not null            comment 'qrtz_triggers表trigger_group的外键',
    str_prop_1           varchar(512)    null                comment 'String类型的trigger的第一个参数',
    str_prop_2           varchar(512)    null                comment 'String类型的trigger的第二个参数',
    str_prop_3           varchar(512)    null                comment 'String类型的trigger的第三个参数',
    int_prop_1           int             null                comment 'int类型的trigger的第一个参数',
    int_prop_2           int             null                comment 'int类型的trigger的第二个参数',
    long_prop_1          bigint          null                comment 'long类型的trigger的第一个参数',
    long_prop_2          bigint          null                comment 'long类型的trigger的第二个参数',
    dec_prop_1           numeric(13,4)   null                comment 'decimal类型的trigger的第一个参数',
    dec_prop_2           numeric(13,4)   null                comment 'decimal类型的trigger的第二个参数',
    bool_prop_1          varchar(1)      null                comment 'Boolean类型的trigger的第一个参数',
    bool_prop_2          varchar(1)      null                comment 'Boolean类型的trigger的第二个参数',
    primary key (sched_name, trigger_name, trigger_group),
    foreign key (sched_name, trigger_name, trigger_group) references QRTZ_TRIGGERS(sched_name, trigger_name, trigger_group)
) engine=innodb comment = '同步机制的行锁表';


-- =============================================
-- 3. 体检预约模块（来自 reservation.sql，已修正）
-- =============================================

-- 3.1 套餐组表
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
  `status`      CHAR(1)      DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `del_flag`    CHAR(1)      DEFAULT '0' COMMENT '删除标志',
  `create_by`   VARCHAR(64)  DEFAULT '' COMMENT '创建者',
  `create_time` DATETIME     DEFAULT NULL COMMENT '创建时间',
  `update_by`   VARCHAR(64)  DEFAULT '' COMMENT '更新者',
  `update_time` DATETIME     DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='套餐组表';

-- 3.1.1 套餐检查组关联表
DROP TABLE IF EXISTS `t_setmeal_checkgroup`;
CREATE TABLE `t_setmeal_checkgroup` (
  `setmeal_id`    BIGINT(20) NOT NULL COMMENT '套餐ID',
  `checkgroup_id` BIGINT(20) NOT NULL COMMENT '检查组ID',
  PRIMARY KEY (`setmeal_id`, `checkgroup_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='套餐检查组关联表';

-- 3.1.2 预约设置表
DROP TABLE IF EXISTS `t_ordersetting`;
CREATE TABLE `t_ordersetting` (
  `id`           BIGINT(20)  NOT NULL AUTO_INCREMENT COMMENT '预约设置ID',
  `order_date`   DATE        DEFAULT NULL COMMENT '预约日期',
  `number`       INT(11)     DEFAULT 0 COMMENT '可预约人数',
  `reservations` INT(11)     DEFAULT 0 COMMENT '已预约人数',
  `create_by`    VARCHAR(64) DEFAULT '' COMMENT '创建者',
  `create_time`  DATETIME    DEFAULT NULL COMMENT '创建时间',
  `update_by`    VARCHAR(64) DEFAULT '' COMMENT '更新者',
  `update_time`  DATETIME    DEFAULT NULL COMMENT '更新时间',
  `remark`       VARCHAR(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  KEY `idx_order_date` (`order_date`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='预约设置表';

-- 3.2 检查组表
DROP TABLE IF EXISTS `t_checkgroup`;
CREATE TABLE `t_checkgroup` (
  `id`          BIGINT(20)  NOT NULL AUTO_INCREMENT COMMENT '检查组ID',
  `code`        VARCHAR(50)  DEFAULT '' COMMENT '检查组编码',
  `name`        VARCHAR(100) DEFAULT '' COMMENT '检查组名称',
  `help_code`   VARCHAR(50)  DEFAULT '' COMMENT '助记码',
  `sex`         CHAR(1)      DEFAULT '2' COMMENT '适用性别',
  `age`         VARCHAR(50)  DEFAULT '' COMMENT '适用年龄范围',
  `attention`   TEXT         COMMENT '注意事项',
  `remark`      VARCHAR(500) DEFAULT NULL COMMENT '备注',
  `sort`        INT(4)       DEFAULT 0 COMMENT '排序',
  `create_by`   VARCHAR(64)  DEFAULT '' COMMENT '创建者',
  `create_time` DATETIME     DEFAULT NULL COMMENT '创建时间',
  `update_by`   VARCHAR(64)  DEFAULT '' COMMENT '更新者',
  `update_time` DATETIME     DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='检查组表';

-- 3.3 检查项表（修正：gender字段改为check_dept检查科室，与sex区分）
DROP TABLE IF EXISTS `t_checkitem`;
CREATE TABLE `t_checkitem` (
  `id`            BIGINT(20)  NOT NULL AUTO_INCREMENT COMMENT '检查项ID',
  `code`          VARCHAR(50)  DEFAULT '' COMMENT '检查项编码',
  `name`          VARCHAR(100) DEFAULT '' COMMENT '检查项名称',
  `sex`           CHAR(1)      DEFAULT '2' COMMENT '适用性别（0男 1女 2不限）',
  `age`           VARCHAR(50)  DEFAULT '' COMMENT '适用年龄',
  `check_dept`    CHAR(1)      DEFAULT '2' COMMENT '检查科室',
  `help_code`     VARCHAR(50)  DEFAULT '' COMMENT '助记码',
  `cate`          VARCHAR(50)  DEFAULT '' COMMENT '检查类别',
  `checkgroup_id` BIGINT(20)   DEFAULT NULL COMMENT '关联检查组ID',
  `notice`        VARCHAR(500) DEFAULT '' COMMENT '注意事项',
  `type`          VARCHAR(20)  DEFAULT '' COMMENT '类型（1检查 2检验）',
  `attention`     TEXT         COMMENT '检查注意事项详细说明',
  `remark`        VARCHAR(500) DEFAULT NULL COMMENT '备注',
  `abstract`      VARCHAR(500) DEFAULT NULL COMMENT '检查摘要',
  `is_addin`      CHAR(1)      DEFAULT '0' COMMENT '是否加项',
  `price`         BIGINT(20)   DEFAULT 0 COMMENT '价格',
  `sort`          INT(4)       DEFAULT 0 COMMENT '排序',
  `create_by`     VARCHAR(64)  DEFAULT '' COMMENT '创建人',
  `create_time`   DATETIME     DEFAULT NULL COMMENT '创建时间',
  `update_by`     VARCHAR(64)  DEFAULT '' COMMENT '更新人',
  `update_time`   DATETIME     DEFAULT NULL COMMENT '更新时间',
  `del_flag`      CHAR(1)      DEFAULT '0' COMMENT '删除标志',
  `unit`          VARCHAR(50)  DEFAULT '' COMMENT '单位',
  `normal_range`  VARCHAR(200) DEFAULT '' COMMENT '参考范围',
  PRIMARY KEY (`id`),
  KEY `idx_checkgroup_id` (`checkgroup_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='检查项表';

-- 3.4 检查组-检查项关联表
DROP TABLE IF EXISTS `t_checkgroup_checkitem`;
CREATE TABLE `t_checkgroup_checkitem` (
  `checkgroup_id` BIGINT(20) NOT NULL COMMENT '检查组ID',
  `checkitem_id`  BIGINT(20) NOT NULL COMMENT '检查项ID',
  PRIMARY KEY (`checkgroup_id`, `checkitem_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='检查组-检查项关联表';

-- 3.5 体检报告表
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

-- 3.6 体检报告明细表
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

-- 3.7 体检报告图片表
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


-- =============================================
-- 4. AI对话模块（合并 ai.sql + new_tables.sql + doc/sql/ai_chat.sql）
--    以 new_tables.sql 为准（字段更完善）
-- =============================================

-- 4.1 AI对话会话表
DROP TABLE IF EXISTS `ai_conversation`;
CREATE TABLE `ai_conversation` (
  `id`          BIGINT(20)  NOT NULL AUTO_INCREMENT COMMENT '会话ID',
  `title`       VARCHAR(200) NOT NULL DEFAULT '' COMMENT '会话标题',
  `user_id`     BIGINT(20)  NOT NULL COMMENT '用户ID',
  `model`       VARCHAR(100) DEFAULT NULL COMMENT 'AI模型名称',
  `status`      INT(1)      DEFAULT 1 COMMENT '状态（1正常 0删除）',
  `create_by`   VARCHAR(64) DEFAULT '' COMMENT '创建者',
  `create_time` DATETIME    DEFAULT NULL COMMENT '创建时间',
  `update_by`   VARCHAR(64) DEFAULT '' COMMENT '更新者',
  `update_time` DATETIME    DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_user_id` (`user_id`),
  KEY `idx_update_time` (`update_time`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='AI对话会话表';

-- 4.2 AI对话消息表
DROP TABLE IF EXISTS `ai_message`;
CREATE TABLE `ai_message` (
  `id`             BIGINT(20)  NOT NULL AUTO_INCREMENT COMMENT '消息ID',
  `conversation_id` BIGINT(20) NOT NULL COMMENT '会话ID',
  `role`           VARCHAR(20) NOT NULL COMMENT '角色（user/assistant）',
  `content`        TEXT NOT NULL COMMENT '消息内容',
  `tokens`         INT         DEFAULT 0 COMMENT '消耗Token数',
  `create_time`    DATETIME    DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `idx_conversation_id` (`conversation_id`),
  KEY `idx_create_time` (`create_time`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='AI对话消息表';


-- =============================================
-- 5. 心理测评/情绪/咨询/报告模块（来自 new_tables.sql）
-- =============================================

-- 5.1 心理量表表
DROP TABLE IF EXISTS `assessment`;
CREATE TABLE `assessment` (
  `id`            BIGINT NOT NULL AUTO_INCREMENT COMMENT '量表ID',
  `code`          VARCHAR(50) NOT NULL COMMENT '量表编码，如 PHQ9/GAD7/SAS',
  `name`          VARCHAR(100) NOT NULL COMMENT '量表名称',
  `description`   TEXT COMMENT '量表说明',
  `total_score`   INT NOT NULL COMMENT '满分',
  `severity_levels` TEXT COMMENT '严重程度分级JSON',
  `estimated_duration` INT DEFAULT 10 COMMENT '预计完成时间(分钟)',
  `category`      VARCHAR(50) DEFAULT 'psychological' COMMENT '分类',
  `status`        CHAR(1) DEFAULT '0' COMMENT '状态（0启用 1禁用）',
  `create_by`     VARCHAR(64) DEFAULT '' COMMENT '创建者',
  `create_time`   DATETIME DEFAULT NULL COMMENT '创建时间',
  `update_by`     VARCHAR(64) DEFAULT '' COMMENT '更新者',
  `update_time`   DATETIME DEFAULT NULL COMMENT '更新时间',
  `remark`        VARCHAR(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_code` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='心理量表表';

-- 5.2 量表题目表
DROP TABLE IF EXISTS `assessment_question`;
CREATE TABLE `assessment_question` (
  `id`            BIGINT NOT NULL AUTO_INCREMENT COMMENT '题目ID',
  `assessment_id` BIGINT NOT NULL COMMENT '关联量表ID',
  `question_no`   INT NOT NULL COMMENT '题号',
  `question_text` TEXT NOT NULL COMMENT '题干',
  `question_type` VARCHAR(20) DEFAULT 'single' COMMENT '题型：single/multiple/text',
  `options`       TEXT COMMENT '选项JSON',
  `reverse_score` CHAR(1) DEFAULT '0' COMMENT '是否反向计分（0否 1是）',
  `sort_order`    INT DEFAULT 0 COMMENT '排序',
  `create_by`     VARCHAR(64) DEFAULT '' COMMENT '创建者',
  `create_time`   DATETIME DEFAULT NULL,
  `update_by`     VARCHAR(64) DEFAULT '' COMMENT '更新者',
  `update_time`   DATETIME DEFAULT NULL,
  `remark`        VARCHAR(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  KEY `idx_assessment_id` (`assessment_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='量表题目表';

-- 5.3 测评结果表
DROP TABLE IF EXISTS `assessment_result`;
CREATE TABLE `assessment_result` (
  `id`              BIGINT NOT NULL AUTO_INCREMENT COMMENT '结果ID',
  `user_id`         BIGINT NOT NULL COMMENT '用户ID',
  `assessment_id`   BIGINT NOT NULL COMMENT '量表ID',
  `total_score`     INT NOT NULL COMMENT '总分',
  `severity_level`  VARCHAR(50) COMMENT '严重程度等级',
  `severity_desc`   TEXT COMMENT '结果描述',
  `answers`         TEXT COMMENT '答案JSON',
  `ai_analysis`     TEXT COMMENT 'AI分析结果',
  `ai_suggestion`   TEXT COMMENT 'AI建议',
  `duration_seconds` INT COMMENT '答题耗时(秒)',
  `create_by`       VARCHAR(64) DEFAULT '' COMMENT '创建者',
  `create_time`     DATETIME DEFAULT NULL,
  `update_by`       VARCHAR(64) DEFAULT '' COMMENT '更新者',
  `update_time`     DATETIME DEFAULT NULL,
  `remark`          VARCHAR(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  KEY `idx_user_id` (`user_id`),
  KEY `idx_assessment_id` (`assessment_id`),
  KEY `idx_create_time` (`create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='测评结果表';

-- 5.4 情绪记录表
DROP TABLE IF EXISTS `mood_record`;
CREATE TABLE `mood_record` (
  `id`              BIGINT NOT NULL AUTO_INCREMENT COMMENT '记录ID',
  `user_id`         BIGINT NOT NULL COMMENT '用户ID',
  `mood_score`      INT NOT NULL COMMENT '情绪评分 1-10（1极差 10极好）',
  `mood_label`      VARCHAR(50) COMMENT '情绪标签',
  `mood_emoji`      VARCHAR(10) DEFAULT '😐' COMMENT '情绪表情',
  `energy_level`    INT DEFAULT 5 COMMENT '精力水平 1-10',
  `sleep_hours`     DECIMAL(3,1) COMMENT '睡眠时长(小时)',
  `exercise_minutes` INT DEFAULT 0 COMMENT '运动时长(分钟)',
  `notes`           TEXT COMMENT '日记内容',
  `tags`            VARCHAR(500) COMMENT '标签，逗号分隔',
  `record_time`     DATETIME NOT NULL COMMENT '记录时间',
  `create_by`       VARCHAR(64) DEFAULT '' COMMENT '创建者',
  `create_time`     DATETIME DEFAULT NULL,
  `update_by`       VARCHAR(64) DEFAULT '' COMMENT '更新者',
  `update_time`     DATETIME DEFAULT NULL,
  `remark`          VARCHAR(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  KEY `idx_user_id` (`user_id`),
  KEY `idx_record_time` (`record_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='情绪记录表';

-- 5.5 咨询师信息表
DROP TABLE IF EXISTS `counselor`;
CREATE TABLE `counselor` (
  `id`                BIGINT NOT NULL AUTO_INCREMENT COMMENT '咨询师ID',
  `user_id`           BIGINT NOT NULL COMMENT '关联系统用户ID',
  `real_name`         VARCHAR(50) NOT NULL COMMENT '真实姓名',
  `avatar`            VARCHAR(500) COMMENT '头像路径',
  `title`             VARCHAR(100) COMMENT '职称',
  `specialties`       TEXT COMMENT '擅长领域JSON',
  `experience_years`  INT DEFAULT 0 COMMENT '从业年限',
  `satisfaction_rate` DECIMAL(4,2) DEFAULT 95.00 COMMENT '好评率%',
  `consultation_count` INT DEFAULT 0 COMMENT '累计咨询次数',
  `bio`               TEXT COMMENT '个人简介',
  `education`         TEXT COMMENT '教育背景',
  `hourly_rate`       INT DEFAULT 200 COMMENT '时薪(元)',
  `status`            CHAR(1) DEFAULT '0' COMMENT '状态（0在职 1离职 2休假）',
  `is_featured`       CHAR(1) DEFAULT '0' COMMENT '是否推荐',
  `sort_order`        INT DEFAULT 0,
  `create_by`         VARCHAR(64) DEFAULT '' COMMENT '创建者',
  `create_time`       DATETIME DEFAULT NULL,
  `update_by`         VARCHAR(64) DEFAULT '' COMMENT '更新者',
  `update_time`       DATETIME DEFAULT NULL,
  `remark`            VARCHAR(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  KEY `idx_user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='咨询师信息表';

-- 5.6 咨询师排班表
DROP TABLE IF EXISTS `counselor_schedule`;
CREATE TABLE `counselor_schedule` (
  `id`              BIGINT NOT NULL AUTO_INCREMENT COMMENT '排班ID',
  `counselor_id`    BIGINT NOT NULL COMMENT '咨询师ID',
  `schedule_date`   DATE NOT NULL COMMENT '排班日期',
  `start_time`      TIME NOT NULL COMMENT '开始时间',
  `end_time`        TIME NOT NULL COMMENT '结束时间',
  `slot_duration`   INT DEFAULT 50 COMMENT '每个时段分钟数',
  `total_slots`     INT NOT NULL COMMENT '总时段数',
  `available_slots` INT DEFAULT 0 COMMENT '剩余可约时段',
  `is_available`    CHAR(1) DEFAULT '0' COMMENT '是否可用',
  `create_by`       VARCHAR(64) DEFAULT '' COMMENT '创建者',
  `create_time`     DATETIME DEFAULT NULL,
  `update_by`       VARCHAR(64) DEFAULT '' COMMENT '更新者',
  `update_time`     DATETIME DEFAULT NULL,
  `remark`          VARCHAR(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  KEY `idx_counselor_date` (`counselor_id`, `schedule_date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='咨询师排班表';

-- 5.7 咨询预约表
DROP TABLE IF EXISTS `appointment`;
CREATE TABLE `appointment` (
  `id`                 BIGINT NOT NULL AUTO_INCREMENT COMMENT '预约ID',
  `user_id`            BIGINT NOT NULL COMMENT '用户ID',
  `counselor_id`       BIGINT NOT NULL COMMENT '咨询师ID',
  `schedule_id`        BIGINT NOT NULL COMMENT '排班ID',
  `appointment_time`   DATETIME NOT NULL COMMENT '预约时间',
  `duration_minutes`   INT DEFAULT 50 COMMENT '咨询时长(分钟)',
  `status`             CHAR(1) DEFAULT '0' COMMENT '状态（0待确认 1已确认 2已完成 3已取消）',
  `consultation_type`  VARCHAR(20) DEFAULT 'online' COMMENT '咨询方式：online/offline',
  `notes`              TEXT COMMENT '用户备注',
  `create_by`          VARCHAR(64) DEFAULT '' COMMENT '创建者',
  `create_time`        DATETIME DEFAULT NULL,
  `update_by`          VARCHAR(64) DEFAULT '' COMMENT '更新者',
  `update_time`        DATETIME DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_user_id` (`user_id`),
  KEY `idx_counselor_id` (`counselor_id`),
  KEY `idx_status` (`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='咨询预约表';

-- 5.8 综合健康报告表
DROP TABLE IF EXISTS `health_report`;
CREATE TABLE `health_report` (
  `id`                BIGINT NOT NULL AUTO_INCREMENT COMMENT '报告ID',
  `report_no`         VARCHAR(50) NOT NULL COMMENT '报告编号',
  `user_id`           BIGINT NOT NULL COMMENT '用户ID',
  `report_type`       VARCHAR(20) DEFAULT 'comprehensive' COMMENT '报告类型',
  `assessment_scores` TEXT COMMENT '各量表得分JSON',
  `mood_summary`      TEXT COMMENT '情绪统计摘要JSON',
  `ai_analysis`       TEXT COMMENT 'AI综合分析',
  `ai_recommendations` TEXT COMMENT 'AI个性化建议',
  `report_status`     CHAR(1) DEFAULT '0' COMMENT '状态（0待生成 1已审核 2已发布）',
  `generate_time`     DATETIME DEFAULT NULL COMMENT '报告生成时间',
  `create_by`         VARCHAR(64) DEFAULT '' COMMENT '创建者',
  `create_time`       DATETIME DEFAULT NULL,
  `update_by`         VARCHAR(64) DEFAULT '' COMMENT '更新者',
  `update_time`       DATETIME DEFAULT NULL,
  `remark`            VARCHAR(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_report_no` (`report_no`),
  KEY `idx_user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='综合健康报告表';

-- 5.9 消息通知表
DROP TABLE IF EXISTS `sys_message`;
CREATE TABLE `sys_message` (
  `id`            BIGINT NOT NULL AUTO_INCREMENT COMMENT '消息ID',
  `user_id`       BIGINT NOT NULL COMMENT '接收用户ID',
  `message_type`  VARCHAR(20) NOT NULL COMMENT '消息类型：appointment/report/assessment/system',
  `title`         VARCHAR(200) NOT NULL COMMENT '消息标题',
  `content`       TEXT COMMENT '消息内容',
  `is_read`       CHAR(1) DEFAULT '0' COMMENT '是否已读（0未读 1已读）',
  `related_id`    BIGINT DEFAULT NULL COMMENT '关联业务ID',
  `related_type`  VARCHAR(50) DEFAULT NULL COMMENT '关联业务类型',
  `create_time`   DATETIME DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `idx_user_id` (`user_id`),
  KEY `idx_is_read` (`is_read`),
  KEY `idx_create_time` (`create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='消息通知表';


COMMIT;
