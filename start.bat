cd web
call npm install

REM jump out if case of error
if %errorlevel% neq 0 exit /b %errorlevel%

call npm ci

REM jump out if case of error
if %errorlevel% neq 0 exit /b %errorlevel%

call npm run build

cd ..

call mvn clean package

REM jump out if case of error
if %errorlevel% neq 0 exit /b %errorlevel%

call docker-compose -f docker-compose.local.yml up -d