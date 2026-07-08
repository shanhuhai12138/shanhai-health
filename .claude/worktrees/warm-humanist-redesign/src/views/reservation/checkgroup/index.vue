<template>
  <div class="page-container">
    <!-- Page Header -->
    <div class="page-header">
      <div class="header-icon">
        <el-icon :size="22"><Menu /></el-icon>
      </div>
      <div class="header-text">
        <h1 class="header-title">检查组管理</h1>
        <p class="header-sub">管理和配置体检检查组，将多个检查项组合为检查套餐</p>
      </div>
    </div>

    <!-- Search Card -->
    <el-card class="search-card" shadow="never">
      <el-form :model="queryParams" ref="queryRef" :inline="true" class="search-form">
        <el-form-item label="编码" prop="code">
          <el-input v-model="queryParams.code" placeholder="请输入编码" clearable @keyup.enter="handleQuery" class="search-input" />
        </el-form-item>
        <el-form-item label="名称" prop="name">
          <el-input v-model="queryParams.name" placeholder="请输入名称" clearable @keyup.enter="handleQuery" class="search-input" />
        </el-form-item>
        <el-form-item label="助记码" prop="helpCode">
          <el-input v-model="queryParams.helpCode" placeholder="请输入助记码" clearable @keyup.enter="handleQuery" class="search-input" />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" class="btn-search" @click="handleQuery">
            <el-icon><Search /></el-icon> 搜索
          </el-button>
          <el-button class="btn-reset" @click="resetQuery">
            <el-icon><Refresh /></el-icon> 重置
          </el-button>
        </el-form-item>
      </el-form>
    </el-card>

    <!-- Table Card -->
    <el-card class="table-card" shadow="never">
      <div class="toolbar">
        <el-button type="primary" class="toolbar-btn" @click="handleAdd" v-hasPermi="['reservation:checkgroup:add']">
          <el-icon><Plus /></el-icon> 新增检查组
        </el-button>
      </div>

      <el-table v-loading="loading" :data="checkgroupList" class="data-table" stripe border>
        <el-table-column label="序号" type="index" align="center" width="60">
          <template #default="scope">
            {{ (queryParams.pageNum - 1) * queryParams.pageSize + scope.$index + 1 }}
          </template>
        </el-table-column>
        <el-table-column label="检查组编码" align="center" prop="code" width="130">
          <template #default="scope">
            <span class="mono-text">{{ scope.row.code }}</span>
          </template>
        </el-table-column>
        <el-table-column label="检查组名称" align="center" prop="name" min-width="160" />
        <el-table-column label="适用性别" align="center" prop="sex" width="100">
          <template #default="scope">
            <dict-tag :options="health_sex" :value="scope.row.sex" />
          </template>
        </el-table-column>
        <el-table-column label="助记码" align="center" prop="helpCode" width="130" />
        <el-table-column label="说明" align="center" prop="remark" min-width="160" show-overflow-tooltip />
        <el-table-column label="创建时间" align="center" prop="createTime" width="170">
          <template #default="scope">
            <span class="time-text">{{ parseTime(scope.row.createTime, '{y}-{m}-{d}') }}</span>
          </template>
        </el-table-column>
        <el-table-column label="操作" align="center" width="120" fixed="right">
          <template #default="scope">
            <div class="action-links">
              <el-button link type="primary" @click="handleUpdate(scope.row)" v-hasPermi="['reservation:checkgroup:edit']">
                <el-icon><Edit /></el-icon>
              </el-button>
              <el-button link type="danger" @click="handleDelete(scope.row)" v-hasPermi="['reservation:checkgroup:remove']">
                <el-icon><Delete /></el-icon>
              </el-button>
            </div>
          </template>
        </el-table-column>
      </el-table>

      <div class="pagination-wrapper" v-show="total > 0">
        <pagination :total="total" v-model:page="queryParams.pageNum" v-model:limit="queryParams.pageSize" @pagination="getList" />
      </div>
    </el-card>

    <!-- Dialog -->
    <el-dialog :title="title" v-model="open" width="700px" append-to-body class="custom-dialog" :close-on-click-modal="false">
      <el-form ref="checkgroupRef" :model="form" :rules="rules" label-width="90px" class="dialog-form">
        <el-tabs v-model="activeTab" class="custom-tabs">
          <el-tab-pane label="基本信息" name="first">
            <el-row :gutter="16">
              <el-col :span="12">
                <el-form-item label="编码" prop="code">
                  <el-input v-model="form.code" placeholder="请输入编码" />
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="名称" prop="name">
                  <el-input v-model="form.name" placeholder="请输入名称" />
                </el-form-item>
              </el-col>
            </el-row>
            <el-row :gutter="16">
              <el-col :span="12">
                <el-form-item label="助记码" prop="helpCode">
                  <el-input v-model="form.helpCode" placeholder="请输入助记码" />
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="性别" prop="sex">
                  <el-select v-model="form.sex" placeholder="请选择" class="full-width">
                    <el-option v-for="dict in health_sex" :key="dict.value" :label="dict.label" :value="dict.value" />
                  </el-select>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row>
              <el-col :span="24">
                <el-form-item label="注意事项" prop="attention">
                  <el-input v-model="form.attention" placeholder="请输入注意事项" />
                </el-form-item>
              </el-col>
            </el-row>
            <el-row>
              <el-col :span="24">
                <el-form-item label="说明" prop="remark">
                  <el-input v-model="form.remark" type="textarea" :rows="2" placeholder="请输入说明" />
                </el-form-item>
              </el-col>
            </el-row>
          </el-tab-pane>
          <el-tab-pane label="检查项信息" name="second">
            <div class="check-scroll">
              <el-table :data="checkItemList" height="360" ref="checkItemTableRef" row-key="id"
                @selection-change="handleCheckItemSelection" class="check-table" stripe>
                <el-table-column type="selection" width="50" />
                <el-table-column label="项目编码" prop="code" width="130">
                  <template #default="scope"><span class="mono-text">{{ scope.row.code }}</span></template>
                </el-table-column>
                <el-table-column label="项目名称" prop="name" min-width="160" />
                <el-table-column label="项目说明" prop="remark" min-width="160" show-overflow-tooltip />
              </el-table>
            </div>
          </el-tab-pane>
        </el-tabs>
      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button class="btn-cancel" @click="cancel">取 消</el-button>
          <el-button type="primary" class="btn-submit" @click="submitForm">确 定</el-button>
        </div>
      </template>
    </el-dialog>
  </div>
