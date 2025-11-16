@echo off
REM ============================================================================
REM Microservices Startup Script with Zipkin + ELK Stack
REM ============================================================================
REM This script starts all microservices in the correct order for learning
REM distributed tracing and centralized logging concepts.
REM
REM Services Started:
REM 1. Service Registry (Eureka) - Port 8761
REM 2. Config Server - Port 8888
REM 3. Zipkin Server - Port 9411 (Distributed Tracing)
REM 4. Order Service - Port 8081
REM 5. Payment Service - Port 8082
REM 6. Cloud Gateway - Port 8080 (API Gateway)
REM 7. Monitoring Dashboard - Port 9090
REM
REM Prerequisites:
REM - Java 17 installed
REM - Maven installed and in PATH
REM - All services compiled (mvn clean package)
REM
REM For ELK Stack:
REM - Run elk-stack/download-elk.bat (first time only)
REM - Run elk-stack/setup-elk.bat (first time only)
REM - Run elk-stack/start-elk.bat (to start ELK)
REM ============================================================================

echo Starting Microservices with Zipkin + ELK Stack...

echo.
echo ========================================
echo Starting Service Registry (Port 8761)
echo ========================================
echo Service Registry is the foundation - all services register here
start "Service Registry" cmd /k "cd service-registry && mvn spring-boot:run"
echo Waiting 30 seconds for Service Registry to start...
timeout /t 30

echo.
echo ========================================
echo Starting Config Server (Port 8888)
echo ========================================
echo Config Server provides centralized configuration management
start "Config Server" cmd /k "cd config-server && mvn spring-boot:run"
echo Waiting 20 seconds for Config Server to start...
timeout /t 20

echo.
echo ========================================
echo Starting Zipkin Server (Port 9411)
echo ========================================
echo Zipkin Server collects and visualizes distributed traces
start "Zipkin Server" cmd /k "cd zipkin-server && mvn spring-boot:run"
echo Waiting 15 seconds for Zipkin Server to start...
timeout /t 15

echo.
echo ========================================
echo Starting Order Service (Port 8081)
echo ========================================
echo Order Service handles order management and payment integration
start "Order Service" cmd /k "cd order-service && mvn spring-boot:run"
echo Waiting 15 seconds for Order Service to start...
timeout /t 15

echo.
echo ========================================
echo Starting Payment Service (Port 8082)
echo ========================================
echo Payment Service processes payments for orders
start "Payment Service" cmd /k "cd payment-service && mvn spring-boot:run"
echo Waiting 15 seconds for Payment Service to start...
timeout /t 15

echo.
echo ========================================
echo Starting Cloud Gateway (Port 8080)
echo ========================================
echo Cloud Gateway provides API routing and load balancing
start "Cloud Gateway" cmd /k "cd cloud-gateway && mvn spring-boot:run"
echo Waiting 15 seconds for Cloud Gateway to start...
timeout /t 15

echo.
echo ========================================
echo Starting Monitoring Dashboard (Port 9090)
echo ========================================
echo Monitoring Dashboard provides simple service monitoring
start "Monitoring Dashboard" cmd /k "cd hystrix-dashboard && mvn spring-boot:run"

echo.
echo ========================================
echo All Services Started Successfully!
echo ========================================
echo.
echo 🌐 Service URLs:
echo - Service Registry: http://localhost:8761
echo - Config Server: http://localhost:8888
echo - Zipkin Tracing: http://localhost:9411
echo - Order Service: http://localhost:8081
echo - Payment Service: http://localhost:8082
echo - API Gateway: http://localhost:8080
echo - Monitoring Dashboard: http://localhost:9090
echo.
echo 📊 Observability Stack:
echo - Zipkin UI: http://localhost:9411 (Distributed Tracing)
echo - Kibana: http://localhost:5601 (Log Analysis - if ELK is running)
echo - Elasticsearch: http://localhost:9200 (Log Storage - if ELK is running)
echo.
echo 🔧 For ELK Stack (Centralized Logging):
echo 1. Run elk-stack\download-elk.bat (first time only)
echo 2. Run elk-stack\setup-elk.bat (first time only)
echo 3. Run elk-stack\start-elk.bat (to start ELK)
echo.
echo 🧪 To test the system:
echo Run: test-tracing.bat
echo.
pause