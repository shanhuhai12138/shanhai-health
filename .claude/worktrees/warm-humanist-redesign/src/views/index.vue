<template>
  <div class="home-container">
    <!-- Hero Section -->
    <div class="hero-section">
      <div class="hero-bg"></div>
      <div class="hero-content">
        <div class="hero-left">
          <h1 class="hero-title">
            <span class="title-serif">HealthCare</span>
            <span class="title-sub">智慧医疗管理平台</span>
          </h1>
          <p class="hero-subtitle">集预约管理、体检报告、AI问诊于一体的现代化医疗健康管理系统</p>
          <div class="hero-stats">
            <div class="stat-item" v-for="stat in stats" :key="stat.label">
              <div class="stat-value">{{ stat.value }}</div>
              <div class="stat-label">{{ stat.label }}</div>
            </div>
          </div>
          <div class="hero-actions">
            <el-button type="primary" size="large" @click="goTarget('https://gitee.com/y_project/RuoYi-Vue')" class="hero-btn hero-btn--primary">
              访问码云
            </el-button>
            <el-button size="large" @click="goTarget('http://ruoyi.vip')" class="hero-btn hero-btn--secondary">
              访问主页
            </el-button>
          </div>
        </div>
        <div class="hero-right">
          <div class="tech-stack-card">
            <div class="card-header">
              <span class="header-icon">&#9670;</span>
              <span>技术架构</span>
            </div>
            <div class="tech-grid">
              <div class="tech-col">
                <h4 class="tech-title">后端技术</h4>
                <ul class="tech-list">
                  <li><span class="dot dot-green"></span>SpringBoot</li>
                  <li><span class="dot dot-blue"></span>Spring Security</li>
                  <li><span class="dot dot-purple"></span>JWT</li>
                  <li><span class="dot dot-orange"></span>MyBatis</li>
                  <li><span class="dot dot-red"></span>Druid</li>
                  <li><span class="dot dot-teal"></span>Fastjson</li>
                </ul>
              </div>
              <div class="tech-col">
                <h4 class="tech-title">前端技术</h4>
                <ul class="tech-list">
                  <li><span class="dot dot-blue"></span>Vue 3</li>
                  <li><span class="dot dot-green"></span>Vuex</li>
                  <li><span class="dot dot-red"></span>Element Plus</li>
                  <li><span class="dot dot-orange"></span>Axios</li>
                  <li><span class="dot dot-purple"></span>Sass</li>
                  <li><span class="dot dot-teal"></span>Quill</li>
                </ul>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>

    <!-- Quick Links -->
    <div class="quick-links">
      <div class="section-header">
        <h2 class="section-title">快速入口</h2>
        <div class="section-line"></div>
      </div>
      <div class="link-grid">
        <div
          v-for="link in quickLinks"
          :key="link.title"
          class="link-card"
          @click="link.action"
        >
          <div class="link-icon" :style="{ background: link.color }">
            <component :is="link.iconComponent" />
          </div>
          <div class="link-info">
            <div class="link-title">{{ link.title }}</div>
            <div class="link-desc">{{ link.desc }}</div>
          </div>
          <div class="link-arrow">&#8594;</div>
        </div>
      </div>
    </div>

    <!-- Main Content -->
    <div class="main-content">
      <div class="content-grid">
        <!-- Contact Info -->
        <div class="info-card">
          <div class="card-header">
            <span class="header-icon">&#9670;</span>
            <span>联系信息</span>
          </div>
          <div class="contact-list">
            <div class="contact-item">
              <span class="contact-label">官网</span>
              <a href="http://www.ruoyi.vip" target="_blank" class="contact-value">www.ruoyi.vip</a>
            </div>
            <div class="contact-item">
              <span class="contact-label">QQ群</span>
              <span class="qq-group">127358632</span>
            </div>
            <div class="contact-item">
              <span class="contact-label">微信</span>
              <span class="contact-value">/ *&#26853;&#26368;&#20307;</span>
            </div>
            <div class="contact-item">
              <span class="contact-label">支付宝</span>
              <span class="contact-value">/ *&#26853;&#26368;&#20307;</span>
            </div>
          </div>
        </div>

        <!-- Update Log -->
        <div class="log-card">
          <div class="card-header">
            <span class="header-icon">&#9670;</span>
            <span>更新日志</span>
          </div>
          <div class="log-content">
            <div
              v-for="version in versions"
              :key="version.tag"
              class="log-entry"
              :class="{ 'log-entry--latest': version.latest }"
            >
              <div class="log-header">
                <span class="log-version">{{ version.tag }}</span>
                <span class="log-date">{{ version.date }}</span>
                <el-tag v-if="version.latest" size="small" class="log-badge" type="success">最新</el-tag>
              </div>
              <ul class="log-list">
                <li v-for="(item, i) in version.changes" :key="i">{{ item }}</li>
              </ul>
            </div>
          </div>
        </div>

        <!-- Donation -->
        <div class="donate-card">
          <div class="card-header">
            <span class="header-icon">&#9670;</span>
            <span>捐赠支持</span>
          </div>
          <div class="donate-body">
            <div class="donate-image-wrapper">
              <img src="@/assets/images/pay.png" alt="donate" class="donate-img" />
            </div>
            <p class="donate-text">
              你可以请作者喝杯咖啡表示鼓励
            </p>
            <div class="donate-tags">
              <span class="donate-tag">&#10084; 开源免费</span>
              <span class="donate-tag">&#9889; 持续维护</span>
              <span class="donate-tag">&#9918;&#65039; 社区驱动</span>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup name="Index">
