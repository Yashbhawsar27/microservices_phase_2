@echo off
REM ============================================================================
REM ELK Stack Setup Script - Simple Configuration (No Installation)
REM ============================================================================
REM This script provides instructions to configure ELK components.
REM All configurations are file-based - no system installation required.
REM
REM What this does:
REM - Creates logs directory for microservices
REM - Provides configuration instructions for each component
REM - All configs are simple text file edits
REM - No complex installation procedures
REM
REM Configuration Files to Edit:
REM - elasticsearch.yml (basic settings)
REM - kibana.yml (basic settings)  
REM - logstash.conf (already provided)
REM ============================================================================

echo ========================================
echo ELK Stack Setup - Simple Configuration
echo ========================================
echo.
echo ✅ No system installation required!
echo ✅ Just edit configuration files!
echo.

echo Creating logs directory for microservices...
if not exist ..\logs mkdir ..\logs
echo ✅ Created: logs\ directory

echo.
echo ========================================
echo Elasticsearch Configuration (Simple)
echo ========================================
echo.
echo 📁 Navigate to: elasticsearch\elasticsearch-8.11.0\config\
echo 📝 Edit file: elasticsearch.yml
echo.
echo Add these lines at the end:
echo.
echo # Simple configuration for learning
echo xpack.security.enabled: false
echo network.host: localhost  
echo http.port: 9200
echo cluster.name: microservices-logs
echo node.name: node-1
echo.
echo 💡 This disables security for easy learning setup

echo.
echo ========================================
echo Kibana Configuration (Simple)  
echo ========================================
echo.
echo 📁 Navigate to: kibana\kibana-8.11.0\config\
echo 📝 Edit file: kibana.yml
echo.
echo Add these lines at the end:
echo.
echo # Simple configuration for learning
echo server.port: 5601
echo server.host: "localhost"
echo elasticsearch.hosts: ["http://localhost:9200"]
echo.
echo 💡 This connects Kibana to Elasticsearch

echo.
echo ========================================
echo Logstash Configuration (Already Done)
echo ========================================
echo.
echo ✅ logstash.conf is already configured!
echo 📁 Location: elk-stack\logstash.conf
echo 🔄 Will be copied to logstash config during startup
echo.
echo Configuration includes:
echo - Input: Reads from logs\*.log files
echo - Filter: Parses microservice logs
echo - Output: Sends to Elasticsearch

echo.
echo ========================================
echo Index Pattern Setup (After Starting)
echo ========================================
echo.
echo After starting ELK stack:
echo 1. 🌐 Open Kibana: http://localhost:5601
echo 2. 🔍 Go to "Stack Management" → "Index Patterns"
echo 3. ➕ Click "Create index pattern"
echo 4. 📝 Enter pattern: microservices-logs-*
echo 5. ⏰ Select time field: @timestamp
echo 6. ✅ Click "Create index pattern"
echo.
echo Then you can view logs in "Discover" section!

echo.
echo ========================================
echo File Structure After Setup:
echo ========================================
echo.
echo elk-stack\
echo ├── elasticsearch\
echo │   └── elasticsearch-8.11.0\
echo │       ├── bin\elasticsearch.bat
echo │       └── config\elasticsearch.yml (edited)
echo ├── logstash\
echo │   └── logstash-8.11.0\
echo │       ├── bin\logstash.bat
echo │       └── config\logstash.conf (copied)
echo ├── kibana\
echo │   └── kibana-8.11.0\
echo │       ├── bin\kibana.bat
echo │       └── config\kibana.yml (edited)
echo └── logs\ (created for microservice logs)

echo.
echo ========================================
echo Ready to Start!
echo ========================================
echo.
echo After configuration, run: start-elk.bat
echo.
echo 💡 Remember: No installation, just configuration files!
echo.
pause