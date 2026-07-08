<template>
  <div class="page-container">
    <!-- Page Header -->
    <div class="page-header">
      <div class="header-icon">
        <el-icon :size="22"><Calendar /></el-icon>
      </div>
      <div class="header-text">
        <h1 class="header-title">预约设置</h1>
        <p class="header-sub">管理每日预约名额，设置可预约人数与已预约人数</p>
      </div>
    </div>

    <!-- Upload Card -->
    <el-card class="upload-card" shadow="never">
      <div class="upload-bar">
        <el-button class="upload-btn" @click="handleDownloadTemplate">
          <el-icon><Download /></el-icon> 模板下载
        </el-button>
        <el-upload
          class="upload-trigger"
          :show-file-list="false"
          :http-request="customUpload"
          :before-upload="beforeUpload"
        >
          <el-button class="upload-btn upload-btn--upload">
            <el-icon><Upload /></el-icon> 上传文件
          </el-button>
        </el-upload>
        <span class="upload-hint">请先下载模板，录入数据后上传</span>
      </div>
    </el-card>

    <!-- Calendar Card -->
    <el-card class="calendar-card" shadow="never">
      <div class="calendar-nav">
        <div class="nav-left">
          <el-button link class="nav-btn" @click="goCurrentMonth">今天</el-button>
          <el-button link class="nav-btn nav-btn--icon" @click="pickPre">&#8249;</el-button>
          <el-button link class="nav-btn nav-btn--icon" @click="pickNext">&#8250;</el-button>
        </div>
        <div class="nav-center">
          <span class="nav-year">{{ currentYear }}年</span>
          <span class="nav-month">{{ currentMonth }}月</span>
        </div>
      </div>

      <div class="calendar-body">
        <ul class="weekdays">
          <li v-for="day in weekDays" :key="day">{{ day }}</li>
        </ul>
        <ul class="days">
          <li v-for="(dayobject, index) in days" :key="index">
            <div class="other-month" v-if="dayobject.day.getMonth() + 1 !== currentMonth">
              {{ dayobject.day.getDate() }}
            </div>
            <div class="everyday" v-else>
              <span class="datenumber">{{ dayobject.day.getDate() }}</span>
              <template v-for="(obj, idx) in leftobj" :key="idx">
                <div v-if="obj.date === dayobject.day.getDate()" :class="obj.number > obj.reservations ? 'slot-usual' : 'slot-fulled'">
                  <p>可预约{{ obj.number }}人</p>
                  <p>已预约{{ obj.reservations }}人</p>
                  <p v-if="obj.number <= obj.reservations" class="slot-full">已满</p>
                </div>
              </template>
              <button v-if="dayobject.day > today" @click="handleOrderSet(dayobject.day)" class="orderbtn">设置</button>
            </div>
          </li>
        </ul>
      </div>
    </el-card>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import { Calendar, Download, Upload } from '@element-plus/icons-vue'
import {
  downloadTemplate, getOrderSettingByMonth, editNumberByOrderDate, addOrderSetting, uploadOrderSettingExcel
} from '@/api/reservation/ordersetting'

const today = ref(new Date())
const currentYear = ref(0)
const currentMonth = ref(0)
const days = ref([])
const leftobj = ref([])
const weekDays = ['一', '二', '三', '四', '五', '六', '日']

const formatDate = (year, month, day) => {
  const y = year
  const m = String(month).padStart(2, '0')
  const d = String(day).padStart(2, '0')
  return `${y}-${m}-${d}`
}

const initData = async (dateStr = null) => {
  const now = new Date()
  const targetDate = dateStr ? new Date(dateStr) : now
  const year = targetDate.getFullYear()
  const month = targetDate.getMonth() + 1
  currentYear.value = year
  currentMonth.value = month

  const firstDay = new Date(year, month - 1, 1)
  let firstDayOfWeek = firstDay.getDay()
  if (firstDayOfWeek === 0) firstDayOfWeek = 7

  const calendarDays = []
  const totalDays = 42
  const prevMonthLastDate = new Date(year, month - 1, 0).getDate()
  for (let i = firstDayOfWeek - 1; i > 0; i--) {
    const d = new Date(year, month - 2, prevMonthLastDate - i + 1)
    calendarDays.push({ day: d })
  }
  const currentMonthLastDate = new Date(year, month, 0).getDate()
  for (let i = 1; i <= currentMonthLastDate; i++) {
    const d = new Date(year, month - 1, i)
    calendarDays.push({ day: d })
  }
  let nextDay = 1
  while (calendarDays.length < totalDays) {
    const d = new Date(year, month, nextDay++)
    calendarDays.push({ day: d })
  }
  days.value = calendarDays

  try {
    const res = await getOrderSettingByMonth(`${year}-${String(month).padStart(2, '0')}`)
    if (res.code === 200) {
      const rawData = Array.isArray(res.data) ? res.data : []
      const processedData = rawData.map(item => ({
        id: item.id,
        date: new Date(item.orderDate).getDate(),
        number: item.number,
        reservations: item.reservations !== null ? item.reservations : 0
      }))
      leftobj.value = processedData
    } else {
      ElMessage.warning(res.msg || '暂无预约设置数据')
    }
  } catch (error) {
    console.error('加载预约数据失败:', error)
    ElMessage.error('加载预约数据失败，请稍后重试')
  }
}