import { ref, computed } from 'vue'
import {
  User, Calendar, Document, ChatDotRound
} from '@element-plus/icons-vue'

function goTarget(url) {
  window.open(url, '_blank')
}

const stats = ref([
  { value: '10+', label: '功能模块' },
  { value: '50+', label: '数据表' },
  { value: '100+', label: 'API 接口' },
  { value: '∞', label: '可能' },
])

const quickLinks = computed(() => [
  { title: '系统管理', desc: '用户 / 角色 / 菜单管理', iconComponent: 'User', color: '#2B6B7A', action: () => {} },
  { title: '预约管理', desc: '检查组 / 项 / 套餐管理', iconComponent: 'Calendar', color: '#E8956A', action: () => {} },
  { title: '体检报告', desc: '报告录入 / 审核 / 归档', iconComponent: 'Document', color: '#5B8C72', action: () => {} },
  { title: 'AI 对话', desc: '智能医疗问答助手', iconComponent: 'ChatDotRound', color: '#7B6BA5', action: () => {} },
])

const versions = ref([
  {
    tag: 'v3.9.3', date: '2026-07-02', latest: true,
    changes: [
      '新增体检报告模块，含报告列表/审核/发布/归档全流程',
      '新增 Excel 导入体检报告功能，支持模板下载批量录入',
      '新增 AI 智能对话模块，集成大模型问答能力',
      '新增预约设置 Excel 导入功能',
      '预约模块重构为独立子模块 health-reservation',
    ]
  },
  {
    tag: 'v3.9.2', date: '2026-03-26', latest: false,
    changes: [
      '新增锁定屏幕功能',
      '首页新增通知公告消息提醒',
      '使用 SpringDoc 代替 Swagger',
      '升级 spring-boot 到最新版本 4.0.3',
    ]
  },
  {
    tag: 'v3.9.1', date: '2025-12-18', latest: false,
    changes: [
      '支持防盗链功能',
      '菜单导航设置支持纯顶部',
      '支持 Excel 导出对象的多个子列表',
      '升级 spring-security 到 5.7.14',
    ]
  },
  {
    tag: 'v3.9.0', date: '2025-05-28', latest: false,
    changes: [
      '导航栏显示昵称 & 设置',
      'Excel 导入导出支持多图片',
      '支持富文本复制粘贴图片上传',
      '升级 element-plus 到最新版本 2.15.6',
    ]
  },
  {
    tag: 'v3.8.9', date: '2024-12-30', latest: false,
    changes: [
      '用户管理支持分栏拖动',
      '操作日志记录 DELETE 请求参数',
      '菜单面包屑导航支持多层级显示',
      '升级 spring-boot 到最新版本 5.3.39',
    ]
  },
])
</script>

