-- ----------------------------
-- 体检报告模块 - 升级为顶级菜单 + Excel导入
-- ----------------------------

-- 1. 删除旧的体检报告菜单（如果存在，即挂在预约管理下的）
-- 先清理旧数据（如果体检报告是挂在预约管理下的子菜单）
use health;
DELETE FROM sys_role_menu WHERE menu_id IN (
    SELECT menu_id FROM sys_menu WHERE perms LIKE 'reservation:report:%'
);
DELETE FROM sys_menu WHERE perms LIKE 'reservation:report:%';

-- 2. 插入体检报告为顶级菜单（parent_id = 0）
-- menu_type = 'C' 表示菜单（页面），不是目录。这样会直接渲染页面，不替换整个布局
INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('体检报告', 0, '3', 'report', 'reservation/report/index', 1, 0, 'C', '0', '0', 'reservation:report:list', 'form', 'admin', NOW(), '', NULL, '体检报告菜单（顶级）');

-- 获取刚插入的体检报告菜单ID
SELECT @reportMenuId := LAST_INSERT_ID();

-- 3. 按钮权限
INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('体检报告查询', @reportMenuId, '1', '#', '', 1, 0, 'F', '0', '0', 'reservation:report:query', '#', 'admin', NOW(), '', NULL, '');

INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('体检报告新增', @reportMenuId, '2', '#', '', 1, 0, 'F', '0', '0', 'reservation:report:add', '#', 'admin', NOW(), '', NULL, '');

INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('体检报告修改', @reportMenuId, '3', '#', '', 1, 0, 'F', '0', '0', 'reservation:report:edit', '#', 'admin', NOW(), '', NULL, '');

INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('体检报告删除', @reportMenuId, '4', '#', '', 1, 0, 'F', '0', '0', 'reservation:report:remove', '#', 'admin', NOW(), '', NULL, '');

INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('体检报告导出', @reportMenuId, '5', '#', '', 1, 0, 'F', '0', '0', 'reservation:report:export', '#', 'admin', NOW(), '', NULL, '');

INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('体检报告导入', @reportMenuId, '6', '#', '', 1, 0, 'F', '0', '0', 'reservation:report:import', '#', 'admin', NOW(), '', NULL, '');

INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('体检报告审核', @reportMenuId, '7', '#', '', 1, 0, 'F', '0', '0', 'reservation:report:audit', '#', 'admin', NOW(), '', NULL, '');

INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('体检报告发布', @reportMenuId, '8', '#', '', 1, 0, 'F', '0', '0', 'reservation:report:publish', '#', 'admin', NOW(), '', NULL, '');

INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('体检报告归档', @reportMenuId, '9', '#', '', 1, 0, 'F', '0', '0', 'reservation:report:archive', '#', 'admin', NOW(), '', NULL, '');

-- 4. 分配给超级管理员角色（role_id = 1）
INSERT INTO sys_role_menu (role_id, menu_id) VALUES (1, @reportMenuId);
INSERT INTO sys_role_menu (role_id, menu_id) VALUES (1, @reportMenuId + 1);
INSERT INTO sys_role_menu (role_id, menu_id) VALUES (1, @reportMenuId + 2);
INSERT INTO sys_role_menu (role_id, menu_id) VALUES (1, @reportMenuId + 3);
INSERT INTO sys_role_menu (role_id, menu_id) VALUES (1, @reportMenuId + 4);
INSERT INTO sys_role_menu (role_id, menu_id) VALUES (1, @reportMenuId + 5);
INSERT INTO sys_role_menu (role_id, menu_id) VALUES (1, @reportMenuId + 6);
INSERT INTO sys_role_menu (role_id, menu_id) VALUES (1, @reportMenuId + 7);
INSERT INTO sys_role_menu (role_id, menu_id) VALUES (1, @reportMenuId + 8);
INSERT INTO sys_role_menu (role_id, menu_id) VALUES (1, @reportMenuId + 9);

-- ============================
-- 示例数据：插入几条测试体检报告
-- ============================

