<p align="center">
  <h1 align="center">🏥 山海健康 (ShanHai Health)</h1>
  <p align="center">AI 驱动的心理健康管理平台 — Spring Boot 3 + Vue 3 + LangChain4j</p>
</p>

[![Java](https://img.shields.io/badge/Java-21-blue)]()
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.14-brightgreen)]()
[![Vue](https://img.shields.io/badge/Vue-3.5-42b883)]()
[![License](https://img.shields.io/badge/License-MIT-yellow)]()

---

## ✨ 项目亮点

- **AI 健康报告** — 基于 LangChain4j + 通义千问，自动生成四段式健康分析和改善建议
- **智能推荐引擎** — 根据心理测评结果动态匹配体检套餐/咨询师服务
- **C端+B端双门户** — 管理后台 + 患者端完整页面
- **用户自选 AI 模型** — 支持通义千问 / OpenAI / DeepSeek，可自定义 API Key
- **流式对话** — SSE 打字机效果实时输出

## 📋 技术栈

| 层 | 技术 |
|---|------|
| 后端 | Java 21, Spring Boot 3.5.14, Spring Security, MyBatis |
| 前端 | Vue 3, Vite 6, Element Plus, ECharts, Pinia |
| AI | LangChain4j 0.36.2, 通义千问 / OpenAI / DeepSeek |
| 数据库 | MySQL 8+, Redis |
| 工具 | Maven, Docker (可部署), 阿里云 DashScope |

## 🚀 快速启动

### 前置条件

- JDK 21+
- MySQL 8+
- Redis 7+
- Node.js 18+

### 1. 初始化数据库

```bash
# 创建数据库
mysql -u root -p -e "CREATE DATABASE health DEFAULT CHARACTER SET utf8mb4;"

# 按顺序执行 SQL
mysql -u root -p health < sql/system.sql
mysql -u root -p health < sql/reservation.sql
mysql -u root -p health < sql/new_tables.sql
mysql -u root -p health < sql/ai.sql
mysql -u root -p health < sql/quartz.sql
mysql -u root -p health < sql/seed_data.sql
mysql -u root -p health < sql/menu.sql
mysql -u root -p health < sql/ai_menu.sql
```

### 2. 启动后端

```bash
cd health
mvn clean package -DskipTests
java -jar health-admin/target/health-admin.jar
# 或直接在 IDEA 中运行 ShanHaiApplication
```

> 如需自定义数据库密码：`java -DDB_PASSWORD=yourpwd -jar ...`

### 3. 启动前端

```bash
cd health-Vue3
npm install
npm run dev
```

浏览器访问 `http://localhost`，默认账号 `admin` / `admin123`

### 4. AI 对话（可选）

在 `application.yml` 中配置：
```yaml
ai:
  model:
    provider: dashscope       # 或 openai / deepseek
    api-key: ${AI_API_KEY:}   # 通过环境变量注入
```

或在 AI 聊天页面直接填写 API Key（仅保存在浏览器本地）。

## 📁 项目结构

```
health/
├── health-admin/           # 启动入口 + 系统管理
├── health-framework/       # 核心框架（安全/配置/AOP）
├── health-system/          # 系统模块（用户/角色/菜单）
├── health-reservation/     # 预约/测评/情绪/报告/推荐模块 ⭐
├── health-ai/              # AI 对话模块 ⭐
├── health-common/          # 公共工具类
├── health-quartz/          # 定时任务
├── health-generator/       # 代码生成器
├── health-Vue3/            # 前端项目 ⭐
└── sql/                    # 数据库初始化脚本
```

## 🖥️ 功能模块

### 管理后台
- 系统管理：用户/角色/菜单/字典
- 预约管理：套餐/检查组/检查项/预约设置
- 心理测评：PHQ-9/GAD-7/SAS 量表管理
- 情绪追踪：记录/趋势图表
- 咨询服务：咨询师/排班/预约
- 健康报告：生成/AI分析/编辑/删除
- AI 对话：会话管理/流式聊天
- 数据看板：统计概览/图表分析

### 患者端
- 首页：统计概览/快捷入口
- 预约套餐：浏览/下单
- 心理测评：答题/结果查看
- 情绪记录：评分/趋势图
- 健康报告：AI 分析/建议
- 推荐中心：智能匹配
- 消息通知：已读/未读
- 个人中心

## 📄 许可证

本项目基于 [MIT License](LICENSE)，基于 [RuoYi-Vue](https://gitee.com/y_project/RuoYi-Vue) 二次开发。

## 👤 作者

- **shanhuhai12138**
- GitHub: [@shanhuhai12138](https://github.com/shanhuhai12138)


---

## 📝 项目完善计划

本项目已创建完整的《项目完善计划书》，包含 6 个阶段、27 个子任务的详细规划，详见：项目完善计划书.md

该计划涵盖：SQL修复、安全加固、功能完善、测试覆盖、文档完善、CI/CD 部署等全流程，可用于指导项目迭代和开源发布。