-- =============================================
-- 传智健康 - 业务菜单数据（menu.sql）
-- 数据库名: health
-- 执行顺序: 在 seed_data.sql 之后执行
-- 包含: 预约管理 / 心理测评 / 情绪追踪 / 咨询服务 / 健康报告 / AI对话
-- 说明: 系统管理模块的菜单已在 seed_data.sql 中插入（menu_id 1-117, 500-501, 1000-1060），此处不再重复
--       使用 INSERT IGNORE 避免重复执行时报错
-- =============================================

USE `health`;

-- =============================================
-- 1. 体检预约管理菜单（来自 reservation_menu.sql）
-- =============================================

-- 一级菜单：预约管理
INSERT IGNORE INTO `sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `route_name`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `remark`)
VALUES (150, '预约管理', 0, 3, 'reservation', NULL, '', '', 1, 0, 'M', '0', '0', '', 'date-range', 'admin', NOW(), '预约管理菜单');

-- 二级菜单
INSERT IGNORE INTO `sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `route_name`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `remark`)
VALUES
(1501, '预约设置', 150, 1, 'ordersetting', 'reservation/ordersetting/index', '', '', 1, 0, 'C', '0', '0', 'reservation:ordersetting:list', 'calendar', 'admin', NOW(), '预约设置菜单'),
(1502, '检查项管理', 150, 2, 'checkitem', 'reservation/checkitem/index', '', '', 1, 0, 'C', '0', '0', 'reservation:checkitem:list', 'list', 'admin', NOW(), '检查项管理菜单'),
(1503, '检查组管理', 150, 3, 'checkgroup', 'reservation/checkgroup/index', '', '', 1, 0, 'C', '0', '0', 'reservation:checkgroup:list', 'tree-table', 'admin', NOW(), '检查组管理菜单'),
(1504, '体检套餐', 150, 4, 'setmeal', 'reservation/setmeal/index', '', '', 1, 0, 'C', '0', '0', 'reservation:setmeal:list', 'shopping', 'admin', NOW(), '体检套餐菜单'),
(1505, '体检报告', 150, 5, 'treport', 'reservation/report/index', '', 'Report', 1, 0, 'C', '0', '0', 'reservation:report:list', 'form', 'admin', NOW(), '体检报告菜单');

-- 预约设置按钮
INSERT IGNORE INTO `sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `route_name`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `remark`)
VALUES
(15011, '预约设置查询', 1501, 1, '', '', '', '', 1, 0, 'F', '0', '0', 'reservation:ordersetting:query', '#', 'admin', NOW(), ''),
(15012, '预约设置新增', 1501, 2, '', '', '', '', 1, 0, 'F', '0', '0', 'reservation:ordersetting:add', '#', 'admin', NOW(), ''),
(15013, '预约设置修改', 1501, 3, '', '', '', '', 1, 0, 'F', '0', '0', 'reservation:ordersetting:edit', '#', 'admin', NOW(), ''),
(15014, '预约设置删除', 1501, 4, '', '', '', '', 1, 0, 'F', '0', '0', 'reservation:ordersetting:remove', '#', 'admin', NOW(), ''),
(15015, '预约设置导出', 1501, 5, '', '', '', '', 1, 0, 'F', '0', '0', 'reservation:ordersetting:export', '#', 'admin', NOW(), '');

-- 检查项按钮
INSERT IGNORE INTO `sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `route_name`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `remark`)
VALUES
(15021, '检查项查询', 1502, 1, '', '', '', '', 1, 0, 'F', '0', '0', 'reservation:checkitem:query', '#', 'admin', NOW(), ''),
(15022, '检查项新增', 1502, 2, '', '', '', '', 1, 0, 'F', '0', '0', 'reservation:checkitem:add', '#', 'admin', NOW(), ''),
(15023, '检查项修改', 1502, 3, '', '', '', '', 1, 0, 'F', '0', '0', 'reservation:checkitem:edit', '#', 'admin', NOW(), ''),
(15024, '检查项删除', 1502, 4, '', '', '', '', 1, 0, 'F', '0', '0', 'reservation:checkitem:remove', '#', 'admin', NOW(), ''),
(15025, '检查项导出', 1502, 5, '', '', '', '', 1, 0, 'F', '0', '0', 'reservation:checkitem:export', '#', 'admin', NOW(), '');

