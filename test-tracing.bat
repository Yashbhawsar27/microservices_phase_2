@echo off
REM ============================================================================
REM Distributed Tracing Test Script
REM ============================================================================
REM This script tests distributed tracing by making API calls that span
REM multiple microservices. Each request generates traces that can be
REM viewed in Zipkin UI.
REM
REM What this script does:
REM 1. Creates multiple orders with different scenarios
REM 2. Each order request flows through: Gateway -> Order Service -> Payment Service
REM 3. Generates distributed traces with unique Trace IDs and Span IDs
REM 4. Demonstrates successful and failed payment scenarios
REM 5. Shows service-to-service communication patterns
REM
REM Prerequisites:
REM - All microservices must be running (run start-with-zipkin-elk.bat first)
REM - curl command must be available (usually comes with Windows 10+)
REM
REM After running this script:
REM - Open Zipkin UI: http://localhost:9411
REM - Click "Find Traces" to see request flows
REM - Analyze latency and service dependencies
REM - Check logs in Kibana if ELK stack is running
REM ============================================================================

echo Testing Distributed Tracing with Zipkin...

echo.
echo ========================================
echo Creating Multiple Orders for Tracing
echo ========================================
echo This will generate traces showing request flow through:
echo Gateway (8080) -> Order Service (8081) -> Payment Service (8082)

echo.
echo 📱 Creating Order 1 - Laptop (Success Scenario)
echo ------------------------------------------------
curl -X POST http://localhost:8080/order/bookOrder ^
  -H "Content-Type: application/json" ^
  -d "{\"order\":{\"id\":1,\"name\":\"Laptop\",\"qty\":1,\"price\":50000.0},\"payment\":{\"paymentMode\":\"CARD\",\"status\":\"success\"}}"

echo.
echo.
echo 📱 Creating Order 2 - Mobile (Success Scenario)
echo ------------------------------------------------
curl -X POST http://localhost:8080/order/bookOrder ^
  -H "Content-Type: application/json" ^
  -d "{\"order\":{\"id\":2,\"name\":\"Mobile\",\"qty\":2,\"price\":25000.0},\"payment\":{\"paymentMode\":\"UPI\",\"status\":\"success\"}}"

echo.
echo.
echo 📱 Creating Order 3 - Headphones (Failure Scenario)
echo ----------------------------------------------------
echo This order will demonstrate payment failure handling
curl -X POST http://localhost:8080/order/bookOrder ^
  -H "Content-Type: application/json" ^
  -d "{\"order\":{\"id\":3,\"name\":\"Headphones\",\"qty\":1,\"price\":5000.0},\"payment\":{\"paymentMode\":\"CASH\",\"status\":\"failed\"}}"

echo.
echo.
echo 📋 Fetching all orders to generate more traces
echo -----------------------------------------------
curl -X GET http://localhost:8080/order/all

echo.
echo.
echo 🔍 Fetching specific order (Order ID: 1)
echo -----------------------------------------
curl -X GET http://localhost:8080/order/1

echo.
echo.
echo ========================================
echo Distributed Tracing Analysis
echo ========================================
echo.
echo 🔍 Zipkin UI: http://localhost:9411
echo.
echo What to look for in Zipkin:
echo 1. Click "Find Traces" to see all request traces
echo 2. Each trace shows the complete request journey
echo 3. Trace ID: Unique identifier for each request
echo 4. Span ID: Unique identifier for each service call
echo 5. Service dependencies and communication patterns
echo 6. Request latency and performance bottlenecks
echo 7. Error traces (from the failed payment scenario)
echo.
echo Trace Flow Analysis:
echo - Gateway receives request (Span 1)
echo - Gateway routes to Order Service (Span 2)
echo - Order Service calls Payment Service (Span 3)
echo - Payment Service processes payment (Span 4)
echo - Response flows back through all services
echo.
echo ========================================
echo Centralized Logging Analysis (if ELK is running)
echo ========================================
echo.
echo 📊 Kibana: http://localhost:5601
echo.
echo What to look for in Kibana:
echo 1. Create index pattern: microservices-logs-*
echo 2. Search for logs by service name
echo 3. Filter logs by trace ID to see complete request flow
echo 4. Correlate logs with Zipkin traces using trace IDs
echo 5. Analyze error logs from failed payment scenario
echo.
echo Log Correlation:
echo - Each log entry contains trace ID and span ID
echo - Use trace ID to find all logs for a specific request
echo - Correlate logs across multiple services
echo - Debug issues using structured log data
echo.
echo ========================================
echo Learning Objectives Achieved:
echo ========================================
echo ✅ Distributed Tracing with Zipkin
echo ✅ Service-to-Service Communication
echo ✅ Request Flow Visualization
echo ✅ Error Handling and Fault Tolerance
echo ✅ Performance Monitoring
echo ✅ Log Correlation (if ELK is running)
echo.
pause