</template>

<script setup name="Checkgroup">
import { listCheckgroup, getCheckgroup, delCheckgroup, addCheckgroup, updateCheckgroup } from "@/api/reservation/checkgroup"
import { getAllCheckitems } from "@/api/reservation/checkitem"
import { Menu, Search, Refresh, Plus, Edit, Delete } from '@element-plus/icons-vue'

const { proxy } = getCurrentInstance()
const { health_sex, health_type } = proxy.useDict('health_sex', 'health_type')

const checkgroupList = ref([])
const open = ref(false)
const loading = ref(true)
const showSearch = ref(true)
const ids = ref([])
const single = ref(true)
const multiple = ref(true)
const total = ref(0)
const title = ref('')
const checkItemList = ref([])
const activeTab = ref('first')
const selectedCheckItemIds = ref([])
const checkItemTableRef = ref(null)

const data = reactive({
  form: {},
  queryParams: { pageNum: 1, pageSize: 10, code: undefined, name: undefined, helpCode: undefined, sex: undefined, attention: undefined },
  rules: {}
})
const { queryParams, form, rules } = toRefs(data)

function loadCheckItems() {
  getAllCheckitems().then(response => {
    checkItemList.value = response.data || []
  }).catch(() => {})
}

function handleCheckItemSelection(selection) {
  selectedCheckItemIds.value = selection.map(item => item.id)
}

function getList() {
  loading.value = true
  listCheckgroup(queryParams.value).then(response => {
    checkgroupList.value = response.rows
    total.value = response.total
    loading.value = false
  })
}

function cancel() { open.value = false; reset() }

function reset() {
  form.value = { id: null, code: null, name: null, helpCode: null, sex: null, remark: null, attention: null }
  selectedCheckItemIds.value = []
  proxy.resetForm('checkgroupRef')
}

function handleQuery() { queryParams.value.pageNum = 1; getList() }
function resetQuery() { proxy.resetForm('queryRef'); handleQuery() }

function handleSelectionChange(selection) {
  ids.value = selection.map(item => item.id)
  single.value = selection.length != 1
  multiple.value = !selection.length
}

function handleAdd() {
  reset()
  open.value = true
  title.value = '添加检查组'
  nextTick(() => { loadCheckItems() })
}

