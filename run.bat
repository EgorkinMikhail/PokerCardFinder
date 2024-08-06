@echo off
setlocal
set JAR_NAME=PokerCardFinder-1.0-SNAPSHOT.jar
if not exist "%~dp0%JAR_NAME%" (
    echo Error: %JAR_NAME% not found in the current directory.    exit /b 1
)
if "%1"=="" (    echo Error: No argument provided
    exit /b 1)
java -jar "%~dp0%JAR_NAME%" %1