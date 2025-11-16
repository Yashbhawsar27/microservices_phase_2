@echo off
REM ============================================================================
REM ELK Stack Startup Script - Portable Execution (No Installation)
REM ============================================================================
REM This script starts ELK components as portable applications.
REM No system services, no installation - just runs the executables.
REM
REM Startup Order:
REM 1. Elasticsearch (data storage) - Port 9200
REM 2. Kibana (visualization) - Port 5601  
REM 3. Logstash (log processing) - Processes logs
REM
REM All components run as regular applications, not Windows services.
REM You can see them running in separate command windows.
REM ============================================================================

echo ========================================
echo Starting ELK Stack (Portable Mode)
echo ========================================
echo.
echo ✅ No system services installed!
echo ✅ Running as portable applications!
echo ✅ Each component in separate window!
echo.

REM Copy logstash configuration
echo Copying logstash configuration...
if exist logstash\logstash-8.11.0\config\ (
    copy logstash.conf logstash\logstash-8.11.0\config\
    echo ✅ Logstash config copied
) else (
    echo ❌ Logstash directory not found! Run download-elk.bat first
    pause
    exit
)

echo.
echo ========================================
echo Starting Elasticsearch (Port 9200)
echo ========================================
echo 🔍 Data storage and search engine
echo 🌐 Will be available at: http://localhost:9200
echo.
if exist elasticsearch\elasticsearch-8.11.0\bin\elasticsearch.bat (
    start "Elasticsearch" cmd /k "cd elasticsearch\elasticsearch-8.11.0\bin && elasticsearch.bat"
    echo ✅ Elasticsearch starting in separate window...
    echo ⏳ Waiting 30 seconds for startup...
    timeout /t 30
) else (
    echo ❌ Elasticsearch not found! Run download-elk.bat first
    pause
    exit
)

echo.
echo ========================================
echo Starting Kibana (Port 5601)
echo ========================================
echo 📊 Data visualization dashboard
echo 🌐 Will be available at: http://localhost:5601
echo.
if exist kibana\kibana-8.11.0\bin\kibana.bat (
    start "Kibana" cmd /k "cd kibana\kibana-8.11.0\bin && kibana.bat"
    echo ✅ Kibana starting in separate window...
    echo ⏳ Waiting 20 seconds for startup...
    timeout /t 20
) else (
    echo ❌ Kibana not found! Run download-elk.bat first
    pause
    exit
)

echo.
echo ========================================
echo Starting Logstash (Log Processing)
echo ========================================
echo 🔄 Processes microservice logs
echo 📁 Reads from: logs\*.log files
echo 📤 Sends to: Elasticsearch
echo.
if exist logstash\logstash-8.11.0\bin\logstash.bat (
    start "Logstash" cmd /k "cd logstash\logstash-8.11.0\bin && logstash.bat -f ..\config\logstash.conf"
    echo ✅ Logstash starting in separate window...
) else (
    echo ❌ Logstash not found! Run download-elk.bat first
    pause
    exit
)

echo.
echo ========================================
echo ELK Stack Started Successfully!
echo ========================================
echo.
echo 🌐 Access URLs:
echo - Elasticsearch: http://localhost:9200
echo - Kibana Dashboard: http://localhost:5601
echo.
echo 📊 Next Steps:
echo 1. Wait 2-3 minutes for all components to fully start
echo 2. Open Kibana: http://localhost:5601
echo 3. Create index pattern: microservices-logs-*
echo 4. Start microservices to generate logs
echo 5. View logs in Kibana "Discover" section
echo.
echo 🔧 To Stop ELK Stack:
echo - Close all ELK command windows
echo - Or press Ctrl+C in each window
echo.
echo 💡 All components running as portable apps!
echo 💡 No system services installed!
echo.
pause