function handleUpdate(row) {
  reset()
  const _id = row.id || ids.value
  getCheckgroup(_id).then(response => {
    form.value = response.data
    open.value = true
    title.value = '修改检查组'
    nextTick(() => {
      loadCheckItems()
      if (response.data.checkItemIds && response.data.checkItemIds.length > 0) {
        selectedCheckItemIds.value = response.data.checkItemIds
        nextTick(() => {
          if (checkItemTableRef.value) {
            checkItemList.value.forEach(item => {
              if (selectedCheckItemIds.value.includes(item.id)) {
                checkItemTableRef.value.toggleRowSelection(item, true)
              }
            })
          }
        })
      }
    })
  })
}

function submitForm() {
  proxy.$refs['checkgroupRef'].validate(valid => {
    if (valid) {
      form.value.checkItemIds = selectedCheckItemIds.value
      if (form.value.id != null) {
        updateCheckgroup(form.value).then(() => { proxy.$modal.msgSuccess('修改成功'); open.value = false; getList() })
      } else {
        addCheckgroup(form.value).then(() => { proxy.$modal.msgSuccess('新增成功'); open.value = false; getList() })
      }
    }
  })
}

function handleDelete(row) {
  const _ids = row.id || ids.value
  proxy.$modal.confirm('是否确认删除检查组编号为"' + _ids + '"的数据项？').then(function() { return delCheckgroup(_ids) })
    .then(() => { getList(); proxy.$modal.msgSuccess('删除成功') }).catch(() => {})
}

function handleExport() {
  proxy.download('reservation/checkgroup/export', { ...queryParams.value }, 'checkgroup_' + new Date().getTime() + '.xlsx')
}
getList()
</script>

<style lang="scss" scoped>
.page-container {
  --color-ground: #FDF8F0; --color-surface: #FFFFFF; --color-ink: #2C2825;
  --color-secondary: #8A8279; --color-hairline: #E0D5C4;
  --color-accent: #2B6B7A; --color-accent-light: rgba(43, 107, 122, 0.08);
  --color-coral: #E8956A; --color-success: #7CB68E; --color-warning: #D4756A;
  --font-display: 'Georgia', 'Noto Serif SC', serif;
  --font-body: -apple-system, BlinkMacSystemFont, 'Segoe UI', 'PingFang SC', 'Microsoft YaHei', sans-serif;
  --font-mono: 'JetBrains Mono', 'Courier New', monospace;
  --shadow-card: 0 2px 12px rgba(44, 40, 37, 0.04);
  --shadow-card-hover: 0 8px 32px rgba(44, 40, 37, 0.08);

  padding: 24px; background: var(--color-ground); min-height: calc(100vh - 84px);
  font-family: var(--font-body); color: var(--color-ink);
}

