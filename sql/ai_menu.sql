-- ----------------------------
-- AI 对话菜单配置
-- ----------------------------
USE health;
-- 一级菜单：AI对话（目录）
INSERT INTO sys_menu VALUES('5000', 'AI对话', '0', '5', 'ai',           null, '', '', 1, 0, 'M', '0', '0', '',        'message',  'admin', sysdate(), '', NULL, 'AI对话菜单目录');

-- 二级菜单：AI聊天（页面）
INSERT INTO sys_menu VALUES('5001', 'AI聊天', '5000', '1',  'chat',       'ai/chat/index',        '', '', 1, 0, 'C', '0', '0', 'ai:chat:list',        'chat-line', 'admin', sysdate(), '', NULL, 'AI聊天菜单');

-- 按钮权限
INSERT INTO sys_menu VALUES('5002', 'AI对话查询', '5001', '1',  '#', '', '', '', 1, 0, 'F', '0', '0', 'ai:chat:query',       '#', 'admin', sysdate(), '', NULL, '');
INSERT INTO sys_menu VALUES('5003', 'AI对话新增', '5001', '2',  '#', '', '', '', 1, 0, 'F', '0', '0', 'ai:chat:add',         '#', 'admin', sysdate(), '', NULL, '');
INSERT INTO sys_menu VALUES('5004', 'AI对话修改', '5001', '3',  '#', '', '', '', 1, 0, 'F', '0', '0', 'ai:chat:edit',        '#', 'admin', sysdate(), '', NULL, '');
INSERT INTO sys_menu VALUES('5005', 'AI对话删除', '5001', '4',  '#', '', '', '', 1, 0, 'F', '0', '0', 'ai:chat:remove',      '#', 'admin', sysdate(), '', NULL, '');

-- 将菜单分配给管理员角色（角色ID=1为超级管理员）
INSERT INTO sys_role_menu VALUES ('1', '5000');
INSERT INTO sys_role_menu VALUES ('1', '5001');
INSERT INTO sys_role_menu VALUES ('1', '5002');
INSERT INTO sys_role_menu VALUES ('1', '5003');
INSERT INTO sys_role_menu VALUES ('1', '5004');
INSERT INTO sys_role_menu VALUES ('1', '5005');
