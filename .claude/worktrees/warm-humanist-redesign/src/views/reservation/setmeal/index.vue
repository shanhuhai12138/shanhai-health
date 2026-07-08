<template>
  <div class="page-container">
    <!-- Page Header -->
    <div class="page-header">
      <div class="header-icon">
        <el-icon :size="22"><Van /></el-icon>
      </div>
      <div class="header-text">
        <h1 class="header-title">套餐管理</h1>
        <p class="header-sub">管理和配置体检套餐组，将多个检查项组合为套餐</p>
      </div>
    </div>

    <!-- Search Card -->
    <el-card class="search-card" shadow="never">
      <el-form :model="queryParams" ref="queryRef" :inline="true" class="search-form">
        <el-form-item label="名称" prop="name">
          <el-input v-model="queryParams.name" placeholder="请输入名称" clearable @keyup.enter="handleQuery" class="search-input" />
        </el-form-item>
        <el-form-item label="编码" prop="code">
          <el-input v-model="queryParams.code" placeholder="请输入编码" clearable @keyup.enter="handleQuery" class="search-input" />
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
        <el-button type="primary" class="toolbar-btn" @click="handleAdd" v-hasPermi="['reservation:setmeal:add']">
          <el-icon><Plus /></el-icon> 新增套餐
        </el-button>
      </div>

      <el-table v-loading="loading" :data="setmealList" class="data-table" stripe border>
        <el-table-column label="序号" type="index" align="center" width="60">
          <template #default="scope">
            {{ (queryParams.pageNum - 1) * queryParams.pageSize + scope.$index + 1 }}
          </template>
        </el-table-column>
        <el-table-column label="套餐名称" align="center" prop="name" min-width="160" />
        <el-table-column label="编码" align="center" prop="code" width="130">
          <template #default="scope"><span class="mono-text">{{ scope.row.code }}</span></template>
        </el-table-column>
        <el-table-column label="助记码" align="center" prop="helpCode" width="130" />
        <el-table-column label="年龄范围" align="center" prop="age" width="100" />
        <el-table-column label="价格" align="center" prop="price" width="100">
          <template #default="scope"><span class="price-text">¥{{ scope.row.price }}</span></template>
        </el-table-column>
        <el-table-column label="备注" align="center" prop="remark" min-width="160" show-overflow-tooltip />
        <el-table-column label="注意事项" align="center" prop="attention" min-width="160" show-overflow-tooltip />
        <el-table-column label="图片" align="center" prop="img" width="80">
          <template #default="scope">
            <image-preview :src="scope.row.img" :width="40" :height="40" v-if="scope.row.img" />
          </template>
        </el-table-column>
        <el-table-column label="创建时间" align="center" prop="createTime" width="170">
          <template #default="scope"><span class="time-text">{{ parseTime(scope.row.createTime, '{y}-{m}-{d}') }}</span></template>
        </el-table-column>
        <el-table-column label="操作" align="center" width="120" fixed="right">
          <template #default="scope">
            <div class="action-links">
              <el-button link type="primary" @click="handleUpdate(scope.row)" v-hasPermi="['reservation:setmeal:edit']">
                <el-icon><Edit /></el-icon>
              </el-button>
              <el-button link type="danger" @click="handleDelete(scope.row)" v-hasPermi="['reservation:setmeal:remove']">
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
    <el-dialog :title="title" v-model="open" width="640px" append-to-body class="custom-dialog" :close-on-click-modal="false">
      <el-form ref="setmealRef" :model="form" :rules="rules" label-width="80px" class="dialog-form">
        <el-row :gutter="16">
          <el-col :span="12">
            <el-form-item label="名称" prop="name">
              <el-input v-model="form.name" placeholder="请输入名称" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="编码" prop="code">
              <el-input v-model="form.code" placeholder="请输入编码" />
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
            <el-form-item label="年龄范围" prop="age">
              <el-input v-model="form.age" placeholder="如: 18-60" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="16">
          <el-col :span="12">
            <el-form-item label="价格" prop="price">
              <el-input v-model="form.price" placeholder="请输入价格" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="备注" prop="remark">
              <el-input v-model="form.remark" placeholder="请输入备注" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="16">
          <el-col :span="12">
            <el-form-item label="注意事项" prop="attention">
              <el-input v-model="form.attention" placeholder="请输入注意事项" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="图片路径" prop="img">
              <el-input v-model="form.img" placeholder="请输入图片路径" />
            </el-form-item>
          </el-col>
        </el-row>
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

<script setup name="Setmeal">
import { listSetmeal, getSetmeal, delSetmeal, addSetmeal, updateSetmeal } from "@/api/reservation/setmeal"
import { Van, Search, Refresh, Plus, Edit, Delete } from '@element-plus/icons-vue'

