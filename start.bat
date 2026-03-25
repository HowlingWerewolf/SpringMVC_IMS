call mvn clean package

REM jump out if case of error
if %errorlevel% neq 0 exit /b %errorlevel%

@REM cd web
@REM call npm install
@REM
@REM REM jump out if case of error
@REM if %errorlevel% neq 0 exit /b %errorlevel%
@REM
@REM call npm ci
@REM
@REM REM jump out if case of error
@REM if %errorlevel% neq 0 exit /b %errorlevel%
@REM
@REM call npm run build
@REM
@REM REM jump out if case of error
@REM if %errorlevel% neq 0 exit /b %errorlevel%

call docker compose up