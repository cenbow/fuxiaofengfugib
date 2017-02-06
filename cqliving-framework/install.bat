@echo off
echo [INFO] Install module with pom.xml to local repository.

cd %~dp0
call mvn clean install -Dmaven.test.skip=true
pause