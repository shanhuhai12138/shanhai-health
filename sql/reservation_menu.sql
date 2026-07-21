-- =============================================
-- 传智健康 - 体检预约模块菜单
-- 数据库名: health
-- =============================================

USE health;

-- ----------------------------
-- 预约管理 - 一级菜单
-- ----------------------------
INSERT IGNORE INTO `sys_menu` VALUES (150, '预约管理', 0, 3, 'reservation', NULL, NULL, '', 1, 0, 'M', '0', '0', '', 'date-range', 'admin', NOW(), '', NULL, '预约管理菜单');

-- 预约设置
INSERT IGNORE INTO `sys_menu` VALUES (1501, '预约设置', 150, 1, 'ordersetting', 'reservation/ordersetting/index', NULL, '', 1, 0, 'C', '0', '0', 'reservation:ordersetting:list', 'calendar', 'admin', NOW(), '', NULL, '预约设置菜单');

-- 检查项管理
INSERT IGNORE INTO `sys_menu` VALUES (1502, '检查项管理', 150, 2, 'checkitem', 'reservation/checkitem/index', NULL, '', 1, 0, 'C', '0', '0', 'reservation:checkitem:list', 'list', 'admin', NOW(), '', NULL, '检查项管理菜单');

-- 检查组管理
INSERT IGNORE INTO `sys_menu` VALUES (1503, '检查组管理', 150, 3, 'checkgroup', 'reservation/checkgroup/index', NULL, '', 1, 0, 'C', '0', '0', 'reservation:checkgroup:list', 'tree-table', 'admin', NOW(), '', NULL, '检查组管理菜单');

-- 体检套餐
INSERT IGNORE INTO `sys_menu` VALUES (1504, '体检套餐', 150, 4, 'setmeal', 'reservation/setmeal/index', NULL, '', 1, 0, 'C', '0', '0', 'reservation:setmeal:list', 'shopping', 'admin', NOW(), '', NULL, '体检套餐菜单');

-- 体检报告
INSERT IGNORE INTO `sys_menu` VALUES (1505, '体检报告', 150, 5, 'report', 'reservation/report/index', NULL, '', 1, 0, 'C', '0', '0', 'reservation:report:list', 'form', 'admin', NOW(), '', NULL, '体检报告菜单');

-- 预约设置按钮
INSERT IGNORE INTO `sys_menu` VALUES (15011, '预约设置查询', 1501, 1, '', '', '', '', 1, 0, 'F', '0', '0', 'reservation:ordersetting:query', '#', 'admin', NOW(), '', NULL, '');
INSERT IGNORE INTO `sys_menu` VALUES (15012, '预约设置新增', 1501, 2, '', '', '', '', 1, 0, 'F', '0', '0', 'reservation:ordersetting:add', '#', 'admin', NOW(), '', NULL, '');
INSERT IGNORE INTO `sys_menu` VALUES (15013, '预约设置修改', 1501, 3, '', '', '', '', 1, 0, 'F', '0', '0', 'reservation:ordersetting:edit', '#', 'admin', NOW(), '', NULL, '');
INSERT IGNORE INTO `sys_menu` VALUES (15014, '预约设置删除', 1501, 4, '', '', '', '', 1, 0, 'F', '0', '0', 'reservation:ordersetting:remove', '#', 'admin', NOW(), '', NULL, '');
INSERT IGNORE INTO `sys_menu` VALUES (15015, '预约设置导出', 1501, 5, '', '', '', '', 1, 0, 'F', '0', '0', 'reservation:ordersetting:export', '#', 'admin', NOW(), '', NULL, '');

