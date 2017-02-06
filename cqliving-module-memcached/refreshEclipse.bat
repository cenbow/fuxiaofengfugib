@echo off

set curdir=%~dp0
set partition=%curdir:~0,1%
%partition%:
cd %curdir%

call mvn eclipse:clean
call mvn eclipse:eclipse
pause