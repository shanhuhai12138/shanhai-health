# 数据库 SQL 文件说明

## 执行顺序

部署数据库时，按以下顺序执行 **6 个文件**即可：

```bash
# 第1步：系统基础数据（部门/用户/角色/菜单/字典/配置）
mysql -u root -p health < sql/system.sql

# 第2步：体检预约表（套餐/检查项/检查组/报告）+ 预约设置
mysql -u root -p health < sql/reservation.sql

# 第3步：心理测评/情绪/咨询/报告建表
mysql -u root -p health < sql/new_tables.sql

# 第4步：AI对话建表
mysql -u root -p health < sql/ai.sql

# 第5步：量表种子数据
mysql -u root -p health < sql/seed_data.sql

# 第6步：业务菜单权限（预约管理/心理测评/情绪/咨询/报告/AI/通知 + 按钮权限 + 角色关联）
mysql -u root -p health < sql/menu.sql
```

## 文件清单

| 文件 | 行数 | 用途 |
|------|------|------|
| `system.sql` | 686 | 系统管理表+数据 — 部门/用户/角色/菜单(系统管理)/字典/配置/定时任务/公告等 |
| `reservation.sql` | 294 | 体检预约表 — 套餐/检查项/检查组/报告/预约设置/套餐检查组关联 |
| `new_tables.sql` | 203 | 心理/情绪/咨询/报告表 — 量表/题目/结果/情绪/咨询师/排班/预约/健康报告 |
| `ai.sql` | 39 | AI对话表 — 会话/消息 |
| `seed_data.sql` | 49 | 量表种子数据 — PHQ-9/GAD-7/SAS 量表题目 |
| `menu.sql` | 179 | 业务菜单权限 — 预约管理/心理测评/情绪追踪/咨询服务/健康报告/AI对话/消息通知 + 按钮权限 + 角色关联 |

## 注意

- 所有 INSERT 使用 `INSERT IGNORE` 确保可重复执行
- 执行前确保 MySQL 已启动，数据库连接配置在 `application-druid.yml` 中
- 默认账号：admin/admin123