-- 检查项按钮
INSERT IGNORE INTO `sys_menu` VALUES (15021, '检查项查询', 1502, 1, '', '', '', '', 1, 0, 'F', '0', '0', 'reservation:checkitem:query', '#', 'admin', NOW(), '', NULL, '');
INSERT IGNORE INTO `sys_menu` VALUES (15022, '检查项新增', 1502, 2, '', '', '', '', 1, 0, 'F', '0', '0', 'reservation:checkitem:add', '#', 'admin', NOW(), '', NULL, '');
INSERT IGNORE INTO `sys_menu` VALUES (15023, '检查项修改', 1502, 3, '', '', '', '', 1, 0, 'F', '0', '0', 'reservation:checkitem:edit', '#', 'admin', NOW(), '', NULL, '');
INSERT IGNORE INTO `sys_menu` VALUES (15024, '检查项删除', 1502, 4, '', '', '', '', 1, 0, 'F', '0', '0', 'reservation:checkitem:remove', '#', 'admin', NOW(), '', NULL, '');
INSERT IGNORE INTO `sys_menu` VALUES (15025, '检查项导出', 1502, 5, '', '', '', '', 1, 0, 'F', '0', '0', 'reservation:checkitem:export', '#', 'admin', NOW(), '', NULL, '');

-- 检查组按钮
INSERT IGNORE INTO `sys_menu` VALUES (15031, '检查组查询', 1503, 1, '', '', '', '', 1, 0, 'F', '0', '0', 'reservation:checkgroup:query', '#', 'admin', NOW(), '', NULL, '');
INSERT IGNORE INTO `sys_menu` VALUES (15032, '检查组新增', 1503, 2, '', '', '', '', 1, 0, 'F', '0', '0', 'reservation:checkgroup:add', '#', 'admin', NOW(), '', NULL, '');
INSERT IGNORE INTO `sys_menu` VALUES (15033, '检查组修改', 1503, 3, '', '', '', '', 1, 0, 'F', '0', '0', 'reservation:checkgroup:edit', '#', 'admin', NOW(), '', NULL, '');
INSERT IGNORE INTO `sys_menu` VALUES (15034, '检查组删除', 1503, 4, '', '', '', '', 1, 0, 'F', '0', '0', 'reservation:checkgroup:remove', '#', 'admin', NOW(), '', NULL, '');
INSERT IGNORE INTO `sys_menu` VALUES (15035, '检查组导出', 1503, 5, '', '', '', '', 1, 0, 'F', '0', '0', 'reservation:checkgroup:export', '#', 'admin', NOW(), '', NULL, '');

-- 体检套餐按钮
INSERT IGNORE INTO `sys_menu` VALUES (15041, '体检套餐查询', 1504, 1, '', '', '', '', 1, 0, 'F', '0', '0', 'reservation:setmeal:query', '#', 'admin', NOW(), '', NULL, '');
INSERT IGNORE INTO `sys_menu` VALUES (15042, '体检套餐新增', 1504, 2, '', '', '', '', 1, 0, 'F', '0', '0', 'reservation:setmeal:add', '#', 'admin', NOW(), '', NULL, '');
INSERT IGNORE INTO `sys_menu` VALUES (15043, '体检套餐修改', 1504, 3, '', '', '', '', 1, 0, 'F', '0', '0', 'reservation:setmeal:edit', '#', 'admin', NOW(), '', NULL, '');
INSERT IGNORE INTO `sys_menu` VALUES (15044, '体检套餐删除', 1504, 4, '', '', '', '', 1, 0, 'F', '0', '0', 'reservation:setmeal:remove', '#', 'admin', NOW(), '', NULL, '');
INSERT IGNORE INTO `sys_menu` VALUES (15045, '体检套餐导出', 1504, 5, '', '', '', '', 1, 0, 'F', '0', '0', 'reservation:setmeal:export', '#', 'admin', NOW(), '', NULL, '');

