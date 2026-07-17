# 数据库 SQL 文件说明

## 执行顺序

部署数据库时，按以下顺序执行 **3 个文件**即可：

```bash
# 第1步：创建所有表结构（DROP + CREATE）
mysql -u root -p < sql/clean.sql

# 第2步：插入业务种子数据（套餐/检查项/量表/情绪/咨询师/健康报告）
mysql -u root -p < sql/seed_data.sql

# 第3步：插入业务菜单权限（预约管理/心理测评/情绪/咨询/报告/AI/通知）
mysql -u root -p < sql/menu.sql
```

## 文件清单

| 文件 | 行数 | 用途 |
|------|------|------|
| `clean.sql` | 949 | 建表 DDL — 52 张表（系统管理 20 张 + Quartz 11 张 + 业务 21 张） |
| `seed_data.sql` | 366 | 业务种子数据 — 套餐/检查项/检查组/量表题目/情绪记录/咨询师/健康报告 |
| `menu.sql` | 179 | 业务菜单权限 — 预约管理/心理测评/情绪追踪/咨询服务/健康报告/AI对话/消息通知 + 角色关联 |

## 注意

- `seed_data.sql` 不包含系统管理数据（部门/用户/角色/字典等），这些由 RuoYi 框架启动时自动初始化
- 所有 INSERT 使用 `INSERT IGNORE` 确保可重复执行
- 执行前确保 MySQL 已启动，数据库连接配置在 `application-druid.yml` 中