const handleDownloadTemplate = async () => {
  try {
    const blob = await downloadTemplate()
    const url = window.URL.createObjectURL(blob)
    const a = document.createElement('a')
    a.href = url; a.download = 'ordersetting_template.xlsx'
    document.body.appendChild(a); a.click(); document.body.removeChild(a)
    window.URL.revokeObjectURL(url)
  } catch (error) {
    console.error('模板下载失败:', error)
    ElMessage.error('模板下载失败，请稍后重试')
  }
}

const customUpload = async (options) => {
  const { file } = options
  try {
    const res = await uploadOrderSettingExcel(file)
    handleSuccess(res)
  } catch (error) {
    console.error('上传失败:', error)
    ElMessage.error(error.msg || error.message || '上传失败，请稍后重试')
  }
}

const beforeUpload = (file) => {
  const validTypes = ['application/vnd.ms-excel', 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet']
  const isValid = validTypes.includes(file.type)
  if (!isValid) ElMessage.error('仅支持 .xls 或 .xlsx 格式文件！')
  return isValid
}

const handleSuccess = (response) => {
  if (response.code === 200) {
    ElMessage.success(response.msg || '上传成功')
    const year = currentYear.value
    const month = String(currentMonth.value).padStart(2, '0')
    initData(`${year}-${month}`)
  } else {
    ElMessage.error(response.msg || '上传失败')
  }
}

const handleOrderSet = (day) => {
  const clickDate = day.getDate()
  const clickDateStr = formatDate(day.getFullYear(), day.getMonth() + 1, clickDate)
  const existing = leftobj.value.find(item => item.date === clickDate)

  ElMessageBox.prompt(
    `请输入可预约人数${existing ? '（当前值: ' + existing.number + '）' : ''}`,
    '预约设置',
    {
      confirmButtonText: '确定', cancelButtonText: '取消',
      inputPattern: /^[1-9]\d*$/, inputErrorMessage: '请输入大于0的整数',
      inputValue: existing ? String(existing.number) : ''
    }
  ).then(async ({ value }) => {
    const number = Number(value)
    try {
      let res
      if (existing) {
        res = await editNumberByOrderDate({ id: existing.id, number, orderDate: clickDateStr })
      } else {
        res = await addOrderSetting({ number, orderDate: clickDateStr })
      }
      if (res.code === 200) {
        ElMessage.success(existing ? '修改成功' : '新增成功')
        if (existing) {
          existing.number = number
        } else {
          const newId = res.data?.id || existing?.id
          leftobj.value.push({ date: clickDate, number, reservations: 0, id: newId || null })
        }
      } else {
        ElMessage.error(res.msg || (existing ? '修改失败' : '新增失败'))
      }
    } catch (error) {
      console.error('操作失败:', error)
      ElMessage.error('操作失败，请稍后重试')
    }
  }).catch(() => { ElMessage.info('已取消') })
}

const goCurrentMonth = () => { initData() }
const pickPre = () => {
  const prev = new Date(currentYear.value, currentMonth.value - 1, 0)
  initData(formatDate(prev.getFullYear(), prev.getMonth() + 1, 1))
}
const pickNext = () => {
  const next = new Date(currentYear.value, currentMonth.value, 1)
  initData(formatDate(next.getFullYear(), next.getMonth() + 1, 1))
}

onMounted(() => { initData() })
</script>

<style lang="scss" scoped>
.page-container {
  --color-ground: #FDF8F0; --color-surface: #FFFFFF; --color-ink: #2C2825;
  --color-secondary: #8A8279; --color-hairline: #E0D5C4;
  --color-accent: #2B6B7A; --color-accent-light: rgba(43, 107, 122, 0.08);
  --color-coral: #E8956A; --color-success: #7CB68E; --color-warning: #D4756A;
  --font-display: 'Georgia', 'Noto Serif SC', serif;
  --font-body: -apple-system, BlinkMacSystemFont, 'Segoe UI', 'PingFang SC', 'Microsoft YaHei', sans-serif;
  --shadow-card: 0 2px 12px rgba(44, 40, 37, 0.04);

  padding: 24px; background: var(--color-ground); min-height: calc(100vh - 84px);
  font-family: var(--font-body); color: var(--color-ink);
}

.page-header {
  display: flex; align-items: center; gap: 16px; margin-bottom: 24px;
  .header-icon {
    width: 44px; height: 44px; border-radius: 10px;
    background: linear-gradient(135deg, var(--color-success) 0%, #5B8C72 100%);
    display: flex; align-items: center; justify-content: center; color: #fff;
    box-shadow: 0 4px 14px rgba(124, 182, 142, 0.3); flex-shrink: 0;
  }
  .header-text .header-title {
    font-family: var(--font-display); font-size: 24px; font-weight: 400; font-style: italic; color: var(--color-ink); margin: 0;
  }
  .header-text .header-sub { font-size: 13px; color: var(--color-secondary); margin: 4px 0 0; }
}

.upload-card {
  border-radius: 0; border: 1px solid var(--color-hairline); box-shadow: var(--shadow-card);
  background: var(--color-surface); margin-bottom: 20px;
  :deep(.el-card__header) { padding: 18px 20px; border-bottom: 1px solid var(--color-hairline); }
  :deep(.el-card__body) { padding: 0 20px 16px; }
  .upload-bar {
    display: flex; align-items: center; gap: 12px; flex-wrap: wrap;
    .upload-btn {
      height: 36px; padding: 0 20px; border-radius: 0;
      font-weight: 500; font-family: var(--font-body);
      background: linear-gradient(135deg, var(--color-accent) 0%, #1A4A58 100%);
      border: none; box-shadow: 0 2px 8px rgba(43, 107, 122, 0.25);
      transition: all 0.3s ease;
      &:hover { box-shadow: 0 4px 16px rgba(43, 107, 122, 0.35); transform: translateY(-1px); }
      &--upload {
        background: linear-gradient(135deg, var(--color-coral) 0%, #D4756A 100%);
        box-shadow: 0 2px 8px rgba(232, 149, 106, 0.25);
        &:hover { box-shadow: 0 4px 16px rgba(232, 149, 106, 0.35); }
      }
    }
    .upload-hint { font-size: 12px; color: var(--color-secondary); }
  }
}

.calendar-card {
  border-radius: 0; border: 1px solid var(--color-hairline); box-shadow: var(--shadow-card);
  background: var(--color-surface);
  :deep(.el-card__header) { padding: 18px 20px; border-bottom: 1px solid var(--color-hairline); }
  :deep(.el-card__body) { padding: 0; }

  .calendar-nav {
    display: flex; justify-content: space-between; align-items: center;
    padding: 16px 20px; border-bottom: 1px solid var(--color-hairline);
    .nav-left { display: flex; align-items: center; gap: 4px; }
    .nav-btn {
      height: 32px; padding: 0 12px; border-radius: 0; font-size: 13px;
      color: var(--color-secondary); transition: all 0.2s ease;
      &:hover { color: var(--color-accent); }
      &--icon { font-size: 16px; font-weight: 600; padding: 0 8px; }
    }
    .nav-center {
      display: flex; align-items: center; gap: 4px;
      .nav-year {
        font-family: var(--font-display); font-size: 18px; font-weight: 400;
        font-style: italic; color: var(--color-ink);
      }
      .nav-month {
        font-family: var(--font-display); font-size: 22px; font-weight: 700;
        color: var(--color-accent);
      }
    }
  }

  .calendar-body {
    padding: 16px 20px 20px;

    .weekdays {
      list-style: none; display: grid; grid-template-columns: repeat(7, 1fr);
      padding: 0; margin: 0;
      li {
        text-align: center; padding: 10px 0;
        background: #F5F0E6; font-weight: 600; font-size: 13px;
        color: var(--color-secondary);
      }
    }

    .days {
      list-style: none; display: grid; grid-template-columns: repeat(7, 1fr);
      gap: 1px; background-color: var(--color-hairline); padding: 0; margin: 0;
      li {
        height: 110px; background-color: white; padding: 6px; position: relative;
      }
      .other-month { opacity: 0.35; font-size: 12px; color: var(--color-secondary); padding: 4px; }
      .everyday {
        width: 100%; height: 100%; display: flex; flex-direction: column;
        .datenumber { font-weight: 600; font-size: 14px; color: var(--color-ink); }
      }
      .slot-usual { font-size: 11px; color: var(--color-success); margin-top: 2px; }
      .slot-fulled { font-size: 11px; color: var(--color-warning); margin-top: 2px; }
      .slot-full { font-weight: 600; color: var(--color-warning); }
      .orderbtn {
        position: absolute; bottom: 4px; right: 4px;
        padding: 3px 10px; font-size: 11px;
        background: linear-gradient(135deg, var(--color-accent) 0%, #1A4A58 100%);
        color: white; border: none; border-radius: 0;
        cursor: pointer; font-weight: 500; font-family: var(--font-body);
        transition: all 0.3s ease;
        &:hover { box-shadow: 0 2px 8px rgba(43, 107, 122, 0.4); }
      }
    }
  }
}

@media (max-width: 768px) {
  .page-container { padding: 16px; }
}
</style>
