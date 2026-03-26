call build_scripts/be_build.bat

REM jump out if case of error
if %errorlevel% neq 0 exit /b %errorlevel%

call build_scripts/fe_build.bat

REM jump out if case of error
if %errorlevel% neq 0 exit /b %errorlevel%

call docker compose up --build
