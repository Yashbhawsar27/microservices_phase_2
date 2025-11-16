@echo off
REM ============================================================================
REM ELK Stack Download Script - NO SYSTEM INSTALLATION REQUIRED
REM ============================================================================
REM This script helps download ELK stack components as PORTABLE applications.
REM No system installation needed - just download, extract, and run!
REM
REM What this does:
REM - Creates directories for ELK components
REM - Provides download links for portable ZIP files
REM - No admin rights required
REM - No system registry changes
REM - No Windows services installation
REM
REM ELK Components (All Portable):
REM 1. Elasticsearch - Search and analytics engine
REM 2. Logstash - Log processing pipeline  
REM 3. Kibana - Data visualization dashboard
REM
REM Total Size: ~500MB (all three components)
REM Java Requirement: Java 11+ (usually already installed)
REM ============================================================================

echo ========================================
echo ELK Stack - Portable Download (No Installation)
echo ========================================
echo.
echo ✅ NO system installation required!
echo ✅ NO admin rights needed!
echo ✅ Just download, extract, and run!
echo.

echo Creating directories for portable ELK components...
if not exist elasticsearch mkdir elasticsearch
if not exist logstash mkdir logstash  
if not exist kibana mkdir kibana

echo.
echo ========================================
echo Download Links (Manual Download Required):
echo ========================================
echo.
echo 📥 1. Elasticsearch 8.11.0 (Portable ZIP):
echo    https://artifacts.elastic.co/downloads/elasticsearch/elasticsearch-8.11.0-windows-x86_64.zip
echo    📁 Extract to: elk-stack\elasticsearch\
echo    💾 Size: ~200MB
echo.
echo 📥 2. Logstash 8.11.0 (Portable ZIP):
echo    https://artifacts.elastic.co/downloads/logstash/logstash-8.11.0-windows-x86_64.zip
echo    📁 Extract to: elk-stack\logstash\
echo    💾 Size: ~180MB
echo.
echo 📥 3. Kibana 8.11.0 (Portable ZIP):
echo    https://artifacts.elastic.co/downloads/kibana/kibana-8.11.0-windows-x86_64.zip
echo    📁 Extract to: elk-stack\kibana\
echo    💾 Size: ~120MB
echo.
echo ========================================
echo Download Instructions:
echo ========================================
echo.
echo 1. 🌐 Open each URL in browser
echo 2. 📥 Download the ZIP files
echo 3. 📂 Extract each ZIP to respective folder:
echo    - elasticsearch-8.11.0-windows-x86_64.zip → elk-stack\elasticsearch\
echo    - logstash-8.11.0-windows-x86_64.zip → elk-stack\logstash\
echo    - kibana-8.11.0-windows-x86_64.zip → elk-stack\kibana\
echo.
echo ✅ After extraction, you should have:
echo    elk-stack\elasticsearch\elasticsearch-8.11.0\
echo    elk-stack\logstash\logstash-8.11.0\
echo    elk-stack\kibana\kibana-8.11.0\
echo.
echo ========================================
echo Next Steps (After Download):
echo ========================================
echo 1. 🔧 Run: setup-elk.bat (configure components)
echo 2. 🚀 Run: start-elk.bat (start ELK stack)
echo.
echo ========================================
echo Why No Installation Required?
echo ========================================
echo ✅ Portable Applications: Run from any folder
echo ✅ No Registry Changes: Clean system
echo ✅ No Admin Rights: Regular user can run
echo ✅ No Windows Services: Manual start/stop
echo ✅ Easy Cleanup: Just delete folders
echo.
echo 💡 Perfect for learning and development!
echo.
pause