const { proxy } = getCurrentInstance()
const setmealList = ref([])
const open = ref(false)
const loading = ref(true)
const showSearch = ref(true)
const ids = ref([])
const single = ref(true)
const multiple = ref(true)
const total = ref(0)
const title = ref('')

const data = reactive({
  form: {},
  queryParams: { pageNum: 1, pageSize: 10, name: undefined, code: undefined, helpCode: undefined, sex: undefined, age: undefined, price: undefined, attention: undefined, img: undefined },
  rules: {}
})
const { queryParams, form, rules } = toRefs(data)

function getList() {
  loading.value = true
  listSetmeal(queryParams.value).then(response => {
    setmealList.value = response.rows; total.value = response.total; loading.value = false
  })
}

function cancel() { open.value = false; reset() }

function reset() {
  form.value = { id: null, name: null, code: null, helpCode: null, sex: null, age: null, price: null, remark: null, attention: null, img: null }
  proxy.resetForm("setmealRef")
}

function handleQuery() { queryParams.value.pageNum = 1; getList() }
function resetQuery() { proxy.resetForm("queryRef"); handleQuery() }

function handleSelectionChange(selection) {
  ids.value = selection.map(item => item.id)
  single.value = selection.length != 1
  multiple.value = !selection.length
}

function handleAdd() { reset(); open.value = true; title.value = '添加套餐组' }

function handleUpdate(row) {
  reset()
  const _id = row.id || ids.value
  getSetmeal(_id).then(response => { form.value = response.data; open.value = true; title.value = '修改套餐组' })
}

function submitForm() {
  proxy.$refs["setmealRef"].validate(valid => {
    if (valid) {
      if (form.value.id != null) {
        updateSetmeal(form.value).then(() => { proxy.$modal.msgSuccess("修改成功"); open.value = false; getList() })
      } else {
        addSetmeal(form.value).then(() => { proxy.$modal.msgSuccess("新增成功"); open.value = false; getList() })
      }
    }
  })
}

function handleDelete(row) {
  const _ids = row.id || ids.value
  proxy.$modal.confirm('是否确认删除套餐组编号为"' + _ids + '"的数据项？').then(function() { return delSetmeal(_ids) })
    .then(() => { getList(); proxy.$modal.msgSuccess("删除成功") }).catch(() => {})
}

function handleExport() { proxy.download('reservation/setmeal/export', { ...queryParams.value }, `setmeal_${new Date().getTime()}.xlsx`) }
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
    background: linear-gradient(135deg, var(--color-coral) 0%, #D4756A 100%);
    display: flex; align-items: center; justify-content: center; color: #fff;
    box-shadow: 0 4px 14px rgba(232, 149, 106, 0.3); flex-shrink: 0;
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
      background: linear-gradient(135deg, var(--color-coral) 0%, #D4756A 100%);
      border: none; font-weight: 500; font-family: var(--font-body);
      box-shadow: 0 2px 8px rgba(232, 149, 106, 0.25); transition: all 0.3s ease;
      &:hover { box-shadow: 0 4px 16px rgba(232, 149, 106, 0.35); transform: translateY(-1px); }
    }
    .btn-reset {
      height: 36px; padding: 0 20px; border-radius: 0;
      border: 1px solid var(--color-hairline); font-weight: 500; font-family: var(--font-body);
      transition: all 0.3s ease;
      &:hover { border-color: var(--color-coral); color: var(--color-coral); }
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
      background: linear-gradient(135deg, var(--color-coral) 0%, #D4756A 100%);
      border: none; box-shadow: 0 2px 8px rgba(232, 149, 106, 0.25);
      transition: all 0.3s ease;
      &:hover { box-shadow: 0 4px 16px rgba(232, 149, 106, 0.35); transform: translateY(-1px); }
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
    .mono-text { font-family: var(--font-mono); font-size: 13px; color: var(--color-coral); font-weight: 500; }
    .price-text { font-weight: 600; color: var(--color-accent); }
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
}

.dialog-footer { display: flex; justify-content: flex-end; gap: 10px; }
.btn-cancel {
  height: 38px; padding: 0 24px; border-radius: 0; border: 1px solid var(--color-hairline);
  font-weight: 500; font-family: var(--font-body); transition: all 0.3s ease;
  &:hover { border-color: var(--color-coral); color: var(--color-coral); }
}
.btn-submit {
  height: 38px; padding: 0 28px; border-radius: 0; font-weight: 500; font-family: var(--font-body);
  background: linear-gradient(135deg, var(--color-coral) 0%, #D4756A 100%);
  border: none; box-shadow: 0 2px 8px rgba(232, 149, 106, 0.25); transition: all 0.3s ease;
  &:hover { box-shadow: 0 4px 16px rgba(232, 149, 106, 0.35); transform: translateY(-1px); }
}

@media (max-width: 768px) {
  .page-container { padding: 16px; }
  .search-form .search-input { width: 100% !important; }
}
</style>
