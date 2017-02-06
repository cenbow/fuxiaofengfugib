@echo off
set curdir=%~dp0
set partition=%curdir:~0,1%
%partition%:
cd %curdir%

call mvn clean
call mvn deploy -Dmaven.test.skip=true
pause