-- 体检报告按钮
INSERT IGNORE INTO `sys_menu` VALUES (15051, '体检报告查询', 1505, 1, '', '', '', '', 1, 0, 'F', '0', '0', 'reservation:report:query', '#', 'admin', NOW(), '', NULL, '');
INSERT IGNORE INTO `sys_menu` VALUES (15052, '体检报告新增', 1505, 2, '', '', '', '', 1, 0, 'F', '0', '0', 'reservation:report:add', '#', 'admin', NOW(), '', NULL, '');
INSERT IGNORE INTO `sys_menu` VALUES (15053, '体检报告修改', 1505, 3, '', '', '', '', 1, 0, 'F', '0', '0', 'reservation:report:edit', '#', 'admin', NOW(), '', NULL, '');
INSERT IGNORE INTO `sys_menu` VALUES (15054, '体检报告删除', 1505, 4, '', '', '', '', 1, 0, 'F', '0', '0', 'reservation:report:remove', '#', 'admin', NOW(), '', NULL, '');
INSERT IGNORE INTO `sys_menu` VALUES (15055, '体检报告导出', 1505, 5, '', '', '', '', 1, 0, 'F', '0', '0', 'reservation:report:export', '#', 'admin', NOW(), '', NULL, '');
INSERT IGNORE INTO `sys_menu` VALUES (15056, '体检报告审核', 1505, 6, '', '', '', '', 1, 0, 'F', '0', '0', 'reservation:report:audit', '#', 'admin', NOW(), '', NULL, '');
INSERT IGNORE INTO `sys_menu` VALUES (15057, '体检报告发布', 1505, 7, '', '', '', '', 1, 0, 'F', '0', '0', 'reservation:report:publish', '#', 'admin', NOW(), '', NULL, '');
INSERT IGNORE INTO `sys_menu` VALUES (15058, '体检报告归档', 1505, 8, '', '', '', '', 1, 0, 'F', '0', '0', 'reservation:report:archive', '#', 'admin', NOW(), '', NULL, '');

-- 分配给 admin 角色
INSERT IGNORE INTO `sys_role_menu` VALUES (1, 150);
INSERT IGNORE INTO `sys_role_menu` VALUES (1, 1501);
INSERT IGNORE INTO `sys_role_menu` VALUES (1, 1502);
INSERT IGNORE INTO `sys_role_menu` VALUES (1, 1503);
INSERT IGNORE INTO `sys_role_menu` VALUES (1, 1504);
INSERT IGNORE INTO `sys_role_menu` VALUES (1, 1505);
INSERT IGNORE INTO `sys_role_menu` VALUES (1, 15011);
INSERT IGNORE INTO `sys_role_menu` VALUES (1, 15012);
INSERT IGNORE INTO `sys_role_menu` VALUES (1, 15013);
INSERT IGNORE INTO `sys_role_menu` VALUES (1, 15014);
INSERT IGNORE INTO `sys_role_menu` VALUES (1, 15015);
INSERT IGNORE INTO `sys_role_menu` VALUES (1, 15021);
INSERT IGNORE INTO `sys_role_menu` VALUES (1, 15022);
INSERT IGNORE INTO `sys_role_menu` VALUES (1, 15023);
INSERT IGNORE INTO `sys_role_menu` VALUES (1, 15024);
INSERT IGNORE INTO `sys_role_menu` VALUES (1, 15025);
INSERT IGNORE INTO `sys_role_menu` VALUES (1, 15031);
INSERT IGNORE INTO `sys_role_menu` VALUES (1, 15032);
INSERT IGNORE INTO `sys_role_menu` VALUES (1, 15033);
INSERT IGNORE INTO `sys_role_menu` VALUES (1, 15034);
INSERT IGNORE INTO `sys_role_menu` VALUES (1, 15035);
INSERT IGNORE INTO `sys_role_menu` VALUES (1, 15041);
INSERT IGNORE INTO `sys_role_menu` VALUES (1, 15042);
INSERT IGNORE INTO `sys_role_menu` VALUES (1, 15043);
INSERT IGNORE INTO `sys_role_menu` VALUES (1, 15044);
INSERT IGNORE INTO `sys_role_menu` VALUES (1, 15045);
INSERT IGNORE INTO `sys_role_menu` VALUES (1, 15051);
INSERT IGNORE INTO `sys_role_menu` VALUES (1, 15052);
INSERT IGNORE INTO `sys_role_menu` VALUES (1, 15053);
INSERT IGNORE INTO `sys_role_menu` VALUES (1, 15054);
INSERT IGNORE INTO `sys_role_menu` VALUES (1, 15055);
INSERT IGNORE INTO `sys_role_menu` VALUES (1, 15056);
INSERT IGNORE INTO `sys_role_menu` VALUES (1, 15057);
INSERT IGNORE INTO `sys_role_menu` VALUES (1, 15058);
