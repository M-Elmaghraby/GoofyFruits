@echo off
echo Executing %1 with JInput...

java -cp d:\jinput\bin\jinput-windows.jar;. -Djava.library.path="d:\jinput\bin" %1 %2 %3

echo.