<style lang="scss" scoped>
/* ===== Design Tokens ===== */
.home-container {
  --color-ground: #FDF8F0;
  --color-surface: #FFFFFF;
  --color-ink: #2C2825;
  --color-secondary: #8A8279;
  --color-hairline: #E0D5C4;

  --color-accent: #2B6B7A;
  --color-accent-light: rgba(43, 107, 122, 0.08);
  --color-accent-hover: rgba(43, 107, 122, 0.15);

  --color-coral: #E8956A;
  --color-coral-light: rgba(232, 149, 106, 0.08);

  --color-success: #7CB68E;
  --color-warning: #D4756A;

  --font-display: 'Georgia', 'Noto Serif SC', 'Songti SC', serif;
  --font-body: -apple-system, BlinkMacSystemFont, 'Segoe UI', 'PingFang SC', 'Hiragino Sans GB', 'Microsoft YaHei', sans-serif;
  --font-mono: 'JetBrains Mono', 'Courier New', monospace;

  --shadow-card: 0 2px 12px rgba(44, 40, 37, 0.04);
  --shadow-card-hover: 0 8px 32px rgba(44, 40, 37, 0.08);
  --shadow-elevated: 0 12px 40px rgba(44, 40, 37, 0.12);

  --radius-sm: 8px;
  --radius-md: 12px;
  --radius-lg: 16px;

  padding: 0;
  background: var(--color-ground);
  min-height: calc(100vh - 84px);
  font-family: var(--font-body);
  color: var(--color-ink);
}