-- 检查组按钮
INSERT IGNORE INTO `sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `route_name`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `remark`)
VALUES
(15031, '检查组查询', 1503, 1, '', '', '', '', 1, 0, 'F', '0', '0', 'reservation:checkgroup:query', '#', 'admin', NOW(), ''),
(15032, '检查组新增', 1503, 2, '', '', '', '', 1, 0, 'F', '0', '0', 'reservation:checkgroup:add', '#', 'admin', NOW(), ''),
(15033, '检查组修改', 1503, 3, '', '', '', '', 1, 0, 'F', '0', '0', 'reservation:checkgroup:edit', '#', 'admin', NOW(), ''),
(15034, '检查组删除', 1503, 4, '', '', '', '', 1, 0, 'F', '0', '0', 'reservation:checkgroup:remove', '#', 'admin', NOW(), ''),
(15035, '检查组导出', 1503, 5, '', '', '', '', 1, 0, 'F', '0', '0', 'reservation:checkgroup:export', '#', 'admin', NOW(), '');

-- 体检套餐按钮
INSERT IGNORE INTO `sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `route_name`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `remark`)
VALUES
(15041, '体检套餐查询', 1504, 1, '', '', '', '', 1, 0, 'F', '0', '0', 'reservation:setmeal:query', '#', 'admin', NOW(), ''),
(15042, '体检套餐新增', 1504, 2, '', '', '', '', 1, 0, 'F', '0', '0', 'reservation:setmeal:add', '#', 'admin', NOW(), ''),
(15043, '体检套餐修改', 1504, 3, '', '', '', '', 1, 0, 'F', '0', '0', 'reservation:setmeal:edit', '#', 'admin', NOW(), ''),
(15044, '体检套餐删除', 1504, 4, '', '', '', '', 1, 0, 'F', '0', '0', 'reservation:setmeal:remove', '#', 'admin', NOW(), ''),
(15045, '体检套餐导出', 1504, 5, '', '', '', '', 1, 0, 'F', '0', '0', 'reservation:setmeal:export', '#', 'admin', NOW(), '');

-- 体检报告按钮
INSERT IGNORE INTO `sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `route_name`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `remark`)
VALUES
(15051, '体检报告查询', 1505, 1, '', '', '', '', 1, 0, 'F', '0', '0', 'reservation:report:query', '#', 'admin', NOW(), ''),
(15052, '体检报告新增', 1505, 2, '', '', '', '', 1, 0, 'F', '0', '0', 'reservation:report:add', '#', 'admin', NOW(), ''),
(15053, '体检报告修改', 1505, 3, '', '', '', '', 1, 0, 'F', '0', '0', 'reservation:report:edit', '#', 'admin', NOW(), ''),
(15054, '体检报告删除', 1505, 4, '', '', '', '', 1, 0, 'F', '0', '0', 'reservation:report:remove', '#', 'admin', NOW(), ''),
(15055, '体检报告导出', 1505, 5, '', '', '', '', 1, 0, 'F', '0', '0', 'reservation:report:export', '#', 'admin', NOW(), ''),
(15056, '体检报告审核', 1505, 6, '', '', '', '', 1, 0, 'F', '0', '0', 'reservation:report:audit', '#', 'admin', NOW(), ''),
(15057, '体检报告发布', 1505, 7, '', '', '', '', 1, 0, 'F', '0', '0', 'reservation:report:publish', '#', 'admin', NOW(), ''),
(15058, '体检报告归档', 1505, 8, '', '', '', '', 1, 0, 'F', '0', '0', 'reservation:report:archive', '#', 'admin', NOW(), '');


-- =============================================
-- 2. 心理测评/情绪追踪/咨询服务/健康报告菜单（来自 menu_update.sql）
-- =============================================

-- 一级菜单
INSERT IGNORE INTO `sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `route_name`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `remark`)
VALUES
(200, '心理测评', 0, 4, 'assessment', NULL, '', '', 1, 0, 'M', '0', '0', '', 'reading', 'admin', NOW(), '心理测评菜单'),
(201, '情绪追踪', 0, 5, 'mood', NULL, '', '', 1, 0, 'M', '0', '0', '', 'heart', 'admin', NOW(), '情绪追踪菜单'),
(202, '咨询服务', 0, 6, 'counseling', NULL, '', '', 1, 0, 'M', '0', '0', '', 'service', 'admin', NOW(), '咨询服务菜单'),
(203, '健康报告', 0, 7, 'report', NULL, '', '', 1, 0, 'M', '0', '0', '', 'document', 'admin', NOW(), '健康报告菜单');