-- 示例报告1
INSERT INTO t_report (report_no, member_name, member_phone, member_idcard, member_sex, member_age, setmeal_id, setmeal_name, order_date, report_status, doctor_advice, create_by, create_time)
VALUES ('RP202607020001', '张三', '13800138001', '110101199001011234', '0', 36, NULL, '入职体检套餐', '2026-07-01', '2', '各项指标基本正常，建议保持良好生活习惯。', 'admin', NOW());

-- 示例报告明细 - 张三（血常规）
INSERT INTO t_report_item (report_id, checkitem_id, checkgroup_id, checkitem_name, checkgroup_name, result, unit, normal_range, abnormal_flag, abnormal_mark, create_time)
SELECT LAST_INSERT_ID(), ci.id, cg.id, ci.name, cg.name,
       CASE ci.name WHEN '白细胞' THEN '6.5' WHEN '红细胞' THEN '4.8' WHEN '血红蛋白' THEN '145' WHEN '血小板' THEN '210' ELSE '正常' END,
       CASE ci.name WHEN '白细胞' THEN '10^9/L' WHEN '红细胞' THEN '10^12/L' WHEN '血红蛋白' THEN 'g/L' WHEN '血小板' THEN '10^9/L' ELSE '' END,
       CASE ci.name WHEN '白细胞' THEN '3.5-9.5' WHEN '红细胞' THEN '4.0-5.5' WHEN '血红蛋白' THEN '120-160' WHEN '血小板' THEN '100-300' ELSE '' END,
        '0', '', NOW()
FROM t_checkitem ci
LEFT JOIN t_checkgroup cg ON ci.checkgroup_id = cg.id
WHERE ci.name IN ('白细胞', '红细胞', '血红蛋白', '血小板')
LIMIT 0;

-- 手动插入血常规明细
SET @rid1 = LAST_INSERT_ID();
INSERT INTO t_report_item (report_id, checkitem_id, checkgroup_id, checkitem_name, checkgroup_name, result, unit, normal_range, abnormal_flag, abnormal_mark, create_time)
VALUES (@rid1, NULL, NULL, '白细胞', '血常规', '6.5', '10^9/L', '3.5-9.5', '0', '', NOW()),
       (@rid1, NULL, NULL, '红细胞', '血常规', '4.8', '10^12/L', '4.0-5.5', '0', '', NOW()),
       (@rid1, NULL, NULL, '血红蛋白', '血常规', '145', 'g/L', '120-160', '0', '', NOW()),
       (@rid1, NULL, NULL, '血小板', '血常规', '210', '10^9/L', '100-300', '0', '', NOW());

-- 示例报告2
INSERT INTO t_report (report_no, member_name, member_phone, member_idcard, member_sex, member_age, setmeal_id, setmeal_name, order_date, report_status, doctor_advice, create_by, create_time)
VALUES ('RP202607020002', '李四', '13800138002', '110101198501011235', '1', 41, NULL, '全面体检套餐', '2026-07-01', '1', '血脂偏高，建议低脂饮食，适当运动，定期复查。', 'admin', NOW());

SET @rid2 = LAST_INSERT_ID();
INSERT INTO t_report_item (report_id, checkitem_id, checkgroup_id, checkitem_name, checkgroup_name, result, unit, normal_range, abnormal_flag, abnormal_mark, create_time)
VALUES (@rid2, NULL, NULL, '总胆固醇', '血脂分析', '6.2', 'mmol/L', '3.1-5.7', '1', '↑ 偏高', NOW()),
       (@rid2, NULL, NULL, '甘油三酯', '血脂分析', '2.1', 'mmol/L', '0.56-1.7', '1', '↑ 偏高', NOW()),
       (@rid2, NULL, NULL, '高密度脂蛋白', '血脂分析', '1.3', 'mmol/L', '1.0-1.9', '0', '', NOW()),
       (@rid2, NULL, NULL, '低密度脂蛋白', '血脂分析', '3.8', 'mmol/L', '0-3.37', '1', '↑ 偏高', NOW());

-- 示例报告3（待录入状态）
INSERT INTO t_report (report_no, member_name, member_phone, member_idcard, member_sex, member_age, setmeal_id, setmeal_name, order_date, report_status, doctor_advice, create_by, create_time)
VALUES ('RP202607020003', '王五', '13800138003', '110101199501011236', '0', 31, NULL, '基础体检套餐', '2026-07-02', '0', NULL, 'admin', NOW());
