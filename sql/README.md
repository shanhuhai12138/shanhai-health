# 数据库 SQL 文件说明

## 执行顺序

部署数据库时，按以下顺序执行 **4 个文件**即可：

```bash
# 第1步：创建所有表结构（DROP + CREATE）
mysql -u root -p health < clean.sql

# 第2步：插入心理量表种子数据
mysql -u root -p health < seed_data.sql

# 第3步：插入系统基础数据（部门/用户/角色/菜单/字典/配置）
mysql -u root -p health < system_init.sql

# 第4步：插入业务菜单权限（预约/测评/情绪/咨询/报告/AI/通知）
mysql -u root -p health < menu.sql
```

## 文件清单

| 文件 | 行数 | 用途 |
|------|------|------|
| `clean.sql` | 949 | 建表 DDL — 52 张表（系统管理 20 张 + Quartz 11 张 + 业务 21 张） |
| `seed_data.sql` | 366 | 心理量表种子数据 — PHQ-9/GAD-7/SAS/SDS 量表题目 |
| `system_init.sql` | 666 | 系统基础数据 — 部门/用户/角色/菜单(系统管理)/字典/配置 |
| `menu.sql` | 210 | 业务菜单权限 — 预约管理/心理测评/情绪追踪/咨询服务/健康报告/AI对话/消息通知 + 角色关联 |

## 注意

- `seed_data.sql` 不包含系统管理数据（部门/用户/角色/字典等），这些由 `system_init.sql` 提供
- 所有 INSERT 使用 `INSERT IGNORE` 或幂等写法，确保可重复执行
- `system_inserts.sql` 已合并至 `system_init.sql`，可安全删除
- 执行前确保数据库名为 `health`，连接配置在 `application-druid.yml` 中