-- 心理测评二级菜单
INSERT IGNORE INTO `sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `route_name`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `remark`)
VALUES
(2001, '量表管理', 200, 1, 'list', 'reservation/assessment/list', '', 'AssessmentList', 1, 0, 'C', '0', '0', 'reservation:assessment:list', 'list', 'admin', NOW(), '量表管理菜单'),
(2002, '量表答题', 200, 2, 'wizard', 'reservation/assessment/wizard', '', 'AssessmentWizard', 1, 0, 'C', '0', '0', 'reservation:assessment:edit', 'edit', 'admin', NOW(), '量表答题菜单'),
(2003, '测评结果', 200, 3, 'result', 'reservation/assessment/result', '', 'AssessmentResult', 1, 0, 'C', '0', '0', 'reservation:assessment:query', 'guide', 'admin', NOW(), '测评结果菜单');

-- 情绪追踪二级菜单
INSERT IGNORE INTO `sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `route_name`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `remark`)
VALUES
(2011, '情绪记录', 201, 1, 'record', 'reservation/mood/record', '', 'MoodRecord', 1, 0, 'C', '0', '0', 'reservation:mood:list', 'edit', 'admin', NOW(), '情绪记录菜单'),
(2012, '情绪趋势', 201, 2, 'trend', 'reservation/mood/trend', '', 'MoodTrend', 1, 0, 'C', '0', '0', 'reservation:mood:query', 'chart', 'admin', NOW(), '情绪趋势菜单');

-- 咨询服务二级菜单
INSERT IGNORE INTO `sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `route_name`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `remark`)
VALUES
(2021, '咨询师管理', 202, 1, 'list', 'reservation/counselor/list', '', 'CounselorList', 1, 0, 'C', '0', '0', 'reservation:counselor:list', 'user', 'admin', NOW(), '咨询师管理菜单'),
(2022, '预约管理', 202, 2, 'appointment', 'reservation/counselor/appointment', '', 'CounselorAppointment', 1, 0, 'C', '0', '0', 'reservation:appointment:list', 'peoples', 'admin', NOW(), '预约管理菜单');

-- 健康报告二级菜单
INSERT IGNORE INTO `sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `route_name`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `remark`)
VALUES
(2031, '报告列表', 203, 1, 'reportlist', 'reservation/report/list', '', 'ReportList', 1, 0, 'C', '0', '0', 'reservation:report:list', 'document', 'admin', NOW(), '报告列表菜单'),
(2032, '报告详情', 203, 2, 'reportdetail/:id', 'reservation/report/detail', '1', 'ReportDetail', 1, 0, 'C', '0', '0', 'reservation:report:query', 'eye', 'admin', NOW(), '报告详情菜单');