.page-header {
  display: flex; align-items: center; gap: 16px; margin-bottom: 24px;
  .header-icon {
    width: 44px; height: 44px; border-radius: 10px;
    background: linear-gradient(135deg, var(--color-accent) 0%, #1A4A58 100%);
    display: flex; align-items: center; justify-content: center; color: #fff;
    box-shadow: 0 4px 14px rgba(43, 107, 122, 0.3); flex-shrink: 0;
  }
  .header-text .header-title {
    font-family: var(--font-display); font-size: 24px; font-weight: 400; font-style: italic; color: var(--color-ink); margin: 0;
  }
  .header-text .header-sub { font-size: 13px; color: var(--color-secondary); margin: 4px 0 0; }
}

.search-card {
  border-radius: 0; border: 1px solid var(--color-hairline); box-shadow: var(--shadow-card);
  margin-bottom: 16px; background: var(--color-surface);
  :deep(.el-card__header) { padding: 0; border-bottom: none; }
  :deep(.el-card__body) { padding: 0; }
  .search-form {
    padding: 20px 20px 4px; display: flex; flex-wrap: wrap; align-items: flex-end;
    .el-form-item { margin-bottom: 16px; margin-right: 12px; }
    .search-input { width: 180px; }
    .btn-search {
      height: 36px; padding: 0 20px; border-radius: 0;
      background: linear-gradient(135deg, var(--color-accent) 0%, #1A4A58 100%);
      border: none; font-weight: 500; font-family: var(--font-body);
      box-shadow: 0 2px 8px rgba(43, 107, 122, 0.25); transition: all 0.3s ease;
      &:hover { box-shadow: 0 4px 16px rgba(43, 107, 122, 0.35); transform: translateY(-1px); }
    }
    .btn-reset {
      height: 36px; padding: 0 20px; border-radius: 0;
      border: 1px solid var(--color-hairline); font-weight: 500; font-family: var(--font-body);
      transition: all 0.3s ease;
      &:hover { border-color: var(--color-accent); color: var(--color-accent); }
    }
  }
}

.table-card {
  border-radius: 0; border: 1px solid var(--color-hairline); box-shadow: var(--shadow-card);
  background: var(--color-surface);
  :deep(.el-card__header) { padding: 0; border-bottom: none; }
  .toolbar {
    padding: 16px 20px; border-bottom: 1px solid var(--color-hairline);
    .toolbar-btn {
      height: 36px; padding: 0 20px; border-radius: 0;
      font-weight: 500; font-family: var(--font-body);
      background: linear-gradient(135deg, var(--color-accent) 0%, #1A4A58 100%);
      border: none; box-shadow: 0 2px 8px rgba(43, 107, 122, 0.25);
      transition: all 0.3s ease;
      &:hover { box-shadow: 0 4px 16px rgba(43, 107, 122, 0.35); transform: translateY(-1px); }
    }
  }
  .data-table {
    :deep(.el-table__header-wrapper th.el-table__cell) {
      background: #F5F0E6; color: var(--color-secondary);
      font-weight: 600; font-size: 12px; letter-spacing: 0.5px;
      text-transform: uppercase; border-bottom: 1px solid var(--color-hairline);
    }
    :deep(.el-table__row:hover) { background: #FAF6EE; }
    :deep(.el-table td) { padding: 12px 0; border-bottom: 1px solid rgba(224, 213, 196, 0.3); }
    .mono-text { font-family: var(--font-mono); font-size: 13px; color: var(--color-accent); font-weight: 500; }
    .time-text { color: var(--color-secondary); font-size: 13px; }
    .action-links { display: flex; align-items: center; gap: 4px;
      .el-button { padding: 4px; border-radius: 4px; transition: all 0.2s ease; &:hover { background: rgba(0,0,0,0.04); } }
    }
  }
  .pagination-wrapper { padding: 16px 20px; border-top: 1px solid var(--color-hairline); display: flex; justify-content: flex-end; }
}

.custom-dialog {
  :deep(.el-dialog__header) {
    border-bottom: 1px solid var(--color-hairline); padding: 20px;
    .el-dialog__title { font-family: var(--font-display); font-style: italic; font-weight: 400; font-size: 18px; }
  }
  :deep(.el-dialog__body) { padding: 24px 20px; }
  :deep(.el-dialog__footer) { border-top: 1px solid var(--color-hairline); padding: 14px 20px; }
}

.dialog-form {
  :deep(.el-form-item) { margin-bottom: 18px; }
  :deep(.el-form-item__label) { font-weight: 500; color: var(--color-secondary); font-size: 13px; }
  .full-width { width: 100%; }
}

.custom-tabs {
  :deep(.el-tabs__header) { margin-bottom: 20px; border-bottom: 1px solid var(--color-hairline); }
  :deep(.el-tabs__item) {
    font-weight: 500; color: var(--color-secondary);
    &.is-active { color: var(--color-accent); font-weight: 600; }
  }
  :deep(.el-tabs__active-bar) { background: var(--color-accent); }
}

.check-scroll { padding: 0 4px; }
.check-table {
  :deep(.el-table__header-wrapper th.el-table__cell) {
    background: #F5F0E6; color: var(--color-secondary); font-weight: 600; font-size: 12px;
  }
  :deep(.el-table__row:hover) { background: #FAF6EE; }
}

.dialog-footer { display: flex; justify-content: flex-end; gap: 10px; }
.btn-cancel {
  height: 38px; padding: 0 24px; border-radius: 0; border: 1px solid var(--color-hairline);
  font-weight: 500; font-family: var(--font-body); transition: all 0.3s ease;
  &:hover { border-color: var(--color-accent); color: var(--color-accent); }
}
.btn-submit {
  height: 38px; padding: 0 28px; border-radius: 0; font-weight: 500; font-family: var(--font-body);
  background: linear-gradient(135deg, var(--color-accent) 0%, #1A4A58 100%);
  border: none; box-shadow: 0 2px 8px rgba(43, 107, 122, 0.25); transition: all 0.3s ease;
  &:hover { box-shadow: 0 4px 16px rgba(43, 107, 122, 0.35); transform: translateY(-1px); }
}

@media (max-width: 768px) {
  .page-container { padding: 16px; }
  .search-form .search-input { width: 100% !important; }
}
</style>
