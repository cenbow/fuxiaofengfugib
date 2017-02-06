@echo off
set curdir=%~dp0
set partition=%curdir:~0,1%
set PROJECT_LIB_DIR=%curdir%\src\main\webapp\WEB-INF\lib
set PROJECT_CLASS_DIR=%curdir%\src\main\webapp\WEB-INF\classes

%partition%:
cd %curdir%
echo 1.����MAVEN���� =================================
call mvn clean

echo 2.����Eclipse���� =============================
call mvn eclipse:clean

echo 3.����Eclipse���̣������ñ���Ŀ¼Ϊ��/WEB-INF/classes =============================
del /f/s/q %PROJECT_CLASS_DIR%\*
rd /s /q %PROJECT_CLASS_DIR%
md %PROJECT_CLASS_DIR%
call mvn eclipse:eclipse -DdownloadSources=false -DoutputDirectory=%PROJECT_CLASS_DIR%

echo 4.����������jar������WEB-INF/lib ===============================
rd /s /q %PROJECT_LIB_DIR%
md %PROJECT_LIB_DIR%
call mvn dependency:copy-dependencies -DoutputDirectory=%PROJECT_LIB_DIR%
del /f/s/q %PROJECT_LIB_DIR%\jsp-api*
echo MAVEN���̹����ɹ���
pause
