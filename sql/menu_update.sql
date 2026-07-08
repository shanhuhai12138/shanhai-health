-- =============================================
-- 传智健康 - 心理测评/情绪追踪/咨询/报告模块菜单
-- 数据库名: health
-- =============================================

USE health;

-- ----------------------------
-- 心理测评 - 一级菜单
-- ----------------------------
INSERT INTO `sys_menu` VALUES (200, '心理测评', 0, 4, 'assessment', NULL, NULL, 1, 0, 'M', '0', '0', '', 'reading', 'admin', NOW(), '', NULL, '心理测评菜单');

-- ----------------------------
-- 情绪追踪 - 一级菜单
-- ----------------------------
INSERT INTO `sys_menu` VALUES (201, '情绪追踪', 0, 5, 'mood', NULL, NULL, 1, 0, 'M', '0', '0', '', 'heart', 'admin', NOW(), '', NULL, '情绪追踪菜单');

-- ----------------------------
-- 咨询服务 - 一级菜单
-- ----------------------------
INSERT INTO `sys_menu` VALUES (202, '咨询服务', 0, 6, 'counseling', NULL, NULL, 1, 0, 'M', '0', '0', '', 'service', 'admin', NOW(), '', NULL, '咨询服务菜单');

-- ----------------------------
-- 健康报告 - 一级菜单
-- ----------------------------
INSERT INTO `sys_menu` VALUES (203, '健康报告', 0, 7, 'report', NULL, NULL, 1, 0, 'M', '0', '0', '', 'document', 'admin', NOW(), '', NULL, '健康报告菜单');

-- 心理测评 - 二级菜单
INSERT INTO `sys_menu` VALUES (2001, '量表管理', 200, 1, 'assessment/list', 'reservation/assessment/list', NULL, 1, 0, 'C', '0', '0', 'reservation:assessment:list', 'list', 'admin', NOW(), '', NULL, '量表管理菜单');
INSERT INTO `sys_menu` VALUES (2002, '量表答题', 200, 2, 'assessment/wizard', 'reservation/assessment/wizard', NULL, 1, 0, 'C', '0', '0', 'reservation:assessment:edit', 'edit', 'admin', NOW(), '', NULL, '量表答题菜单');
INSERT INTO `sys_menu` VALUES (2003, '测评结果', 200, 3, 'assessment/result', 'reservation/assessment/result', NULL, 1, 0, 'C', '0', '0', 'reservation:assessment:query', 'guide', 'admin', NOW(), '', NULL, '测评结果菜单');

-- 情绪追踪 - 二级菜单
INSERT INTO `sys_menu` VALUES (2011, '情绪记录', 201, 1, 'mood/record', 'reservation/mood/record', NULL, 1, 0, 'C', '0', '0', 'reservation:mood:list', 'edit', 'admin', NOW(), '', NULL, '情绪记录菜单');
INSERT INTO `sys_menu` VALUES (2012, '情绪趋势', 201, 2, 'mood/trend', 'reservation/mood/trend', NULL, 1, 0, 'C', '0', '0', 'reservation:mood:query', 'chart', 'admin', NOW(), '', NULL, '情绪趋势菜单');

-- 咨询服务 - 二级菜单
INSERT INTO `sys_menu` VALUES (2021, '咨询师管理', 202, 1, 'counselor/list', 'reservation/counselor/list', NULL, 1, 0, 'C', '0', '0', 'reservation:counselor:list', 'user', 'admin', NOW(), '', NULL, '咨询师管理菜单');
INSERT INTO `sys_menu` VALUES (2022, '预约管理', 202, 2, 'counselor/appointment', 'reservation/counselor/appointment', NULL, 1, 0, 'C', '0', '0', 'reservation:appointment:list', 'peoples', 'admin', NOW(), '', NULL, '预约管理菜单');

-- 健康报告 - 二级菜单
INSERT INTO `sys_menu` VALUES (2031, '报告列表', 203, 1, 'report/list', 'reservation/report/list', NULL, 1, 0, 'C', '0', '0', 'reservation:report:list', 'document', 'admin', NOW(), '', NULL, '报告列表菜单');
INSERT INTO `sys_menu` VALUES (2032, '报告详情', 203, 2, 'report/detail/:id', 'reservation/report/detail', NULL, 1, 0, 'C', '0', '0', 'reservation:report:query', 'eye', 'admin', NOW(), '', NULL, '报告详情菜单', TRUE);

-- 分配给 admin 角色
INSERT INTO `sys_role_menu` VALUES (1, 200);
INSERT INTO `sys_role_menu` VALUES (1, 201);
INSERT INTO `sys_role_menu` VALUES (1, 202);
INSERT INTO `sys_role_menu` VALUES (1, 203);
INSERT INTO `sys_role_menu` VALUES (1, 2001);
INSERT INTO `sys_role_menu` VALUES (1, 2002);
INSERT INTO `sys_role_menu` VALUES (1, 2003);
INSERT INTO `sys_role_menu` VALUES (1, 2011);
INSERT INTO `sys_role_menu` VALUES (1, 2012);
INSERT INTO `sys_role_menu` VALUES (1, 2021);
INSERT INTO `sys_role_menu` VALUES (1, 2022);
INSERT INTO `sys_role_menu` VALUES (1, 2031);
INSERT INTO `sys_role_menu` VALUES (1, 2032);