/* ===== Hero Section ===== */
.hero-section {
  position: relative;
  background: linear-gradient(135deg, #2B6B7A 0%, #1A4A58 60%, #0F3440 100%);
  border-radius: 0;
  margin: 24px 24px 0 24px;
  padding: 56px 48px 48px;
  overflow: hidden;
  box-shadow: var(--shadow-elevated);

  .hero-bg {
    position: absolute;
    inset: 0;
    background:
      radial-gradient(ellipse at 30% 60%, rgba(232, 149, 106, 0.12) 0%, transparent 60%),
      radial-gradient(ellipse at 80% 30%, rgba(124, 182, 142, 0.1) 0%, transparent 50%),
      radial-gradient(ellipse at 50% 90%, rgba(43, 107, 122, 0.2) 0%, transparent 50%);
    pointer-events: none;
  }

  .hero-content {
    position: relative;
    z-index: 1;
    display: flex;
    align-items: flex-start;
    gap: 48px;
  }

  .hero-left {
    flex: 1;

    .hero-title {
      font-family: var(--font-display);
      font-size: 40px;
      font-weight: 400;
      margin: 0 0 16px 0;
      letter-spacing: 0.5px;
      line-height: 1.2;

      .title-serif {
        color: #FFFFFF;
        font-style: italic;
      }

      .title-sub {
        color: rgba(255, 255, 255, 0.75);
        font-size: 22px;
        font-weight: 400;
        display: block;
        margin-top: 4px;
        font-family: var(--font-body);
        letter-spacing: 2px;
      }
    }

    .hero-subtitle {
      font-size: 15px;
      color: rgba(255, 255, 255, 0.6);
      margin: 0 0 36px 0;
      line-height: 1.7;
      max-width: 480px;
    }

    .hero-stats {
      display: flex;
      gap: 40px;
      margin-bottom: 36px;

      .stat-item {
        text-align: left;

        .stat-value {
          font-family: var(--font-display);
          font-size: 32px;
          font-weight: 400;
          font-style: italic;
          color: #FFFFFF;
          line-height: 1.2;
        }

        .stat-label {
          font-size: 13px;
          color: rgba(255, 255, 255, 0.45);
          margin-top: 6px;
          letter-spacing: 0.5px;
        }
      }
    }

    .hero-actions {
      display: flex;
      gap: 12px;

      .hero-btn {
        height: 44px;
        padding: 0 28px;
        font-size: 14px;
        font-weight: 500;
        border-radius: 0;
        letter-spacing: 0.5px;
        transition: all 0.3s ease;
        font-family: var(--font-body);

        &--primary {
          background: #FFFFFF;
          border: 1px solid rgba(255, 255, 255, 0.3);
          color: var(--color-accent);

          &:hover {
            background: rgba(255, 255, 255, 0.9);
            box-shadow: 0 4px 16px rgba(0, 0, 0, 0.15);
          }
        }

        &--secondary {
          background: transparent;
          border: 1px solid rgba(255, 255, 255, 0.25);
          color: rgba(255, 255, 255, 0.8);

          &:hover {
            border-color: rgba(255, 255, 255, 0.5);
            color: #FFFFFF;
          }
        }
      }
    }
  }

  .hero-right {
    flex-shrink: 0;
    width: 380px;
  }
}

/* ===== Tech Stack Card ===== */
.tech-stack-card {
  background: rgba(255, 255, 255, 0.06);
  border: 1px solid rgba(255, 255, 255, 0.1);
  padding: 24px;

  .card-header {
    display: flex;
    align-items: center;
    gap: 10px;
    font-size: 14px;
    font-weight: 600;
    color: rgba(255, 255, 255, 0.85);
    margin-bottom: 20px;
    letter-spacing: 0.5px;

    .header-icon {
      font-size: 10px;
      color: var(--color-coral);
    }
  }

  .tech-grid {
    display: grid;
    grid-template-columns: 1fr 1fr;
    gap: 24px;
  }

  .tech-col {
    .tech-title {
      font-size: 12px;
      font-weight: 600;
      color: rgba(255, 255, 255, 0.5);
      margin: 0 0 12px 0;
      letter-spacing: 1px;
      text-transform: uppercase;
    }

    .tech-list {
      list-style: none;
      padding: 0;
      margin: 0;

      li {
        display: flex;
        align-items: center;
        gap: 10px;
        padding: 6px 0;
        font-size: 14px;
        color: rgba(255, 255, 255, 0.7);
        font-family: var(--font-mono);

        .dot {
          width: 8px;
          height: 8px;
          flex-shrink: 0;

          &.dot-green { background: var(--color-success); }
          &.dot-blue { background: #6BA3BE; }
          &.dot-purple { background: #A78BFA; }
          &.dot-orange { background: var(--color-coral); }
          &.dot-red { background: var(--color-warning); }
          &.dot-teal { background: #6BC4B8; }
        }
      }
    }
  }
}

/* ===== Quick Links ===== */
.quick-links {
  padding: 40px 24px 0;

  .section-header {
    text-align: center;
    margin-bottom: 32px;

    .section-title {
      font-family: var(--font-display);
      font-size: 24px;
      font-weight: 400;
      font-style: italic;
      color: var(--color-ink);
      margin: 0;
    }

    .section-line {
      width: 48px;
      height: 1px;
      background: var(--color-hairline);
      margin: 12px auto 0;
    }
  }

  .link-grid {
    display: grid;
    grid-template-columns: repeat(4, 1fr);
    gap: 16px;
  }

  .link-card {
    display: flex;
    align-items: center;
    gap: 14px;
    padding: 20px;
    cursor: pointer;
    transition: all 0.3s ease;
    background: var(--color-surface);
    border: 1px solid var(--color-hairline);

    &:hover {
      box-shadow: var(--shadow-card-hover);
      transform: translateY(-2px);
      border-color: transparent;

      .link-arrow {
        transform: translateX(4px);
        opacity: 1;
      }
    }

    .link-icon {
      width: 40px;
      height: 40px;
      display: flex;
      align-items: center;
      justify-content: center;
      color: #FFFFFF;
      flex-shrink: 0;
      font-size: 20px;
      opacity: 0.9;
    }

    .link-info {
      flex: 1;

      .link-title {
        font-size: 15px;
        font-weight: 600;
        color: var(--color-ink);
        margin-bottom: 2px;
      }

      .link-desc {
        font-size: 12px;
        color: var(--color-secondary);
      }
    }

    .link-arrow {
      font-size: 18px;
      color: var(--color-hairline);
      transition: all 0.3s ease;
      opacity: 0.6;
    }
  }
}

/* ===== Main Content ===== */
.main-content {
  padding: 32px 24px 24px;
}

.content-grid {
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  gap: 20px;
}

.info-card,
.log-card,
.donate-card {
  background: var(--color-surface);
  border: 1px solid var(--color-hairline);
  box-shadow: var(--shadow-card);
  transition: all 0.3s ease;

  &:hover {
    box-shadow: var(--shadow-card-hover);
  }

  .card-header {
    display: flex;
    align-items: center;
    gap: 10px;
    padding: 18px 20px;
    border-bottom: 1px solid var(--color-hairline);
    font-size: 14px;
    font-weight: 600;
    color: var(--color-ink);
    letter-spacing: 0.5px;

    .header-icon {
      font-size: 10px;
      color: var(--color-accent);
    }
  }
}

/* Contact List */
.contact-list {
  padding: 4px 20px 20px;

  .contact-item {
    display: flex;
    align-items: center;
    justify-content: space-between;
    padding: 14px 0;
    border-bottom: 1px solid rgba(224, 213, 196, 0.4);
    font-size: 14px;

    &:last-child {
      border-bottom: none;
    }

    .contact-label {
      color: var(--color-secondary);
      flex-shrink: 0;
      width: 50px;
    }

    .contact-value {
      color: var(--color-accent);
      text-decoration: none;
      font-weight: 500;

      &:hover {
        text-decoration: underline;
      }
    }

    .qq-group {
      font-family: var(--font-mono);
      font-size: 13px;
      color: var(--color-ink);
      background: var(--color-accent-light);
      padding: 4px 12px;
      border-radius: var(--radius-sm);
    }
  }
}

/* Log Card */
.log-content {
  padding: 4px 20px 20px;
  max-height: 520px;
  overflow-y: auto;

  .log-entry {
    padding: 16px 0;
    border-bottom: 1px solid rgba(224, 213, 196, 0.3);

    &:last-child {
      border-bottom: none;
    }

    &--latest {
      .log-version {
        color: var(--color-accent);
        font-weight: 700;
      }
    }

    .log-header {
      display: flex;
      align-items: center;
      gap: 8px;
      margin-bottom: 8px;

      .log-version {
        font-family: var(--font-display);
        font-size: 15px;
        font-weight: 600;
        color: var(--color-ink);
      }

      .log-date {
        font-size: 12px;
        color: var(--color-secondary);
      }

      .log-badge {
        margin-left: auto;
      }
    }

    .log-list {
      list-style: none;
      padding: 0;
      margin: 0;

      li {
        position: relative;
        padding: 3px 0 3px 14px;
        font-size: 13px;
        color: var(--color-secondary);
        line-height: 1.7;

        &::before {
          content: '\00B7';
          position: absolute;
          left: 0;
          color: var(--color-accent);
          font-size: 16px;
          line-height: 1.5;
        }
      }
    }
  }
}

/* Donate Card */
.donate-body {
  text-align: center;
  padding: 20px;

  .donate-image-wrapper {
    border-radius: var(--radius-sm);
    overflow: hidden;
    margin-bottom: 16px;
    border: 1px solid var(--color-hairline);

    .donate-img {
      width: 100%;
      display: block;
      transition: transform 0.3s ease;

      &:hover {
        transform: scale(1.02);
      }
    }
  }

  .donate-text {
    font-family: var(--font-display);
    font-size: 15px;
    font-style: italic;
    color: var(--color-ink);
    margin: 0 0 16px;
  }

  .donate-tags {
    display: flex;
    justify-content: center;
    gap: 8px;
    flex-wrap: wrap;

    .donate-tag {
      font-size: 12px;
      color: var(--color-secondary);
      background: var(--color-ground);
      padding: 4px 12px;
      border-radius: 100px;
    }
  }
}

/* ===== Scrollbar ===== */
.log-content::-webkit-scrollbar {
  width: 4px;
}

.log-content::-webkit-scrollbar-thumb {
  background: var(--color-hairline);
  border-radius: 2px;
}

/* ===== Responsive ===== */
@media (max-width: 1200px) {
  .home-container {
    .hero-section {
      .hero-content {
        flex-direction: column;
      }

      .hero-right {
        width: 100% !important;
      }
    }

    .quick-links {
      .link-grid {
        grid-template-columns: repeat(2, 1fr);
      }
    }

    .content-grid {
      grid-template-columns: 1fr;
    }
  }
}

@media (max-width: 768px) {
  .home-container {
    .hero-section {
      margin: 12px;
      padding: 32px 20px 24px;

      .hero-title {
        font-size: 28px;

        .title-sub {
          font-size: 16px;
        }
      }

      .hero-stats {
        gap: 20px;

        .stat-value {
          font-size: 24px;
        }
      }

      .hero-actions {
        flex-direction: column;

        .hero-btn {
          width: 100%;
        }
      }
    }

    .quick-links {
      padding: 24px 12px 0;

      .link-grid {
        grid-template-columns: 1fr;
      }
    }

    .main-content {
      padding: 16px 12px;
    }
  }
}
</style>
