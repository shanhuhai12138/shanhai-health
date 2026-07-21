# 数据库与前后端一致性修复计划

## 概述
基于数据库审查报告，发现以下不一致问题需要修复。修复范围：仅涉及 Java Entity 类和 MyBatis Mapper XML 文件，不改动数据库表结构、不改动前端、不改动 Controller。

## 修复原则
- 所有 Entity 类继承 BaseEntity（已提供 createBy/createTime/updateBy/updateTime/remark）
- Entity 字段必须与 Mapper XML resultMap 和 SQL 保持一致
- 类型尽量使用 Integer/Long 对应 DB INT/BIGINT，String 对应 VARCHAR/CHAR/TEXT

---

## 任务清单

### Task 1: TCheckgroup — 补充缺失字段
**文件:**
- `health-reservation/src/main/java/com/health/reservation/domain/TCheckgroup.java`
- `health-reservation/src/main/resources/mapper/reservation/TCheckgroupMapper.xml`

**DB 表 `t_checkgroup` 有但 Entity/Mapper 缺少的字段:**
- `age` VARCHAR(50) — 适用年龄范围
- `sort` INT(4) — 排序

**修改内容:**
1. Entity 添加 `age` (String) 和 `sort` (Integer) 字段 + getter/setter
2. Mapper resultMap 添加 `age` 和 `sort` 映射
3. Mapper select SQL 添加 `age`, `sort` 列
4. Mapper insert/update SQL 添加 `age`, `sort` 条件

---

### Task 2: TSetmeal — 补充缺失字段
**文件:**
- `health-reservation/src/main/java/com/health/reservation/domain/TSetmeal.java`
- `health-reservation/src/main/resources/mapper/reservation/TSetmealMapper.xml`

**DB 表 `t_setmeal` 有但 Entity/Mapper 缺少的字段:**
- `status` CHAR(1) — 状态（0正常 1停用）
- `del_flag` CHAR(1) — 删除标志

**修改内容:**
1. Entity 添加 `status` (String) 和 `delFlag` (String) 字段 + getter/setter
2. Mapper resultMap 添加 `status` 和 `del_flag` 映射
3. Mapper select SQL 添加 `status`, `del_flag` 列
4. Mapper insert/update SQL 添加 `status`, `del_flag` 条件

---

### Task 3: TCheckitem — Mapper 缺少大量字段
**文件:**
- `health-reservation/src/main/resources/mapper/reservation/TCheckitemMapper.xml`

**DB 表 `t_checkitem` 有但 Mapper XML 缺少的字段:**
- `check_dept` — 检查科室
- `help_code` — 助记码
- `cate` — 检查类别
- `checkgroup_id` — 关联检查组ID
- `notice` — 注意事项（简版）
- `abstract` — 检查摘要
- `is_addin` — 是否加项
- `price` — 价格（Entity 有但 Mapper 没映射！）
- `sort` — 排序
- `del_flag` — 删除标志
- `unit` — 单位
- `normal_range` — 参考范围

**注意:** Entity TCheckitem 已有这些字段，但 Mapper XML 完全没有映射它们！这是一个严重遗漏。

**修改内容:**
1. Mapper resultMap 补全所有缺失字段映射
2. Mapper select SQL 添加所有缺失列
3. Mapper insert/update SQL 添加所有缺失字段条件

---

### Task 4: TOrdersetting — Entity 使用 Long 但 DB 是 INT
**文件:**
- `health-reservation/src/main/java/com/health/reservation/domain/TOrdersetting.java`
- `health-reservation/src/main/resources/mapper/reservation/TOrdersettingMapper.xml`

**差异:**
- DB `number` INT(11) → Entity `Long number`
- DB `reservations` INT(11) → Entity `Long reservations`

**修改内容:**
1. Entity 将 `number` 和 `reservations` 类型改为 `Integer`
2. Mapper XML 的 insert/update SQL 已够用，不需要改（MyBatis 自动转换）

---

### Task 5: HealthReport — 统一 report_status 注释
**文件:**
- `health-reservation/src/main/java/com/health/reservation/domain/HealthReport.java`

**差异:**
- DB 注释: `report_status` CHAR(1) — "0待生成 1已审核 2已发布"
- Entity 注释: `"0生成中 1已完成 2已归档"`

**修改内容:**
1. Entity 注释改为与 DB 一致: `"0待生成 1已审核 2已发布"`

---

### Task 6: Appointment — 补充 remark 字段映射
**文件:**
- `health-reservation/src/main/resources/mapper/reservation/AppointmentMapper.xml`

**差异:**
- DB `appointment` 表有 `remark` 列
- Mapper XML resultMap 已映射 `remark` ✅
- Entity Appointment 继承 BaseEntity，有 remark ✅

**结论:** 实际已完整映射，无需修改。

---

### Task 7: TReport — 确认 reviewerId 映射
**文件:**
- `health-reservation/src/main/resources/mapper/reservation/TReportMapper.xml`

**验证:**
- Mapper resultMap 已有 `reviewerId` ← `reviewer_id` ✅
- Entity TReport 已有 `reviewerId` ✅
- select SQL 已有 `reviewer_id` ✅

**结论:** 实际已完整映射，无需修改。

---

## 最终需要修改的文件汇总

| 序号 | 文件路径 | 修改内容 |
|---|---|---|
| 1 | `TCheckgroup.java` | 添加 age, sort 字段 |
| 2 | `TCheckgroupMapper.xml` | 添加 age, sort 映射和 SQL |
| 3 | `TSetmeal.java` | 添加 status, delFlag 字段 |
| 4 | `TSetmealMapper.xml` | 添加 status, del_flag 映射和 SQL |
| 5 | `TCheckitemMapper.xml` | **全面补全**缺失的字段映射和 SQL（check_dept, help_code, cate, checkgroup_id, notice, abstract, is_addin, price, sort, del_flag, unit, normal_range） |
| 6 | `TOrdersetting.java` | number/reservations 类型改为 Integer |
