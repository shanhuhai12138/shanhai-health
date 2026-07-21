# 项目纠察方案 — MindCare AI 心理健康管理平台

> 生成时间：2026-07-17
> 上次更新：2026-07-18 (全面审计)
> 分支：codex/0714
> 技术栈：Spring Boot 3.5.14 / Java 21 / Vue 3 + Element Plus / MyBatis / MySQL / Redis

---

## 一、项目架构总览

`
health (根项目)
├── health-admin          ← Spring Boot 入口 (端口 8090)
├── health-framework      ← 安全/数据源/配置/拦截器
├── health-system         ← 系统管理 (用户/角色/菜单/字典/部门)
├── health-quartz         ← 定时任务 (Quartz)
├── health-generator      ← 代码生成器 (Velocity)
├── health-common         ← 通用工具/注解/常量/异常
├── health-reservation    ← 核心业务 (预约/测评/情绪/咨询/报告/通知)
├── health-ai             ← AI 对话 (LangChain4j + DashScope)
└── health-Vue3           ← 前端 (端口 80, 代理到 8090)
`

**模块统计：**
- 数据库表：51 张（系统 20 + Quartz 11 + 业务 20）
- Java 实体类：39 个（含 TReportImage）
- Mapper 接口：39 个（含 TReportImageMapper）
- Service 接口：38 个（含 ITReportImageService）
- REST Controller：23 个（后端 15 + admin 8）
- 前端 API 模块：34 个
- 前端视图页面：70 个
- 菜单项：130+ 个（系统管理 117 + 业务 13 + AI 6）
