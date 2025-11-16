@echo off
echo Testing Production Microservices APIs...
echo.

REM Wait for services to be ready
echo Waiting for services to start up...
timeout /t 10

echo ========================================
echo Testing Service Health Checks
echo ========================================

echo 1. Testing Service Registry Health...
curl -s http://localhost:8761/actuator/health
echo.

echo 2. Testing Config Server Health...
curl -s http://localhost:8888/actuator/health
echo.

echo 3. Testing Order Service Health...
curl -s http://localhost:8081/actuator/health
echo.

echo 4. Testing Payment Service Health...
curl -s http://localhost:8082/actuator/health
echo.

echo 5. Testing Gateway Health...
curl -s http://localhost:8080/actuator/health
echo.

echo ========================================
echo Testing Business APIs
echo ========================================

echo 6. Creating Test Order...
curl -X POST http://localhost:8080/api/orders ^
  -H "Content-Type: application/json" ^
  -d "{\"name\":\"Test Order\",\"qty\":2,\"price\":100.0}"
echo.

echo 7. Getting All Orders...
curl -s http://localhost:8080/api/orders
echo.

echo 8. Processing Test Payment...
curl -X POST http://localhost:8080/api/payments ^
  -H "Content-Type: application/json" ^
  -d "{\"orderId\":1,\"amount\":200.0,\"paymentStatus\":\"SUCCESS\"}"
echo.

echo 9. Getting All Payments...
curl -s http://localhost:8080/api/payments
echo.

echo ========================================
echo Testing Circuit Breaker (Simulating Failure)
echo ========================================

echo 10. Testing Circuit Breaker Fallback...
curl -s http://localhost:8080/fallback/orders
echo.

echo ========================================
echo Monitoring URLs
echo ========================================
echo.
echo - Eureka Dashboard: http://localhost:8761
echo - Spring Boot Admin: http://localhost:9411
echo - Order Service H2 Console: http://localhost:8081/h2-console
echo - Payment Service H2 Console: http://localhost:8082/h2-console
echo - Gateway Actuator: http://localhost:8080/actuator
echo.
echo Testing completed!
pause