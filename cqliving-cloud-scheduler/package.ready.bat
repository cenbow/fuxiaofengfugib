@echo off
echo [INFO] Install module with pom.xml to local repository.

cd %~dp0
call mvn clean
call mvn package -Dmaven.test.skip=true -Pready
pause