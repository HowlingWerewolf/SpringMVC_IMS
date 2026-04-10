call build_scripts/fe_build.bat

REM jump out if case of error
if %errorlevel% neq 0 exit /b %errorlevel%

cd web
call ng serve
cd ..
