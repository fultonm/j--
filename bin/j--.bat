@echo off

REM Copyright 2013 Bill Campbell, Swami Iyer and Bahar Akbal-Delibas

REM Wrapper script for running jminusminus.Main program.

set BASE_DIR=%~dp0
set j="%BASE_DIR%\..\"
set JAVA=java
set CPATH="%BASE_DIR%\..\out\production\j--;"
if "%CLASSPATH%" == "" goto runApp
set CPATH=%CPATH%;"%CLASSPATH%"

:runApp
%JAVA% -classpath %CPATH% jminusminus.Main "j--" %*

set JAVA=
set BASE_DIR=
set CPATH=