-- 一级菜单：消息通知
INSERT IGNORE INTO `sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `route_name`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `remark`)
VALUES (204, '消息通知', 0, 9, 'notification', NULL, '', '', 1, 0, 'M', '0', '0', '', 'message', 'admin', NOW(), '消息通知菜单');

-- 消息通知二级菜单
INSERT IGNORE INTO `sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `route_name`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `remark`)
VALUES (2041, '消息中心', 204, 1, 'index', 'reservation/notification/index', '', 'Notification', 1, 0, 'C', '0', '0', 'reservation:notification:list', 'message', 'admin', NOW(), '消息中心菜单');

-- 消息通知按钮权限
INSERT IGNORE INTO `sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `route_name`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `remark`)
VALUES
(20411, '消息查询', 2041, 1, '', '', '', '', 1, 0, 'F', '0', '0', 'reservation:notification:query', '#', 'admin', NOW(), ''),
(20412, '消息已读', 2041, 2, '', '', '', '', 1, 0, 'F', '0', '0', 'reservation:notification:read', '#', 'admin', NOW(), '');


-- =============================================
-- 3. AI对话菜单（来自 ai_menu.sql）
-- =============================================

-- AI对话 - 一级菜单
INSERT IGNORE INTO `sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `route_name`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `remark`)
VALUES (5000, 'AI对话', 0, 8, 'ai', NULL, '', '', 1, 0, 'M', '0', '0', '', 'cpu', 'admin', NOW(), 'AI对话菜单目录');

-- AI聊天 - 二级菜单
INSERT IGNORE INTO `sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `route_name`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `remark`)
VALUES (5001, 'AI聊天', 5000, 1, 'chat', 'ai/chat/index', '', '', 1, 0, 'C', '0', '0', 'ai:chat:list', 'message', 'admin', NOW(), 'AI聊天菜单');

-- AI对话按钮权限
INSERT IGNORE INTO `sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `route_name`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `remark`)
VALUES
(5002, 'AI对话查询', 5001, 1, '', '', '', '', 1, 0, 'F', '0', '0', 'ai:chat:query', '#', 'admin', NOW(), ''),
(5003, 'AI对话新增', 5001, 2, '', '', '', '', 1, 0, 'F', '0', '0', 'ai:chat:add', '#', 'admin', NOW(), ''),
(5004, 'AI对话修改', 5001, 3, '', '', '', '', 1, 0, 'F', '0', '0', 'ai:chat:edit', '#', 'admin', NOW(), ''),
(5005, 'AI对话删除', 5001, 4, '', '', '', '', 1, 0, 'F', '0', '0', 'ai:chat:remove', '#', 'admin', NOW(), '');


-- =============================================
-- 4. 角色菜单关联
-- =============================================

-- Admin角色(1) - 预约管理菜单权限
INSERT IGNORE INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES
(1, 150), (1, 1501), (1, 1502), (1, 1503), (1, 1504), (1, 1505),
(1, 15011), (1, 15012), (1, 15013), (1, 15014), (1, 15015),
(1, 15021), (1, 15022), (1, 15023), (1, 15024), (1, 15025),
(1, 15031), (1, 15032), (1, 15033), (1, 15034), (1, 15035),
(1, 15041), (1, 15042), (1, 15043), (1, 15044), (1, 15045),
(1, 15051), (1, 15052), (1, 15053), (1, 15054), (1, 15055), (1, 15056), (1, 15057), (1, 15058);

-- Admin角色(1) - 心理测评/情绪/咨询/报告菜单权限
INSERT IGNORE INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES
(1, 200), (1, 201), (1, 202), (1, 203),
(1, 2001), (1, 2002), (1, 2003),
(1, 2011), (1, 2012),
(1, 2021), (1, 2022),
(1, 2031), (1, 2032),
(1, 204), (1, 2041),
(1, 20411), (1, 20412);

-- Admin角色(1) - AI对话菜单权限
INSERT IGNORE INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES
(1, 5000), (1, 5001), (1, 5002), (1, 5003), (1, 5004), (1, 5005);


COMMIT;
