cd web
call npm install

REM jump out if case of error
if %errorlevel% neq 0 exit /b %errorlevel%

call npm ci

REM jump out if case of error
if %errorlevel% neq 0 exit /b %errorlevel%

call npm run build

REM jump out if case of error
if %errorlevel% neq 0 exit /b %errorlevel%

cd ..