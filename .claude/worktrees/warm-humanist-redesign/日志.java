/*
0628
搜索框拆分：
        - 原来一个 searchValue 输入框 → 拆分为 编码（input）、名称（input）、类型（select，下拉选择"检查"/"检验"）
        - 补上了之前注释掉的"重置"按钮

表格列调整：
        - 删除了 type="selection" 选框列
  - 删除了 @selection-change 事件绑定
  - 给每个列设置了合理的 width，让表格内容紧凑
  - 操作列加了 fixed="right" 固定在右侧，width="150" 确保"修改""删除"两个按钮能在一行内展示
  - 注意事项列加了 show-overflow-tooltip，超长文本会自动省略号+tooltip

逻辑清理：
        - 移除了不再需要的 ids、single、multiple 变量
  - 移除了 handleSelectionChange 方法
  - handleUpdate / handleDelete 改为直接用传入行的 row.id（因为已经没有多选功能了）
        - queryParams 中去掉了 searchValue，code/name/type 值改为 undefined（与项目中其他页面保持一致）*/


/*0629
*
* 修改的文件

  1. 前端 health-Vue3/src/views/reservation/checkgroup/index.vue
  - 导入 getAllCheckitems API
  - 新增响应式变量：checkItemList（检查项列表）、activeTab（Tab
  切换）、selectedCheckItemIds（选中的检查项ID）、checkItemTableRef（表格引用）
  - 新增 loadCheckItems() 函数：调用 /all 接口加载全部检查项
  - 新增 handleCheckItemSelection() 函数：记录勾选的检查项ID
  - handleAdd()：新增时先加载检查项列表，清空选中状态
  - handleUpdate()：修改时加载检查项列表，并回显已关联的检查项（调用 toggleRowSelection）
  - reset()：重置时清空 selectedCheckItemIds
  - submitForm()：提交时将 selectedCheckItemIds 绑定到 form.checkItemIds 传给后端

  2. 后端 health/health-reservation/.../domain/TCheckgroup.java
  - 新增 checkItemIds 字段（Long[]），非数据库映射，仅用于前后端传递关联的检查项ID

  3. 后端 health/health-reservation/.../service/impl/TCheckgroupServiceImpl.java
  - selectTCheckgroupById：查询单个检查组时，从 t_checkgroup_checkitem 关联表查出已关联的检查项ID，设置到 checkItemIds
  字段返回
  - insertTCheckgroup：新增后调用 insertCheckgroupCheckitem() 写入关联表
  - updateTCheckgroup：修改前先删除旧关联，再写入新关联
  - deleteTCheckgroupByIds / deleteTCheckgroupById：级联删除关联表数据
  - 新增 insertCheckgroupCheckitem() 方法：遍历 checkItemIds 数组写入关联记录*/