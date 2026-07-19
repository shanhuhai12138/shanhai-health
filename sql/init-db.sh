#!/bin/bash
# ============================================
# 传智健康 - 数据库一键初始化脚本
# 用法: ./init-db.sh [用户名] [密码] [主机] [端口]
# 示例: ./init-db.sh root admin localhost 3306
#       ./init-db.sh root
#       ./init-db.sh
# ============================================

set -e

# 解析参数
USER="${1:-root}"
PASS="${2:-}"
HOST="${3:-localhost}"
PORT="${4:-3306}"

# 拼接连接参数
CONN_ARGS="-u${USER} -h${HOST} -P${PORT}"
if [ -n "$PASS" ]; then
    CONN_ARGS="$CONN_ARGS -p${PASS}"
fi

MYSQL_CMD="mysql ${CONN_ARGS}"

echo ""
echo "============================================"
echo "  传智健康 - 数据库一键初始化"
echo "============================================"
echo "  用户: $USER"
echo "  主机: $HOST:$PORT"
echo "============================================"
echo ""
echo "即将按以下顺序执行 SQL:"
echo "  1. clean.sql       - 创建所有表结构"
echo "  2. seed_data.sql   - 插入心理量表种子数据"
echo "  3. system_init.sql - 插入系统基础数据"
echo "  4. menu.sql        - 插入业务菜单权限"
echo ""
read -p "确认继续? (y/n): " CONFIRM
if [ "$CONFIRM" != "y" ] && [ "$CONFIRM" != "Y" ]; then
    echo "已取消"
    exit 1
fi
echo ""

# SQL 文件列表
FILES=("clean.sql" "seed_data.sql" "system_init.sql" "menu.sql")
NAMES=("建表DDL" "量表种子数据" "系统基础数据" "业务菜单权限")

for i in "${!FILES[@]}"; do
    FILE="${FILES[$i]}"
    NAME="${NAMES[$i]}"
    IDX=$((i + 1))
    echo "[${IDX}/${#FILES[@]}] 正在执行 ${FILE} (${NAME}) ..."
    ${MYSQL_CMD} < "${FILE}"
    echo "     成功"
    echo ""
done

echo "============================================"
echo "  数据库初始化完成！"
echo "============================================"
echo ""
echo "下一步:"
echo "  1. 启动后端: cd health-admin && mvn spring-boot:run"
echo "  2. 启动前端: cd health-Vue3 && npm run dev"
echo "  3. 登录: admin / admin123"
echo ""
