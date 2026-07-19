@echo off
chcp 65001 >nul
REM ============================================
REM 传智健康 - 数据库一键初始化脚本
REM 用法: init-db.bat [用户名] [密码] [主机] [端口]
REM 示例: init-db.bat root admin localhost 3306
REM       init-db.bat root                    (使用默认 localhost:3306)
REM       init-db.bat                         (使用 root@localhost:3306)
REM ============================================

setlocal EnableDelayedExpansion

:: 解析参数
set "USER=%~1"
set "PASS=%~2"
set "HOST=%~3"
set "PORT=%~4"

:: 默认值
if "%USER%"=="" set "USER=root"
if "%PASS%"=="" set "PASS="
if "%HOST%"=="" set "HOST=localhost"
if "%PORT%"=="" set "PORT=3306"

:: 拼接 mysql 连接串
if "%PASS%"=="" (
    set "CONN=mysql -u%USER% -h%HOST% -P%PORT%"
) else (
    set "CONN=mysql -u%USER% -p%PASS% -h%HOST% -P%PORT%"
)

echo.
echo ============================================
echo   传智健康 - 数据库一键初始化
echo ============================================
echo   用户: %USER%
echo   主机: %HOST%:%PORT%
echo ============================================
echo.
echo 即将按以下顺序执行 SQL:
echo   1. clean.sql       - 创建所有表结构
echo   2. seed_data.sql   - 插入心理量表种子数据
echo   3. system_init.sql - 插入系统基础数据
echo   4. menu.sql        - 插入业务菜单权限
echo.
set /p "CONFIRM=确认继续? (Y/N): "
if /i not "!CONFIRM!"=="Y" (
    echo 已取消
    exit /b 1
)
echo.

:: 定义 SQL 文件列表（按执行顺序）
set "FILES=clean.sql seed_data.sql system_init.sql menu.sql"
set "NAMES=建表DDL 量表种子数据 系统基础数据 业务菜单权限"
set IDX=0

for %%F in (%FILES%) do (
    set /a IDX+=1
    echo [%IDX%/4] 正在执行 %%F ...
    %CONN% < "%%F"
    if errorlevel 1 (
        echo.
        echo !!! 执行 %%F 失败，请检查错误信息后重试 !!!
        echo.
        pause
        exit /b 1
    )
    echo     成功
    echo.
)

echo ============================================
echo   数据库初始化完成！
echo ============================================
echo.
echo 下一步:
echo   1. 启动后端: cd health-admin ^&^& mvn spring-boot:run
echo   2. 启动前端: cd health-Vue3 ^&^& npm run dev
echo   3. 登录: admin / admin123
echo.
pause
