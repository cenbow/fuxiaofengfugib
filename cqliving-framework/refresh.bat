@echo off
set curdir=%~dp0
set partition=%curdir:~0,1%
set PROJECT_LIB_DIR=%curdir%\src\main\webapp\WEB-INF\lib
set PROJECT_CLASS_DIR=%curdir%\src\main\webapp\WEB-INF\classes

%partition%:
cd %curdir%
echo 1.清理MAVEN工程 =================================
call mvn clean

echo 2.清理Eclipse工程 =============================
call mvn eclipse:clean

echo 3.构建Eclipse工程，并设置编译目录为：/WEB-INF/classes =============================
del /f/s/q %PROJECT_CLASS_DIR%\*
rd /s /q %PROJECT_CLASS_DIR%
md %PROJECT_CLASS_DIR%
call mvn eclipse:eclipse -DdownloadSources=false -DoutputDirectory=%PROJECT_CLASS_DIR%

echo 4.生成依赖的jar拷贝到WEB-INF/lib ===============================
rd /s /q %PROJECT_LIB_DIR%
md %PROJECT_LIB_DIR%
call mvn dependency:copy-dependencies -DoutputDirectory=%PROJECT_LIB_DIR%
del /f/s/q %PROJECT_LIB_DIR%\jsp-api*
echo MAVEN工程构建成功。
pause
