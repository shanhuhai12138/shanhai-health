-- =============================================
-- 传智健康 - AI 模块菜单
-- 数据库名: health
-- =============================================

USE health;

-- AI对话 - 一级菜单
INSERT IGNORE INTO `sys_menu` VALUES (5000, 'AI对话', 0, 8, 'ai', NULL, '', '', 1, 0, 'M', '0', '0', '', 'cpu', 'admin', NOW(), '', NULL, 'AI对话菜单目录');

-- AI聊天 - 二级菜单
INSERT IGNORE INTO `sys_menu` VALUES (5001, 'AI聊天', 5000, 1, 'chat', 'ai/chat/index', '', '', 1, 0, 'C', '0', '0', 'ai:chat:list', 'message', 'admin', NOW(), '', NULL, 'AI聊天菜单');

-- 按钮权限
INSERT IGNORE INTO `sys_menu` VALUES (5002, 'AI对话查询', 5001, 1, '', '', '', '', 1, 0, 'F', '0', '0', 'ai:chat:query', '#', 'admin', NOW(), '', NULL, '');
INSERT IGNORE INTO `sys_menu` VALUES (5003, 'AI对话新增', 5001, 2, '', '', '', '', 1, 0, 'F', '0', '0', 'ai:chat:add', '#', 'admin', NOW(), '', NULL, '');
INSERT IGNORE INTO `sys_menu` VALUES (5004, 'AI对话修改', 5001, 3, '', '', '', '', 1, 0, 'F', '0', '0', 'ai:chat:edit', '#', 'admin', NOW(), '', NULL, '');
INSERT IGNORE INTO `sys_menu` VALUES (5005, 'AI对话删除', 5001, 4, '', '', '', '', 1, 0, 'F', '0', '0', 'ai:chat:remove', '#', 'admin', NOW(), '', NULL, '');

-- 分配给 admin 角色
INSERT IGNORE INTO `sys_role_menu` VALUES (1, 5000);
INSERT IGNORE INTO `sys_role_menu` VALUES (1, 5001);
INSERT IGNORE INTO `sys_role_menu` VALUES (1, 5002);
INSERT IGNORE INTO `sys_role_menu` VALUES (1, 5003);
INSERT IGNORE INTO `sys_role_menu` VALUES (1, 5004);
INSERT IGNORE INTO `sys_role_menu` VALUES (1, 5005);
