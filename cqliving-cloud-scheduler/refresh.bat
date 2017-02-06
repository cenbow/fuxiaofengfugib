@echo off
echo [INFO] Generate Maven-Pure-Eclipse project Structure.
set curdir=%~dp0
set partition=%curdir:~0,1%
set PROJECT_LIB_DIR=%curdir%\src\main\webapp\WEB-INF\lib
set PROJECT_CLASS_DIR=%curdir%\src\main\webapp\WEB-INF\classes

%partition%:
cd %curdir%
echo [INFO] Clean Maven Project 
call mvn clean

echo [INFO] Clean Eclipse Project
call mvn eclipse:clean

echo [INFO] Generates eclipse project configuration files and setting outputClasspath:'/WEB-INF/classes'
if exist %PROJECT_CLASS_DIR% (rd /s /q %PROJECT_CLASS_DIR%)
md %PROJECT_CLASS_DIR%
call mvn eclipse:eclipse -DdownloadSources=false -DoutputDirectory=%PROJECT_CLASS_DIR%

echo [INFO] Copy all dependencies jar to '/WEB-INF/lib'
if exist %PROJECT_LIB_DIR% (rd /s /q %PROJECT_LIB_DIR%)
md %PROJECT_LIB_DIR%
call mvn dependency:copy-dependencies -DoutputDirectory=%PROJECT_LIB_DIR% -DexcludeScope=provided
echo [INFO] Generate Maven Project Success.
pause
