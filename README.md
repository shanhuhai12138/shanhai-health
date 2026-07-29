# 🏥 ShanHai Health（山海健康）

> **一个基于若依框架（RuoYi）二次开发的医疗健康管理系统练手项目**

![Java](https://img.shields.io/badge/Java-21-blue) ![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.5.14-brightgreen) ![Vue 3](https://img.shields.io/badge/Vue-3.5.26-42b883) ![Element Plus](https://img.shields.io/badge/Element_Plus-2.13.1-blue) ![MySQL](https://img.shields.io/badge/MySQL-8+-white) ![MIT License](https://img.shields.io/badge/license-MIT-yellow)

---

## 📖 项目简介

**ShanHai Health** 是一个面向医疗健康场景的管理系统，旨在为用户提供预约管理、心理测评、情绪追踪、健康报告等一站式服务。本项目基于开源的 **若依框架（RuoYi-Vue）** 二次开发，结合了若依成熟的架构与医疗健康业务场景的需求，是一个个人练手项目。

> ⚠️ **说明**：本项目为依靠若依框架的个人练手项目，代码质量、功能完整度和稳定性仅供参考学习，不建议直接用于生产环境。部分功能仍在完善中。

---

## 🚀 核心功能

### ✅ 已实现功能

| 模块 | 功能描述 |
|------|---------|
| **系统管理** | 用户、角色、菜单、字典、部门等基础管理 |
| **预约管理** | 套餐/检查项/检查组管理、预约创建与取消 |
| **排班管理** | 咨询师排班设置（表格 + 日历双视图）|
| **心理测评** | PHQ-9、GAD-7、SAS 量表题目与结果管理 |
| **情绪追踪** | 情绪记录与趋势图表可视化 |
| **健康报告** | 报告查看、AI 生成健康分析报告 |
| **AI 对话** | 基于 LangChain4j 的智能问答（支持 DashScope/DeepSeek/OpenAI）|
| **定时提醒** | Quartz 定时任务，预约前发送通知 |
| **数据统计** | 今日预约数、本月体检人次、活跃咨询师等统计 |

### ⚠️ 待完善功能

| 模块 | 当前状态 | 计划 |
|------|---------|------|
| **AI 健康报告生成** | 仅展示 AI 回复，缺少完整四段式分析结构 | 需完善 AI 提示词和报告模板 |
| **预约提醒功能** | ReminderJob 已创建但未配置 Cron（内存模式）| 需配置数据库 Quartz 任务表 |
| **移动端适配** | 基础响应式，体验一般 | 进一步优化移动端布局 |
| **测试覆盖** | 缺少单元测试 | 计划补充核心 Service 层测试 |
| **文档完善** | README 初版 | 完善 API 文档、数据库说明 |

---

## 🛠️ 技术栈

### 后端
- **JDK 21** + **Spring Boot 3.5.14**
- **Spring Security** + **JWT** 认证授权
- **MyBatis Plus** + **PageHelper** 分页
- **Druid** 数据库连接池 + **Redis** 缓存
- **Quartz** 定时任务（内存模式）
- **LangChain4j** AI 对话框架
- **Fastjson2** JSON 处理

### 前端
- **Vue 3.5.26** + **Vite 6**
- **Element Plus 2.13.1** UI 组件库
- **Pinia** 状态管理
- **Vue Router 4.6.4** 路由
- **Axios** 请求拦截
- **ECharts** 图表可视化

### 数据库
- **MySQL 8+** 关系型数据库
- **Redis 7+** 缓存服务（基础配置）

---

## 📁 项目结构

```
health/
├── health-admin/          # Spring Boot 启动入口 + 系统管理模块
├ ├── health-framework     # 核心框架（安全/配置/AOP/拦截器）
├ ├── health-system        # 系统模块（用户/角色/菜单/字典）
├ ├── health-reservation   # 核心业务（预约/测评/情绪/报告/排班）
├ ├── health-ai            # AI 对话模块（LangChain4j 集成）
├ ├── health-common        # 通用工具类
├ ├── health-quartz        # 定时任务模块
├ ├── health-generator     # 代码生成器
├ ├── health-Vue3          # 前端 Vue3 项目
└── sql/                   # 数据库初始化脚本
```

---

## 🚀 快速启动

### 前置条件

- JDK 21+
- MySQL 8+
- Redis 7+（可选）
- Node.js 18+
- Maven 3.8+

### 1. 初始化数据库

```bash
# 创建数据库
mysql -u root -p -e "CREATE DATABASE health DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;"

# 登录并执行 SQL 脚本
mysql -u root -p health < sql/system.sql
mysql -u root -p health < sql/reservation.sql
mysql -u root -p health < sql/new_tables.sql
mysql -u root -p health < sql/ai.sql
mysql -u root -p health < sql/seed_data.sql
mysql -u root -p health < sql/menu.sql
```

> **默认账号**：`admin` / `admin123`

### 2. 启动后端

```bash
cd health
mvn clean install -DskipTests
# 或直接在 IDEA 中运行 health-admin 模块的 ShanHaiApplication.java
```

后端默认端口：**8090**

### 3. 启动前端

```bash
cd health-Vue3
npm install
npm run dev
```

前端默认端口：**80**

浏览器访问：`http://localhost`

---

## 🎨 项目特色

- ✅ **双视图排班管理**：表格 + 日历（el-calendar）双视图，直观查看每日可用时段
- ✅ **AI 健康助手**：集成通义千问（DashScope），支持流式输出和 API Key 自定义
- ✅ **定时提醒机制**：预约提醒功能框架已搭建，支持 Quartz 任务扩展
- ✅ **现代化 UI 美化**：卡片阴影、按钮悬停、侧边栏动效等增强体验
- ✅ **医疗主题色**：采用青绿色（#00b8a0）为主色调，符合医疗健康场景氛围

---

## ⚠️ 使用说明与注意事项

1. **这是一个练手项目**：代码质量和功能完整性未经过生产环境验证，仅适合学习参考。

2. **Quartz 配置**：当前使用内存模式，如需持久化请配置 `spring.quartz.job-store-type: jdbc` 并创建 Quartz 表。

3. **AI 功能**：需在 `application.yml` 中配置 AI API Key（第 152 行左右），或使用环境变量 `AI_API_KEY` 注入。
   > TODO: 在 `health-admin/src/main/resources/application.yml` 的 `ai.model.api-key` 处填入你的 AI Key（支持 DashScope/OpenAI/DeepSeek）

4. **端口冲突**：默认后端 8090、前端 80，如被占用请修改配置。

5. **数据库配置**：数据库密码应通过环境变量 `DB_PASSWORD` 设置，不要硬编码在配置文件中。
   > TODO: 修改 `health-admin/src/main/resources/application-druid.yml` 中的数据库密码，建议使用强密码并通过环境变量注入

6. **默认账号**：`admin` / `admin123`（生产环境请修改默认密码）

---

## 📊 项目对比（若依原版 vs ShanHai Health）

| 特性 | 若依原版 (RuoYi-Vue) | ShanHai Health 改进版 |
|------|---------------------|----------------------|
| 项目命名 | RuoYi | ShanHai Health |
| 业务模块 | 通用 OA/CRM | 医疗健康业务（预约/测评/情绪/AI报告）|
| 颜色主题 | 默认蓝 | 医疗青绿色主调 |
| 额外模块 | - | AI 对话、情绪追踪、健康报告 |
| 日历视图 | 无 | SchedulePage 新增日历双视图 |
| 定时任务 | 基础 Job | 新增 ReminderJob 框架 |
| 包名冲突 | - | health-ai 已修复包名冲突 |
| SQL 完整性 | 部分问题 | 已修复 INSERT IGNORE / USE health |

---

## 🤝 致谢

本项目基于开源框架 **[RuoYi-Vue](https://gitee.com/y_project/RuoYi-Vue)** 二次开发，感谢 RuoYi 团队提供的优秀基础架构。

---

## 📄 许可证

本项目采用 **MIT License**，详见 [LICENSE](LICENSE) 文件。

---

**最后更新：2026-07-29** | **作者：shanhuhai12138**
