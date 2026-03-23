call mvn clean package

REM jump out if case of error
if %errorlevel% neq 0 exit /b %errorlevel%

call docker compose up